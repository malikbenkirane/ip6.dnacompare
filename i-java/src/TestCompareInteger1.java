import java.util.List;

/**
   Cette classe à pour but de montrer comment on utilise les
   différentes classes implémentés pour tester les algorithmes COUT1
   et SOL1. (cf. {@link PaireDeSequences}, {@link CompareInteger1})
 */
public class TestCompareInteger1 {

    public static void main(String[] args) {

        String fichier_instance;
        String fichier_penalites;
        
        try {
            fichier_instance = args[0];
            fichier_penalites = args[1];

        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("usage: $ fichier_instance fichier_penalites");
            return;
        }

        try {

            PaireDeSequences instance =
                new PaireDeSequences(fichier_instance);
            PenalitesInteger penalites =
                new PenalitesInteger(fichier_penalites);

            instance.afficher();
            penalites.afficher();

            CompareInteger1 sequenceur =
                new CompareInteger1(instance, penalites);

            List<Paire> opt = sequenceur.sol(instance.longueurX(),
                                             instance.longueurY());

            String a1 = "";
            String a2 = "";
            for ( Paire p : opt ) {
                a1 += p.getN1();
                a2 += p.getN2();
            }
            System.out.println("\nAlignement optimal de coût "+
                               sequenceur + " : ");
            System.out.println(a1 + "\n" + a2);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

}
