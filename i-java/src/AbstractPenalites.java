import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
   Cette classe abstraite permet d'implémenter l'algorithme P(i,j)
   discuté à la question 3.2 de la partie théorique du
   rapport. L'implémentation est générique pour permettre le cas ou
   les séquences deviennent trop grande et les coûts ne peuvent plus
   être stocké par un Integer (ce qui n'arrivera surement pas dans le
   cadre de nos test...). Pour l'instant on ne traite que les cas ou
   Integer ou Long (cf. classes PenalitesInteger et PenalitesLong).

   On s'est rendus comptes que cette classe et la classe
   PenaliteDeCorrespondance peuvent être facilement implémentés depuis
   la JDK8 avec la classe Mapper... TODO
*/
public abstract class AbstractPenalites<T> {

    /**liste de pénalité de correspondances*/
    protected ArrayList<PenaliteDeCorrespondance<T>> penalites;
    /**pénalité associée à un gap*/
    protected T penaliteGap;
    /**pénalité pour les paires non listés dans la liste penalites*/
    protected T penaliteAutre;
    private String filename = null;

    /**
       Construit une instance de cette classe à partir d'un
       fichier. Le format du fichier est assez simple : la première
       ligne indique la pénalité delta-gap, la deuxième la pénalité à
       attribuer si elle n'est pas listée et les lignes qui suivent
       donnent cette liste en suivant le format N1:N2:P où (N1,N2) est
       le couple de nucléotide auquel on associe une pénalité P.
       @param filename nom du fichier
       @throws FichierDePenalitesInvalide exception au cas ou le
       fichier est invalide
    */
    public AbstractPenalites(String filename)
        throws FichierDePenalitesInvalide {
        lireFichierDePenalites(filename);
        this.filename = filename;
    }

    /**
       Méthode pour l'affichage
    */
    public void afficher() {
        System.out.println(toString());
    }

    /**
       Méthode pour faciliter l'affichage
       @return description des pénalités de correspondances de
       l'instance
    */
    public String toString() {
        String s = ":\n";
        for ( PenaliteDeCorrespondance<T> p : penalites ) {
            char n1 = p.getN1();
            char n2 = p.getN2();
            s += "("+ n1 + ","+ n2 + "," + p.toString(n1,n2) + ") ";
        }
        return
            "Pénalités de correspondances "+
            ((filename != null)?"(lues à partir de "+filename+") ":"")+s+
            "\nPénalité correspondant à un gap : " + penaliteGap + "\n"+
            "Pénalité pour les autres correspondances : " + penaliteAutre;
    }

    /* appelé par le constructeur qui prends en paramétre un String le
       format lu est précisé dans la documentation de ce constructeur
    */
    private void lireFichierDePenalites(String filename)
        throws FichierDePenalitesInvalide {
        try {
            BufferedReader b =
                new BufferedReader(new FileReader(filename));
            //on essaye de lire la première ligne de fichier
            penaliteGap = lirePenalite(b.readLine());
            penaliteAutre = lirePenalite(b.readLine());
            penalites = new ArrayList<PenaliteDeCorrespondance<T>>();
            String ligne;
            int numeroTriplet = 1; //debug
            //on essaye de lire les lignes suivantes
            ligne = b.readLine();
            while ( ligne != null ) {
                String[] pc = ligne.split(":");
                /* la décomposition de ligne devrait donné un tableau
                   de 3 String, on test toutes les erreurs de formats
                   possibles
                */
                char n1 = pc[0].charAt(0);
                if ( n1 != 'A' &&
                     n1 != 'C' &&
                     n1 != 'T' &&
                     n1 != 'G' )
                    throw new FichierDePenalitesInvalide
                        ("La première composante du triplet "+
                         numeroTriplet+" "+
                         "de pénalité de correspondance "+
                         "n'est pas une nucléotide");
                char n2 = pc[1].charAt(0);
                if ( n2 != 'A' &&
                     n2 != 'C' &&
                     n2 != 'T' &&
                     n2 != 'G' )
                    throw new FichierDePenalitesInvalide
                        ("La seconde composante du triplet "+
                         numeroTriplet+" "+
                         "de pénalité de correspondance "+
                         "n'est pas une nucléotide");
                PenaliteDeCorrespondance<T> pdc =
                    new PenaliteDeCorrespondance<T>(n1,
                                                    n2,
                                                    lirePenalite
                                                    (pc[2]));
                penalites.add(pdc);
                ligne = b.readLine();
            }
            b.close();
        }
        catch (FileNotFoundException e) {
            throw new FichierDePenalitesInvalide
                ("Fichier " + filename + " inexistant");
        }
        catch (IOException e) {
            throw new FichierDePenalitesInvalide(e+"");
        }
    }

    /**
       Méthode abstraite, on devra implémenter une méthode qui
       retourne une valeur de type T pour une chaîne de caractères lue
       @param pc chaîne de caractères lue
       @return valeur de type T retournée
    */
    abstract T lirePenalite(String pc); 
    /**
       Determine la pénalité d'une paire donnée. Ceci est une méthode
       abstraite elle devra être implémenter selon le type T
       @param n1 premiére nucléotide
       @param n2 seconde nucléotide
       @return valeur de la pénalité
    */
    abstract T penalite(char n1, char n2);

    public T getPGap() {
        return penaliteGap;
    }
    
}
