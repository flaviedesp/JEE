/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetDistant;

import daoJdbcMapping.*;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.Vector;
import metierMapping.*;
import utilitairesMG.jdbc.*;
import java.rmi.*;
import java.rmi.server.*;
import utilitairesMG.divers.Colonne;

/**
 *
 * @author afpa1797
 */
public class MappingRMIImpl extends UnicastRemoteObject implements MappingRMI
{

    private BaseDeDonnees base;

    public MappingRMIImpl() throws RemoteException
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        } catch (ClassNotFoundException e)
        {
            System.out.println("Driver inconnu : " + e.getMessage());
        }
        base = new BaseDeDonnees("jdbc:sqlserver://mars;databasename=gnmi;"
                + "user=util_bip;password=x");
        base.setFormatDate("dd/MM/yyyy");
    }

    public Contact lireContact(Integer numero) throws RemoteException, SQLException
    {
        Contact contact;
        AccesBase accesBase;
        ContactDAO contactDAO;

        accesBase = new AccesBase(base);
        contactDAO = new ContactDAO(accesBase);

        try
        {
            System.out.println(getClientHost());
        } catch (ServerNotActiveException e)
        {
        }
        accesBase.getConnection();

        try
        {
            contact = new Contact();
            contact.setNumero(numero);
            contactDAO.lire(contact);
        } finally
        {
            accesBase.closeConnection();
        }
        return contact;
    }

    public Vector<Vector> lireListeContacts() throws RemoteException, SQLException
    {
        Vector<Contact> listeContact;
        Vector<Colonne> listeColonne;
        Vector<Vector> liste;
        ContactDAO contactDAO;
        AccesBase accesBase;

        accesBase = new AccesBase(base);
        contactDAO = new ContactDAO(accesBase);
        listeContact = new Vector<Contact>();
        listeColonne = new Vector<Colonne>();
        liste = new Vector<Vector>();

        accesBase.getConnection();
        try
        {

            listeContact = contactDAO.lireListe();
            listeColonne = contactDAO.getListeColonnes();

            liste.addElement(listeContact);
            liste.addElement(listeColonne);
        } finally
        {
            accesBase.closeConnection();
        }

        return liste;
    }

    public int modifierContact(Contact contact) throws RemoteException, SQLException
    {
        ContactDAO contactDAO;
        AccesBase accesBase;
        Integer retour;

        accesBase = new AccesBase(base);
        contactDAO = new ContactDAO(accesBase);

        accesBase.getConnection();
        try
        {
            retour = contactDAO.modifier(contact);
        } finally
        {
            accesBase.closeConnection();
        }

        return retour;
    }

    public Vector<Vector> lireListeSecteurs() throws RemoteException, SQLException
    {
        Vector<Secteur> listeSecteur;
        Vector<Colonne> listeColonne;
        Vector<Vector> liste;
        SecteurDAO secteurDAO;
        AccesBase accesBase;

        accesBase = new AccesBase(base);
        secteurDAO = new SecteurDAO(accesBase);
        listeSecteur = new Vector<Secteur>();
        listeColonne = new Vector<Colonne>();
        liste = new Vector<Vector>();

        accesBase.getConnection();
        try
        {

            listeSecteur = secteurDAO.lireListe();
            listeColonne = secteurDAO.getListeColonnes();

            liste.addElement(listeSecteur);
            liste.addElement(listeColonne);
        } finally
        {
            accesBase.closeConnection();
        }

        return liste;
    }

}
