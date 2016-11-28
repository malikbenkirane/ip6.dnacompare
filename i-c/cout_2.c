#include "cout_2.h"

void maj_memo(int** t0, int** t1, int i, int j, int dgap)
{
	for (int k = 0; k <= j; k++)
	{
		(*t0)[k] = (*t1)[k];
	}
	(*t1)[0] = i * dgap;
	for (int l = 1; l <= j; l++)
	{
		(*t1)[l] = -1;
	}
}

int memo_cout2(int** t0, int** t1, int** p, int dgap, int i, int j, int k, int l)
{
	if ((*t1)[j] < 0) {
		int min = (*t0)[j-1] + p[k-1][l-1];
		if ((*t1)[j-1] + dgap < min)
			min = (*t1)[j-1] + dgap;
		if ((*t0)[j] + dgap < min)
			min = (*t0)[j] + dgap;
		return min;
	}
	return (*t1)[j];
}

int cout2(int i, int j, int** p, int dgap)
{
	int* t0 = malloc((j+1) * sizeof(int));
	int* t1 = malloc((j+1) * sizeof(int));
	t0[0] = 0;
	t1[0] = dgap;
	for (int l = 1; l <= j; l++)
		t0[l] = l * dgap;
	for (int l = 1; l <= i; l++)
		t1[l] = -1;
	for (int k = 1; k <= i; k++)
	{
		for (int l = 1; l <= j; l++)
		{
			t1[l] = memo_cout2(&t0, &t1, p, dgap, k, l, k, l);
		}
		maj_memo(&t0, &t1, k, j, dgap);
	}
	int ret = t0[j];
	return ret;
}
