#include <iostream>

enum class Major :char
{
	Undefined,
	I,
	IS,
	SE,
	CS
};

enum class Criteria :char
{
	sortByName,
	sortByMajor,
	sortByFn
};

struct Student
{
	char name[100] = "";
	unsigned fn = 0;
	Major major = Major::Undefined;
};

constexpr size_t STUDENTS_MAX_SIZE = 10;
struct StudentDb
{
	unsigned studentsCount = 0;
	Student students[STUDENTS_MAX_SIZE];
};

const char* getMajorName(Major m)
{
	switch (m)
	{

	case Major::I:
		return "I";
	case Major::IS:
		return "IS";
	case Major::SE:
		return "SE";
	case Major::CS:
		return "CS";
	}

	return "Undefined";
}

void printStudent(const Student& s)
{
	std::cout << s.fn << " " << s.name << " " << getMajorName(s.major) << std::endl;
}

void printStudentDb(const StudentDb& db)
{
	if (db.studentsCount > STUDENTS_MAX_SIZE)
		return;

	for (size_t i = 0; i < db.studentsCount; i++)
	{
		printStudent(i[db.students]);
	}

	std::cout << std::endl;
}

void sortStudentDb(StudentDb& db, bool(*isLess)(const Student& lhs, const Student& rhs))
{
	for (int i = 0; i < db.studentsCount - 1; i++)
	{
		int minIndex = i;

		for (int j = i + 1; j < db.studentsCount; j++)
		{
			if (isLess(db.students[j], db.students[minIndex]))
				minIndex = j;
		}

		if (i != minIndex)
			std::swap(db.students[i], db.students[minIndex]);
	}
}
void sortByCriteria(StudentDb& db, Criteria c)
{
	switch (c)
	{
	case Criteria::sortByName:
		sortStudentDb(db, [](const Student& lhs, const Student& rhs) {return std::strcmp(lhs.name, rhs.name) < 0; });
		break;

	case Criteria::sortByMajor:
		return sortStudentDb(db, [](const Student& lhs, const Student& rhs) {return lhs.major < rhs.major; });

	case Criteria::sortByFn:
		sortStudentDb(db, [](const Student& lhs, const Student& rhs) {return lhs.fn < rhs.fn; });
		break;
	}
}
int main()
{
	StudentDb s =
	{
		3,
		{
			{"Petur", 12, Major::IS},
			{"Ivan", 12345, Major::CS},
			{"Aleksandur", 56, Major::SE}
		}
	};

	printStudentDb(s);

	sortByCriteria(s, Criteria::sortByName);

	printStudentDb(s);

	sortByCriteria(s, Criteria::sortByMajor);

	printStudentDb(s);

	sortByCriteria(s, Criteria::sortByFn);

	printStudentDb(s);
}