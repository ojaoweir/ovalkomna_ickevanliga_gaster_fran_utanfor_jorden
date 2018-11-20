package menus;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

import gui.GameWindow;
public class HelpScreen extends JComponent{
	/*
	 * Hjälpskärmen som beskriver för dig hur spelet fungerar
	 */
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GameWindow gameWindow;
    private JButton goBack;
    private JLabel helpLabel1;
    private JLabel helpLabel2;
    private JLabel helpLabel3;
    private JLabel helpLabel4;
    private JLabel helpLabel5;
   
    public  HelpScreen (final GameWindow inGameWindow) {
        this.gameWindow = inGameWindow;

        setBounds(800,300,(int) getPreferredSize().getWidth(),(int) getPreferredSize().getWidth()); //Placerar ut
        setLayout(new BorderLayout()); //Fixar layout
        setSize(new Dimension(500,300));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
       
        //Skriver ut hjälptext
        helpLabel1 = new JLabel("Förflyttning i sidleds med piltangenter.");
        helpLabel2 = new JLabel("Skjut med mellanslag.");
        helpLabel3 = new JLabel("Tryck ESC för PAUS under spelets gång.");
        helpLabel4 = new JLabel("Skjut fienderna, du förlorar om de når bottnen av skärmen.");
        helpLabel5 = new JLabel("Åk på en powerup för att få dess effekt.");
       
        add(helpLabel1);
        add(helpLabel2);
        add(helpLabel3);
        add(helpLabel4);
        add(helpLabel5);
       //Lägger till knapp så man kan gå tillbaka från hjälpskärm
        goBack = new JButton("Tillbaka");
        add(goBack);
       
        goBack.addActionListener( new ActionListener() {     //tar oss tillbaka till tidigare fönster
            @Override
            public void actionPerformed(ActionEvent e) {
                gameWindow.returnTo();               
            }
           
        });

        setVisible(true);
    }
}
