#include "cout_sol_1.h"

int memo_cout(int** c, int** p, int dgap, int i, int j)
{
	if (c[i][j] < 0)
	{
		int min = c[i-1][j-1] + p[i-1][j-1];
		if ((c[i-1][j] + dgap) < min)
			min = c[i-1][j] + dgap;
		if ((c[i][j-1] + dgap) < min)
			min = c[i][j-1] + dgap;
		return min;	
	}
	return c[i][j];
}

/* La matrice p correspond aux pénalités de correspondances delta_{x_{i}y_{j}} */
int cout1(struct infoInst infos, int** c, int** p, int dgap)
{	
	for (int i = 0; i < infos.m + 1; i++) {
		for (int j = 0; j < infos.n + 1; j++)
		{
			c[i][j] = -1;
		}
	}
	c[0][0] = 0;
	for (int i = 1; i < infos.m + 1; i++)
		c[i][0] = i * dgap;
	for (int j = 1; j < infos.n + 1; j++)
		c[0][j] = j * dgap;
	for (int i = 1; i < infos.m + 1; i++) {
		for (int j = 1; j < infos.n + 1; j++) {
			c[i][j] = memo_cout(c, p, dgap, i, j);
		}
	}
	int ret = c[infos.m][infos.n];
	return ret;
}

struct paires* ajouter_en_tete(struct paires* list, int a, int b)
{
	struct paires* new = malloc(sizeof(struct paires));
	new->xi = a;
	new->yj = b;
	new->next = list;
	return new;
}

void affichage_align(struct infoInst infos, struct paires** listPaires)
{
	struct paires* tmp = *listPaires;
	printf("M = {");
	while (tmp != NULL)
	{
		if (!(tmp->next))
		{
			printf("(%c, %c)}\n", infos.x[tmp->xi - 1], infos.y[tmp->yj - 1]);
		} else {
			printf("(%c, %c),", infos.x[tmp->xi - 1], infos.y[tmp->yj - 1]);
		}
		tmp = tmp->next;
	}
}

void rec_sol1(int** c, struct paires** listPaires, int i, int j, int** p, int dgap)
{
	if ((i == 0) || (j == 0))
		return;
	if (c[i][j] == c[i-1][j-1] + p[i-1][j-1])
	{
		*listPaires = ajouter_en_tete(*listPaires, i, j);
		rec_sol1(c, listPaires, i-1, j-1, p, dgap);
	} else
	{
		if (c[i][j] == c[i-1][j] + dgap)
		{
			rec_sol1(c, listPaires, i-1, j, p, dgap);
		} else
		{
			rec_sol1(c, listPaires, i, j-1, p, dgap);
		}
	}
}

void sol1(struct infoInst infos, struct paires** listPaires, int** c, int m, int n, int** p, int dgap)
{
	rec_sol1(c, listPaires, m, n, p, dgap);
/*	struct paires* tmp = *listPaires;
	printf("M = {");
	while (tmp != NULL)
	{
		if (!(tmp->next))
		{
			printf("(%c, %c)}\n", infos.x[tmp->xi - 1], infos.y[tmp->yj - 1]);
		} else {
			printf("(%c, %c),", infos.x[tmp->xi - 1], infos.y[tmp->yj - 1]);
		}
		tmp = tmp->next;
	}*/
}

