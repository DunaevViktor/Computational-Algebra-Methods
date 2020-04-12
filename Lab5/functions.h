#ifndef FUNCTIONS_H
#define FUNCTIONS_H

template <typename T>
void gaussTransformation (T** A, int num)
{
	T** At = new T* [num];
	for (int i = 0; i < num; ++i)
		At[i] = new T [num];
	for (int i = 0; i < num; ++i)
		for (int j = i; j < num; ++j)
		{
			At[i][j] = 0;
			for (int k = 0; k < num; ++k)
				At[i][j] += A[k][i] * A[k][j];
		}
	for (int i = 0; i < num; ++i)
		for (int j = i; j < num; ++j)
			A[i][j] = A[j][i] = At[i][j];
	for (int i = 0; i < num; ++i)
		delete [] At[i];
	delete [] At;
}

template <typename T>
int findAbsMaxEigenvalue (T** A, T* y, T& eValue, int num, T eps)
{
	int numIter = 0;
	bool continueIter = true;
	T norm;
	T	*ytmp	= new T [num],
		*ratio	= new T [num];
	y[0] = 1;
	ratio[0] = 0;
	for (int i = 1; i < num; ++i)
		y[i] = ratio[i] = 0;
	while (continueIter)
	{
		++numIter;
		for (int i = 0; i < num; ++i)
		{
			ytmp[i] = 0;
			for (int j = 0; j < num; ++j)
				ytmp[i] += A[i][j] * y[j];
		}
		norm = normVCube (ytmp, num);
		continueIter = false;
		for (int i = 0; i < num; ++i)
		{
			eValue = ytmp[i] / y[i];
			if (!continueIter && eValue - ratio[i] > eps)
				continueIter = true;
			ratio[i] = eValue;
			y[i] = ytmp[i] / norm;
		}
	}
	delete [] ratio;
	delete [] ytmp;
	return numIter;
}

template <typename T>
T normVCube (T* v, int num)
{
	T norm = abs (v[0]);
	for (int i = 1; i < num; ++i)
		if (norm < abs (v[i]))
			norm = abs (v[i]);
	return norm;
}

#endif