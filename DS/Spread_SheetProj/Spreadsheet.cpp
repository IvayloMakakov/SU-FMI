#include "Spreadsheet.h"
#include "FormulaAST.h" 
#include <iostream>
#include <algorithm>
#include <limits>
#include <sstream>
#include <fstream>
#include <iomanip> 

void Spreadsheet::clear() {
    for (auto& rowPair : table) {
        for (auto& colPair : rowPair.second) {
            delete colPair.second;
        }
    }
    table.clear();

    minRow = std::numeric_limits<int>::max();
    maxRow = std::numeric_limits<int>::min();
    minCol = std::numeric_limits<int>::max();
    maxCol = std::numeric_limits<int>::min();
}

void Spreadsheet::copyFrom(const Spreadsheet& other) {
    minRow = other.minRow; maxRow = other.maxRow;
    minCol = other.minCol; maxCol = other.maxCol;

    for (const auto& rowPair : other.table) {
        for (const auto& colPair : rowPair.second) {
            if (colPair.second) {
                table[rowPair.first][colPair.first] = colPair.second->clone();
            }
        }
    }
}

void Spreadsheet::moveFrom(Spreadsheet&& other) {
    table = std::move(other.table);
    minRow = other.minRow; 
    maxRow = other.maxRow;
    minCol = other.minCol; 
    maxCol = other.maxCol;

    other.minRow = 0; other.maxRow = 0;
    other.minCol = 0; other.maxCol = 0;
}

Spreadsheet::Spreadsheet() {
    minRow = std::numeric_limits<int>::max(); 
    maxRow = std::numeric_limits<int>::min();
    minCol = std::numeric_limits<int>::max(); 
    maxCol = std::numeric_limits<int>::min();
}

Spreadsheet::~Spreadsheet() {
    clear();
}

Spreadsheet::Spreadsheet(const Spreadsheet& other) : Spreadsheet() {
    try {
        copyFrom(other);
    }
    catch (...) {
        clear();
        throw;
    }
}

Spreadsheet::Spreadsheet(Spreadsheet&& other) noexcept : Spreadsheet() {
    moveFrom(std::move(other));
}

Spreadsheet& Spreadsheet::operator=(const Spreadsheet& other) {
    if (this != &other) {
        Spreadsheet temp(other);
        *this = std::move(temp);
    }
    return *this;
}

Spreadsheet& Spreadsheet::operator=(Spreadsheet&& other) noexcept {
    if (this != &other) {
        clear();
        moveFrom(std::move(other));
    }
    return *this;
}

Cell* Spreadsheet::createCellFromInput(const std::string& input) {
    if (input.empty()) return nullptr;

    if (input[0] == '=') {
        std::string upperFormula = input.substr(1);
        for (auto& c : upperFormula) c = toupper(c);

        return new FormulaCell(upperFormula);
    }
    try {
        size_t proc = 0;//бро€ символи прочетени
        double _val = std::stod(input, &proc);
        if (proc == input.length()) return new DoubleCell(_val);
    }
    catch (...) {}

    std::string text = input;
    if (text.size() >= 2 && text.front() == '"' && text.back() == '"') {
        text = text.substr(1, text.size() - 2);
    }
    return new StringCell(text);
}

void Spreadsheet::setCell(int row, int col, const std::string& input) {
    Cell* newCell = nullptr;
    
    try {
        newCell = createCellFromInput(input);
    }
    catch (...) {
        throw;
    }

    if (table[row].count(col)) {
        delete table[row][col];
    }

    if (newCell) {
        table[row][col] = newCell;
        if (table.size() == 1 && table[row].size() == 1) {
            minRow = maxRow = row;
            minCol = maxCol = col;
        }
        else {
            if (row < minRow) minRow = row;
            if (row > maxRow) maxRow = row;
            if (col < minCol) minCol = col;
            if (col > maxCol) maxCol = col;
        }
    }
    else {
        if (table.count(row)) {
            table[row].erase(col);
            if (table[row].empty()) {
                table.erase(row);
            }
        }
      
        recomputeBounds();
    }
}

const Cell* Spreadsheet::getCell(int row, int col) const {
    auto rIt = table.find(row);
    if (rIt != table.end()) {
        auto cIt = rIt->second.find(col);
        if (cIt != rIt->second.end())
            return cIt->second;
    }
    return nullptr;
}

double Spreadsheet::getCellValue(int row, int col) const {
    if (calculating.count({ row, col })) {
        throw std::runtime_error("Cycle detected at R" + std::to_string(row) + "C" + std::to_string(col));
    }

    const Cell* c = getCell(row, col);
    if (!c) return 0.0;

    calculating.insert({ row, col });

    double result = 0.0;
    try {
        result = c->getValue(row, col, *this);
    }
    catch (...) {
        calculating.erase({ row, col });
        throw;
    }

    calculating.erase({ row, col });

    return result;
}

