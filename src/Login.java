import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Connection.LoginInfo;
import Connection.Connect;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Login extends JFrame implements ActionListener{
	
	JPanel utm;
	JButton reg, log;
	LoginInfo info;
	void display(){
		setTitle("Cadbuly Shop");
		setSize(400,200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		utm = new JPanel();
		utm.setBackground(Color.cyan);
		utm.setLayout(new GridLayout(3, 1));
		
		JLabel sign = new JLabel("SIGN IN");
		sign.setForeground(Color.WHITE);
		sign.setFont(new Font("Arial", Font.BOLD, 14));
		utm.add(sign);
		
		log();
		btnLog();
		
		this.add(utm);
		setVisible(true);
	}
	
	JLabel email, pass;
	JTextField emailTF; JPasswordField passTF;
	
	void log(){
		JPanel mid = new JPanel(new GridLayout(2, 2));
		mid.setBackground(new Color(64,54,51));
		
		email = new JLabel("Email:");
		email.setForeground(Color.WHITE);
		pass = new JLabel("Password:");
		pass.setForeground(Color.WHITE);
		
		emailTF = new JTextField();
		emailTF.setPreferredSize(new Dimension(100,20));
		passTF = new JPasswordField();
		passTF.setPreferredSize(new Dimension(100,20));
		
		mid.add(email);
		mid.add(emailTF);
		mid.add(pass);
		mid.add(passTF);
		utm.add(mid);
	}
	
	void btnLog(){
		JPanel bot = new JPanel();
		bot.setBackground(new Color(64,54,51));
		
		reg = new JButton("Register");
		log = new JButton("Login");
		
		bot.add(reg, BorderLayout.WEST);
		bot.add(log, BorderLayout.EAST);
		utm.add(bot);
		
		reg.addActionListener(this);
		log.addActionListener(this);
	}
	
	public Login(){
		display();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String email = emailTF.getText();
		String pass = passTF.getText();
		
		if(arg0.getSource() == reg){
			new Register();
			dispose();
		}else if(email.equals("")){
			JOptionPane.showMessageDialog(null,   "Email tidak boleh kosong", "Error Message", JOptionPane.ERROR_MESSAGE);
		}else if(pass.equals("")){
			JOptionPane.showMessageDialog(null,   "Password tidak boleh kosong", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
		else{
			final String QUERY ="SELECT * FROM user WHERE Email =? AND Password =?";
			
			try {
				PreparedStatement ps = Connect.getInstance().con.prepareStatement(QUERY);
				ps.setString(1, email);
				ps.setString(2, pass);
				
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()) {
					info = new LoginInfo();
					info.setUserID(rs.getString("userID"));
					info.setName(rs.getString("Name"));
					info.setEmail(rs.getString("Email"));
					info.setPassword(rs.getString("Password"));
					info.setBirth(rs.getString("Birthday"));
					info.setGender(rs.getString("gender"));
					
					//Main Form
					JOptionPane.showConfirmDialog(null, "Welcome "+info.getEmail()+" !");
					new MenuUser();
					dispose();
					
					
				}
				else {
					JOptionPane.showConfirmDialog(null,"Username or Password are wrong!");
				}
				
			} catch (SQLException el) {
				el.printStackTrace();
			}
		}
		
	}

}
