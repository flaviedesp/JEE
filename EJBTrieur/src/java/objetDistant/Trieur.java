/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetDistant;

import java.rmi.RemoteException;
import javax.ejb.Stateless;

@Stateless
public class Trieur implements TrieurRemote
{

    public Trieur() throws RemoteException
    {

    }

    @Override
    public Comparable[] trier(Comparable[] tableau) throws RemoteException
    {
        boolean permut;
        int n;
        Comparable x;

        do
        {
            permut = false;
            for (n = 0; n < tableau.length - 1; n++)
            {
                if (tableau[n].compareTo(tableau[n + 1]) > 0)
                {
                    x = tableau[n];
                    tableau[n] = tableau[n + 1];
                    tableau[n + 1] = x;
                    permut = true;
                }
            }
        }
        while (permut == true);

        return tableau;
    }

}
