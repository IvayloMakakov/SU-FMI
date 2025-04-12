#include <iostream>
#include <fstream>

struct Order
{
    double grossPrice;
    double vat;
    int customerID;
};

bool saveOrderToFile(const char* fileName, const Order& order)
{
    std::ofstream outFile(fileName, std::ios::binary|std::ios::app);

    if (!outFile.is_open())
    {
        std::cerr << "Error opening file \"" << fileName << "\"\n";
        return false;
    }

    outFile.write(reinterpret_cast<const char*>(&order), sizeof(order));

    return true;
}

Order* getAllOrders(const char* fileName, size_t& count)
{
    std::ifstream inFile(fileName, std::ios::binary);

    if (!inFile.is_open())
    {
        std::cerr << "Error opening file \"" << fileName << "\"\n";
        return nullptr;
    }

    Order curr;

    while (inFile.read(reinterpret_cast<char*>(&curr),sizeof(Order)))
    {
        count++;
    }

    Order* allOrders = new Order[count];

    inFile.seekg(0);

    inFile.read(reinterpret_cast<char*>(allOrders), count * sizeof(Order));

    return allOrders;
}

int main()
{
	//We work for exactly one shop
    
    size_t count;

    Order* orders = getAllOrders("storeOrders.bin", count);

    for (size_t i = 0; i < count; i++)
    {
        std::cout << orders[i].customerID << " "
            << orders[i].grossPrice << " "
            << orders[i].vat << "\n";
    }

    delete[] orders;
}