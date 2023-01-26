import java.awt.Color;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Profile implements ActionListener {

    public void getProfile(String userId, String userPassword) {

        JPanel profilePanel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(350, 350);
        frame.setResizable(false);
        frame.setTitle("Profile");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(profilePanel);

        profilePanel.setLayout(null);
        Color color = new Color(250,230,230);
        profilePanel.setBackground(color);

        String url = "jdbc:mysql://127.0.0.1:3306/practice-db";
        String userName = "root";
        String password = "Arindam#1091";
        try {
            Connection con = DriverManager.getConnection(url,userName,password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select*from employee_db");
            while(rs.next()) {
                if(rs.getString(1).equals(userId) && rs.getString(2).equals(userPassword)) {

                    String emailId = rs.getString(1);
                    String eid = rs.getString(5);

                    JLabel eIdlabel = new JLabel("Employee ID.:");
                    eIdlabel.setBounds(10,20,90,25);
                    profilePanel.add(eIdlabel);
                    JLabel eId = new JLabel();
                    eId.setBounds(110,20,90,25);
                    eId.setText(rs.getString(5));
                    profilePanel.add(eId);

                    JLabel namelabel = new JLabel("Name:");
                    namelabel.setBounds(10,50,90,25);
                    profilePanel.add(namelabel);
                    JLabel name = new JLabel();
                    name.setBounds(110,50,90,25);
                    name.setText(rs.getString(4));
                    profilePanel.add(name);

                    JLabel agelabel = new JLabel("Age:");
                    agelabel.setBounds(10,80,90,25);
                    profilePanel.add(agelabel);
                    JLabel age = new JLabel();
                    age.setBounds(110,80,90,25);
                    age.setText(rs.getString(3));
                    profilePanel.add(age);

                    JLabel emaillabel = new JLabel("Email Id.:");
                    emaillabel.setBounds(10,110,90,25);
                    profilePanel.add(emaillabel);
                    JLabel email = new JLabel();
                    email.setBounds(110,110,190,25);
                    email.setText(rs.getString(1));
                    profilePanel.add(email);

                    JLabel addresslabel = new JLabel("Address:");
                    addresslabel.setBounds(10,140,90,25);
                    profilePanel.add(addresslabel);
                    JLabel address = new JLabel();
                    address.setBounds(110,140,90,25);
                    address.setText(rs.getString(6));
                    profilePanel.add(address);

                    JLabel departmentlabel = new JLabel("Department:");
                    departmentlabel.setBounds(10,170,90,25);
                    profilePanel.add(departmentlabel);
                    JLabel department = new JLabel();
                    department.setBounds(110,170,90,25);
                    department.setText(rs.getString(7));
                    profilePanel.add(department);

                    JLabel dseignamtionlabel = new JLabel("Designation:");
                    dseignamtionlabel.setBounds(10,200,90,25);
                    profilePanel.add(dseignamtionlabel);
                    JLabel dseignamtion = new JLabel();
                    dseignamtion.setBounds(110,200,90,25);
                    dseignamtion.setText(rs.getString(8));
                    profilePanel.add(dseignamtion);

                    JLabel salarylabel = new JLabel("Basic Salary:");
                    salarylabel.setBounds(10,230,90,25);
                    profilePanel.add(salarylabel);
                    JLabel salary = new JLabel();
                    salary.setBounds(110,230,90,25);
                    salary.setText(rs.getString(9));
                    profilePanel.add(salary);

                    JButton closeButton = new JButton("Close");
                    closeButton.setBounds(20,270,80,25);
                    closeButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            try {
                                rs.close();
                                con.close();
                                frame.dispose();
                            } catch (SQLException e1) {
                                System.exit(0);
                            }
                        }
                    });
                    profilePanel.add(closeButton);

                    JButton updateButton = new JButton("Edit");
                    updateButton.setBounds(120,270,80,25);
                    updateButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            try {
                                new Update(eid, emailId);
                            } catch(Exception E){
                                System.exit(0);
                            }
                        }
                    });
                    profilePanel.add(updateButton);

                    JButton deleteButton = new JButton("Delete");
                    deleteButton.setBounds(220,270,80,25);
                    deleteButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            try {
                                int result = JOptionPane.showOptionDialog(frame,"Sure, you want to delete your account?","Delete Account", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,new Object[] {"Yes", "No"}, JOptionPane.NO_OPTION);
                                if(result == JOptionPane.YES_OPTION) {
                                    String values = "DELETE FROM `practice-db`.`employee_db` WHERE (`email_id` = '"+emailId+"') and (`Employee_id` = '"+eid+"');";
                                    int x = stmt.executeUpdate(values);
                                    if (x > 0) {
                                        JOptionPane.showMessageDialog(frame, "Successfully Deleted!", "Delete Account", JOptionPane.PLAIN_MESSAGE);
                                        frame.remove(profilePanel);
                                    }
                                }
                                else if(result == JOptionPane.NO_OPTION) {
                                    System.exit(0);
                                }
                            } catch(Exception E){
                                System.out.println(E);
                                System.exit(0);
                            }
                        }
                    });
                    profilePanel.add(deleteButton);
                }
            }
            
        } catch(SQLException e) {
            System.exit(0);
        }
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {}
}
