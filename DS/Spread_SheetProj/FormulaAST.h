#pragma once
#include <algorithm>
#include <string>
#include <vector>
#include <cmath>
#include "Spreadsheet.h" 

constexpr double EPSILON = 1e-9;

class Spreadsheet;

class Expression {
public:
	virtual ~Expression() = default;
	virtual double eval(int ctxR, int ctxC, const Spreadsheet& sheet) const = 0;
};
//от гледна точка на оператора и операндите му

class DoubleNode : public Expression {
	double _val;
public:
	DoubleNode(double v) : _val(v) {}
	double eval(int, int, const Spreadsheet&) const override { return _val; }
};

class ReferenceNode : public Expression {
	std::string _refStr;//работи и с абсолютни адреси
public:
	ReferenceNode(std::string r) : _refStr(r) {}

	void getCoords(int ctxR, int ctxC, int& outR, int& outC) const {
		size_t cPos = _refStr.find('C');
		if (cPos == std::string::npos) { outR = 0; outC = 0; return; }

		std::string rPart = _refStr.substr(1, cPos - 1);
		std::string cPart = _refStr.substr(cPos + 1);

		if (!rPart.empty() && rPart.front() == '[')
			outR = ctxR + std::stoi(rPart.substr(1, rPart.size() - 2));
		else
			outR = std::stoi(rPart);

		if (!cPart.empty() && cPart.front() == '[')
			outC = ctxC + std::stoi(cPart.substr(1, cPart.size() - 2));
		else
			outC = std::stoi(cPart);
	}

	double eval(int ctxR, int ctxC, const Spreadsheet& sheet) const override {
		int r, c;
		getCoords(ctxR, ctxC, r, c);
		return sheet.getCellValue(r, c);
	}
};

class BinaryNode : public Expression {
	char _op;
	Expression* _left, * _right;
public:
	BinaryNode(char o, Expression* l , Expression* r) : _op(o), _left(l), _right(r) {}

	BinaryNode(const BinaryNode&) = delete;
	BinaryNode& operator=(const BinaryNode&) = delete;
	BinaryNode(BinaryNode&&) = delete;
	BinaryNode& operator=(BinaryNode&&) = delete;

	~BinaryNode() { delete _left; delete _right; _left = _right = nullptr; _op = '\0'; }

	double eval(int r, int c, const Spreadsheet& sheet) const override {
		double lVal = _left->eval(r, c, sheet);
		double rVal = _right->eval(r, c, sheet);

		switch (_op) {
		case '+': return lVal + rVal;
		case '-': return lVal - rVal;
		case '*': return lVal * rVal;
		case '/': if (std::abs(rVal) < EPSILON) throw std::runtime_error("Division by zero");
			return lVal / rVal;
		case '<': return lVal < rVal ? 1.0 : 0.0;
		case '>': return lVal > rVal ? 1.0 : 0.0;
		case '=': return std::abs(lVal - rVal) < EPSILON ? 1.0 : 0.0;
		case '!': return std::abs(lVal - rVal) > EPSILON ? 1.0 : 0.0;
		case '%':
			if (std::abs((int)rVal) < EPSILON) throw std::runtime_error("Division by zero");
			return (int)lVal % (int)rVal;
		default: return 0.0;
		}
	}
};

class FunctionNode : public Expression {
	std::string _name;
	std::vector<Expression*> _args;
public:
	FunctionNode(std::string n, std::vector<Expression*> a) : _name(n), _args(a) {}
	~FunctionNode() { for (auto a : _args) delete a; }

