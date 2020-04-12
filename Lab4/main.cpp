#include <iostream>
#include <fstream>
#include "cinoutFunctions.h"
#include "functions.h"
using namespace std;

int main ()
{
	const int num = 5;
	const double eps = 0.000001;
	double	**A = new double* [num],
			**S = new double* [num],
			**M = new double* [num],
			*eVect = new double [num],
			*r = new double [num],
			eValue;
	for (int i = 0; i < num; ++i)
	{
		A[i] = new double [num];
		S[i] = new double [num];
		M[i] = new double [num];
	}

	ifstream isM ("matrix.txt");
	ofstream os ("output.txt");
	CinM (A, num, isM);
	isM.close();
//	gaussTransformation (A, num);

	os << "Initial matrix:" << endl << endl;
	CoutM (A, num, os);
	os << endl;

	if ( danilevsky (A, M, num) )
	{
		os << "Frobenius form:" << endl << endl;
		CoutM (A, num, os);
		eValue = findMaxEigenvalue (A, num, eps);
		os << endl << "Max eigenvalue is " << eValue << endl << endl << "Eigenvector: " << endl << endl;
		findEigenvector (M, eVect, eValue, num);
		CoutV (eVect, num, os);
		ifstream isM ("matrix.txt");
		CinM (A, num, isM);
		isM.close();
		os << endl << "Residual:" << endl << endl;
		findResidual (A, eVect, eValue, r, num);
		CoutV (r, num, os);
	}

	os.close();
	for (int i = 0; i < num; ++i)
	{
		delete [] A[i];
		delete [] S[i];
		delete [] M[i];
	}
	delete [] A;
	delete [] S;
	delete [] M;
	delete [] eVect;
	delete [] r;
	return 0;
}