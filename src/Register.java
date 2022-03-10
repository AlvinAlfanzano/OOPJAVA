import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Connection.Connect;
import Connection.LoginInfo;

public class Register extends JFrame implements ActionListener{
	
	JPanel utm;
	JPanel top, mid, bot;
	JLabel name, email, pass, cPass, gender, birth;
	JTextField nameTF, emailTF;
	JPasswordField passPF, passCPF; 
	JButton back, submit;
	JRadioButton male, female;
	JComboBox<Integer> day, month, year;
	JCheckBox check;
	ButtonGroup btGroup;
	
	void display(){
		setTitle("Cadbuly Shop");
		setSize(500,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		utm = new JPanel();
		utm.setBackground(new Color(64,54,51));
		
		JLabel crt = new JLabel("CREATE ACCOUNT");
		crt.setFont(new Font("Arial", Font.BOLD, 18));
		crt.setForeground(Color.WHITE);
		
		top = new JPanel();
		top.setBackground(new Color(64,54,51));
		top.add(crt);
		this.add(top, BorderLayout.NORTH);
		
		input();
		
		this.add(utm);
		setVisible(true);
	}
	
	void input(){
		mid = new JPanel(new GridLayout(6,2));
		mid.setBackground(new Color(64,54,51));
		
		name = new JLabel("Name");
		name.setPreferredSize(new Dimension(200,30));
		name.setForeground(Color.WHITE);
		
		nameTF = new JTextField();
		nameTF.setPreferredSize(new Dimension(150,30));
		
		mid.add(name);
		mid.add(nameTF);
		
		email = new JLabel("Email");
		email.setPreferredSize(new Dimension(200,30));
		email.setForeground(Color.WHITE);
		
		emailTF = new JTextField();
		emailTF.setPreferredSize(new Dimension(150,30));
		
		mid.add(email);
		mid.add(emailTF);

		pass = new JLabel("Password");
		pass.setPreferredSize(new Dimension(200,30));
		pass.setForeground(Color.WHITE);
		
		passPF = new JPasswordField();
		passPF.setPreferredSize(new Dimension(150,30));

		mid.add(pass);
		mid.add(passPF);
		
		cPass = new JLabel("Confirm Password");
		cPass.setPreferredSize(new Dimension(200,30));
		cPass.setForeground(Color.WHITE);
		
		passCPF = new JPasswordField();
		passCPF.setPreferredSize(new Dimension(150,30));
		
		mid.add(cPass);
		mid.add(passCPF);

		gender = new JLabel("Gender");
		gender.setPreferredSize(new Dimension(200,30));
		gender.setForeground(Color.WHITE);
		
		JPanel genderPanel = new JPanel(new FlowLayout());
		genderPanel.setBackground(new Color(64,54,51));

		male = new JRadioButton("Male");
		female = new JRadioButton("Female");
		btGroup = new ButtonGroup();
		btGroup.add(male);
		btGroup.add(female);
		male.setPreferredSize(new Dimension(75,30));
		female.setPreferredSize(new Dimension(75,30));
		
		mid.add(gender);
		genderPanel.add(male);
		genderPanel.add(female);
		mid.add(genderPanel);
		

		birth = new JLabel("Birthday");
		birth.setPreferredSize(new Dimension(200,30));
		birth.setForeground(Color.WHITE);
		
		day = new JComboBox<Integer>();
		day.setPreferredSize(new Dimension(50,20));
		for(int i = 1 ; i < 32 ;i++){
			day.addItem(i);
		}
		
		month = new JComboBox<Integer>();
		month.setPreferredSize(new Dimension(50,20));
		for(int i = 1 ; i < 13 ;i++){
			month.addItem(i);
		}
		
		year = new JComboBox<Integer>();
		year.setPreferredSize(new Dimension(100,20));
		for(int i = 1990 ; i < 2020 ;i++){
			year.addItem(i);
		}
		
		JPanel birthPanel = new JPanel();

		mid.add(birth);
		birthPanel.add(day);
		birthPanel.add(month);
		birthPanel.add(year);
		mid.add(birthPanel);

		utm.add(mid);
		
		bot = new JPanel();
		
		check = new JCheckBox("Terms and Condition");
		back = new JButton("Back");
		submit = new JButton("Submit");
		bot.add(check);
		bot.add(back);
		bot.add(submit);
		
		back.addActionListener(this);
		submit.addActionListener(this);
		
		utm.add(bot);
		
	}
	
	public Register(){
		display();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String getName,getEmail,getPass,getCPass,getBirth,getGender = "",userID,status;
		getName = nameTF.getText();
		getEmail = emailTF.getText();
		getPass = passPF.getText();
		getCPass = passCPF.getText();
		getBirth = year.getSelectedItem().toString();
		int setBirth = Integer.parseInt(getBirth);
		setBirth = 2019 - setBirth;
		if(male.isSelected()){
			getGender = "Male";
		}else if(female.isSelected()){
			getGender = "Female";
		}
		if(e.getSource() == back){
			dispose();
			new Login();
		}
		else if(getName.equals("") && getName.length() < 5 && getName.length() > 25){
			JOptionPane.showMessageDialog(null,
					"Nama tidak boleh kosong dan kurang dari 5 atau lebih dari 25 char", "Error Message", JOptionPane.ERROR_MESSAGE);
		}else if(getPass.equals("")){
			JOptionPane.showMessageDialog(null,
					"Password tidak boleh kosong", "Error Message", JOptionPane.ERROR_MESSAGE);
		}else if(getPass.length() < 10){
			JOptionPane.showMessageDialog(null,
					"Password tidak boleh kurang dari 10 char", "Error Message", JOptionPane.ERROR_MESSAGE);
		}else if(!getPass.equals(getCPass)){
			JOptionPane.showMessageDialog(null,
					"Password harus sama", "Error Message", JOptionPane.ERROR_MESSAGE);
		}else if(getGender.equals("")){
			JOptionPane.showMessageDialog(null,
					"Gender harus dipilih", "Error Message", JOptionPane.ERROR_MESSAGE);
		}else if(!check.isSelected()){
			JOptionPane.showMessageDialog(null,
					"Terms and condition must be checked", "Error Message", JOptionPane.ERROR_MESSAGE);
		}else if(setBirth < 18){
			JOptionPane.showMessageDialog(null,
					"Umur harus di atas 18 tahun","Error Message", JOptionPane.ERROR_MESSAGE);
		}else if(getEmail.startsWith("@") && getEmail.startsWith(".")){
			JOptionPane.showMessageDialog(null,
					"Email tidak boleh diawali dengan '@' atau '.'","Error Message", JOptionPane.ERROR_MESSAGE);
		}else if(getEmail.contains("@.")){
			JOptionPane.showMessageDialog(null,
					". tidak boleh bersebelahan dengan @","Error Message", JOptionPane.ERROR_MESSAGE);
		}else if(!getEmail.endsWith(".com")){
			JOptionPane.showMessageDialog(null,
					"Email harus di akhiri dengan .com","Error Message", JOptionPane.ERROR_MESSAGE);
		}else{
			try {
				final String QUERY ="insert into user (Name,Email,Password,Birthday,Gender,Status) values (?,?,?,?,?,?)";
				PreparedStatement ps = Connect.getInstance().con.prepareStatement(QUERY);
				
				ps.setString(1,  getName);
				ps.setString(2, getEmail);
				ps.setString(3, getPass);
				ps.setInt(4, setBirth);
				ps.setString(5, getGender);
				ps.setString(6, "User");
				ps.execute();
				
				JOptionPane.showMessageDialog(null, "You have successfully register!");
				dispose();
				new Login();
				
			}catch(SQLException el) {
				el.printStackTrace();
			}
		}
		
	}
}
