#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#define TAILLE_MAX 10

struct infoInst
{
	int m;
	int n;
	char* x;
	char* y;
};

struct penaliteCorres
{
	char c1;
	char c2;
	int delta;
};

typedef struct paires paires;
struct paires
{
	int xi;
	int yj;
	struct paires* next;
};

void lecture_instances(char* file, struct infoInst* infos);
void affichage_instances(struct infoInst infos);
