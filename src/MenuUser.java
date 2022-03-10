import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Connection.LoginInfo;
import Connection.Connect;

public class MenuUser extends JFrame implements ActionListener {

	JMenuBar bar;
	JMenu store, profile;
	JLabel background;
	
	JMenuItem our, hampers, cart, about;
	JMenuItem chg,logout;
	LoginInfo log;
	
	public static void main(String[]args){
		new MenuUser();
	}
	
	public MenuUser() {
		log = new LoginInfo();
		
		setTitle("Cadbully Shop");
		setSize(800,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		bar = new JMenuBar();
		store = new JMenu("Store");
		profile = new JMenu("Profile");
		
		our = new JMenuItem("Our Products");
		cart = new JMenuItem("Cart");
		hampers = new JMenuItem("Hampers");
		about = new JMenuItem("About");
		
		chg = new JMenuItem("Change Password");
		logout = new JMenuItem("Logout");
		
		store.add(our);
		store.add(cart);
		store.add(hampers);
		store.add(about);
		profile.add(chg);
		profile.add(logout);
		
		bar.add(store);
		bar.add(profile);
		
		setJMenuBar(bar);
		
		background = new JLabel(new ImageIcon("chocolate_cup_coffee_morning_88253_240x320.jpg"));
		this.add(background, BorderLayout.CENTER);
		
		logout.addActionListener(this);
		chg.addActionListener(this);
		
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == logout){
			dispose();
			new Login();
		}else if(e.getSource() == chg){
			dispose();
			new ChangePass();
			
		}
		
	}


}
