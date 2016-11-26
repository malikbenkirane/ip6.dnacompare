import java.util.List;

/**
   Classe abstraite générique pour permettre de comparer deux séquence
*/
public abstract class AbstractCompare<T> {

    /**paire de séquences à comparer*/
    protected PaireDeSequences xy;
    /**pénalités de correspondances pour la comparaison*/
    protected AbstractPenalites<T> p;

    /**
       Constructeur d'une instance de cette classe abstraite
       (factorisation générique du code du constructeur des classes
       qui hérites de cette classe)
       @param xy instance de la paire de séqunces à comparer
       @param p instance des pénalités de correspondances nécessaire pour la
       comparaison
    */
    public AbstractCompare(PaireDeSequences xy,
                           AbstractPenalites<T> p) {
        this.xy = xy;
        this.p = p;
    }

    /*
      methodes abstraites pour implémenter les différents algorithmes
      de la partie théorique
    */
    /**
       Méthode abstraite qui sera surchargé pour calculé le coût
       optimal de l'alignement des s\'equences (x1,..,xi) et
       (y1,..,yj)
       @param i taille de la sous s\'equence (x1,..,xi)
       @param j taille de la sous s\'equence (y1,..,yj)
       @return cout minimal d'un alignement des deux sous-séquences
       (x1,..,xi) et (y1,..,yk) de cette instance
    */
    abstract T cout(int i,int j);
    /**
       Méthode abstraite qui sera surchargé pour calculé le
       sous-alignement optimal des s\'equences (x1,..,xi) et
       (y1,..,yj)
       @param i taille de la sous s\'equence (x1,..,xi)
       @param j taille de la sous s\'equence (y1,..,yj)
       @return un alignement minimal de deux séquences
    */
    abstract List<Paire> sol(int i, int j);

    //pour se simplifier la tâche (cf. min3)...
    //ref:[[http://stackoverflow.com/questions/6452313/how-to-implement-a-generic-maxcomparable-a-comparable-b-function-in-java]]
    private static <T1 extends Comparable<? super T1>> T1 min(T1 v1,
                                                              T1 v2) {
        if ( v1.compareTo(v2) <= 0 )
            return v1;
        return v2;
    }
    
    /**
       Méthode générique et statique pour déterminer le min de trois
       instances comparables de T.
       @param v1 première instance
       @param v2 deuxième instance
       @param v3 troisième instance
    */
    public static <T1 extends Comparable<? super T1>> T1 min3(T1 v1,
                                                              T1 v2,
                                                              T1 v3) {
        return min(min(v1, v2), min(min(v1, v3), min(v2,v3)));
    }

}
