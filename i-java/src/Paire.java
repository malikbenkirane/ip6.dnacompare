/**
   Cette classe implémente une paire de nucléotide, elle sera
   ré-utilisée pour implémenter un alignement. On se contentera
   d'utiliser une List de {@link Paire}.
*/
public class Paire {

    private char n1;
    private char n2;

    /**
       Constructeur pour une Paire
       @param n1 première nucléotide
       @param n2 deuxième nucléotide
    */
    public Paire(char n1, char n2) {
        this.n1 = n1;
        this.n2 = n2;
    }

    public char getN1() {
        return n1;
    }

    public char getN2() {
        return n2;
    }
    
    public String toString() {
        return "(" + n1 + "," + n2 + ")";
    }
    
}
            
