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
			*y = new double [num],
			eValue;
	for (int i = 0; i < num; ++i)
		A[i] = new double [num];
	ifstream isM ("matrix.txt");
	ofstream os ("output.txt");
	CinM (A, num, isM);
	isM.close();

	gaussTransformation (A, num);
	os << "Initial matrix:" << endl << endl;
	CoutM (A, num, os);
	os << endl << findAbsMaxEigenvalue (A, y, eValue, num, eps) << " iterations needed." << endl << endl;
	os << "Abs max eigenvalue is " << eValue << "." << endl << endl << "Eigenvector: " << endl << endl;
	CoutV (y, num, os);

	os.close();
	for (int i = 0; i < num; ++i)
		delete [] A[i];
	delete [] A;
	delete [] y;
	return 0;
}