	double eval(int r, int c, const Spreadsheet& sheet) const override {
		if (_name == "IF" && _args.size() == 3) {
			if (std::abs(_args[0]->eval(r, c, sheet)) > EPSILON)
				return _args[1]->eval(r, c, sheet);
			else
				return _args[2]->eval(r, c, sheet);
		}

		if (_name == "NOT" && _args.size() == 1) {
			double _val = _args[0]->eval(r, c, sheet);
			return (std::abs(_val) < EPSILON) ? 1.0 : 0.0; 
		}
		if (_name == "AND" && _args.size() == 2) {//няма lazy evaluation
			double v1 = _args[0]->eval(r, c, sheet);
			double v2 = _args[1]->eval(r, c, sheet);
			return (std::abs(v1) > EPSILON && std::abs(v2) > EPSILON) ? 1.0 : 0.0;
		}
		if (_name == "OR" && _args.size() == 2) {//няма lazy evaluation
			double v1 = _args[0]->eval(r, c, sheet);
			double v2 = _args[1]->eval(r, c, sheet);
			return (std::abs(v1) > EPSILON || std::abs(v2) > EPSILON) ? 1.0 : 0.0;
		}

		if ((_name == "SUM" || _name == "AVG" || _name == "MIN" || _name == "MAX" || _name == "COUNT") && _args.size() == 2) {
			ReferenceNode* topLeftCorner = dynamic_cast<ReferenceNode*>(_args[0]);
			ReferenceNode* bottomRightCorner = dynamic_cast<ReferenceNode*>(_args[1]);

			if (topLeftCorner && bottomRightCorner) {
				int r1, c1, r2, c2;
				topLeftCorner->getCoords(r, c, r1, c1);
				bottomRightCorner->getCoords(r, c, r2, c2);

				int startR = std::min(r1, r2), endR = std::max(r1, r2);
				int startC = std::min(c1, c2), endC = std::max(c1, c2);

				double sum = 0, minV = 1e9, maxV = -1e9;

				int count = 0;
				int totalCells = 0;

				for (int i = startR; i <= endR; ++i) {
					for (int j = startC; j <= endC; ++j) {
						double v = sheet.getCellValue(i, j); //за целите на проекта приемам 0 за празно или просто стойност
						
						if (sheet.getCell(i, j)) {//Ако съществува.
							count++;
							sum += v;
							if (v < minV) minV = v;
							if (v > maxV) maxV = v;
						}
						totalCells++;
					}
				}

				if (_name == "SUM") return sum;
				if (_name == "AVG") return count ? sum / count : 0;
				if (_name == "MIN") return count ? minV : 0;
				if (_name == "MAX") return count ? maxV : 0;
				if (_name == "COUNT") return (double)count;
			}
		}
		return 0.0;
	} 
};
 
class Parser {
	std::string _expr;
	size_t _pos = 0;

	char peek() {
		while (_pos < _expr.size() && isspace(_expr[_pos])) _pos++;
		return (_pos == _expr.size()) ? 0 : _expr[_pos];
	}

	char get() { char c = peek(); if (_pos < _expr.size()) _pos++; return c; }

	//най-приоритетното
	Expression* parseFactor() {
		char c = peek();

		if (c == '-') {
			get(); 
			return new BinaryNode('-', new DoubleNode(0), parseFactor());
		}
		if (c == '+') {
			get();
			return parseFactor();
		}
		if (isdigit(c) || c == '.') {
			//ако гръмне
			size_t len; double _val = std::stod(_expr.substr(_pos), &len);
			_pos += len; 
			return new DoubleNode(_val);
		}
		else if (c == '(') {
			get(); Expression* e = parseExpression(); get(); return e;
		}
		else if (isalpha(c)) {
			size_t start = _pos;
			while (_pos < _expr.size() && (isalnum(_expr[_pos]) || _expr[_pos] == '[' || _expr[_pos] == ']' || _expr[_pos] == '-')) 
				_pos++;

			std::string token = _expr.substr(start, _pos - start);

			if (peek() == '(') { // Function
				get(); 

				std::vector<Expression*> _args;

				if (peek() != ')') {
					while (true) {
						_args.push_back(parseExpression());
						if (peek() == ')') break;
						if (peek() == ',') get();
					}
				}
				get();

				for (char& ch : token) 
					ch = toupper(ch);

				return new FunctionNode(token, _args);
			}
			return new ReferenceNode(token);//адрес
		}
		throw std::runtime_error("Syntax Error: Unexpected token.");
	}

	//среден по приоритет
	Expression* parseTerm() {
		Expression* _left = parseFactor();

		while (peek() == '*' || peek() == '/' || peek() == '%') {
			char _op = get();
			_left = new BinaryNode(_op, _left, parseFactor());
		}

		return _left;
	}

public:
	Parser(std::string s) : _expr(s) {}

	//най-нисък приоритет
	Expression* parseExpression() {
		Expression* _left = parseTerm();

		while (peek() == '+' || peek() == '-' || peek() == '<' || peek() == '>' || peek() == '=' || peek() == '!') {
			char _op = get();
	
			if (_op == '!' && peek() == '=') {
				get(); 
			}
			else if (_op == '=' && peek() == '=') {
				get();
			}
			
			_left = new BinaryNode(_op, _left, parseTerm());
		}
		return _left;
	}
};