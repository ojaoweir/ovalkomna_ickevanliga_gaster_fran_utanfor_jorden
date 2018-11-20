package menus;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ErrorWindow extends JFrame {
	/*
	 * Errorwindow som kommer om du inte har ett spel att ladda
	 */
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton close;
	
	public ErrorWindow(String message) { //Skapar ett fönster med givet felmeddelande
		super("ERROR");
		close = new JButton("Ok");
        setLayout(new BorderLayout());
        setSize(new Dimension(500, 300));
        setVisible(true);
        add(new JLabel(message),BorderLayout.CENTER);
        add(close,BorderLayout.SOUTH);
        

		close.addActionListener(new ActionListener() { //Gör så knapp stänger ner fönster
		      public void actionPerformed(ActionEvent e) { 
		    	  dispose();
		      } 
		    } );
	}

}
