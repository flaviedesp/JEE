package objetDistant;

import daoJdbcMapping.ContactDAO;
import daoJdbcMapping.SecteurDAO;
import java.sql.SQLException;
import java.util.Vector;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import metierMapping.Contact;
import metierMapping.Secteur;
import utilitairesMG.divers.Colonne;
import utilitairesMG.jdbc.AccesBase;
import utilitairesMG.jdbc.BaseDeDonnees;

@Stateless
public class ContactDistant implements ContactDistantRemote
{

    private BaseDeDonnees base;

    @PostConstruct 
    public void init()
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e)
        {
            System.out.println("Driver Inconnu : " + e.getMessage());
            System.exit(0);
        }
        base = new BaseDeDonnees("jdbc:sqlserver://mars;databasename=gnmi;"
                + "user=util_bip;password=x");
        base.setFormatDate("dd/MM/yyyy");
    }

    @Override
    public metierMapping.Contact lireContact(Integer numero) throws SQLException
    {
        Contact contact;
        AccesBase accesBase;
        ContactDAO contactDAO;

        accesBase = new AccesBase(base);
        contactDAO = new ContactDAO(accesBase);

        accesBase.getConnection();
        try
        {
            contact = new Contact();
            contact.setNumero(numero);
        } finally
        {
            accesBase.closeConnection();
        }
        return contact;
    }

    @Override
    public Vector<Vector> lireListeContact() throws SQLException
    {
        Vector<Vector> resultat;
        Vector<Contact> listeContacts;
        Vector<Colonne> listeColonnes;
        AccesBase accesBase;
        ContactDAO contactDAO;

        accesBase = new AccesBase(base);
        contactDAO = new ContactDAO(accesBase);

        accesBase.getConnection();
        try
        {
            listeContacts = contactDAO.lireListe();
            listeColonnes = contactDAO.getListeColonnes();

            resultat = new Vector<Vector>();
            resultat.addElement(listeContacts);
            resultat.addElement(listeColonnes);
        } finally
        {
            accesBase.closeConnection();
        }
        return resultat;
    }

    @Override
    public Vector<Vector> lireListeSecteur() throws SQLException
    {
        Vector<Vector> resultat;
        Vector<Secteur> listeSecteurs;
        Vector<Colonne> listeColonnes;
        AccesBase accesBase;
        SecteurDAO secteurDAO;

        accesBase = new AccesBase(base);
        secteurDAO = new SecteurDAO(accesBase);

        accesBase.getConnection();
        try
        {
            listeSecteurs = secteurDAO.lireListe();
            listeColonnes = secteurDAO.getListeColonnes();

            resultat = new Vector<Vector>();
            resultat.addElement(listeSecteurs);
            resultat.addElement(listeColonnes);
        } finally
        {
            accesBase.closeConnection();
        }
        return resultat;
    }

}
