import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

/**
   Implémentation des algorithmes COUT2, COUT2BIS et SOL2
*/
public class CompareInteger2 extends AbstractCompare<Integer> {

    private ArrayList<Integer> t0; //tableau pour la ligne précédente
    private ArrayList<Integer> t1; //tableau pour la ligne en cours de calcul
    private ArrayList<Integer> t0bis; //idem pour les méthodes bis
    private ArrayList<Integer> t1bis; //idem pour les méthodes bis

    //attributs nécessaires pour l'implémentation de SOL2
    private List<Paire> la;
    private List<Paire> lb;
    //
    private char[] x2a;
    private char[] y2a;
    private char[] x2b;
    private char[] y2b;
    //
    private int n;
    private int m;
    //
    private PenalitesInteger pcast;

    public CompareInteger2(PaireDeSequences xy, PenalitesInteger p) {
        super(xy, p);
        pcast = p;
        //instanciations des attributs pour SOL2
        m = xy.longueurX();
        n = xy.longueurY();
        x2a = new char[3];
        y2a = new char[n+1];
        x2b = new char[m+1];
        y2b = new char[3];
    }

    /**
       Implémentation de MAJ-MEMO (déplace la ligne où s'effectue le
       calcul)
       @param t0 ligne déjà calculée à l'itération d'avant (i-1)
       @param t1 ligne calculée à l'itération actuelle (i)
       @param bis booléen qui permet de savoir qui appel majMemo (cout
       [false] ou coutbis [true]) puisque Java est un langage
       "pass-by-value".
       @param i indice de la ligne
       @param j taille des tableaux ligne i et i-1
    */
    private void majMemo(ArrayList<Integer> t0, ArrayList<Integer> t1,
                         boolean bis, int i, int j) {
        //(cette implémentation peut-être améliorée)
        //trace("~avant~majMemo > " + t0 + t1);
        t0 = new ArrayList<Integer>(t1);
        t1.set(0, (i+1) * p.getPGap());
        for ( int k = 1 ; k <= j ; k++ )
            t1.set(k, null);
        if ( bis ) {
            this.t0bis = t0;
            this.t1bis = t1;
        } else {
            this.t0 = t0;
            this.t1 = t1;
        }
        //trace("~après~majMemo > " + t0 + t1);
    }

    /*
      Utilitaire pour le debug
    */
    private void trace(String m) {
        System.out.println(m);
    }

    /**
       Implémentation de MEMO-COUT2 (contrairement à majMemo on ne
       modifie pas directement t0 et t1 mais on retrourne la pénalité
       calculée ligne i, colonne j)
       @param t0 premier tableau
       @param t1 second tableau
       @param i indice de ligne
       @param j indice de colonne
       @param ip nécessaire pour coutbis ou il faut lire la bonne
       pénalité de correspondance (P(ip,_))
       @param jp idem (P(_,jp))
    */
    private Integer memoCout(ArrayList<Integer> t0, ArrayList<Integer> t1,
                             int i, int j, int ip, int jp) {
        if ( t1.get(j) == null ) {
            Integer dgap = p.getPGap();
            Integer[] pre = new Integer[3];
            pre[0] = t0.get(j-1) + p.penalite(xy.getX(ip),
                                              xy.getY(jp));
            pre[1] = t0.get(j) + dgap;
            pre[2] = t1.get(j-1) + dgap;
            return AbstractCompare.<Integer>min3(pre[0], pre[1], pre[2]);
        }
        return t1.get(j);
    }

    /**
       Se reférer à la documentation de {@link AbstractCompare#cout(int,int)}
       @param i cf. {@link AbstractCompare#cout(int,int)}
       @param j cf. {@link AbstractCompare#cout(int,int)}
    */
    public Integer cout(int i, int j) {
        Integer dgap = p.getPGap();
        //initialisation
        t0 = new ArrayList<Integer>();
        t1 = new ArrayList<Integer>();
        while ( t0.size() < j+1 )
            t0.add(null);
        while ( t1.size() < j+1 )
            t1.add(null);
        t0.set(0, 0);
        t1.set(0, dgap);
        for ( int l = 1 ; l <= j ; l++ ) {
            t0.set(l, l * dgap);
            t1.set(l, null);
        }
        //trace("~cout~initialisé");
        //memoization
        for ( int k = 1 ; k <= i ; k++ ) {
            for ( int l = 1 ; l <= j ; l++ ) {
                t1.set(l, memoCout(t0, t1, k, l, k, l));
                //trace("~cout~" + t0+ "" + t1);
            }
            if ( k < i )
                majMemo(t0, t1, false, k, j);
        }
        //cas particulier de la première ligne & valeur de retour
        if ( i > 0 ) return t1.get(j);
        else return t0.get(j);
    }

