#include "cout_sol_1.h"
#include "cout_2.h"

int main(int argc, char* argv[])
{
	if (argc != 2) {
		printf("Argument attendu : fichier d'instances\n");
		return -1;
	}
	char* file = argv[1];
	struct infoInst infos;
	infos.m = 0;
	infos.n = 0;
	infos.x = NULL;
	infos.y = NULL;
	lecture_instances(file, &infos);
	
	int** p = malloc(infos.m * sizeof(*p));
	for (int i = 0; i < infos.m; i++)
	{
		p[i] = malloc(infos.n * sizeof(int));
	}
	
	/* Initialiser P à partir de la lecture des instances et de la structure des pénalités de correspondances */
	struct penaliteCorres penas[16];
	penas[0].c1 = 'A'; penas[0].c2 = 'A'; penas[0].delta = 0;
	penas[1].c1 = 'T'; penas[1].c2 = 'T'; penas[1].delta = 0;
	penas[2].c1 = 'G'; penas[2].c2 = 'G'; penas[2].delta = 0;
	penas[3].c1 = 'C'; penas[3].c2 = 'C'; penas[3].delta = 0;
	penas[4].c1 = 'A'; penas[4].c2 = 'T'; penas[4].delta = 3;
	penas[5].c1 = 'T'; penas[5].c2 = 'A'; penas[5].delta = 3;
	penas[6].c1 = 'C'; penas[6].c2 = 'G'; penas[6].delta = 3;
	penas[7].c1 = 'G'; penas[7].c2 = 'C'; penas[7].delta = 3;
	penas[8].c1 = 'A'; penas[8].c2 = 'G'; penas[8].delta = 4;
	penas[9].c1 = 'G'; penas[9].c2 = 'A'; penas[9].delta = 4;
	penas[10].c1 = 'A'; penas[10].c2 = 'C'; penas[10].delta = 4;
	penas[11].c1 = 'C';	penas[11].c2 = 'A'; penas[11].delta = 4;
	penas[12].c1 = 'T'; penas[12].c2 = 'G';	penas[12].delta = 4;
	penas[13].c1 = 'G'; penas[13].c2 = 'T'; penas[13].delta = 4;
	penas[14].c1 = 'T'; penas[14].c2 = 'C'; penas[14].delta = 4;
	penas[15].c1 = 'C'; penas[15].c2 = 'T'; penas[15].delta = 4;

	for (int i = 0; i < infos.m; i++) {
		for (int j = 0; j < infos.n; j++) {
			int k = 0;
			while (((infos.x[i] != penas[k].c1) || (infos.y[j] != penas[k].c2)) && (k < 16))
			{
				k++;
			}
			p[i][j] = penas[k].delta;
		}
	}
	int** c = malloc((infos.m + 1) * sizeof(*c));
	for (int i = 0; i < infos.m + 1; i++) {
		c[i] = malloc((infos.n + 1) * sizeof(int));
	}
	int dgap = 2;
	int ret = cout1(infos, c, p, dgap);
	struct paires* listPaires = NULL;
	printf("Valeur d'un alignement de coût minimal (cout1) : %d\n", ret);
	sol1(infos, &listPaires, c, infos.m, infos.n, p, dgap);
	affichage_align(infos, &listPaires);
	int ret2 = cout2(infos.m, infos.n, p, dgap);
	printf("Valeur d'un alignement de coût minimal (cout2) : %d\n", ret2);
	for (int i = 0; i < infos.m; i++)
	{
		free(p[i]);
	}
	free(p);
	for (int i = 0; i < infos.m + 1; i++)
	{
		free(c[i]);
	}
	free(c);
	return 0;
}
