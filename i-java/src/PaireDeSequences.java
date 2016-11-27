import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/** 
    Permet de représenter un fichier d'instance 
*/
public class PaireDeSequences {

    private String instance;
    private Sequence x;
    private Sequence y;
    private int m;
    private int n;

    /**
       Construit une paire de séquences à partir de deux chaines de caractères
       @param sx sequence x
       @param sy sequence y
    */
    public PaireDeSequences(String sx, String sy) {
        x = new Sequence(sx);
        y = new Sequence(sy);
        m = x.longueur();
        n = y.longueur();
        instance = null;
    }
    
    /**
       Construit une paire de séquences en lisant un fichier instance
       @param filename Fichier d'instance
       @throws PaireDeSequencesInvalide lorsque le fichier n'est pas
       trouvé, on n'arrrive pas à le lire (java.io.IOException)
       ou que les deux première lignes n'indiquent pas la taille de la
       séquence (java.lang.NumberFormatExcepyion)
     */
    public PaireDeSequences(String filename)
        throws PaireDeSequencesInvalide {
        try {
            BufferedReader b = new BufferedReader(new FileReader(filename));
            //on lit la taille des séquences (2 premières lignes)
            m = Integer.parseInt(b.readLine());
            n = Integer.parseInt(b.readLine());
            //on lit la première séquence (\\s+ pour enlever les espaces)
            x = new Sequence(b.readLine().replaceAll("\\s+", ""));
            //on test si les longeurs correspondent
            if ( x.longueur() != m )
                throw new PaireDeSequencesInvalide
                    ("La première séquence de " +
                     filename + " n'a pas la bonne longueur.");
            //on lit la deuxième séquence (\\s+ pour enlever les espaces)
            y = new Sequence(b.readLine().replaceAll("\\s+", ""));
            //on test si les longeurs correspondent
            if ( y.longueur() != n )
                throw new PaireDeSequencesInvalide
                    ("La deuxième séquence de " +
                     filename + " n'a pas la bonne longueur.");
            instance = filename;
            b.close();
        }
        /*
          on récupère les éventulles erreurs dues au fichier
          qu'on essaye de lire
        */
        catch (FileNotFoundException e) {
            throw new PaireDeSequencesInvalide
                (filename + " n'existe pas.");
        }
        catch (IOException e) {
            throw new PaireDeSequencesInvalide
                (filename + " ne peut pas être lu.");
        }
        catch (NumberFormatException e) {
            throw new PaireDeSequencesInvalide
                ("Les deux premières lignes d'une instance doivent " +
                 "correspondre aux longueur des séquences.");
        }
    }

    private void trace(String m) {
        System.out.println(m);
    }

    /**
       Permet d'obtenir un nucléotide de la séquence X d'une instance
       @param i indice de la nucléotide
       @return nucléotide à l'indice i de la séquence X
    **/
    public char getX(int i) {
        if ( i == 0 ) {
            return '-';
        }
        return x.nucleotide(i-1);
    }
    //debug
    public char getX(int i, String trace) {
        this.trace("  ~getX~" + trace);
        return getX(i);
    }

    /**
       Permet d'obtenir un nucléotide de la séquence Y d'une instance
       @param j indice de la nucléotide
       @return nucléotide à l'indice j de la séquence Y
    **/
    public char getY(int j) {
        if ( j == 0 ) {
            return '-';
        }
        return y.nucleotide(j-1);
    }
    //debug
    public char getY(int j, String trace) {
        this.trace("  ~getY~" + trace);
        return getY(j);
    }

    /**
       Méthode pour accéder à la séquence x
       @return séquence x de la paire
    */
    public String getX() {
        return x.toString();
    }

    /**
       Méthode pour accéder à la séquence y
       @return séquence y de la paire
    */
    public String getY() {
        return y.toString();
    }

    /**
       Permet de récupérer la longueur de la séquence X
       @return longueur de la séquence X
    */
    public int longueurX() {
        return x.longueur();
    }

    /**
       Permet de récupérer la longueur de la séquence Y
       @return longueur de la séquence Y
    */
    public int longueurY() {
        return y.longueur();
    }
    
    public void afficher() {
        System.out.println(this + "");
    }

    public String toString() {
        String s = "Paire de séquence ";
        s += (instance != null)?("(" + instance + ")\n"):"\n";
        s += "(TailleX=" + longueurX() + ",TailleY=" + longueurY() + ")\n";
        return s + "X=(" + x + ")\nY=(" + y + ")";
    }

}
