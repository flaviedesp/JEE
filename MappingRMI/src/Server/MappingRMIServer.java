/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import objetDistant.MappingRMIImpl;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.*;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author afpa1797
 */
public class MappingRMIServer
{
    public static void main (String args[]) throws UnknownHostException, RemoteException, MalformedURLException{
        LocateRegistry.createRegistry(6128);
        String adresse = "//"
                + InetAddress.getLocalHost().getHostAddress() + ":6128";
        System.out.println("Adresse : " + adresse);
        
        System.out.println("Construction de l'objet distant...");
        MappingRMIImpl contact = new MappingRMIImpl();
        
        System.out.println("Liaison de l'objet distant sur le serveur d'annuaire...");
        Naming.rebind(adresse + "/mappingDistant", contact);
        
        System.out.println("Attente des invocations des clients...");
    }
}
