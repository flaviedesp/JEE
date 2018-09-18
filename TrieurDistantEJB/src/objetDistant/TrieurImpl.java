package objetDistant;

// ==========================================================================
// Projet RMI TrieurDistant
// --------------------------------------------------------------------------
// TrieurImpl : classe d'implémentation de l'objet distant Trieur
// ==========================================================================
import java.rmi.*;                  // RemoteException
import java.rmi.server.*;           // UnicastRemoteObject

public class TrieurImpl extends UnicastRemoteObject implements Trieur
{

// --------------------------------------------------------------------------
// Constructeur : construit une implementation de Trieur
// --------------------------------------------------------------------------
// Remarque : le constructeur de UnicastRemoteObject peut declencher une
// RemoteException... Il faut la traiter...  Ce constructeur est donc
// obligatoire, meme si c'est celui par defaut.
// --------------------------------------------------------------------------
    public TrieurImpl() throws RemoteException
    {
    }

// --------------------------------------------------------------------------
// Implementation de la methode trier exposee par l'interface Trieur
// --------------------------------------------------------------------------
    public Comparable[] trier(Comparable tableau[]) throws RemoteException
    {
        boolean permut;
        int n;
        Comparable x;
        
        do
        {
            permut = false;
            for (n = 0; n < tableau.length - 1; n++)
            {
                if (tableau[n].compareTo(tableau[n + 1]) > 0)
                {
                    x = tableau[n];
                    tableau[n] = tableau[n + 1];
                    tableau[n + 1] = x;
                    permut = true;
                }
            }
        }
        while (permut == true);

        return tableau;
    }
}
