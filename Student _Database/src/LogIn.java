import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import java.sql.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LogIn implements ActionListener {

    private static JLabel userlabel;
    private static JTextField userText;
    private static JLabel passwordLabel;
    private static JPasswordField passwordText;
    private static JButton logInButton;
    private static JButton cancelButton;
    private static JButton registerButton;
    private static JLabel success;

    static public boolean dataCheck(String emailId, String userPassword) {
        String url = "jdbc:mysql://localhost:3306/practice-db";
        String userName = "root";
        String password = "Arindam#1091";
        boolean check = false;
        try {
            Connection con = DriverManager.getConnection(url,userName,password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select*from employee_db");
            while(rs.next()){

                if(emailId.equals(rs.getString(1)) && userPassword.equals(rs.getString(2))){
                    check =  true;
                    break;
                }
            }
            con.close();

        } catch(SQLException e){
            System.out.println("Unexpected: "+e);
            e.printStackTrace();
        }
        return check;
    }

    public LogIn() {
        JPanel loginPanel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(350, 300);
        frame.setResizable(false);
        frame.setTitle("Log In");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(loginPanel);
        
        loginPanel.setLayout(null);
        Color color = new Color(250,230,230);
        loginPanel.setBackground(color);
        
        userlabel = new JLabel("User");
        userlabel.setBounds(10,20,80,25);
        loginPanel.add(userlabel);
        
        userText = new JTextField();
        userText.setBounds(100,20,165,25);
        loginPanel.add(userText);
    
        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10,50,80,25);
        loginPanel.add(passwordLabel);
    
        passwordText = new JPasswordField();
        passwordText.setBounds(100,50,165,25);
        loginPanel.add(passwordText);
    
        logInButton = new JButton("Log In");
        logInButton.setBounds(110,100,80,25);
        logInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
        
                String user = userText.getText();
                String password = passwordText.getText();
        
                if(dataCheck(user,password) == true) {
                    Profile pf = new Profile();
                    pf.getProfile(user, password);
                }
                else {
                    success.setText("Invalid user name or password!");
                }
            }
        });
        loginPanel.add(logInButton);
    
        cancelButton = new JButton("Close");
        cancelButton.setBounds(20,100,80,25);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        loginPanel.add(cancelButton);

        registerButton = new JButton("Register");
        registerButton.setBounds(200,100,100,25);
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Register();
            }
        });
        loginPanel.add(registerButton);

        success = new JLabel();
        success.setBounds(10, 150, 300, 25);
        loginPanel.add(success);

        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new LogIn();
 
    }
    @Override
    public void actionPerformed(ActionEvent e) {}
}

