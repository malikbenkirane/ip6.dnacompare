public class Afficher {

    public static void main(String[] args) {
        
        try {
            String filename = args[0];
            PaireDeSequences test =
                new PaireDeSequences(filename);
            test.afficher();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}
