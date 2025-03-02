#include <iostream>

const char* GENDERS[] = { "Male","Female" };

enum class Gender
{
	Male,
	Female
};

struct Person
{
	char name[25 + 1];
	short age;
	Gender gender;
};

void printPerson(const Person person)
{
	std::cout << "Name: " << person.name << "\tAge: " << person.age << "\tGender: " << GENDERS[(int)person.gender] << std::endl;
}

void printAllMen(const Person people[], size_t len)
{
	for (size_t i = 0; i < len; i++)
	{
		if (people[i].gender == Gender::Male)
		{
			printPerson(people[i]);
		}
	}
}

void printAllWomen(const Person people[], size_t len)
{
	for (size_t i = 0; i < len; i++)
	{
		if (people[i].gender == Gender::Female)
		{
			printPerson(people[i]);
		}
	}
}

void printAllPeople(const Person people[], size_t len)
{
	if (len <= 0)
	{
		return;
	}

	printAllMen(people, len);
	printAllWomen(people, len);
}

void printTheOldestWoman(const Person people[], size_t len)
{
	if (len <= 0)
	{
		return;
	}

	int idx = -1, maxAge = -1;

	for (size_t i = 0; i < len; i++)
	{
		if (people[i].gender == Gender::Female && people[i].age > maxAge)
		{
			maxAge = people[i].age;
			idx = i;
		}
	}

	if (idx < 0)
	{
		return;
	}

	printPerson(people[idx]);
}

void printTheYoungestMenWithLetter(const Person people[], size_t len, char letter)
{
	if (len <= 0 || !(letter >= 'A' && letter <= 'Z'))
	{
		return;
	}

	int idx = -1, minAge = 1000;

	for (size_t i = 0; i < len; i++)
	{
		if (people[i].gender == Gender::Male && people[i].age < minAge && people[i].name[0] == letter)
		{
			minAge = people[i].age;
			idx = i;
		}
	}

	if (idx < 0)
	{
		return;
	}

	printPerson(people[idx]);
}

int main()
{
	Person people[5]
	{
		{"Ivan", 25, Gender::Male},
		{"Ivana", 55, Gender::Female},
		{"Alexandar", 35, Gender::Male},
		{"Mimi", 15, Gender::Female},
		{"Alex", 5, Gender::Male}
	};

	printAllPeople(people, 5);

	std::cout << std::endl;

	printTheOldestWoman(people, 5);

	std::cout << std::endl;

	printTheYoungestMenWithLetter(people, 5, 'A');
}