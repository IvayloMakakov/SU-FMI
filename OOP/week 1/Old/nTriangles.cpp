#include <iostream>
#include <cmath>

namespace Points
{
	struct Point
	{
		int x = 0, y = 0;
	};

	void readPoint(Point& p)
	{
		std::cin >> p.x >> p.y;
	}

	void printPoint(const Point& p)
	{
		std::cout << "(" << p.x << ", " << p.y << ") ";
	}

	double getDistance(const Point& p1, const Point& p2)
	{
		int x = p2.x - p1.x;
		int y = p2.y - p1.y;

		return std::sqrt(x * x + y * y);
	}
}

namespace Figures
{
	using namespace Points;

	struct Triangle
	{
		Point p1, p2, p3;
	};

	void readTriangle(Triangle& t)
	{
		readPoint(t.p1);
		readPoint(t.p2);
		readPoint(t.p3);
	}

	void printTriangle(const Triangle& t)
	{
		printPoint(t.p1);
		printPoint(t.p2);
		printPoint(t.p3);
		std::cout << std::endl;
	}

	double getArea(const Triangle& t)
	{
		double sideA = getDistance(t.p1, t.p2);
		double sideB = getDistance(t.p3, t.p2);
		double sideC = getDistance(t.p1, t.p3);

		double halfPerimeter = (sideA + sideB + sideC) / 2;

		return std::sqrt(halfPerimeter * (halfPerimeter - sideA) * (halfPerimeter - sideB) * (halfPerimeter - sideC));
	}

	void sortTrianglesByArea(Triangle* triangles, size_t n)
	{
		double* areas = new double[n];

		for (size_t i = 0; i < n; i++)
		{
			i[areas] = getArea(triangles[i]);
		}

		for (int i = 0; i < n - 1; i++)
		{
			int minAreaTriangleIndex = i;

			for (int j = i + 1; j < n; j++)
			{
				if (areas[j] < areas[minAreaTriangleIndex])
					minAreaTriangleIndex = j;
			}

			if (minAreaTriangleIndex != i)
			{
				std::swap(triangles[i], triangles[minAreaTriangleIndex]);
				std::swap(areas[i], areas[minAreaTriangleIndex]);
			}
		}

		delete[] areas;
	}
}

int main()
{
	size_t n;
	std::cin >> n;

	Figures::Triangle* t = new Figures::Triangle[n];

	for (size_t i = 0; i < n; i++)
	{
		Figures::readTriangle(t[i]);
	}

	Figures::sortTrianglesByArea(t, n);

	for (size_t i = 0; i < n; i++)
	{
		Figures::printTriangle(t[i]);
	}

	delete[] t;
}