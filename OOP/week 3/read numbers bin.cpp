#include <iostream>
#include <fstream>

int main()
{
	std::ifstream inFile("numbers.bin", std::ios::binary);

	if (!inFile.is_open())
	{
		std::cout << "Failed opening \"numbers.bin\"";

		return -1;
	}

	int n, max = INT_MIN;

	inFile.read(reinterpret_cast<char*>(&n), sizeof(int));

	for (size_t i = 0; i < n; i++)
	{
		int num;
		inFile.read(reinterpret_cast<char*>(&num), sizeof(int));

		if (num > max)
			max = num;
	}

	std::cout << max;
}