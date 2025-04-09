#include <iostream>
#include <fstream>

size_t getCountRows(const char* fileName)
{
	std::ifstream inFile(fileName);

	if (!inFile.is_open())
	{
		std::cerr << "Error opening file \"" << fileName << "\"\n";

		return 0;
	}

	size_t count = 0;

	while (!inFile.eof())
	{
		if (inFile.get() == '\n')count++;
	}

	return count + 1;
}

//Another approach
size_t getCharCountFromFile(std::ifstream& ifs, char symbol)
{
	if (!ifs.is_open())
	{
		return 0;
	}

	size_t count = 0;

	while (true)
	{
		char curr = ifs.get();

		if (ifs.eof())
			break;

		if (curr == symbol)
			count++;
	}

	return count;
}

size_t getCharCount(const char* fileName)
{
	std::ifstream inFile(fileName);

	if (!inFile.is_open())
	{
		std::cerr << "Error opening file \"" << fileName << "\"\n";

		return 0;
	}

	return getCharCountFromFile(inFile, '\n') + 1;
}

int main()
{
	std::cout << getCountRows("Week 3 exersices.cpp") << std::endl;

	std::cout << getCharCount("Week 3 exersices.cpp") << std::endl;
}