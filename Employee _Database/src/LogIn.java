import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;

import java.sql.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LogIn implements ActionListener {
    final private Font mainFont = new Font("Algerian", Font.BOLD, 18);
    private static JLabel userlabel;
    private static JTextField userText;
    private static JLabel passwordLabel;
    private static JPasswordField passwordText;
    private static JButton logInButton;
    private static JButton cancelButton;
    private static JButton registerButton;
    private static JLabel success;
    public JPanel loginPanel;
    public JFrame frame;
    public int count = 1;

    static public boolean dataCheck(String emailId, String userPassword) {
        String url = "jdbc:mysql://127.0.0.1:3306/practice-db";
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
        loginPanel = new JPanel();
        frame = new JFrame();
        frame.setSize(350, 300);
        frame.setResizable(false);
        frame.setTitle("Log In");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(loginPanel);
        
        loginPanel.setLayout(null);
        Color color = new Color(250,230,230);
        loginPanel.setBackground(color);
        
        userlabel = new JLabel("User Id");
        userlabel.setBounds(10,70,80,25);
        loginPanel.add(userlabel);
        
        userText = new JTextField();
        userText.setBounds(100,70,165,25);
        loginPanel.add(userText);
    
        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10,100,80,25);
        loginPanel.add(passwordLabel);
    
        passwordText = new JPasswordField();
        passwordText.setBounds(100,100,165,25);
        loginPanel.add(passwordText);
    
        logInButton = new JButton("Log In");
        logInButton.setBounds(110,150,80,25);
        logInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
        
                String user = userText.getText();
                String password =new String(passwordText.getPassword());
        
                if(dataCheck(user,password) == true) {
                    Profile pf = new Profile();
                    pf.getProfile(user, password);
                }
                else {
                    JOptionPane.showMessageDialog(frame, "Invalid User Id or Password!"+"\n"+"You only have "+(3-count)+" chances left", "Invalid", JOptionPane.PLAIN_MESSAGE);
                    if(count == 3){
                        JOptionPane.showMessageDialog(frame, "You have exceeded your limit!\n Try again later!", "Invalid", JOptionPane.PLAIN_MESSAGE);
                        logInButton.setEnabled(false);
                    }
                    count++;
                }
            }
        });
        loginPanel.add(logInButton);
    
        cancelButton = new JButton("Close");
        cancelButton.setBounds(20,150,80,25);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        loginPanel.add(cancelButton);

        registerButton = new JButton("Register");
        registerButton.setBounds(200,150,100,25);
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Register();
            }
        });
        loginPanel.add(registerButton);

        success = new JLabel("Log In");
        success.setBounds(130, 20, 300, 25);
        success.setFont(mainFont);
        success.setForeground(Color.BLUE);
        loginPanel.add(success);

        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new LogIn();
 
    }
    @Override
    public void actionPerformed(ActionEvent e) {}
}

