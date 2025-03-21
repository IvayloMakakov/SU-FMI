#include <iostream>
#include <fstream>

void printFile(const char* fileName)
{
	std::ifstream in(fileName);

	if (!in.is_open())
	{
		std::cout << "Error when openning the file " << fileName << std::endl;;
		return;
	}

	while (!in.eof())
	{
		std::cout << (char)in.get();
	}

	in.close();
}

int main()
{
	printFile("Week 3 exersices.cpp");
}