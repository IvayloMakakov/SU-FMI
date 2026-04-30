#include <iostream>
#include <string>
#include <sstream>
#include <vector>
#include <algorithm>
#include "Spreadsheet.h"

bool parseCoord(std::string s, int& r, int& c) {
    if (s.empty() || s[0] != 'R') return false;

    size_t cPos = s.find('C');

    if (cPos == std::string::npos) return false;

    try {
        r = std::stoi(s.substr(1, cPos - 1));
        c = std::stoi(s.substr(cPos + 1));
        return true;
    }
    catch (...) {
        return false;
    }
}

std::vector<std::string> split(const std::string& str) {
    std::vector<std::string> tokens;

    std::stringstream ss(str);

    std::string token;

    while (ss >> token) 
        tokens.push_back(token);

    return tokens;
}

void printHelp() {
    std::cout << "\n--- COMMANDS ---\n";
    std::cout << "SET <row> <col> <expression>\n";
    std::cout << "PRINT VAL <address>\n";
    std::cout << "PRINT EXPR <address>\n";
    std::cout << "PRINT VAL ALL\n";
    std::cout << "PRINT EXPR ALL\n";
    std::cout << "SAVE <file.csv>\n";
    std::cout << "LOAD <file.csv>\n";
    std::cout << "CLEAR, EXIT\n";
}

/*
SET R1C1 10
SET R1C2 20
SET R1C3 5
SET R2C1 =R1C1+R1C2
SET R2C2 =R[-1]C[0]*4
SET R3C1 =SUM(R1C1, R2C1)
SET R3C2 =MAX(R1C1, R2C2)
SET R4C1 =IF(R2C2 > 50, 111, 222)
SET R5C1 =(R1C1+R1C2)/R1C3
PRINT VAL ALL
SAVE test_num.csv
CLEAR
LOAD test_num.csv
PRINT VAL ALL
------------------------------------
CLEAR
SET R1C1 "Income"
SET R1C2 "Rent"
SET R1C3 "Food"
SET R1C4 "Fun"

SET R2C1 2000
SET R2C2 -800
SET R2C3 -300
SET R2C4 -100

SET R3C1 "Stats:"
SET R3C2 =SUM(R2C2, R2C4)
SET R3C3 =MAX(R2C2, R2C4)
SET R3C4 =MIN(R2C2, R2C4)

SET R4C1 "Alerts:"
SET R4C2 =IF(AND(R2C1 > 1000, R3C2 > -1500), 1, 0)
SET R4C3 =IF(OR(R2C2 < -500, R2C3 < -500), 1, 0)
SET R4C4 =NOT(R3C2 > 0)

SET R5C1 "Count:"
SET R5C2 =COUNT(R2C1, R2C4)

PRINT VAL ALL
PRINT EXPR ALL
SAVE budget.csv

--- (clear, load, print val all, print expr all)
*/

int main() {
    Spreadsheet sheet;
    std::string line;

    std::cout << "Welcome to Spreadsheet!\n";

    printHelp();

    while (true) {
        std::cout << "\n> ";
        if (!std::getline(std::cin, line)) break;

        if (line.empty()) continue;

        std::vector<std::string> _args = split(line);
        if (_args.empty()) continue;

        std::string cmd = _args[0];

        for (auto& c : cmd) c = toupper(c);

        try {
            if (cmd == "EXIT" || cmd == "QUIT") 
                break;

            else if (cmd == "SET") {
                if (_args.size() < 3) { std::cout << "Usage: SET <Address> <Expr> OR SET <R> <C> <Expr>\n"; continue; }

                int r, c;
                int exprStartIndex = 0;

                if (parseCoord(_args[1], r, c)) {
                    exprStartIndex = 2; 
                }
                else {
                    if (_args.size() < 4) { std::cout << "Usage: SET R C VAL\n"; continue; }

                    try {
                        r = std::stoi(_args[1]);
                        c = std::stoi(_args[2]);
                        exprStartIndex = 3;
                    }
                    catch (...) {
                        std::cout << "Invalid coordinates.\n"; continue;
                    }
                }

                std::string _val = "";

                for (size_t i = exprStartIndex; i < _args.size(); ++i) {
                    _val += _args[i];
                    if (i < _args.size() - 1) _val += " ";
                }

                sheet.setCell(r, c, _val);
                std::cout << "Updated.\n";
            }
            else if (cmd == "PRINT") {
                if (_args.size() < 3) { std::cout << "Usage: PRINT VAL/EXPR ALL/Address\n"; continue; }

                std::string type = _args[1];
                std::string target = _args[2];

                for (auto& c : type) c = toupper(c);
                for (auto& c : target) c = toupper(c);

                bool showExpr = (type == "EXPR");

                if (target == "ALL") {
                    if (showExpr) sheet.printExprAll();
                    else sheet.printValAll();
                }
                else {
                    size_t colonPos = target.find(':');
                    int r1, c1, r2, c2;

                    if (colonPos != std::string::npos) {
                        std::string part1 = target.substr(0, colonPos);
                        std::string part2 = target.substr(colonPos + 1);

                        if (parseCoord(part1, r1, c1) && parseCoord(part2, r2, c2)) {
                            sheet.printRange(std::min(r1, r2), std::min(c1, c2), std::max(r1, r2), std::max(c1, c2), showExpr);
                        }
                        else {
                            std::cout << "Invalid range format. Use RxCy:RxCy\n";
                        }
                    }
                    else {
                        if (parseCoord(target, r1, c1)) {
                            sheet.printRange(r1, c1, r1, c1, showExpr);
                        }
                        else {
                            std::cout << "Invalid address format. Use RxCy\n";
                        }
                    }
                }
            }
            else if (cmd == "SAVE") {
                if (_args.size() < 2) std::cout << "Filename missing.\n";
                else sheet.save(_args[1]);
            }
            else if (cmd == "LOAD") {
                if (_args.size() < 2) std::cout << "Filename missing.\n";
                else sheet.load(_args[1]);
            }
            else if (cmd == "CLEAR") {
                sheet = Spreadsheet();
                std::cout << "Cleared.\n";
            }
            else std::cout << "Unknown command.\n"; 
        }
        catch (const std::exception& e) {
            std::cout << "Error: " << e.what() << "\n";
        }
    }
    return 0;
}