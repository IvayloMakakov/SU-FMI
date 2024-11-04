#include <iostream>

enum class ErrorCode:char
{
    OK,
    NullptrGiven,
    WrongCharInString,
    EmptyString
};

struct ConvertResult
{
    ErrorCode code;
    unsigned result;
};
bool isDigit(char c)
{
    return c >= '0' && c <= '9';
}
unsigned toDigit(char c)
{
    return isDigit(c) ? c - '0' : 0;
    //some error value
}
ConvertResult convertStringToNumber(const char* str)
{
    unsigned res = 0;
    if (!str)
    {
        return { ErrorCode::NullptrGiven,res };
    }
    if (!*str)
    {
        return { ErrorCode::EmptyString,res };
    }
    while (*str)
    {
        if (!isDigit(*str))
            return { ErrorCode::WrongCharInString,0 };
        (res *= 10) += toDigit(*str);
        str++;
    }

    return { ErrorCode::OK, res };
}

int main()
{
    ConvertResult res = convertStringToNumber("12343");

    if (res.code == ErrorCode::OK)
    {
        std::cout << res.result << std::endl;
    }
    else
    {
        std::cout << "Error!" << std::endl;
    }
}