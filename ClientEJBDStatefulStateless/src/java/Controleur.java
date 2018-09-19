
import java.util.Hashtable;
import java.util.Random;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import objetDistant.CompteurSFRemote;
import objetDistant.CompteurSLRemote;
import utilitairesMG.graphique.LF;

public class Controleur
{

    private static CompteurSLRemote compteurSL;
    private static CompteurSFRemote compteurSF;

    public static void main(String[] args) throws NamingException
    {
        Hashtable variablesEnv = new Hashtable();
        variablesEnv.put("org.omg.CORBA.ORBInitialHost", "94010-6101877");
        variablesEnv.put("org.omg.CORBA.ORBInitialPort", "3700");

        Context ctx = new InitialContext(variablesEnv);
        compteurSF = (CompteurSFRemote) ctx.lookup("jndiCompteurSF");
        compteurSL = (CompteurSLRemote) ctx.lookup("jndiCompteurSL");

        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                LF.setLF();
                Fenetre maFenetre = new Fenetre("StateFulStateless");
            }
        }
        );
    }

    public static String tirage()
    {
        Random rand = new Random();
        int numAleatoire = rand.nextInt(100);
        String chiffre = Integer.toString(numAleatoire);
        return chiffre;
    }

    public static void majStateless()
    {
        compteurSL.setCpt(compteurSL.getCpt() + 1);
    }
    
    public static String afficheStateless(){
        String retour = "Compteur StateLess : " + compteurSL.getCpt();
        return retour;
    }
    public static void majStateful()
    {
        compteurSF.setCpt(compteurSF.getCpt() + 1);
    }
    
    public static String afficheStateful(){
        String retour = "Compteur StateFul : " + compteurSF.getCpt();
        return retour;
    }

    public static void arreter()
    {
        System.exit(0);
    }

}
