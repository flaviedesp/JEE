package objetDistant;

// ==========================================================================
// Projet RMI TrieurDistant
// --------------------------------------------------------------------------
// Trieur : interface de l'objet distant Trieur.
// ==========================================================================
import java.rmi.*;

// --------------------------------------------------------------------------
// Cette interface sera partagee par le serveur et le client.
// --------------------------------------------------------------------------
// L'objet distant implementera cette interface.
// Elle sera utilisée par le client pour déclarer une reference vers cet 
// objet.
// --------------------------------------------------------------------------

public interface Trieur extends Remote
{

// --------------------------------------------------------------------------
// Methode de tri d'un tableau
// --------------------------------------------------------------------------
// Toutes les methodes doivent lancer une exception RemoteException.
// --------------------------------------------------------------------------
    public Comparable[] trier(Comparable tableau[]) throws RemoteException;
}
