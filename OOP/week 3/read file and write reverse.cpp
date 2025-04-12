#include <iostream>
#include <fstream>

int main()
{
	//Simple approach

	std::ifstream inFile("input.txt");

	if (!inFile.is_open())
	{
		return -1;
	}

	std::ofstream outFile("output.txt");

	if (!outFile.is_open())
	{
		return -2;
	}

	char buff[256]; //Assume we would not need more

	while (inFile.getline(buff, sizeof(buff), '\n'))
	{
		size_t len = std::strlen(buff);

		for (int i = len - 1; i >= 0; i--)
		{
			outFile << buff[i];
		}

		outFile << '\n';
	}

	outFile.close();
	inFile.close();
}