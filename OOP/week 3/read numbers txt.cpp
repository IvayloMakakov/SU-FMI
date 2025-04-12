#include <iostream>
#include <fstream>

int* readNumbers(const char* fileName, int& n)
{
	n = 0;

	std::ifstream inFile("numbers.txt");

	if (!inFile.is_open())
	{
		return nullptr;
	}

	inFile >> n;

	int* dynArray = new int[n];

	for (size_t i = 0; i < n; i++)
	{
		inFile >> dynArray[i];
	}

	inFile.close();

	return dynArray;
}

int main()
{
	int n;

	int* arr = readNumbers("numbers.txt", n);

	if (!arr)
	{
		std::cerr << "Error opening the file";

		return -1;
	}

	std::cout << "The content of the array is:" << std::endl;

	for (size_t i = 0; i < n; i++)
	{
		std::cout << arr[i] << ' ';
	}

	std::cout << std::endl;

	delete[] arr;
}