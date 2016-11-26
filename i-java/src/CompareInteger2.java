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

    public CompareInteger2(PaireDeSequences xy, PenalitesInteger p) {
        super(xy, p);
        int q = xy.longueurY() + 1;
    }

    /**
       Implémentation de MAJ-MEMO (déplace la ligne où s'effectue le
       calcul)
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

    private void trace(String m) {
        System.out.println(m);
    }

    /**
       Implémentation de MEMO-COUT2
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

    public Integer coutbis(int i, int j) {
        int n = xy.longueurY();
        int m = xy.longueurX();
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

    public List<Paire> sol(int i, int j) {
        return null;
    }

}
