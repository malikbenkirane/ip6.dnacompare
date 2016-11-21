/** Classe Sequence : implémente une séquence */
public class Sequence {

    //une séquence est implémenté avec une chaine de caractère
    private String sequence;

    public Sequence(String sequence) {
        this.sequence = sequence;
    }

    public char nucleotide(int i) {
        return sequence.charAt(i);
    }

    public int longueur() {
        return sequence.length();
    }
    
    public String toString() {
        return sequence;
    }

}
