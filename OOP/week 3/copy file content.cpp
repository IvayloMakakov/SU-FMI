#include <iostream>
#include <fstream>

bool copyToFile(const char* fromFileName, const char* toFileName)
{
	std::ifstream inFile(fromFileName);

	if (!inFile.is_open())
	{
		std::cerr << "Error opening the file \"" << fromFileName << "\"\n";

		return false;
	}

	std::ofstream outFile(toFileName);


	if (!outFile.is_open())
	{
		std::cerr << "Error opening the file \"" << toFileName << "\"\n";

		return false;
	}

	char buff[1024]; //Assume
	while (inFile.getline(buff, 1024))
	{
		outFile << buff << '\n';
	}

	inFile.close();
	outFile.close();

	return true;
}

int main()
{
	std::cout << std::boolalpha << copyToFile("Y.txt", "Y-copy.txt");
}