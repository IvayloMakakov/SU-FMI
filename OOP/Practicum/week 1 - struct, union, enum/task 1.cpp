#include <iostream>
#pragma warning(disable: 4996)

enum class Genre : char
{
	COMEDY,
	ACTION,
	HORROR,
	ROMANTIC
};

struct Movie
{
	char* name;
	unsigned short duration;
	Genre genre;
	unsigned short rating;
};

//to be easily
using std::cin;
using std::cout;

Movie createMovie(const char* n, unsigned short d, Genre g, unsigned short r)
{
	char* name = nullptr;

	if (!n || !*n)
	{
		name = new char[8];
		strcpy(name, "no name");
	}
	else
	{
		name = new char[strlen(n) + 1];
		strcpy(name, n);
	}

	return { name,d,g,r };
}

const char* getGenreName(Genre g)
{
	switch (g)
	{
	case Genre::COMEDY:
		return "Comedy";
	case Genre::ACTION:
		return "Action";
	case Genre::HORROR:
		return "Horror";
	case Genre::ROMANTIC:
		return "Romantic";
	}
	return "no genre";
}

void printMovie(const Movie& movie)
{
	cout << "Title: " << movie.name << ", Duration: " << movie.duration << ", Genre: " << getGenreName(movie.genre) << ", Rating: " << movie.rating << std::endl;
}

const Movie* getTopRatedMovie(const Movie* movies, size_t N)
{
	if (N <= 0)
		return nullptr;

	const Movie* curr = &movies[0];
	size_t maxRating = movies[0].rating;

	for (size_t i = 1; i < N; i++)
	{
		if (movies[i].rating > maxRating)
		{
			maxRating = movies[i].rating;
			curr = &movies[i];
		}
	}

	return curr;
}

void deleteMovies(Movie* movies, size_t N)
{
	for (size_t i = 0; i < N; i++)
	{
		delete[] movies[i].name;
	}
}

int main()
{
	constexpr size_t N = 3;

	Movie movies[N]
	{
		createMovie("Love",2,Genre::ROMANTIC,5),
		createMovie("Rawr",3,Genre::HORROR,3),
		createMovie("You",5,Genre::ACTION,2),
	};

	for (size_t i = 0; i < N; i++)
	{
		printMovie(movies[i]);
	}

	const Movie* topRatedMovie = getTopRatedMovie(movies, N);

	cout << "\nTop Rated Movie: ";
	printMovie(*topRatedMovie);

	deleteMovies(movies, N);
}