import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Connection.Connect;
import Connection.LoginInfo;

public class ChangePass extends JFrame{

	JLabel chg;
	JPanel mid,atas,bwh;
	JLabel chgpass, chgpass2, old;
	JPasswordField chgpas, chgpas2, oldp;
	JButton update;
	LoginInfo log;
	public static void main(){
		new ChangePass();
	}
	
	public ChangePass(){
		setTitle("Setting");
		setSize(400,300);
		
		chg = new JLabel("CHANGE PASSWORD");
		
		
		atas = new JPanel();
		mid = new JPanel(new GridLayout(3, 2));
		bwh = new JPanel();
		
		chgpass = new JLabel("Enter old password");
		chgpass2 = new JLabel("Enter new password");
		old = new JLabel("Confirm password");
		
		chgpas = new JPasswordField();
		chgpas2 = new JPasswordField();
		oldp = new JPasswordField();
		
		update = new JButton("UPDATE");
		atas.add(chg);
		mid.add(chgpass);
		mid.add(chgpas);
		mid.add(chgpass2);
		mid.add(chgpas2);
		mid.add(old);
		mid.add(oldp);
		bwh.add(update);
		add(atas, BorderLayout.NORTH);
		add(mid, BorderLayout.CENTER);
		add(bwh, BorderLayout.SOUTH);
		
		update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				log = new LoginInfo();
				String oldpass = chgpass.getText();
				String newpass = chgpas.getText();
				String conpass = chgpas2.getText();
				String old = log.getPassword();
		
				if(oldpass.equals(old)){
					JOptionPane.showConfirmDialog(null, "Password harus sama seperti passwordnya sebelumnya", "Error Message", JOptionPane.ERROR_MESSAGE);
				}else if(newpass.length() < 10){
					JOptionPane.showConfirmDialog(null,"Password harus lebih dari 10", "Error Message", JOptionPane.ERROR_MESSAGE);
				}else if(newpass.equals(conpass)){
					JOptionPane.showConfirmDialog(null, "Confirm password harus sama dengan new password!", "Error Message", JOptionPane.ERROR_MESSAGE);
				}else{
					String query = "update user set Password = ? where UserID = ?";
					try {
						PreparedStatement ps = Connect.getInstance().con.prepareStatement(query);
						ps.setString(1, newpass);
						ps.setString(2, log.getUserID());
						ps.execute();
						JOptionPane.showMessageDialog(null, "Success to update!");
						dispose();
						new MenuUser();
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				
			}
		});
		
		
		
		setVisible(true);
	}
	
}
