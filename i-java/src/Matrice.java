import java.util.List;
import java.util.ArrayList;

/**
   Classe générique pour la manipulation (élémentaires) de matrices.
*/
public class Matrice<T> {

    private List<T> matrice;
    int n;
    int m;
    
    /**
       Instancie une matrice générique. Les indices vont de 0
       à n-1 pour les lignes et de 0 à m-1 pour les colonnes.
       Implémentation avec ArrayList.
       @param n nombre de lignes
       @param m nombre de colonnes
       @param v valeur pour toutes les cases de la matrice
    */
    public Matrice(int n, int m, T v) {
        //on considère ici une matrice comme un ArrayList de taille n*m
        matrice = new ArrayList<T>();
        this.n = n;
        this.m = m;
        for ( int i = 0 ; i < n ; i++ ) {
            for ( int j = 0 ; j < m ; j++ )
                matrice.add(v);
        }
    }
    
    /**
       Méthode générique pour l'accés à un élement
       @param i indice de la ligne
       @param j indice de la colonne
       @return valeur à la matrice à la ligne i et la colonne j
    */
    public T get(int i, int j) {
        return matrice.get(i + j * n);
    }

    /**
       Méthode générique pour assigner une valeur de type T à une case de la
       matrice.
       @param i numéro de la ligne
       @param j numéro de la colonne
       @param v valeur
    */
    public void set(int i, int j, T v)
    {
        matrice.set(i + j * n, v);
    }

    public int nbLignes() {
        return n;
    }

    public int nbColonnes() {
        return m;
    }

}
