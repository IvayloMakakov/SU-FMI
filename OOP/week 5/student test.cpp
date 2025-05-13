#include <iostream>
#include "GraduatedStudent.h"

int main()
{
	unsigned grades[] = { 3,3,4,4,5,5 };

	GraduatedStudent gr("Test Student", grades, sizeof(grades) / sizeof(unsigned), "");

	std::cout << gr.getName();
}