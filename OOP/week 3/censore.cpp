#include <iostream>
#include <fstream>
#include "Week 3 exersices.h"

bool validateFileName(const char* fileName)
{
	const char* FORBIDDEN = "?*\"<>";

	while (*fileName != '\0')
	{
		for (int i = 0; FORBIDDEN[i] != '\0'; i++)
		{
			if (*fileName == FORBIDDEN[i])
				return false;
		}

		fileName++;
	}

	return true;
}

int main()
{
	char originalTextFileName[256];
	std::cin >> originalTextFileName;
	if (!validateFileName(originalTextFileName))
	{
		std::cout << "Failed to validate file name: " << originalTextFileName;
		return -1;
	}

	char censorListFileName[256];
	std::cin >> censorListFileName;
	if (!validateFileName(censorListFileName))
	{
		std::cout << "Failed to validate file name: " << censorListFileName;
		return -2;
	}

	std::ifstream inFileX(originalTextFileName);

	if (!inFileX.is_open())
	{
		std::cout << "Failed opening \"" << originalTextFileName << "\"" << std::endl;
		return -3;
	}

	std::ifstream inFileY(censorListFileName);

	if (!inFileY.is_open())
	{
		std::cout << "Failed opening \"" << censorListFileName << "\"" << std::endl;
		return -4;
	}

	char readYBadWords[16][51];
	char readYGoodWords[16][51];

	size_t idx = 0;

	while (inFileY >> readYBadWords[idx] >> readYGoodWords[idx])
	{
		idx++;
	}

	inFileY.close();

	std::ofstream outFileXCensored("X-censored.txt");

	if (!outFileXCensored.is_open())
	{
		std::cout << "Failed opening X-censored.txt" << std::endl;
		return -5;
	}

	char buff[51];

	while (inFileX >> buff)
	{
		bool isTrue = false;

		for (size_t i = 0; i < idx && !isTrue; i++)
		{
			if (std::strcmp(buff, readYBadWords[i]) == 0)
			{
				outFileXCensored << readYGoodWords[i] << ' ';
				isTrue = true;
			}
		}

		if (!isTrue)
		{
			outFileXCensored << buff << ' ';
		}
	}

	inFileX.close();
	outFileXCensored.close();
}