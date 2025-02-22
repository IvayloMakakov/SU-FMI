#include <iostream>

const char* suits[] = { "Hearts", "Spades", "Diamonds", "Clubs" };

enum class Suit
{
	Hearts = 1,
	Spades,
	Diamonds,
	Clubs
};

const char* ranks[] = { "Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King" };

enum class Rank
{
	Ace = 1,
	Two,
	Three,
	Four,
	Five,
	Six,
	Seven,
	Eight,
	Nine,
	Ten,
	Jack,
	Queen,
	King
};

int main()
{
	Rank rank;
	Suit suit;

	int number;

	std::cout << "Enter rank: ";
	std::cin >> number;

	rank = (Rank)number;

	std::cout << "Enter suit: ";
	std::cin >> number;
	suit = (Suit)number;

	std::cout << ranks[(int)rank - 1] << " of " << suits[(int)suit - 1];
}