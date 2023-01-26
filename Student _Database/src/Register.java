import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Register implements ActionListener {

    JPanel registerPanel;
    static JLabel success;
    static JLabel alert;
    static int id;

    static public void dataEntry(String emailId, String userPassword, String confirmPassword, String name, String age, String address, String department, String designation, String basicSalary) {
        String url = "jdbc:mysql://localhost:3306/practice-db";
        String userName = "root";
        String password = "Arindam#1091";
        boolean check = false;
        try {
            Connection con = DriverManager.getConnection(url,userName,password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select*from employee_db");
            while(rs.next()){
                id++;
                if(rs.getString(1).equals(emailId)) check = true;
            } 
            
            String eId = "e"+id;

            if(emailId.equals(null)) {
                alert.setBounds(380,20,210,25);
                alert.setText("Email id cannot be blanked!");
            }
            else if(check == true) {
                alert.setBounds(380,20,210,25);
                alert.setText("Email already exists!");
            }
            else if(userPassword.length()<8) {
                alert.setBounds(380,50,210,25);
                alert.setText("Password is too short!");
            }
            else if(!(userPassword.equals(confirmPassword))) {
                alert.setBounds(380,80,210,25);
                alert.setText("Password mismatch!");
            }
            else {
                String values = "INSERT INTO `practice-db`.`employee_db` (`email_id`, `password`, `age`, `name`, `Employee_id`, `address`, `department`, `designation`, `basic_salary`) VALUES ('"+emailId+"', '"+userPassword+"', '"+age+"', '"+name+"', '"+eId+"', '"+address+"', '"+department+"', '"+designation+"', '"+basicSalary+"');";
    
                int x = stmt.executeUpdate(values);
    
                if(x > 0) {
                    success.setBounds(20, 340, 400, 25);
                    success.setText("Successfully registered!");
                }
                else {
                    alert.setBounds(20, 340, 400, 25);
                    alert.setText("Failed to register!");
                }
            }
            con.close();
        } catch(SQLException e){
            alert.setBounds(20, 340, 400, 25);
            alert.setText("Unexpected: "+e);
        }
    }

    public Register() {
        registerPanel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(600, 500);
        frame.setResizable(false);
        frame.setTitle("Register Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(registerPanel);

        registerPanel.setLayout(null);
        Color color = new Color(250,230,230);
        registerPanel.setBackground(color);

        JLabel userlabel = new JLabel("Email Id.:");
        userlabel.setBounds(20,20,150,25);
        registerPanel.add(userlabel);
        
        JTextField userText = new JTextField();
        userText.setBounds(210,20,165,25);
        registerPanel.add(userText);

        JLabel passwordlabel = new JLabel("Password:");
        passwordlabel.setBounds(20,50,150,25);
        registerPanel.add(passwordlabel);
        
        JTextField passwordText = new JPasswordField();
        passwordText.setBounds(210,50,165,25);
        registerPanel.add(passwordText);

        JLabel confirmpasswordlabel = new JLabel("Confirm Password:");
        confirmpasswordlabel.setBounds(20,80,150,25);
        registerPanel.add(confirmpasswordlabel);
        
        JTextField confirmpasswordText = new JTextField();
        confirmpasswordText.setBounds(210,80,165,25);
        registerPanel.add(confirmpasswordText);

        JLabel namelabel = new JLabel("Name:");
        namelabel.setBounds(20,110,150,25);
        registerPanel.add(namelabel);
        
        JTextField nameText = new JTextField();
        nameText.setBounds(210,110,165,25);
        registerPanel.add(nameText);

        JLabel agelabel = new JLabel("Age:");
        agelabel.setBounds(20,140,150,25);
        registerPanel.add(agelabel);
        
        JTextField ageText = new JTextField();
        ageText.setBounds(210,140,165,25);
        registerPanel.add(ageText);

        JLabel Addresslabel = new JLabel("City:");
        Addresslabel.setBounds(20,170,150,25);
        registerPanel.add(Addresslabel);
        
        JTextField AddressText = new JTextField();
        AddressText.setBounds(210,170,165,25);
        registerPanel.add(AddressText);

        JLabel departmentlabel = new JLabel("Department:");
        departmentlabel.setBounds(20,200,150,25);
        registerPanel.add(departmentlabel);
        
        JTextField departmentText = new JTextField();
        departmentText.setBounds(210,200,165,25);
        registerPanel.add(departmentText);

        JLabel designationlabel = new JLabel("Designation:");
        designationlabel.setBounds(20,230,150,25);
        registerPanel.add(designationlabel);
        
        JTextField designationText = new JTextField();
        designationText.setBounds(210,230,165,25);
        registerPanel.add(designationText);

        JLabel basicSalarylabel = new JLabel("Basic Salary:");
        basicSalarylabel.setBounds(20,260,150,25);
        registerPanel.add(basicSalarylabel);
        
        JTextField basicSalaryText = new JTextField();
        basicSalaryText.setBounds(210,260,165,25);
        registerPanel.add(basicSalaryText);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(300,310,80,25);
        submitButton.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dataEntry(userText.getText(), passwordText.getText(), confirmpasswordText.getText(), nameText.getText(), ageText.getText(), AddressText.getText(), departmentText.getText(), designationText.getText(), basicSalaryText.getText());
            }
        });
        registerPanel.add(submitButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(200,310,80,25);
        cancelButton.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
        registerPanel.add(cancelButton);

        success = new JLabel();
        success.setForeground(Color.BLUE);
        registerPanel.add(success);

        alert = new JLabel();
        alert.setForeground(Color.RED);
        registerPanel.add(alert);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {}
}
