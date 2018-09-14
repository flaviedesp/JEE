package contactdistantrmi;

import daoJdbcMapping.ContactDAO;
import java.rmi.*;
import java.rmi.server.*;
import java.sql.SQLException;
import metierMapping.Contact;
import utilitairesMG.jdbc.*;

public class ContactDistantImpl extends UnicastRemoteObject implements ContactDistant
{

    private BaseDeDonnees base;

    public ContactDistantImpl() throws RemoteException
    {
        AccesBase accesBase;
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        } catch (ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        base = new BaseDeDonnees("jdbc:sqlserver://mars;databasename=gnmi;"
                + "user=util_bip;password=x");
    }

    
    public Contact lireContact(int num) throws RemoteException, SQLException
    {
        AccesBase accesBase;
        Contact contact;
        ContactDAO contactDAO;

        accesBase = new AccesBase(base);
        contact = new Contact();
        contactDAO = new ContactDAO(accesBase);
        try
        {
            accesBase.getConnection();
            try
            {
                contact.setNumero(num);
                contactDAO.lire(contact);

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
        return contact;
    }

    

}
