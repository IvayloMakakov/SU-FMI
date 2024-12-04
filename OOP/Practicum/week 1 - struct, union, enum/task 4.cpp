#include <iostream>
#pragma warning(disable: 4996)

struct Person
{
	char firstName[30];
	char lastName[30];
};

struct Client
{
	Person person;
	double amount;
};


void readPerson(Person& person)
{
	std::cin >> person.firstName >> person.lastName;
}

void printPerson(const Person& person)
{
	std::cout << "Full name: " << person.firstName << " " << person.lastName ;

}

void readClient(Client& client)
{
	std::cout<<"Enter client info:" << std::endl;
	readPerson(client.person);
	std::cin >> client.amount;
}

void printClient(const Client& client)
{
	printPerson(client.person);
	std::cout << ", Balance: " << client.amount << std::endl;
}

double getSumOfDebts(const Client* clients,size_t count)
{
	double sum = 0.0;

	for (size_t i = 0; i < count; i++)
	{
		if (clients[i].amount < 0)
			sum += clients[i].amount;
	}

	return sum;
}

int main()
{
	std::cout << "Enter count: ";
	size_t count;
	std::cin >> count;

	Client* clients = new Client[count];

	for (size_t i = 0; i < count; i++)
	{
		readClient(clients[i]);
	}

	for (size_t i = 0; i < count; i++)
	{
		printClient(clients[i]);
	}

	std::cout << "Sum of debts: " << getSumOfDebts(clients, count);

	delete[] clients;
}