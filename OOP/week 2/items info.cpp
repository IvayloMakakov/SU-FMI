#include <iostream>

struct Item
{
	double price;
	unsigned quantity;
};

double totalSum(const Item items[], size_t len)
{
	if (len <= 0)
	{
		return 0;
	}

	double sum = 0.0;

	for (size_t i = 0; i < len; i++)
	{
		sum += items[i].price * items[i].quantity;
	}

	return sum;
}

unsigned quantityOfTheMostExpensiveItem(const Item items[], size_t len)
{
	if (len <= 0)
	{
		return 0;
	}

	unsigned max = items[0].quantity;

	for (size_t i = 1; i < len; i++)
	{
		if (items[i].quantity > max)
		{
			max = items[i].quantity;
		}
	}

	return max;
}

int main()
{
	Item items[5]
	{
		{1,2},
		{2,1},
		{5,7},
		{3,0},
		{1,20},
	};

	std::cout << "Total sum: " << totalSum(items, 5) << std::endl;
	std::cout << "Max quantity: " << quantityOfTheMostExpensiveItem(items, 5) << std::endl;
}