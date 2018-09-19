
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.Random;
import objetDistant.CompteurSFRemote;
import objetDistant.CompteurSLRemote;

public class Fenetre extends JFrame implements ActionListener
{

    private JPanel panneauFond;
    private JPanel panneauHaut;
    private JPanel panneauBas;
    private JTextField afficheNum;
    private JTextField afficheText;
    private JButton tirage;
    private JButton majStateless;
    private JButton afficheStateless;
    private JButton majStateful;
    private JButton afficheStateful;
    
    
    

    public Fenetre(String s)
    {
        super(s);
        addWindowListener(new EcouteWindowClosing());

        panneauFond = new JPanel();
        panneauFond.setLayout(new BorderLayout());
        panneauFond.setPreferredSize(new Dimension(300, 500));

        panneauHaut = new JPanel();
        panneauHaut.setLayout(new GridLayout(2,1));
        panneauHaut.setBackground(Color.GRAY);        
        

        afficheNum = new JTextField("", 50);
        afficheNum.setEditable(false);
        afficheNum.setBackground(Color.GRAY); 

        afficheText = new JTextField("", 5);
        afficheText.setEditable(false);
        afficheText.setBackground(Color.BLACK);
        afficheText.setForeground(Color.WHITE);

        panneauHaut.add(afficheNum);
        panneauHaut.add(afficheText);

        panneauBas = new JPanel();
        panneauBas.setLayout(new GridLayout(5,1));
        
        tirage = new JButton("TIRAGE");
        tirage.addActionListener(this);
        majStateless = new JButton("MISE A JOUR Stateless");
        majStateless.addActionListener(this);
        afficheStateless = new JButton("AFFICHAGE Stateless");
        afficheStateless.addActionListener(this);
        majStateful = new JButton("MISE A JOUR Stateful");
        majStateful.addActionListener(this);
        afficheStateful = new JButton("AFFICHAGE stateful");
        afficheStateful.addActionListener(this);
        
        panneauBas.add(tirage);
        panneauBas.add(majStateless);
        panneauBas.add(afficheStateless);
        panneauBas.add(majStateful);
        panneauBas.add(afficheStateful);

        panneauFond.add(panneauHaut, BorderLayout.NORTH);
        panneauFond.add(panneauBas);

        getContentPane().add(panneauFond);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == tirage){
           afficheNum.setText(Controleur.tirage());           
        }
        if(e.getSource() == majStateless){
            Controleur.majStateless();            
        }
        if(e.getSource() == afficheStateless){            
            afficheText.setText(Controleur.afficheStateless());
        }
        if(e.getSource() == majStateful){
            Controleur.majStateful();
        }
        if(e.getSource() == afficheStateful){
            afficheText.setText(Controleur.afficheStateful());
        }
    }

    private class EcouteWindowClosing extends WindowAdapter
    {

        public void WindowClosing(WindowEvent e)
        {
            Controleur.arreter();
        }
    }

}
