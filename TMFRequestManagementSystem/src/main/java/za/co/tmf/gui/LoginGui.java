package za.co.tmf.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import static java.awt.FlowLayout.RIGHT;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import javax.swing.*;
import za.co.DAO.TmfDAO;
import za.co.tmf.domain.Admin;
import za.co.tmf.domain.Technician;

/**
 *
 * @author Mpumzi Mbula
 */
public class LoginGui extends JFrame implements ActionListener {

    private JPanel panelWest, panelEast, fieldsPanel, radioButtonsPanel, jButtonPanel, eastContentPanel;
    private JLabel signInLbl, logoLbl, employeeIdLbl, passwordLbl;
    private JPasswordField passwordTxt;
    private JTextField usernameTxt;
    private Font f1, f2;
    private JRadioButton technicianRBtn, adminRBtn;
    private JButton loginBtn;
    TmfDAO dao;
    Admin admin;

    //Constructor
    public LoginGui() {
        super("Login Page");

//Font Objects
        f1 = new Font("Arial", Font.BOLD, 18);
        f2 = new Font("Arial", Font.PLAIN, 12);

//panel Objects
        panelWest = new JPanel();
        panelWest.setBackground(Color.DARK_GRAY);

        panelEast = new JPanel();
        panelEast.setBackground(Color.white);

        eastContentPanel = new JPanel();

        fieldsPanel = new JPanel();
        fieldsPanel.setBackground(Color.white);

        radioButtonsPanel = new JPanel();
        radioButtonsPanel.setBackground(Color.white);

        jButtonPanel = new JPanel();
        jButtonPanel.setBackground(Color.white);

//Label Objects
        signInLbl = new JLabel("Sign In");
        signInLbl.setFont(f1);

        signInLbl.setHorizontalAlignment(RIGHT);

        ImageIcon tmfLogo = new ImageIcon("C:\\Users\\AI Bot2\\Documents\\NetBeansProjects\\TMFRequestManagementSystem\\TMF Image Resources\\TMFLOGO.jpg");
        logoLbl = new JLabel(tmfLogo);
        logoLbl.setOpaque(true);
        logoLbl.setBackground(Color.red);
        employeeIdLbl = new JLabel("Technician");//must set the value;
        passwordLbl = new JLabel("Password");

//Fields Objects
        passwordTxt = new JPasswordField();
        usernameTxt = new JTextField();

//RadioButtons
        technicianRBtn = new JRadioButton("Technician");
        adminRBtn = new JRadioButton("Admin");

//Buttons Objects
        loginBtn = new JButton("Sign In");
        loginBtn.setBackground(Color.blue);
        loginBtn.setForeground(Color.white);
        technicianRBtn.setSelected(true);

        //dao
        dao = new TmfDAO();

    }//end Constructor

    //Radio Button Group
    public ButtonGroup group1() {
        ButtonGroup group1 = new ButtonGroup();
        group1.add(technicianRBtn);
        group1.add(adminRBtn);
        return group1;
    }//end Radio ButtonGroup

    public void setGui() {

        panelWest.setPreferredSize(new Dimension(482, 300));
        fieldsPanel.setLayout(new GridLayout(5, 1));
        fieldsPanel.setPreferredSize(new Dimension(380, 200));
        panelEast.setLayout(new GridLayout(3, 1));
        panelEast.setPreferredSize(new Dimension(490, 450));
        radioButtonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        jButtonPanel.add(loginBtn);

        panelWest.add(logoLbl);

        radioButtonsPanel.add(technicianRBtn);
        radioButtonsPanel.add(adminRBtn);

        fieldsPanel.add(radioButtonsPanel);
        fieldsPanel.add(employeeIdLbl);
        fieldsPanel.add(usernameTxt);
        fieldsPanel.add(passwordLbl);
        fieldsPanel.add(passwordTxt);

        panelEast.add(signInLbl);
        panelEast.add(fieldsPanel);
        panelEast.add(jButtonPanel);
        eastContentPanel.add(panelEast);

        //ActionListeners
        loginBtn.addActionListener(this);
        technicianRBtn.addActionListener(this);
        adminRBtn.addActionListener(this);
        usernameTxt.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                usernameTxt.setBorder(BorderFactory.createLineBorder(Color.orange, 2));
            }

