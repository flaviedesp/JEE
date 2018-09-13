
import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;
import utilitairesMG.jdbc.*;

public class ServletControleur extends HttpServlet
{

    private static AccesBase accesBase;
    private BaseDeDonnees base;

    public void init()
    {

        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        } catch (ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        base = new BaseDeDonnees("jdbc:sqlserver://mars;databasename=gnmi;"
                + "user=util_bip;password=x");
        accesBase = new AccesBase(base);

    }

    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServletException, IOException
    {
        Integer idEcran;

        String numContact;
        String listeContact;
        String choix;
        Integer numeroContact;
        String messageContact;

        ServletContext contexte;
        RequestDispatcher dispatcher;
        HttpSession session;

        request.setCharacterEncoding("UTF-8");

// --------------------------------------------------------------------------
// Recuperation du SerletContext pour dispatcher...
// --------------------------------------------------------------------------
        contexte = getServletContext();

// --------------------------------------------------------------------------
// Lecture de l'identifiant de l'ecran (champ cache ou parametre d'index.jsp)
// --------------------------------------------------------------------------
        String numeroEcran = request.getParameter("idEcran");
        idEcran = new Integer(numeroEcran);
        System.out.println("Ecran : " + idEcran);

// --------------------------------------------------------------------------
// Lecture du numero de contact saisie
// --------------------------------------------------------------------------
        switch (idEcran)
        {
            case 1:

                numContact = request.getParameter("numContact");

                session = request.getSession();

                choix = request.getParameter("choix");
                System.out.println("choix : " + choix);

                switch (choix)
                {
// --------------------------------------------------------------------------
// On vient de l'ecran accueil
// --------------------------------------------------------------------------
                    case "listeContact":
                        dispatcher = contexte.getRequestDispatcher(TraitementAccueil.traitementListe(session, accesBase));

                        break;

                    case "modification":
                        if (numContact.compareTo("") == 0)
                        {
                            messageContact = "Numéro contact non renseignée";
                            session.setAttribute("messageContact", messageContact);
                            dispatcher = contexte.getRequestDispatcher("/jspAccueil.jsp");
                        }
                        else
                        {
                            numeroContact = new Integer(numContact);
                            dispatcher = contexte.getRequestDispatcher(TraitementAccueil.traitementModification(session, accesBase, numeroContact));
                        }
                        break;

                    case "creation":

                        dispatcher = contexte.getRequestDispatcher(TraitementAccueil.traitementCreer(session, accesBase));
                        break;

                    case "suppression":
                        if (numContact.compareTo("") == 0)
                        {
                            messageContact = "Numéro contact non renseignée";
                            session.setAttribute("messageContact", messageContact);
                            dispatcher = contexte.getRequestDispatcher("/jspAccueil.jsp");
                        }
                        else
                        {
                            numeroContact = new Integer(numContact);
                            dispatcher = contexte.getRequestDispatcher(TraitementAccueil.traitementSupr(session, accesBase, numeroContact));
                        }
                        break;
                    default:
                        dispatcher = contexte.getRequestDispatcher("/jspAccueil.jsp");

                }
                break;

            case 3:

                dispatcher = contexte.getRequestDispatcher("/jspRecap.jsp");
                break;

            case 5:
                numContact = request.getParameter("numero");
                session = request.getSession();

                if (numContact.compareTo("") == 0)
                {
                    messageContact = "Numéro contact non renseignée";
                    session.setAttribute("messageContact", messageContact);
                    dispatcher = contexte.getRequestDispatcher("/jspAccueil.jsp");
                }
                else
                {
                    dispatcher = contexte.getRequestDispatcher("/jspRecap.jsp");
                }
                break;

            default:
                dispatcher = contexte.getRequestDispatcher("/jspAccueil.jsp");
// --------------------------------------------------------------------------
// Divers branchements suivant l'ecran qui vient d'appeler ServletControleur
// --------------------------------------------------------------------------
        }
        dispatcher.forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }
}
