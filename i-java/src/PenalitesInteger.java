public class PenalitesInteger extends AbstractPenalites<Integer> {
    
    /**
       cf. {@link AbstractPenalites}
       @param filename nom du fichier
       @throws FichierDePenalitesInvalide exception lorsque le fichier
       lu est invalide 
       (cf. {@link AbstractPenalites#lireFichierDePenalites(String)})
    */
    public PenalitesInteger(String filename)
        throws FichierDePenalitesInvalide {
        super(filename);
    }
    
    protected Integer lirePenalite(String pc) {
        return new Integer(pc);
    }

    public Integer penalite(char n1, char n2) {
        //si xi=yj delta{{xi}{yj}}=0
        if ( n1 == n2 ) 
            return new Integer(0);
        //sinon on regarde si la paire est dans liste des penalites
        Integer test;
        for ( PenaliteDeCorrespondance<Integer> p : penalites ) {
            test = p.penalite(n1, n2);
            if ( test != null )
                return test;
        }
        //sinon on retourne penalité autre
        return penaliteAutre;
    }

}
