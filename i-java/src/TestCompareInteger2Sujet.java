import java.util.List;

/**
   Cette classe à pour but de montrer comment on utilise les
   différentes classes implémentés pour tester les algorithmes COUT1
   et SOL1. (cf. {@link PaireDeSequences}, {@link CompareInteger1})
*/
public class TestCompareInteger2Sujet {

    public static void main(String[] args) {

        String fichier_instance = "../instances/Inst_sujet.adn";
        String fichier_penalites = "../penalites/question-2.1";

        try {

            PaireDeSequences instance =
                new PaireDeSequences(fichier_instance);
            PenalitesInteger penalites =
                new PenalitesInteger(fichier_penalites);

            instance.afficher();
            penalites.afficher();

            CompareInteger2 sequenceur =
                new CompareInteger2(instance, penalites);
            
            int[] test_sujet_iopt = new int[6];
            int[] test_sujet_jopt = new int[6];
            test_sujet_iopt[0]=0;
            test_sujet_jopt[0]=0;
            test_sujet_iopt[1]=0;
            test_sujet_jopt[1]=1;
            test_sujet_iopt[2]=1;
            test_sujet_jopt[2]=2;
            test_sujet_iopt[3]=2;
            test_sujet_jopt[3]=3;
            test_sujet_iopt[4]=3;
            test_sujet_jopt[4]=3;
            test_sujet_iopt[5]=4;
            test_sujet_jopt[5]=4;
            for ( int i = 0 ; i < 6 ; i++ ) {
                int iopt = test_sujet_iopt[i];
                int jopt = test_sujet_jopt[i];
                System.out.println("#" +iopt + "," + jopt);
                Integer cout = sequenceur.cout(iopt,jopt);
                Integer coutbis = sequenceur.coutbis(iopt,jopt);
                System.out.println("#coût minimal : " + cout + "+" + coutbis);
            }
            
            /*
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
            */

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

}
