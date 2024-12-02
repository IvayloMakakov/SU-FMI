#include <iostream>
#pragma warning(disable: 4996)

struct Laptop
{
	double price;
	char brand[32];
	unsigned short screenSize;
	unsigned short hardDiskCapacity;
	char videoCardModel[32];
	bool hasSSD;
	char osName[32];
};

using std::cout;
using std::cin;

void createLaptop(Laptop& laptop)
{
	cout << "Enter info:\n";
	cin >> laptop.price >> laptop.brand >> laptop.hardDiskCapacity >> laptop.screenSize >> laptop.videoCardModel >> laptop.hasSSD >> laptop.osName;
}

void printLaptop(const Laptop& laptop)
{
	cout << std::boolalpha << "Price: " << laptop.price << ", Brand: " << laptop.brand << ", Screen Size: " << laptop.screenSize << ", HD Capacity: " << laptop.hardDiskCapacity << ", VC model: " << laptop.videoCardModel << ", " << laptop.hasSSD << ", OS: " << laptop.osName << std::endl;
}

Laptop getTheMostExpensiveLaptop(const Laptop* shop, size_t N)
{
	if (N <= 0)
		return {};

	size_t idx = 0, maxPrice = shop[0].price;

	for (size_t i = 1; i < N; i++)
	{
		if (shop[i].price > maxPrice)
		{
			maxPrice = shop[i].price;
			idx = i;
		}
	}

	return shop[idx];
}

double getAvgPriceOnShop(const Laptop* shop, size_t N)
{
	if (N <= 0)
		return 0;

	double sum = 0.0;

	for (size_t i = 0; i < N; i++)
	{
		sum += shop[i].price;
	}

	return sum / N;
}

void printLaptopsWithWondowsOs(const Laptop* shop, size_t N)
{
	if (N <= 0)
		return;

	for (size_t i = 0; i < N; i++)
	{
		if (strcmp(shop[i].osName, "windows") == 0 || strcmp(shop[i].osName, "Windows") == 0)
			printLaptop(shop[i]);
	}
}

int main()
{
	constexpr size_t N = 5;
	Laptop shop[N];

	for (size_t i = 0; i < N; i++)
	{
		createLaptop(shop[i]);
	}

	Laptop mostExpensive = getTheMostExpensiveLaptop(shop, N);

	cout << "\nThe Most Expensive Laptop: ";
	printLaptop(mostExpensive);

	double avgPrice = getAvgPriceOnShop(shop, N);

	cout << "\nAverage price: " << avgPrice << std::endl;

	cout<<"\nLaptops with windows os:" << std::endl;

	printLaptopsWithWondowsOs(shop, N);
}

/* Tests
* 15.99
B1
56
45
M1
1
windows

74.99
B2
87
33
M2
0
linux

123.3
B3
123
654
M3
0
macos

58
B4
111
333
M4
1
Windows

77
B5
1
2
M5
0
linux
*/