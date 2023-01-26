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

public class Update implements ActionListener {

    JPanel updatePanel;
    static JLabel success;
    static JLabel alert;
   

    static public void setupdate(String eId, String emailId, String userPassword, String confirmPassword, String name, String age, String address, String department, String designation, String basicSalary) {
        String url = "jdbc:mysql://localhost:3306/practice-db";
        String userName = "root";
        String password = "Arindam#1091";

        try {
            Connection con = DriverManager.getConnection(url,userName,password);
            Statement stmt = con.createStatement();

            if(userPassword.length()<8) {
                alert.setBounds(380,50,210,25);
                alert.setText("Password is too short!");
            }
            else if(!(userPassword.equals(confirmPassword))) {
                alert.setBounds(380,80,210,25);
                alert.setText("Password mismatch!");
            }
            else {
                String values = "UPDATE `practice-db`.`employee_db` SET `password` = '"+userPassword+"', `age` = '"+age+"', `name` = '"+name+"', `address` = '"+address+"', `department` = '"+department+"', `designation` = '"+designation+"', `basic_salary` = '"+basicSalary+"' WHERE (`email_id` = '"+emailId+"') and (`Employee_id` = '"+eId+"')";
    
                int x = stmt.executeUpdate(values);
    
                if(x > 0) {
                    success.setBounds(20, 340, 400, 25);
                    success.setText("Successfully uptaed!");
                }
                else {
                    alert.setBounds(20, 340, 400, 25);
                    alert.setText("Failed to update!");
                }
            }
            con.close();
        } catch(SQLException e){
            alert.setBounds(20, 340, 400, 25);
            alert.setText("Unexpected: "+e);
        }
    }

    public Update(String eId,String emailId) {
        JTextField passwordText = new JPasswordField();
        JTextField confirmpasswordText = new JTextField();
        JTextField nameText = new JTextField();
        JTextField ageText = new JTextField();
        JTextField AddressText = new JTextField();
        JTextField departmentText = new JTextField();
        JTextField designationText = new JTextField();
        JTextField basicSalaryText = new JTextField();
        try {
            String url = "jdbc:mysql://localhost:3306/practice-db";
            String userName = "root";
            String password = "Arindam#1091";

            Connection con = DriverManager.getConnection(url,userName,password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select*from employee_db");
            while(rs.next()){
                
                if(emailId.equals(rs.getString(1))) {

                    passwordText.setText(rs.getString(2));
                    confirmpasswordText.setText(rs.getString(2));
                    nameText.setText(rs.getString(4));
                    ageText.setText(rs.getString(3));
                    AddressText.setText(rs.getString(6));
                    departmentText.setText(rs.getString(7));
                    designationText.setText(rs.getString(8));
                    basicSalaryText.setText(rs.getString(9));
                    break;
                }
            }
            rs.close(); 
            con.close();

            updatePanel = new JPanel();
            JFrame frame = new JFrame();
            frame.setSize(600, 500);
            frame.setResizable(false);
            frame.setTitle("Update Form");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(updatePanel);
    
            updatePanel.setLayout(null);
            Color color = new Color(250,230,230);
            updatePanel.setBackground(color);
    
            JLabel userlabel = new JLabel("Email Id.:");
            userlabel.setBounds(20,20,150,25);
            updatePanel.add(userlabel);
            
            JLabel userText = new JLabel();
            userText.setBounds(210,20,165,25);
            userText.setText(emailId);
            updatePanel.add(userText);

            JLabel eidText = new JLabel();
            eidText.setBounds(390,20,80,25);
            eidText.setText("Employee Id.:");
            updatePanel.add(eidText);

            JLabel eidlabel = new JLabel();
            eidlabel.setBounds(480,20,65,25);
            eidlabel.setText(eId);
            updatePanel.add(eidlabel);
    
            JLabel passwordlabel = new JLabel("Password:");
            passwordlabel.setBounds(20,50,150,25);
            updatePanel.add(passwordlabel);
            
            passwordText.setBounds(210,50,165,25);
            updatePanel.add(passwordText);
    
            JLabel confirmpasswordlabel = new JLabel("Confirm Password:");
            confirmpasswordlabel.setBounds(20,80,150,25);
            updatePanel.add(confirmpasswordlabel);
            
            confirmpasswordText.setBounds(210,80,165,25);
            updatePanel.add(confirmpasswordText);
    
            JLabel namelabel = new JLabel("Name:");
            namelabel.setBounds(20,110,150,25);
            updatePanel.add(namelabel);
            
            nameText.setBounds(210,110,165,25);
            updatePanel.add(nameText);
    
            JLabel agelabel = new JLabel("Age:");
            agelabel.setBounds(20,140,150,25);
            updatePanel.add(agelabel);
            
            ageText.setBounds(210,140,165,25);
            updatePanel.add(ageText);
    
            JLabel Addresslabel = new JLabel("City:");
            Addresslabel.setBounds(20,170,150,25);
            updatePanel.add(Addresslabel);
            
            AddressText.setBounds(210,170,165,25);
            updatePanel.add(AddressText);
    
            JLabel departmentlabel = new JLabel("Department:");
            departmentlabel.setBounds(20,200,150,25);
            updatePanel.add(departmentlabel);
            
            departmentText.setBounds(210,200,165,25);
            updatePanel.add(departmentText);
    
            JLabel designationlabel = new JLabel("Designation:");
            designationlabel.setBounds(20,230,150,25);
            updatePanel.add(designationlabel);
            
            designationText.setBounds(210,230,165,25);
            updatePanel.add(designationText);
    
            JLabel basicSalarylabel = new JLabel("Basic Salary:");
            basicSalarylabel.setBounds(20,260,150,25);
            updatePanel.add(basicSalarylabel);
            
            basicSalaryText.setBounds(210,260,165,25);
            updatePanel.add(basicSalaryText);
    
            JButton submitButton = new JButton("Submit");
            submitButton.setBounds(300,310,80,25);
            submitButton.addActionListener((ActionListener) new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setupdate(eId, emailId, passwordText.getText(), confirmpasswordText.getText(), nameText.getText(), ageText.getText(), AddressText.getText(), departmentText.getText(), designationText.getText(), basicSalaryText.getText());
                }
            });
            updatePanel.add(submitButton);
    
            JButton cancelButton = new JButton("Cancel");
            cancelButton.setBounds(200,310,80,25);
            cancelButton.addActionListener((ActionListener) new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(false);
                }
            });
            updatePanel.add(cancelButton);
    
            success = new JLabel();
            success.setBounds(20, 340, 400, 25);
            success.setForeground(Color.BLUE);
            updatePanel.add(success);
    
            alert = new JLabel();
            alert.setBounds(20, 340, 400, 25);
            alert.setForeground(Color.RED);
            updatePanel.add(alert);
    
            frame.setVisible(true);
        } catch(Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {}
}
