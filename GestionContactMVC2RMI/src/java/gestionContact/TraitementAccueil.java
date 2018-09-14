package gestionContact;

import daoJdbcMapping.*;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metierMapping.*;
import objetDistant.MappingRMI;
import utilitairesMG.divers.Colonne;
import utilitairesMG.jdbc.AccesBase;
import utilitairesMG.jdbc.BaseDeDonnees;

public class TraitementAccueil
{

    private MappingRMI mappingRMI;

// --------------------------------------------------------------------------
// Constructeur
// --------------------------------------------------------------------------
    public TraitementAccueil(MappingRMI mappingRMI)
    {
        this.mappingRMI = mappingRMI;
    }

// --------------------------------------------------------------------------
// Traitement d'affichage de la liste
// --------------------------------------------------------------------------
    public String traitementListe(HttpServletRequest request) throws RemoteException
    {
        String jspRetour;

        Vector<Vector> listes;
        Vector<Contact> listeContacts;
        Vector<Colonne> listeColonnes;
        HttpSession session = request.getSession();

// --------------------------------------------------------------------------
// L'objet ContactDAO est une variable locale de la methode. Elle est creee a
// chaque appel (et liberee a la fin). Il s'agit d'eviter le melange de
// donnees entre plusieurs utilisateurs. En effet, la ServletControleur est
// instanciée une fois. La classe TraitementAccueil une fois également. Si
// l'objet ContactDAO etait declare en propriete de la classe
// TraitementAccueil, elle serait commune a tous les utilisateurs. Or, un
// objet ContactDAO contient une propriete de type JeuResultat qui est
// modifiee a chaque lecture dans la base.
// --------------------------------------------------------------------------
//        accesBase = new AccesBase(mappingRMI);
//        try
//        {
//            accesBase.getConnection();
//            contactDAO = new ContactDAO(accesBase);
        try
        {
            listes = mappingRMI.lireListeContacts();

            listeContacts = listes.elementAt(0);
            listeColonnes = listes.elementAt(1);

            jspRetour = "/jspListe.jsp";
            session.setAttribute("listeContacts", listeContacts);
            session.setAttribute("listeColonnes", listeColonnes);
        } catch (SQLException e)
        {
            jspRetour = "/jspAccueil.jsp";
            session.setAttribute("message", e.getMessage());
            session.setAttribute("numeroContact", "");
            session.setAttribute("choixAction", "liste");
        }

        return jspRetour;
    }

// --------------------------------------------------------------------------
// Traitement d'affichage de l'ecran de modification
// --------------------------------------------------------------------------
    public String traitementModif(HttpServletRequest request) throws RemoteException
    {
        String jspRetour;

        Contact contact;
        Integer numeroContact;
        Vector<Secteur> vSect;
        Vector<Vector> listesSecteur;
        HttpSession session = request.getSession();

        AccesBase accesBase;
        ContactDAO contactDAO;
        SecteurDAO secteurDAO;

        String chaineNumeroContact = request.getParameter("numeroContact");

//        accesBase = new AccesBase(base);
//
//        try
//        {
//            accesBase.getConnection();
//            contactDAO = new ContactDAO(accesBase);
//            secteurDAO = new SecteurDAO(accesBase);
        try
        {
            numeroContact = Integer.parseInt(chaineNumeroContact);
            contact = new Contact();
            contact.setNumero(numeroContact);
            mappingRMI.modifierContact(contact);
            
            listesSecteur = mappingRMI.lireListeSecteurs();
            vSect = listesSecteur.elementAt(0);

            //vSect = secteurDAO.lireListe();

            jspRetour = "/jspModif.jsp";
            session.setAttribute("message", "");
            session.setAttribute("contact", contact);
            session.setAttribute("vSect", vSect);
        } catch (NumberFormatException e)
        {
            jspRetour = "/jspAccueil.jsp";
            session.setAttribute("message", e.getMessage());
            session.setAttribute("numeroContact", chaineNumeroContact);
            session.setAttribute("choixAction", "modification");
        } catch (SQLException e)
        {
            jspRetour = "/jspAccueil.jsp";
            session.setAttribute("message", e.getMessage());
            session.setAttribute("numeroContact", chaineNumeroContact);
            session.setAttribute("choixAction", "modification");
        }
//            finally
//            {
//                accesBase.closeConnection();
//            }

        return jspRetour;
    }

// --------------------------------------------------------------------------
// Traitement d'affichage du message non realise sur l'ecran d'accueil
// --------------------------------------------------------------------------
    public String traitementNonRealise(HttpServletRequest request)
    {
        String jspRetour;
        HttpSession session = request.getSession();

        String choixAction = request.getParameter("choixAction");
        String chaineNumeroContact = request.getParameter("numeroContact");

        jspRetour = "/jspAccueil.jsp";
        session.setAttribute("message",
                "Ecran de " + choixAction + " non réalisé");
        session.setAttribute("choixAction", choixAction);
        session.setAttribute("numeroContact", chaineNumeroContact);

        return jspRetour;
    }
}
