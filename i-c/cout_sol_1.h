#include "lecture_instances.h"

int memo_cout(int** c, int** p, int dgap, int i, int j);
int cout1(struct infoInst infos, int** c, int** p, int dgap);
struct paires* ajouter_en_tete(struct paires* list, int a, int b);
void affichage_align(struct infoInst infos, struct paires** listPaires);
void rec_sol1(int** c, struct paires** listPaires, int i, int j, int** p, int dgap);
void sol1(struct infoInst infos, struct paires** listPaires, int** c, int m, int n, int** p, int dgap);
