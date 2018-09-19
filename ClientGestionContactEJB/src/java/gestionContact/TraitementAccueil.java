package gestionContact;

import java.sql.SQLException;
import java.util.Vector;
import javax.servlet.http.*;
import metierMapping.*;
import objetDistant.MappingRemote;
import utilitairesMG.divers.*;
import utilitairesMG.jdbc.*;

public class TraitementAccueil
{

    private BaseDeDonnees base;
    private MappingRemote mappingRemote;
    // private DataSource ds;

// --------------------------------------------------------------------------
// Constructeur
// --------------------------------------------------------------------------
    public TraitementAccueil(MappingRemote mappingRemote)
    {
        this.mappingRemote = mappingRemote;
    }

// --------------------------------------------------------------------------
// Traitement d'affichage de la liste
// --------------------------------------------------------------------------
    public String traitementListe(HttpServletRequest request)
    {
        String jspRetour;
        Vector<Vector> listeComplete;
        Vector<Contact> listeContacts;
        Vector<Colonne> listeColonnes;

        HttpSession session = request.getSession();

        try
        {
            listeComplete = mappingRemote.lireListeContacts();
            listeContacts = listeComplete.elementAt(0);
            listeColonnes = listeComplete.elementAt(1);

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
    public String traitementModif(HttpServletRequest request)
    {
        String jspRetour;

        Contact contact;
        Integer numeroContact;
        Vector<Secteur> vSect;
        HttpSession session = request.getSession();

        String chaineNumeroContact = request.getParameter("numeroContact");

        try
        {
            numeroContact = Integer.parseInt(chaineNumeroContact);
            contact = mappingRemote.lireContact(numeroContact);

            vSect = mappingRemote.lireListeSecteurs().elementAt(0);

            jspRetour = "/jspModif.jsp";
            session.setAttribute("message", "");
            session.setAttribute("contact", contact);
            session.setAttribute("vSect", vSect);
        } catch (SQLException e)
        {
            jspRetour = "/jspAccueil.jsp";
            session.setAttribute("message", e.getMessage());
            session.setAttribute("numeroContact", chaineNumeroContact);
            session.setAttribute("choixAction", "modification");
        }

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
