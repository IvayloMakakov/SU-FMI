#include <iostream>

struct Rational
{
	int num, denom;
};

void printRational(const Rational& r)
{
	std::cout << r.num << "/" << r.denom << std::endl;
}

unsigned getGcd(unsigned a, unsigned b)
{
	if (a < b)
	{
		std::swap(a, b);
	}

	while (b != 0)
	{
		int temp = b;
		b = a % b;
		a = temp;
	}

	return a;
}

bool isValid(const Rational& r)
{
	return r.denom != 0;
}

void rationalize(Rational& r)
{
	if (!isValid(r))
	{
		return;
	}

	unsigned gcd = getGcd(r.num, r.denom);

	r.num /= gcd;
	r.denom /= gcd;

	if (r.num < 0 && r.denom < 0 || r.num > 0 && r.denom < 0)
	{
		r.num *= -1;
		r.denom *= -1;
	}
}
Rational& sumRational(Rational& lhs, const Rational& rhs)
{
	if (!isValid(lhs), !isValid(rhs))
		return lhs;//some error value

	lhs.num = lhs.num * rhs.denom + lhs.denom * rhs.num;
	lhs.denom *= rhs.denom;

	rationalize(lhs);

	return lhs;
}
Rational sumRationals(const Rational& lhs, const Rational& rhs)
{
	if (!isValid(lhs), !isValid(rhs))
		return lhs;//some error value

	Rational r = lhs;
	sumRational(r, rhs);

	return r;
}

int main()
{
	Rational r1{ 1,4 }, r2 = { 5,-9 };

	Rational r3 = sumRationals(r1, r2);

	printRational(r3);

	sumRational(r3, { 3,6 });

	printRational(r3);
}
