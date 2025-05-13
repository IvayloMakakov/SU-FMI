#pragma once

class GraduatedStudent
{
public:
	GraduatedStudent(const char* name, const unsigned* grades, size_t, const char* quote);

	GraduatedStudent(const GraduatedStudent& other);

	GraduatedStudent& operator=(const GraduatedStudent& other);

	~GraduatedStudent();

	void setName(const char* name);

	void setGrades(const unsigned* grades, size_t);

	void setQuote(const char* quote);

	const char* getName() const;

	const unsigned* getGrades() const;

	const char* getQuote() const;

	size_t getGradesCount() const;
private:
	char* name = nullptr;
	unsigned* grades = nullptr;
	char quote[31 + 1] = "";
	size_t gradesCount = 0;

	void copyDynamic(const GraduatedStudent& other);
	void freeDynamic();
};