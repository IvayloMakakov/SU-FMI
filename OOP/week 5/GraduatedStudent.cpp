#include "GraduatedStudent.h"
#include<cstring>
#pragma warning (disable:4996)

void GraduatedStudent::copyDynamic(const GraduatedStudent& other)
{
	strcpy(this->quote, other.quote);

	size_t nameLen = strlen(other.name);
	this->name = new char[nameLen + 1];
	strcpy(this->name, other.name);

	gradesCount = other.gradesCount;
	this->grades = new unsigned[gradesCount];

	for (size_t i = 0; i < gradesCount; i++)
	{
		this->grades[i] = other.grades[i];
	}
}

void GraduatedStudent::freeDynamic()
{
	delete[] name;
	delete[] grades;
	name = nullptr;
	grades = nullptr;

	strcpy(quote, "");
}

GraduatedStudent::GraduatedStudent(const char* name, const unsigned* grades, size_t count, const char* quote)
{
	//IMPORTANT, CHECK
	setQuote(quote);
	setName(name);
	setGrades(grades, count);
}

GraduatedStudent::GraduatedStudent(const GraduatedStudent& other)
{
	copyDynamic(other);
}

GraduatedStudent& GraduatedStudent::operator=(const GraduatedStudent& other)
{
	if (this != &other)
	{
		freeDynamic();
		copyDynamic(other);
	}

	return *this;
}

GraduatedStudent::~GraduatedStudent()
{
	freeDynamic();
}

void GraduatedStudent::setName(const char* name)
{
	if (!name || !*name)
	{
		delete[] this->name;
		this->name = new char[strlen("unknown") + 1];

		strcpy(this->name, "unknown");
	}
	else
	{
		delete[] this->name;
		this->name = new char[strlen(name) + 1];

		strcpy(this->name, name);
	}
}

void GraduatedStudent::setGrades(const unsigned* grades, size_t count)
{
	if (count <= 0)
	{
		return;
	}

	delete[] this->grades;

	gradesCount = count;
	this->grades = new unsigned[gradesCount];

	for (size_t i = 0; i < gradesCount; i++)
	{
		this->grades[i] = grades[i];
	}
}

void GraduatedStudent::setQuote(const char* quote)
{
	if (!quote || !*quote)
	{
		strcpy(this->quote, "unknown");
	}
	else
	{
		strcpy(this->quote, quote);
	}
}

const char* GraduatedStudent::getName() const
{
	return name;
}

const unsigned* GraduatedStudent::getGrades() const
{
	return grades;
}

const char* GraduatedStudent::getQuote() const
{
	return quote;
}

size_t GraduatedStudent::getGradesCount() const
{
	return gradesCount;
}