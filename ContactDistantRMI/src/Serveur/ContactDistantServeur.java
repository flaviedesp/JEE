package Serveur;



import java.rmi.Naming;
import java.net.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import contactdistantrmi.ContactDistantImpl;

public class ContactDistantServeur
{
    public static void main(String args[]) throws UnknownHostException, RemoteException, MalformedURLException{
        LocateRegistry.createRegistry(6128);
        String adresse = "/" + InetAddress.getLocalHost() + ":6128";
        System.out.println(adresse);
        
        System.out.println("Construction de l'objet distant...");
        
        ContactDistantImpl contactDistantImpl = new ContactDistantImpl();
        
        System.out.println("Liaison de l'objet distant sur le serveur d'annuaire...");
        Naming.rebind("//localhost:6128/contactDistantImpl", contactDistantImpl);
        
        System.out.println("Attente des invocations des contacts...");
    }
}
