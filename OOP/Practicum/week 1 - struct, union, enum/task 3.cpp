#include <iostream>
#pragma warning(disable: 4996)

struct Planet
{
	char name[30];
	double distanceFromSun, diameter, mass;
};

using std::cin;
using std::cout;

void readPlanet(Planet& planet)
{
	cout << "Enter planet info:\n";
	cin >> planet.name >> planet.distanceFromSun >> planet.diameter >> planet.mass;
}

void printPlanet(const Planet& planet)
{
	cout << "Name: " << planet.name << ", Distance: " << planet.distanceFromSun << ", Diameter:" << planet.diameter << ", Mass: " << planet.mass << std::endl;
}

double countSecondsLightComeToPlanet(const Planet& planet)
{
	return planet.distanceFromSun / 299792.0;
}

void createPlanets(Planet*& planets, size_t size)
{
	if (size <= 0)
	{
		return;
	}

	planets = new Planet[size];

	for (size_t i = 0; i < size; i++)
	{
		readPlanet(i[planets]);
	}
}

void printPlanets(const Planet* planets, size_t size)
{
	if (!planets || size <= 0)
	{
		return;
	}

	for (size_t i = 0; i < size; i++)
	{
		printPlanet(planets[i]);
	}
}

int main()
{
	cout << "Enter count: ";

	size_t count;
	cin >> count;

	Planet* planets = nullptr;

	createPlanets(planets, count);

	printPlanets(planets, count);

	cout << "Seconds: " << countSecondsLightComeToPlanet(planets[0]); //we assume that planets[0] exists

	delete[] planets;
}