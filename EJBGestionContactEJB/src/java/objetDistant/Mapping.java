/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetDistant;

import daoJdbcMapping.*;
import java.sql.SQLException;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.naming.*;
import javax.sql.DataSource;
import metierMapping.*;
import utilitairesMG.divers.Colonne;
import utilitairesMG.jdbc.*;

/**
 *
 * @author afpa1797
 */
@Stateless
public class Mapping implements MappingRemote
{

    private BaseDeDonnees base;
    private DataSource ds;

    @PostConstruct
    public void ouvreBase()
    {
        base = new BaseDeDonnees();
        base.setFormatDate("dd/MM/yyyy");
        try
        {
            Hashtable variablesEnv = new Hashtable();
            variablesEnv.put("org.omg.CORBA.ORBInitialHost", "localhost");
            variablesEnv.put("org.omg.CORBA.ORBInitialPort", "3700");
            Context ctx = new InitialContext(variablesEnv);
            ds = (DataSource) ctx.lookup("jndiPoolGnmi");
            System.out.println("DS serveur :" + ds);

        } catch (NamingException e)
        {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public metierMapping.Contact lireContact(Integer numeroContact) throws SQLException
    {
        Contact contact;
        AccesBase accesBase;
        ContactDAO contactDAO;

        accesBase = new AccesBase(base);
        contactDAO = new ContactDAO(accesBase);

        accesBase.setConnection(ds.getConnection());
  

        try
        {
            contact = new Contact();
            contact.setNumero(numeroContact);
            contactDAO.lire(contact);
        } finally
        {
            accesBase.closeConnection();
        }
        return contact;
    }

    @Override
    public Vector<Vector> lireListeContacts() throws SQLException
    {
        Vector<Vector> resultat;
        Vector<Contact> listeContacts;
        Vector<Colonne> listeColonnes;
        AccesBase accesBase;
        ContactDAO contactDAO;

        accesBase = new AccesBase(base);
        contactDAO = new ContactDAO(accesBase);

        accesBase.setConnection(ds.getConnection());
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
    public int modifierContact(metierMapping.Contact contact) throws SQLException
    {
        int retour;
        AccesBase accesBase;
        ContactDAO contactDAO;

        accesBase = new AccesBase(base);
        contactDAO = new ContactDAO(accesBase);;

// --------------------------------------------------------------------------
// Modification du contact
// --------------------------------------------------------------------------
        accesBase.setConnection(ds.getConnection());
        try
        {
            retour = contactDAO.modifier(contact);
        } finally
        {
            accesBase.closeConnection();
        }

        return retour;
    }

    @Override
    public Vector<Vector> lireListeSecteurs() throws SQLException
    {
        Vector<Vector> resultat;
        Vector<Secteur> listeSecteurs;
        Vector<Colonne> listeColonnes;
        AccesBase accesBase;
        SecteurDAO secteurDAO;

        accesBase = new AccesBase(base);
        secteurDAO = new SecteurDAO(accesBase);

        accesBase.setConnection(ds.getConnection());
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
