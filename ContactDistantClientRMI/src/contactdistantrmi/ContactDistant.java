
package contactdistantrmi;

import java.rmi.*;
import java.sql.SQLException;
import metierMapping.Contact;

public interface ContactDistant extends Remote
{
    public Contact lireContact(int num) throws RemoteException, SQLException;
}