void Spreadsheet::printRangeInternal(int rStart, int cStart, int rEnd, int cEnd, bool showExpr) const {
    if (table.empty() && (rStart == minRow)) { //тук е специфично, ако са равни значи искаме да видим ц€лата таблица, ко€то € н€ма
        std::cout << "Empty table.\n";
        return;
    }

    //      col, value
    std::map<int, int> colWidths;
    for (int c = cStart; c <= cEnd; ++c) 
        colWidths[c] = 5;

    for (auto const& rowPair : table) {
        int r = rowPair.first;
        if (r < rStart || r > rEnd) continue;

        for (auto const& colPair : rowPair.second) {
            int c = colPair.first;
            if (c < cStart || c > cEnd) continue;

            Cell* cell = colPair.second;
            std::string _val = showExpr ? cell->getRawString() : cell->toString(r, c, *this);
            if ((int)_val.length() > colWidths[c]) colWidths[c] = (int)_val.length();
        }
    }

    constexpr size_t CONSOLE_WIDTH = 80;
    std::vector<int> activeCols;

    for (int c = cStart; c <= cEnd; ++c) 
        activeCols.push_back(c);

    size_t currentIdx = 0;

    while (currentIdx < activeCols.size()) {
        std::vector<int> chunkCols;

        int currentWidth = 8;

        while (currentIdx < activeCols.size()) {
            int c = activeCols[currentIdx];
            int w = colWidths[c];

            if (currentWidth + w + 3 > CONSOLE_WIDTH && !chunkCols.empty()) break;

            chunkCols.push_back(c);

            currentWidth += w + 3;
            currentIdx++;
        }

        std::cout << "\n--- Subtable Columns " << chunkCols.front() << " to " << chunkCols.back() << " ---\n";

        std::cout << std::setw(8) << " ";
        for (int c : chunkCols) 
            std::cout << " | " << std::setw(colWidths[c]) << ("C" + std::to_string(c));
        std::cout << " |\n";

        for (int r = rStart; r <= rEnd; ++r) {
            bool isRowInMap = (table.find(r) != table.end());
            if (!isRowInMap && (rEnd == maxRow && rStart == minRow)) continue;

            std::cout << std::setw(6) << ("R" + std::to_string(r)) << " : ";
            for (int c : chunkCols) {
                const Cell* cell = getCell(r, c);
                std::string strVal;
                if (cell) strVal = showExpr ? cell->getRawString() : cell->toString(r, c, *this);
                std::cout << " | " << std::setw(colWidths[c]) << strVal;
            }
            std::cout << " |\n";
        }
    }
}

void Spreadsheet::printValAll() const { printRangeInternal(minRow, minCol, maxRow, maxCol, false); }
void Spreadsheet::printExprAll() const { printRangeInternal(minRow, minCol, maxRow, maxCol, true); }
void Spreadsheet::printRange(int r1, int c1, int r2, int c2, bool showExpr) const { printRangeInternal(r1, c1, r2, c2, showExpr); }

void Spreadsheet::save(const std::string& filename) const {
    std::ofstream out(filename);

    if (!out) { std::cerr << "Error saving.\n"; return; }

    int effectiveMaxRow = (table.empty()) ? 0 : maxRow;
    int effectiveMaxCol = (table.empty()) ? 0 : maxCol;

    for (int r = 1; r <= effectiveMaxRow; ++r) {
        for (int c = 1; c <= effectiveMaxCol; ++c) {
            const Cell* cell = getCell(r, c);
            if (cell) {
                out << cell->getRawString();
            }
            if (c < effectiveMaxCol) {
                out << ";";
            }
        }
        out << "\n";
    }
    std::cout << "Saved to " << filename << " (Standard CSV format).\n";
}

void Spreadsheet::load(const std::string& filename) {
    std::ifstream in(filename);
    if (!in) { std::cerr << "Error loading.\n"; return; }

    clear();//изтрие таблицата

    std::string line;
    int currentRow = 1;

    while (std::getline(in, line)) {
        if (line.empty()) {
            currentRow++;
            continue;
        }

        std::stringstream ss(line);

        std::string segment;
        int currentCol = 1;

        while (std::getline(ss, segment, ';')) {
            if (!segment.empty()) {
                setCell(currentRow, currentCol, segment);
            }
            currentCol++;
        }
        currentRow++;
    }
    std::cout << "Loaded.\n";
}

void Spreadsheet::recomputeBounds() {
    if (table.empty()) {
        minRow = 0; maxRow = 0;
        minCol = 0; maxCol = 0;
        return;
    }

    minRow = std::numeric_limits<int>::max();
    maxRow = std::numeric_limits<int>::min();
    minCol = std::numeric_limits<int>::max();
    maxCol = std::numeric_limits<int>::min();

    for (auto const& rowPair : table) {
        int r = rowPair.first;
        if (r < minRow) minRow = r;
        if (r > maxRow) maxRow = r;

        for (auto const& colPair : rowPair.second) {
            int c = colPair.first;
            if (c < minCol) minCol = c;
            if (c > maxCol) maxCol = c;
        }
    }
}