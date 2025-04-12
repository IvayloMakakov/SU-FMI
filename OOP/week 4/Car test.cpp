#include "Car.h"

int main()
{
	Car c;
	c.setBrand("Audi");
	c.setModel("A5");
	c.setRegistrationNumber("CB8871AA");
	c.setIsElectric(false);
	c.setType(Type::Coupe);

	std::ofstream ofs("car.bin", std::ios::binary);
	c.writeToFile(ofs);
	ofs.close();

	Car c2;
	std::ifstream ifs("car.bin", std::ios::binary);
	c2.readFromFile(ifs);
	ifs.close();

	c2.print();
}