#include "Car.h"
#include<iostream>
#include<fstream>
#pragma warning (disable:4996)

const char* Car::getTypeString(Type type) const
{
    switch (type)
    {
    case Type::Invalid:
        return "Invalid type";
    case Type::Sedan:
        return "Sedan";
    case Type::SUV:
        return "SUV";
    case Type::Hatchback:
        return "Hatchback";
    case Type::Coupe:
        return "Coupe";
    default:
        return nullptr;
    }
}

const char* Car::getBrand() const
{
    return brand;
}

const char* Car::getModel() const
{
    return model;
}

const char* Car::getRegistrationNumber() const
{
    return registrationNumber;
}

Type Car::getType() const
{
    return type;
}

bool Car::getIsElectric() const
{
    return isElectric;
}

void Car::setBrand(const char* brand)
{
    if (!brand || strlen(brand) >= 16)
        return;
     
    strcpy(this->brand, brand);
}

void Car::setModel(const char* model)
{
    if (!model || strlen(model) >= 32)
        return;

    strcpy(this->model, model);
}

void Car::setRegistrationNumber(const char* rn)
{
    if (!rn || strlen(rn) != 8)
        return;

    strcpy(this->registrationNumber, rn);
}

void Car::setType(Type type)
{
    this->type = type;
}

void Car::setIsElectric(bool e)
{
    this->isElectric = e;

}

bool Car::writeToFile(std::ofstream& outFile) const
{
    if (!outFile.is_open())
    {
        std::cerr << "Error opening stream\n";

        return false;
    }

    outFile.write(reinterpret_cast<const char*>(this), sizeof(Car));

    return true;
}

bool Car::readFromFile(std::ifstream& inFile)
{
    if (!inFile.is_open())
    {
        std::cerr << "Error opening stream\n";

        return false;
    }

    inFile.read(reinterpret_cast<char*>(this), sizeof(Car));

    return true;
}

void Car::print() const
{
    std::cout << std::boolalpha << "Brand: " << brand << "\nModel: " << model << "\nReg. num: " << registrationNumber << "\nType: " << getTypeString(type) << "\nIs electric: " << isElectric << std::endl;
}