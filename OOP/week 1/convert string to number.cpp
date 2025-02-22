#include <iostream>

enum class ErrorCode : char
{
	Ok,
	Overflow,
	InvalidString,
	Nullptr
};

struct ConvertResult
{
	ErrorCode code;
	int number;
};

ConvertResult toReturn(ErrorCode code, int num)
{
	return { code,num };
}

bool isDigit(int a)
{
	return a >= '0' && a <= '9';
}

unsigned short toDigit(char ch)
{
	return ch - '0';
}

ConvertResult convertStringToNum(const char* str)
{
	if (!str)
	{
		return toReturn(ErrorCode::Nullptr, 0);
	}

	bool isNegative = *str == '-';

	if (isNegative)
	{
		str++;
	}

	int result = 0;

	while (*str)
	{
		if (!isDigit(*str))
		{
			return toReturn(ErrorCode::InvalidString, 0);
		}

		int candidate = result * 10;

		if (candidate < result)
		{
			return toReturn(ErrorCode::Overflow, 0);
		}

		result = candidate;
		result += toDigit(*str);

		str++;
	}

	if (isNegative)
	{
		result *= -1;
	}

	return toReturn(ErrorCode::Ok, result);
}

void printCode(ErrorCode code)
{
	switch (code)
	{
	case ErrorCode::Overflow:
		std::cout << "Overflow" << std::endl;
		break;
	case ErrorCode::InvalidString:
		std::cout << "InvalidString" << std::endl;
		break;
	case ErrorCode::Nullptr:
		std::cout << "Nullptr" << std::endl;
		break;
	}
}

int main()
{
	ConvertResult res = convertStringToNum("-1234");

	if (res.code == ErrorCode::Ok)
	{
		std::cout << res.number << std::endl;
	}
	else
	{
		printCode(res.code);
	}
}