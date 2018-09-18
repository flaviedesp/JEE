/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetDistant;


import java.rmi.RemoteException;
import javax.ejb.Remote;


@Remote
public interface TrieurRemote
{
    public Comparable[] trier(Comparable tableau[]) throws RemoteException;
}
