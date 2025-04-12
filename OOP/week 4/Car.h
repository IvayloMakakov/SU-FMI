#pragma once
#include <fstream>

enum class Type :char
{
	Invalid = -1,
	Sedan,
	SUV,
	Hatchback,
	Coupe,
	Count
};

class Car
{
private:
	char brand[15 + 1];
	char model[31 + 1];
	char registrationNumber[8 + 1];
	Type type;
	bool isElectric;

	const char* getTypeString(Type type) const;

public:
	const char* getBrand() const;
	const char* getModel() const;
	const char* getRegistrationNumber() const;
	Type getType() const;
	bool getIsElectric() const;

	void setBrand(const char* brand);
	void setModel(const char* model);
	void setRegistrationNumber(const char* rn);
	void setType(Type type);
	void setIsElectric(bool e);

	bool writeToFile(std::ofstream& outFile) const;
	bool readFromFile(std::ifstream& inFile);

	void print() const;
};