            @Override
            public void focusLost(FocusEvent e) {
                usernameTxt.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
            }

        });
        passwordTxt.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                passwordTxt.setBorder(BorderFactory.createLineBorder(Color.orange, 2));
            }

            @Override
            public void focusLost(FocusEvent e) {
                passwordTxt.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
            }

        });
        this.add(panelWest, BorderLayout.WEST);
        this.add(eastContentPanel, BorderLayout.EAST);

        this.setSize(1000, 450);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getRootPane().setDefaultButton(loginBtn);

    }

    public void newRequestPane() {

    }

    public void resetTextFields() {
        usernameTxt.setText("");
        passwordTxt.setText("");
        usernameTxt.requestFocus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ButtonGroup radioBtnGroup = group1();
        if (e.getSource() == technicianRBtn) {
            adminRBtn.setSelected(false);
            employeeIdLbl.setText("Technician ID:");
        } else if (e.getSource() == adminRBtn) {

            technicianRBtn.setSelected(false);
            employeeIdLbl.setText("Employee ID:");
        } else {

            if (usernameTxt.getText().isEmpty() || passwordTxt.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "Error!!Fields Cannot be Empty");
                resetTextFields();

            } else {
                String username = usernameTxt.getText();
                String password = String.valueOf(passwordTxt.getPassword());

                if (adminRBtn.isSelected()) {

                    ArrayList<Admin> adminList = dao.getAllAdmins();

                    boolean found = false;

                    for (Admin admin : adminList) {

                        if (admin.getEmployeeNumber().equals(username) && admin.getPassword().equals(password)) {
                            found = true;
                            this.admin = admin;
                            String loginSuccessMessage = "Success! Admin: " + admin.getTitle() + " " + admin.getSurname() + " Signing In";
                            ImageIcon successIcon = new ImageIcon("C:\\Users\\AI Bot2\\Documents\\NetBeansProjects\\TMFRequestManagementSystem\\TMF Image Resources\\ConfirmationLogo.png");
                            JLabel loginSuccessLbl = new JLabel(loginSuccessMessage, successIcon, SwingConstants.LEFT);
                            JOptionPane.showMessageDialog(null, loginSuccessLbl);

                            AdminGUI runAdminGui = new AdminGUI(signedInAdmin());
                            runAdminGui.setGui();
                            this.dispose();

                        }
                    }
                    if (!found) {
                        String signInFailMessage = "Error! Invalid Password or Username";
                        ImageIcon signInFailIcon = new ImageIcon("C:\\Users\\AI Bot2\\Documents\\NetBeansProjects\\TMFRequestManagementSystem\\TMF Image Resources\\cancel.jpg");
                        JLabel signInFailLbl = new JLabel(signInFailMessage, signInFailIcon, SwingConstants.LEFT);
                        JOptionPane.showMessageDialog(null, signInFailLbl);
                        resetTextFields();
                    }
                } else if (technicianRBtn.isSelected()) {

                    ArrayList<Technician> techniciansList = dao.getAllTechnicians();

                    boolean found = false;

                    for (Technician technician : techniciansList) {

                        if (username.equals(technician.getTechnicianID()) && password.equals(technician.getPassword())) {

                            found = true;

                            String loginSuccessMessage = "Success ! Technician: " + technician.getTitle() + " " + technician.getSurname() + " Signing In";
                            ImageIcon successIcon = new ImageIcon("C:\\Users\\AI Bot2\\Documents\\NetBeansProjects\\TMFRequestManagementSystem\\TMF Image Resources\\ConfirmationLogo.png");
                            JLabel loginSuccessLbl = new JLabel(loginSuccessMessage, successIcon, SwingConstants.LEFT);
                            JOptionPane.showMessageDialog(null, loginSuccessLbl);
                        }

                    }
                    if (!found) {

                        String signInFailMessage = "Error! Invalid Password or Username";
                        ImageIcon signInFailIcon = new ImageIcon("C:\\Users\\AI Bot2\\Documents\\NetBeansProjects\\TMFRequestManagementSystem\\TMF Image Resources\\cancel.jpg");
                        JLabel signInFailLbl = new JLabel(signInFailMessage, signInFailIcon, SwingConstants.LEFT);
                        JOptionPane.showMessageDialog(null, signInFailLbl);
                        resetTextFields();
                    }
                }
            }
        }
    }

    public Admin signedInAdmin() {
        return admin;
    }

    public static void main(String[] args) {
        new LoginGui().setGui();
    }

}
