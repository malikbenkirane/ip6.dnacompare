import java.util.List;
import java.util.LinkedList;

/**
   Implémentation de {@link AbstractCompare} pour le cas Integer (algorithmes
   de la partie 1). On utilise directement la structure de données
   évoquée à la partie 3 (cf. {@link AbstractPenalites}).
*/
public class CompareInteger1 extends AbstractCompare<Integer> {

    private Matrice<Integer> couts; //memo
    private Integer gap;
    private Integer cout = null;

    /**
       Construit un sequenceur
       @param xy paire de sequences à comparer
       @param p pénalités de correspondance pour la comparaison
    */
    public CompareInteger1(PaireDeSequences xy, PenalitesInteger p) {
        super(xy, p);
        couts = new Matrice<Integer>(xy.longueurX() + 1, //m+1 (cf. rapport)
                                     xy.longueurY() + 1, //n+1
                                     null);
        gap = p.getPGap();
    }

    /**
       Implémentation de MEMO-COUT1
       Méthode auxilaire pour le calcul du cout (lisibilité...)  On
       suppose que les couts ont étés calculés pour les trois cas
       (i-1,j), (i,j-1) et (i-1,j-1). (cf. méthode cout() surchargée
       de AbstractCompare<T>
       @param i indice i du cout c(i,j) à calculer
       @param j indice j du cout c(i,j) à calculer
    */
    private Integer memoCout(int i, int j) {
        if ( couts.get(i,j) == null ) {
            Integer[] pre = new Integer[3];
            pre[0] = couts.get(i-1, j-1) + p.penalite(xy.getX(i),
                                                      xy.getY(j));
            pre[1] = couts.get(i, j-1) + gap; 
            pre[2] = couts.get(i-1, j) + gap;
            return AbstractCompare.<Integer>min3(pre[0], pre[1], pre[2]);
        }
        return couts.get(i,j);
    }

    /** 
        Implémentation de COUT1
        Se référer à la documentation de {@link AbstractCompare#cout()}
    */
    public Integer cout() {
        couts.set(0, 0, 0);
        int m = couts.nbLignes() - 1;
        int n = couts.nbColonnes() - 1;
        for ( int i = 1 ; i <= m ; i++ )
            couts.set(i, 0, i * gap);
        for ( int j = 1 ; j <= n ; j++ )
            couts.set(0, j, j * gap);
        for ( int i = 1 ; i <= m ; i++ )
            for (int j = 1 ; j <= n ; j++ )
                couts.set(i, j, memoCout(i, j));
        return couts.get(m,n);
    }

    /**
       Implémentation de REC-SOL1.
       Méthode auxilaire pour calculer l'alignement optimal On suppose
       qu'on a préalablement déterminé la matrice couts.
       (cf. rapport.pdf/Question 1.8)
       @param m liste inversée de paire correspondant à un/l'
       alignement optimal
       @param i récursif sur i
       @param j récursif sur j
    */
    private void recSol(List<Paire> m, int i, int j) {
        if ( i == 0 || j == 0 ) return;
        Integer[] pre = new Integer[3];
        pre[0] = couts.get(i-1, j-1) + p.penalite(xy.getX(i),
                                                  xy.getY(j));
        pre[1] = couts.get(i, j-1) + gap; 
        pre[2] = couts.get(i-1, j) + gap;
        if ( couts.get(i,j).equals(pre[0]) ) {
            m.add(new Paire(xy.getX(i), xy.getY(j)));
            recSol(m, i-1, j-1);
        } else if ( couts.get(i,j).equals(pre[1]) ) {
            m.add(new Paire('-', xy.getY(j)));
            recSol(m, i, j-1);
        }
        else if ( couts.get(i,j).equals(pre[2]) ) {
            m.add(new Paire(xy.getX(i), '-'));
            recSol(m, i-1, j);
        }
    }

    /**
       Implémentation de SOL1
       Se référer à la documentation de {@link AbstractCompare#sol()}
    */
    public List<Paire> sol() {
        List<Paire> align = new LinkedList<Paire>();
        int m = couts.nbLignes() - 1;
        int n = couts.nbColonnes() - 1;
        cout = cout();
        recSol(align, m, n);
        return align;
    }

    public String toString() {
        if ( cout == null )
            return "NON CALCULÉ";
        return cout.toString();
    }

}
