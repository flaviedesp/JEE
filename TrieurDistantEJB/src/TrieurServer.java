// ==========================================================================
// Projet RMI TrieurDistant
// --------------------------------------------------------------------------
// TrieurServer : Serveur d'objets distants.
// --------------------------------------------------------------------------
// Ce programme de serveur instancie un objet Trieur et le reference dans
// l'annuaire (lance par rmiregistry).
// ==========================================================================

import java.rmi.Naming;
import java.net.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import objetDistant.TrieurImpl;

public class TrieurServer
{

    public static void main(String args[]) throws UnknownHostException,
                                                  RemoteException,
                                                  MalformedURLException
    {

// --------------------------------------------------------------------------
// Lancement du serveur d'adressage sur le port 6128.
// --------------------------------------------------------------------------
        LocateRegistry.createRegistry(6128);

// --------------------------------------------------------------------------
// Affichage de l'adresse du serveur d'adressage.
// --------------------------------------------------------------------------
//        String adresse = "//" 
//            + InetAddress.getLocalHost().getHostAddress() + ":6128";
//        System.out.println(adresse);

// --------------------------------------------------------------------------
// Instanciation de l'objet distant.
// --------------------------------------------------------------------------
        System.out.println("Construction de l'objet distant...");
        TrieurImpl trieur = new TrieurImpl();

// --------------------------------------------------------------------------
// Inscription de l'objet distant sur le serveur d'adressage. Il sera connu
// sous le nom "/trieur".
// --------------------------------------------------------------------------
        System.out.println(
            "Liaison de l'objet distant sur le serveur d'annuaire...");
        Naming.rebind("//localhost:6128/trieur", trieur);

        System.out.println("Attente des invocations des clients...");
    }
}
