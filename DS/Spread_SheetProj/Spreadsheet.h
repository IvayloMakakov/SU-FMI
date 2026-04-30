#pragma once
#include <string>
#include <vector>
#include <set>
#include <map>
#include "Cell.h"

class Spreadsheet {
private:
    std::map<int, std::map<int, Cell*>> table;
    int minRow, maxRow;
    int minCol, maxCol;
    mutable std::set<std::pair<int, int>> calculating;

    void clear();
    void copyFrom(const Spreadsheet& other);
    void moveFrom(Spreadsheet&&);

    void printRangeInternal(int r1, int c1, int r2, int c2, bool showExpr) const;
    void recomputeBounds();
    Cell* createCellFromInput(const std::string& input);

public:
    Spreadsheet();

    Spreadsheet(const Spreadsheet& other);
    Spreadsheet& operator=(const Spreadsheet& other);
    Spreadsheet(Spreadsheet&& other) noexcept;
    Spreadsheet& operator=(Spreadsheet&& other) noexcept;
    ~Spreadsheet();

    void setCell(int row, int col, const std::string& input);
    const Cell* getCell(int row, int col) const;
    //calculating
    double getCellValue(int row, int col) const;

    void printValAll() const;
    void printExprAll() const;
    void printRange(int r1, int c1, int r2, int c2, bool showExpr) const;

    void save(const std::string& filename) const;
    void load(const std::string& filename);
};