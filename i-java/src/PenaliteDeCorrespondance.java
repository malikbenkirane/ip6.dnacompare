/** 
    La présente classe est utilisée pour représenter une pénalité dans
    la classe {@link AbstractPenalites} le type générique T (on
    utilisera surement Integer, Long, ...)  permet d'implémenter la
    valeur de la pénalité de correspondace pour une paire donnée
*/
public class PenaliteDeCorrespondance<T> {

    //paire de nucléotides à comparer
    private char n1;
    private char n2;
    //valeur de la penalite
    private T p;

    /**
     Construit une instance de la classe pour le triplet donné
     @param n1 première nucléotide
     @param n2 seconde nucléotide
     @param p pénalité de correspondance associée au couple (n1,n2)
     */
    public PenaliteDeCorrespondance(char n1, char n2, T p) {
        this.n1 = n1;
        this.n2 = n2;
        this.p = p;
    }
    
    /**
       Calcul la pénalité des nucléotides à comparer si les paires
       comparés sont bien celles correspondant à l'instance
       @param n1 première nucléotide
       @param n2 seconde nucléotide
       @return null si les nucléotides correspondantes à l'instance de la
       classe ne sont pas les même que celle passées en arguments 
    */
    public T penalite(char n1, char n2) {
        if ( this.n1 == n1 && this.n2 == n2 ) return p;
       return null;
    }

    /**
       @return première nucléotide de la paire de correspondance
    */
    public char getN1() { return n1; }
    /**
       @return deuxiéme nucléotide de la paire de correspondance
    */
    public char getN2() { return n2; }

    public String toString(char n1, char n2) {
        T penalite = this.penalite(n1, n2);
        return (penalite).toString();
    }
        
}
