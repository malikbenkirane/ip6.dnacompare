#include "lecture_instances.h"

void lecture_instances(char* file, struct infoInst* infos)
{
	FILE* fichier = NULL;
	char cm[TAILLE_MAX];
	char cn[TAILLE_MAX];
	fichier = fopen(file, "r");
	if (fichier != NULL)
	{
		fgets(cm, TAILLE_MAX, fichier);
		infos->m = atoi(cm);
		fgets(cn, TAILLE_MAX, fichier);
		infos->n = atoi(cn);
		infos->x = (char*) malloc((infos->m + 1) * sizeof(char));
		infos->y = (char*) malloc((infos->n + 1) * sizeof(char));
		int c;
		for (int i = 0; i < infos->m; i++)
		{
			c = fgetc(fichier);
			while (isspace(c))
			{	
				c = fgetc(fichier);
			}	
			(infos->x)[i] = c;
		}
		(infos->x)[infos->m] = '\0';
		for (int j = 0; j < infos->n; j++)
		{
			c = fgetc(fichier);
			while (isspace(c))
			{
				c = fgetc(fichier);
			}
			(infos->y)[j] = c;
		}
		(infos->y)[infos->n] = '\0';
	} else {
		printf("Impossible d'ouvrir le fichier %s", file);
	}
	fclose(fichier);
}

void affichage_instances(struct infoInst infos)
{
	printf("X = [%s]\n", infos.x);
	printf("Y = [%s]\n", infos.y);
}

