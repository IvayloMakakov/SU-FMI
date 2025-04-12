#include <iostream>
#include <fstream>
#pragma warning (disable:4996)

/*Personal task*/

struct Student
{
	int fn;
	int gradesCount;
	char* name;
	double averageGrade;
};

Student createStudent(const char* name, int fn, int gradesCount, double avGrade)
{
	Student s;

	size_t len = std::strlen(name);

	s.name = new char[len + 1];
	strcpy(s.name, name);

	s.fn = fn;
	s.gradesCount = gradesCount;
	s.averageGrade = avGrade;

	return s;
}

void saveStudentToFile(std::ofstream& outFile, const Student& s)
{
	if (!outFile.is_open())
	{
		std::cerr << "Error opening file\n";

		return;
	}

	size_t len = std::strlen(s.name);

	outFile.write(reinterpret_cast<const char*>(&len), sizeof(len));

	outFile.write(s.name, len);

	outFile.write(reinterpret_cast<const char*>(&s.fn), sizeof(s.fn));
	outFile.write((const char*)&s.gradesCount, sizeof(s.gradesCount));
	outFile.write((const char*)&s.averageGrade, sizeof(s.averageGrade));
}

Student readStudentFromFile(std::ifstream& inFile)
{
	if (!inFile.is_open())
	{
		std::cerr << "Error opening file\n";

		return { 0,0,nullptr,0 };//Some value
	}

	Student s;

	size_t len;

	inFile.read(reinterpret_cast<char*>(&len), sizeof(len));

	s.name = new char[len + 1];
	inFile.read(s.name, len);
	s.name[len] = '\0';

	inFile.read(reinterpret_cast<char*>(&s.fn), sizeof(s.fn));
	inFile.read(reinterpret_cast<char*>(&s.gradesCount), sizeof(s.gradesCount));
	inFile.read(reinterpret_cast<char*>(&s.averageGrade), sizeof(s.averageGrade));


	return s;
}

void freeStudent(Student& s)
{
	delete[] s.name;
	s.name = nullptr;
	s.averageGrade = s.fn = s.gradesCount = 0;
}

void printStudent(const Student& st) {
	std::cout << st.name << " " << st.fn << " " << st.gradesCount << " " << st.averageGrade << std::endl;
}

int main()
{
	{
		Student s1 = createStudent("Ivan", 1234, 2, 4);
		Student s2 = createStudent("Petur", 5555, 5, 5.5);

		std::ofstream outFile("saveS.bin", std::ios::binary);

		if (!outFile.is_open())
		{
			std::cerr << "Error opening file\n";

			return -1;
		}

		saveStudentToFile(outFile, s1);
		saveStudentToFile(outFile, s2);

		freeStudent(s1);
		freeStudent(s2);
	}

	{
		std::ifstream inFile("saveS.bin", std::ios::binary);

		if (!inFile.is_open())
		{
			std::cerr << "Error opening file\n";

			return -2;
		}

		Student s1 = readStudentFromFile(inFile), s2 = readStudentFromFile(inFile);

		printStudent(s1);
		printStudent(s2);

		freeStudent(s1);
		freeStudent(s2);
	}
}