#include <iostream>

void filter(int*& arr, size_t& len, bool(*pred)(int))
{
	size_t newSize = 0;

	for (size_t i = 0; i < len; i++)
	{
		if (pred(arr[i]))
		{
			newSize++;
		}
	}

	int* ptr = new int[newSize];
	
	int counter = 0;
	for (size_t i = 0; i < len; i++)
	{
		if (pred(arr[i]))
		{
			ptr[counter++] = arr[i];
		}
	}

	delete[] arr;
	arr = ptr;
	len = newSize;
}

int main()
{
	size_t len = 10;
	int* arr = new int[len] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

	filter(arr, len, [](int el) {return el % 2 == 0; });

	filter(arr, len, [](int el) {return el > 3; });

	for (size_t i = 0; i < len; i++)
	{
		std::cout << arr[i] << " ";
	}

	delete[] arr;
}