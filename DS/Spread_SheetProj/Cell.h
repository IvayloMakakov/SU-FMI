#pragma once
#include <string>
#include <sstream>
#include <iomanip>

class Spreadsheet;
class Expression;

enum class CellType { EMPTY, DOUBLE, STRING, FORMULA };

class Cell {
public:
    virtual ~Cell() = default;//задължително
    virtual double getValue(int ctxR, int ctxC, const Spreadsheet& sheet) const = 0;
    virtual std::string toString(int ctxR, int ctxC, const Spreadsheet& sheet) const = 0;
    virtual std::string getRawString() const = 0;
    virtual CellType getType() const = 0;
    virtual Cell* clone() const = 0;
};

class DoubleCell : public Cell {
    double _value;
public:
    DoubleCell(double v) : _value(v) {}
    double getValue(int, int, const Spreadsheet&) const override { return _value; }
    std::string toString(int, int, const Spreadsheet&) const override {
        std::stringstream ss; ss << _value; return ss.str();
    }
    std::string getRawString() const override { std::stringstream ss; ss << _value; return ss.str(); }
    CellType getType() const override { return CellType::DOUBLE; }
    Cell* clone() const override { return new DoubleCell(_value); }
};

class StringCell : public Cell {
    std::string _value;
public:
    StringCell(std::string v) : _value(v) {}
    double getValue(int, int, const Spreadsheet&) const override {
        try { return std::stod(_value); }
        catch (...) { return 0.0; }
    }
    std::string toString(int, int, const Spreadsheet&) const override { return _value; }
    std::string getRawString() const override { return "\"" + _value + "\""; }
    CellType getType() const override { return CellType::STRING; }
    Cell* clone() const override { return new StringCell(_value); }
};

class FormulaCell : public Cell {
private:
    std::string _rawFormula;
    Expression* _root;
public:
    FormulaCell(std::string formula);

    //ТРЯБВАШЕ
    FormulaCell& operator=(const FormulaCell&) = delete;

    ~FormulaCell() noexcept;

    FormulaCell(const FormulaCell& other);

    double getValue(int r, int c, const Spreadsheet& sheet) const override;
    std::string toString(int r, int c, const Spreadsheet& sheet) const override;

    std::string getRawString() const override {   
        return "=" + _rawFormula;
    }
    CellType getType() const override { return CellType::FORMULA; }
    Cell* clone() const override { return new FormulaCell(*this); }
};