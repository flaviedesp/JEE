import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.*;
import contactdistantrmi.*;
import metierMapping.Contact;
import utilitairesMG.divers.Clavier;
import contactdistantrmi.ContactDistant;
import java.sql.SQLException;

public class ClientContactDistant
{
    public static void main(String args[]) {
        try{
            Contact contact;          
            
            String adresse ="//localhost:6128";
            ContactDistant contactDistant = (ContactDistant)Naming.lookup(adresse + "/contactDistantImpl");
            System.out.println("Entrer clavier :");
            int numero = Clavier.readInt();
            
            contact = contactDistant.lireContact(numero);
            System.out.println(contact);
            
        }catch(NotBoundException | SQLException |MalformedURLException | RemoteException e){
            e.printStackTrace();
        }
    }
}
