
import java.io.*;
import java.sql.*;
import javax.servlet.http.HttpSession;
import metierMapping.Contact;
import utilitairesMG.jdbc.*;
import javax.servlet.*;
import java.sql.*;
import java.util.*;
import javax.servlet.http.*;
import metierMapping.Secteur;
import utilitairesMG.divers.*;

public class TraitementAccueil
{

    private static ContactDAO contactDAO;
    private static Vector<Contact> listeContact;
    private static Contact contact;
    private static String messageContact;

    public static String traitementListe(HttpSession session, AccesBase accesBase)
    {
        String adresse;

        Vector<Colonne> listeColonne = null;
        String tableListe;

        contactDAO = new ContactDAO(accesBase);

        try
        {
            accesBase.getConnection();
            try
            {
                // contactDAO.lire(contact);

                listeContact = contactDAO.lireListe();
                listeColonne = contactDAO.getListeColonnes();

            } catch (SQLException e)
            {
                System.out.println(e.getMessage());
            } finally
            {
                accesBase.closeConnection();
            }
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

//         for(int i = 0; i< listeContact.size(); i++){
//                    tableListe += "<tr>";
//                    tableListe += "<td>";
//                    tableListe += listeContact.get(i).getNumero();
//                    tableListe += "</td>";
//                    tableListe += "<td>";
//                    tableListe += listeContact.get(i).getNom();
//                    tableListe += "</td>";
//                    tableListe += "<td>";
//                    tableListe += listeContact.get(i).getAdresse();
//                    tableListe += "</td>";
//                    tableListe += "<td>";
//                    tableListe += listeContact.get(i).getCodePostal();
//                    tableListe += "</td>";
//                    tableListe += "<td>";
//                    tableListe += listeContact.get(i).getVille();
//                    tableListe += "</td>";
//                    tableListe += "<td>";
//                    tableListe += listeContact.get(i).getCodeSecteur();
//                    tableListe += "</td>";
//                    tableListe += "</tr>";
//                }
        //session.setAttribute("tableListe", tableListe);
        session.setAttribute("listeContact", listeContact);
        session.setAttribute("ListeColonne", listeColonne);

        return "/jspListe.jsp";
    }

    public static String traitementModification(HttpSession session, AccesBase accesBase, Integer numeroContact)
    {

        Vector<Secteur> listeSecteur;
        SecteurDAO secteurDAO;

        try
        {
            accesBase.getConnection();

            contactDAO = new ContactDAO(accesBase);
            contact = new Contact();
            secteurDAO = new SecteurDAO(accesBase);

            try
            {
                contact.setNumero(numeroContact);
                contactDAO.lire(contact);

                if (contact.getNom() == null)
                {
                    messageContact = "Contact inconnu";
                    session.setAttribute("messageContact", messageContact);
                    return "/jspAccueil.jsp";
                }
                else
                {
                    listeSecteur = secteurDAO.lireListe();

                    session.setAttribute("listeSecteur", listeSecteur);
                    session.setAttribute("contact", contact);
                    return "/jspModif.jsp";
                }

            } catch (SQLException e)
            {
                System.out.println(e.getMessage());
            } finally
            {
                accesBase.closeConnection();
            }
            System.out.println("nom contact = " + contact.getNom());

        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return "/jspModif.jsp";
    }

    public static String traitementSupr(HttpSession session, AccesBase accesBase, Integer numeroContact)
    {
        contactDAO = new ContactDAO(accesBase);
        contact = new Contact();

        try
        {
            accesBase.getConnection();
            try
            {
                contact.setNumero(numeroContact);
                contactDAO.detruire(contact);

//                if (contact.getNom() == null)
//                {
//                    messageContact = "Contact inconnu";
//                    session.setAttribute("messageContact", messageContact);
//                    return "/jspAccueil.jsp";
//                }
//                else
//                {
//                    contactDAO.detruire(contact);
//                }
            } catch (SQLException e)
            {
                System.out.println(e.getMessage());
            } finally
            {
                accesBase.closeConnection();
            }
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return "/jspRecap.jsp";
    }

    public static String traitementCreer(HttpSession session, AccesBase accesBase)
    {
        contactDAO = new ContactDAO(accesBase);
        contact = new Contact();

        try
        {
            accesBase.getConnection();
            try
            {                
                contactDAO.creer(contact);

//                if (contact.getNom() == null)
//                {
//                    messageContact = "Contact inconnu";
//                    session.setAttribute("messageContact", messageContact);
//                    return "/jspAccueil.jsp";
//                }
//                else
//                {
//                    contactDAO.detruire(contact);
//                }
            } catch (SQLException e)
            {
                System.out.println(e.getMessage());
            } finally
            {
                accesBase.closeConnection();
            }
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
        return "/jspRecap.jsp";
    }
}
