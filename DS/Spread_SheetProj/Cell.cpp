#include "Cell.h"
#include "Spreadsheet.h"
#include "FormulaAST.h"
#include <sstream>

FormulaCell::FormulaCell(std::string formula) : _rawFormula(formula), _root(nullptr) {
    if (!formula.empty()) {
        Parser p(formula);
        _root = p.parseExpression();
    }
}

FormulaCell::~FormulaCell() noexcept {
    delete _root;
}

FormulaCell::FormulaCell(const FormulaCell& other) : _rawFormula(other._rawFormula), _root(nullptr) {
    if (!_rawFormula.empty()) {
        Parser p(_rawFormula);
        _root = p.parseExpression();
    }
}

double FormulaCell::getValue(int r, int c, const Spreadsheet& sheet) const {
    if (_root) {
        return _root->eval(r, c, sheet);
    }
    return 0.0;
}

std::string FormulaCell::toString(int r, int c, const Spreadsheet& sheet) const {
    double _val = getValue(r, c, sheet);

    std::stringstream ss;
    ss << _val;
    return ss.str();
}