    /**
       Implémenation de COUT2BIS : calcul de h(i,j). Méthode propre à
       cette classe.
       @param i
       @param j
    */
    public Integer coutbis(int i, int j) {
        Integer dgap = p.getPGap();
        //initialisation
        t0bis = new ArrayList<Integer>();
        t1bis = new ArrayList<Integer>();
        while ( t0bis.size() < n-j+1 )
            t0bis.add(null);
        while ( t1bis.size() < n-j+1 )
            t1bis.add(null);
        t0bis.set(0, 0);
        t1bis.set(0, dgap);
        for ( int l = 1 ; l <= n-j ; l++ ) {
            t0bis.set(l, l * dgap);
            t1bis.set(l, null);
        }
        //trace("~coutbis~initialisé");
        //memoization
        for ( int k = 1 ; k <= m-i ; k++ ) {
            for ( int l = 1 ; l <= n-j ; l++ ) {
                t1bis.set(l, memoCout(t0bis, t1bis, k, l, k+i, l+j));
                //trace("~coutbis~" + t0bis + "" + t1bis);
            }
            if ( k < m-i )
                majMemo(t0bis, t1bis, true, k, n-j);
        }
        //cas particulier de la dernière ligne & valeur de retour
        //trace("~coutbis~fin~" + t0bis + "" + t1bis);
        if ( i == m ) return t0bis.get(n-j);
        else return t1bis.get(n-j);
    }

    private CompareInteger1 compare;

    public void recSol(List<Paire> l, int k1, int l1, int k2, int l2) {
        if ( k2 - k1 > 0 || l2 - l1 > 0 ) {
            int t1 = k2 - k1 + 1;
            int t2 = l2 - l1 + 1;
            if ( k2 - k1 <= 2 ) {
                for ( int i = k1 ; i <= k2 ; i++ )
                    x2a[i-k1] = xy.getX(i);
                //  x2a[i-k1] = xy.getX(i, "x2a-(" + i + ")");
                for ( int j = l1 ; j <= l2 ; j++ )
                    y2a[j-l1] = xy.getY(j);
                //  y2a[j-l1] = xy.getY(j, "y2a-(" + j + ")");
                compare = new CompareInteger1
                    (new PaireDeSequences(new String(x2a),
                                          new String(y2a)),
                     pcast, t1, t2);
                /*
                trace("  ~recSol(a)(" + t1 + "," + t2 + ")~compare (" +
                      (new String(x2a)) + ") (" +
                      (new String(y2a)) + ")"); 
                */
                la = compare.sol(t1, t2);
                //trace("  ~SOL1(a)~" + la);
                l.addAll(la);
                cout += compare.getCout();
                return;
            } else if ( l2 - l1 <= 2 ) {
                for ( int i = k1 ; i <= k2 ; i++ )
                    x2b[i-k1] = xy.getX(i);
                //  x2b[i-k1] = xy.getX(i, "x2b-(" + i + ")");
                for ( int j = l1 ; j <= l2 ; j++ )
                    y2b[j-l1] = xy.getY(j);
                //  y2b[j-l1] = xy.getY(j, "y2b-(" + j + ")");
                compare = new CompareInteger1
                    (new PaireDeSequences(new String(x2b),
                                          new String(y2b)),
                     pcast, t1, t2);
                /*
                trace("  ~recSol(b)(" + t1 + "," + t2 + ")~compare (" +
                      (new String(x2b)) + ") (" +
                      (new String(y2b)) + ")"); 
                */
                lb = compare.sol(t1, t2);
                //trace("  ~SOL1(b)~" + lb);
                lb.remove(0);
                l.addAll(lb);
                cout += compare.getCout();
                return;
            } else {
                int j = l1 + Math.floorDiv(l2-l1, 2);
                int iopt = k1;
                Integer val;
                Integer valmin = cout(k1, j) + coutbis(k1, j);
                for ( int i = k1 + 1 ; i <= k2 ; i++ ) {
                    val = cout(i, j) + coutbis(i, j);
                    if ( valmin.compareTo(val) > 0 ) {
                        valmin = val;
                        iopt = i;
                    }
                }
                /*
                trace("~recSol(" + k1 + "," + l1 + "," +
                      iopt + "," + j + ")");
                */
                recSol(l, k1, l1, iopt, j);
                /*
                trace("~recSol(" + iopt + "," + j + "," +
                      k2 + "," + l2 + ")");
                */
                recSol(l, iopt, j, k2, l2);
            }
        }
    }
    
    public List<Paire> sol(int i, int j) {
        List<Paire> align = new LinkedList<Paire>();
        cout = 0;
        recSol(align, 0, 0, i, j);
        return align;
    }

}
