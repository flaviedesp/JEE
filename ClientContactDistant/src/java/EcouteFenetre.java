
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author afpa1797
 */
public class EcouteFenetre implements WindowListener
{

    @Override
    public void windowOpened(WindowEvent e)
    {
        System.out.println("windowOpened (Event " + e.hashCode() + ")");
    }

    @Override
    public void windowClosing(WindowEvent e)
    {
        System.out.println("windowClosing (event " + e.hashCode() + ")");
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e)
    {
        System.out.println("windowClosed (event " + e.hashCode() + ")");
    }

    @Override
    public void windowIconified(WindowEvent e)
    {
        System.out.println("windowIconified (event " + e.hashCode() + ")");
    }

    @Override
    public void windowDeiconified(WindowEvent e)
    {
        System.out.println("windowDeiconified (event " + e.hashCode() + ")");
    }

    @Override
    public void windowActivated(WindowEvent e)
    {
        System.out.println("windowActivated (event " + e.hashCode() + ")");
    }

    @Override
    public void windowDeactivated(WindowEvent e)
    {
        System.out.println("windowDeactivated (event " + e.hashCode() + ")");
    }
    
}
