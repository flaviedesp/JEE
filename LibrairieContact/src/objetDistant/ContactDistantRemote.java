/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetDistant;

import java.sql.SQLException;
import java.util.Vector;
import javax.ejb.Remote;
import metierMapping.*;


@Remote
public interface ContactDistantRemote
{
    public Contact lireContact(Integer numero) throws SQLException;
    
    public Vector<Vector> lireListeContact() throws SQLException;
    
    public Vector<Vector> lireListeSecteur() throws SQLException;
}
