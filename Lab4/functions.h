#ifndef FUNCTIONS_H
#define FUNCTIONS_H
#include<cmath>
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
bool danilevskyStep (T** A, T** B, T** M, int num, int step)
{
	int aboveNotUnitRow = num - step;
	int notUnitRow = aboveNotUnitRow - 1;
	if (A[aboveNotUnitRow][notUnitRow] == 0)
		return false;
	for (int j = 0; j < num; ++j)
		if (j != notUnitRow)
			M[notUnitRow][j] = - A[aboveNotUnitRow][j] / A[aboveNotUnitRow][notUnitRow];
		else
			M[notUnitRow][j] = 1 / A[aboveNotUnitRow][notUnitRow];
	for (int i = 0; i < aboveNotUnitRow; ++i)
		for (int j = 0; j < num; ++j)
		{
			B[i][j] = A[i][notUnitRow] * M[notUnitRow][j];
			if (j != notUnitRow)
				B[i][j] += A[i][j];
		}
	for (int j = 0; j < num; ++j)
		if (j != notUnitRow)
			B[aboveNotUnitRow][j] = 0;
		else
			B[aboveNotUnitRow][j] = 1;
	for (int i = 0; i < notUnitRow; ++i)
		for (int j = 0; j < num; ++j)
			A[i][j] = B[i][j];
	for (int j = 0; j < num; ++j)
	{
		A[notUnitRow][j] = 0;
		for (int k = 0; k < num; ++k)
			A[notUnitRow][j] += A[aboveNotUnitRow][k] * B[k][j];
	}
	for (int j = 0; j < num; ++j)
		if (j != notUnitRow)
			A[aboveNotUnitRow][j] = 0;
		else
			A[aboveNotUnitRow][j] = 1;
	return true;
}

template <typename T>
bool danilevsky (T** A, T** M, int num)
{
	T** B = new T* [num];
	for (int i = 0; i < num; ++i)
		B[i] = new T [num];
	bool regular = true;
	for (int i = 1; i < num; ++i)
		if ( !danilevskyStep (A, B, M, num, i) )
		{
			regular = false;
			break;
		}
	for (int i = 0; i < num; ++i)
		delete [] B[i];
	delete [] B;
	return regular;
}

template <typename T>
T countPolynom (T** F, int num, T x)
{
	T res = pow (x, num);
	for (int i = 0; i < num; ++i)
		res -= F[0][i] * pow (x, num-i-1);
	return res;
}

template <typename T>
T countDerivativePolynom (T** F, int num, T x)
{
	T res = num * pow (x, num-1);
	for (int i = 0; i < num-1; ++i)
		res -= F[0][i] * (num-i-1) * pow (x, num-i-2);
	return res;
}

template <typename T>
T findMaxEigenvalue (T** A, int num, T eps, T end = 5, T step = 0.1)
{
	while (countPolynom (A, num, end) > 0)
		end -= step;
	T begin = end;
	end += step;

	// check derivative

	T mid;
	while (end - begin > eps)
	{
		mid = (end + begin) / 2;
		if (countPolynom (A, num, mid) > 0)
			end = mid;
		else
			begin = mid;
	}
	return mid;
}

template <typename T>
void findEigenvector (T** M, T* eVector, T eValue, int num)
{
	T tmp;
	eVector[num-1] = 1;
	for (int i = num - 2; i >= 0; --i)
		eVector[i] = eVector[i+1] * eValue;
	for (int i = 0; i < num - 1; ++i)
	{
		tmp = 0;
		for (int j = 0; j < num; ++j)
			tmp += M[i][j] * eVector[j];
		eVector[i] = tmp;
	}
}

template <typename T>
void findResidual (T** A, T* eVector, T eValue, T* r, int num)
{
	for (int i = 0; i < num; ++i)
	{
		r[i] = - eValue * eVector[i];
		for (int j = 0; j < num; ++j)
			r[i] += A[i][j] * eVector[j];
	}
}

#endif