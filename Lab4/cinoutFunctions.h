#ifndef CINOUTFUNCTIONS_H
#define CINOUTFUNCTIONS_H

#include <iostream>
using namespace std;

template <typename T>
void CoutM (T** m, int num, ostream& os)
{
	for (int i = 0; i < num; ++i)
	{
		for (int j = 0; j < num; ++j)
		{
			os.width (25);
			os.precision (15);
			os << m[i][j];
		}
		os << endl;
	}
}

template <typename T>
void CinM (T** m, int num, istream& is)
{
	for (int i = 0; i < num; ++i)
		for (int j = 0; j < num; ++j)
			is >> m[i][j];
}

template <typename T>
void CoutV (T* v, int num, ostream& os)
{
	for (int i = 0; i < num; ++i)
	{
		os.precision (15);
		os << v[i] << endl;
	}
}

template <typename T>
void CoutLV (T* v, int num, ostream& os)
{
	for (int i = 0; i < num; ++i)
	{
		os.precision (15);
		os << "a[" << i << "][" << i << "] (" << i << ") = " << v[i] << endl;
	}
}

template <typename T>
void CoutDV (T* v, int num, ostream& os)
{
	for (int i = 0; i < num; ++i)
	{
		os.precision (15);
		os << "dx[" << i << "] = " << v[i] << endl;
	}
}

template <typename T>
void CinV (T* v, int num, istream& is)
{
	for (int i = 0; i < num; ++i)
		is >> v[i];
}

#endif