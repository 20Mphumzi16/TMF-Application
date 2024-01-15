package za.co.tmf.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.*;
import static javax.swing.SwingConstants.LEFT;
import static javax.swing.SwingConstants.RIGHT;
import javax.swing.table.DefaultTableModel;
import za.co.DAO.TmfDAO;
import za.co.tmf.domain.Admin;
import za.co.tmf.domain.Category;
import za.co.tmf.domain.Client;
import za.co.tmf.domain.ServiceRequest;
import za.co.tmf.domain.Technician;

/**
 *
 * @author Mpumzi Mbula
 */
public class AdminGUI extends JFrame implements ActionListener {

    private JPanel requestNumNDatePanel, panelNorth, panelCenter, panelNorthContent, panelSouth, tablePanel, newRequestLblPanel, northButtonPanel, newRequestBtnPanel, filterCBOSearchPanel, centerContentGridPanel;
    private JLabel logoLbl, filterLbl, empIcon, searchLbl, allRequestsLbl, currentDateLbl;
    private JComboBox filterCBO, titleCBO, categoryCBO, requestStatusCBO, priorityLevelCBO, techniciansCBO;
    private JButton signOutBtn, searchBtn, newRequestBtn, deleteBtn, assignBtn;
    private JTextField searchField, nameField, surnameField, phoneNumberField, emailField, clientIdField, addressField, costEstimateField, descriptionField, itemNameField, itemSizeField;
    DefaultTableModel tableModel;
    private JTable requestsTable;
    JMenuBar menuBar;
    private JMenu options;
    private JMenuItem viewAll, deleteRequest, viewTechnicians, settings, createNew, signOut, assign;
    private Font f1, f2;
    private ArrayList<ServiceRequest> serviceRequest;
    private ArrayList<String> assignedRequestIDList;
    private ArrayList<String> requestsToDeleteList;
    private TmfDAO dao;
    private JPanel newRequestPanel, newRequestPanelContainer, deviceInfoContainer;
    private JLabel clientInfoHeading, requestIdLbl, requestStatusLbl, clientNameLbl, clientSurnameLbl, emailLbl, phoneNumberLbl, clientIdLbl, titleLbl, addressLbl, deviceInfoHeading, itemNameLbl, itemSizeLbl, categoryLbl, descriptionLbl, priorityLevelLbl, costEstimateLbl;
    Admin admin;
    private String assignedTechnicianID;

    public AdminGUI(Admin admin) {

        super("Admin Page");

        this.admin = admin;
//font
        f1 = new Font("Arial", Font.BOLD, 16);
        f2 = new Font("Arial", Font.PLAIN, 12);

// instantiating panel Objects
        panelNorth = new JPanel();
        panelCenter = new JPanel();
        panelCenter.setBackground(Color.white);
        panelNorthContent = new JPanel();
        northButtonPanel = new JPanel();
        filterCBOSearchPanel = new JPanel();
        centerContentGridPanel = new JPanel();
        newRequestBtnPanel = new JPanel();

        tablePanel = new JPanel();

        panelSouth = new JPanel();

        newRequestLblPanel = new JPanel();
//DAO
        dao = new TmfDAO();

//logo
        ImageIcon logo = new ImageIcon("C:\\Users\\AI Bot2\\Documents\\NetBeansProjects\\TMFRequestManagementSystem\\TMF Image Resources\\TMFLOGOTOP.png");
        logoLbl = new JLabel(logo);
        logoLbl.setHorizontalAlignment(LEFT);

        ImageIcon employeeIcon = new ImageIcon("C:\\Users\\AI Bot2\\Documents\\NetBeansProjects\\TMFRequestManagementSystem\\TMF Image Resources\\user.png");

        String adminNameSurname = admin.getName() + " " + admin.getSurname();
        empIcon = new JLabel("Welcome " + adminNameSurname, employeeIcon, SwingConstants.LEFT);
        empIcon.setFont(f2);
        empIcon.setHorizontalAlignment(RIGHT);

//comboBox
        filterCBO = new JComboBox(new String[]{"All My Requests"});
        techniciansCBO = new JComboBox();

//labels
        filterLbl = new JLabel("Filter by:");
        searchLbl = new JLabel("Search");
        allRequestsLbl = new JLabel("All Service Requests");
        allRequestsLbl.setFont(f1);

//JMenu
        menuBar = new JMenuBar();

        options = new JMenu("Options");

        createNew = new JMenuItem("Create New Request");
        viewAll = new JMenuItem("View All");
        deleteRequest = new JMenuItem("Delete Request");
        viewTechnicians = new JMenuItem("View All Technicians");
        settings = new JMenuItem("Settings");
        signOut = new JMenuItem("Sign Out");

        options.add(createNew);
        options.add(viewAll);
        options.add(deleteRequest);
        options.add(viewTechnicians);
        options.add(settings);
        options.add(signOut);
        menuBar.add(options);

        signOutBtn = new JButton("Sign Out");
        signOutBtn.setHorizontalAlignment(RIGHT);
        signOutBtn.setBackground(Color.red);
        signOutBtn.setForeground(Color.white);
//Buttons
        searchBtn = new JButton(">>>");
        newRequestBtn = new JButton("New Request");
        newRequestBtn.setBackground(Color.blue);
        newRequestBtn.setForeground(Color.white);
        newRequestBtn.setHorizontalAlignment(LEFT);
        assignBtn = new JButton("Assign");
        deleteBtn = new JButton("Delete Request");
        deleteBtn.setBackground(Color.blue);
        deleteBtn.setForeground(Color.white);
//TextFields
        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(150, 25));
        searchField.setText("--Request ID--");
        searchField.setForeground(Color.GRAY);

        //Table
        tableModel = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {

                    case 0:
                        return Boolean.class;
                    case 1:

                    case 2:

                    case 3:

                    case 4:

                    case 5:

                    case 6:

                    case 7:

                    case 8:
                        return String.class;

                    default:
                        return String.class;

                }
            }
        };
        requestsTable = new JTable(tableModel);

        tableModel.addColumn("Assign or Delete");
        tableModel.addColumn("ID");
        tableModel.addColumn("Date");
        tableModel.addColumn("Status");
        tableModel.addColumn("Category");
        tableModel.addColumn("Description");
        tableModel.addColumn("Cost Estimate");
        tableModel.addColumn("Priority");
        tableModel.addColumn("Assigned To");

        requestsTable.setPreferredScrollableViewportSize(new Dimension(800, 400));
        requestsTable.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(new JCheckBox()));
        populateTable();
        populateTechniciansCBO();
    }
//public ArrayList<ServiceRequest>retrieveTableDataFromDB(){
//      serviceRequest = dao.getAllRequests();
//}

    public void populateTable() {
        tableModel.setRowCount(0);
        serviceRequest = dao.getAllRequests();
        ArrayList<Category> categoryList = dao.getAllCategories();

        for (ServiceRequest request : serviceRequest) {
            for (Category category : categoryList) {
                if (category.getCategoryNumber().equals(request.getCategoryId())) {
                    Object[] rowData = {false, request.getRequest_id(), request.getRequestDate().toString(), request.getRequestStatus(), category.getCategoryDescription(), request.getDescription(), request.getCostEstimate(), request.getPriorityLevel(), request.getTechnicianId()};
                    tableModel.addRow(rowData);
                }
            }
        }
    }

    public void newRequestOptionPane() {

//panels
        newRequestPanel = new JPanel();

        newRequestPanel.setLayout(new GridLayout(5, 1));

        newRequestPanelContainer = new JPanel();

        newRequestPanelContainer.setLayout(new GridLayout(4, 4, 10, 10));
        deviceInfoContainer = new JPanel();
        deviceInfoContainer.setLayout(new GridLayout(4, 4, 10, 10));

        requestNumNDatePanel = new JPanel();
        requestNumNDatePanel.setLayout(new GridLayout(1, 2));

//labels 
        clientInfoHeading = new JLabel("Client Info:");
        clientInfoHeading.setFont(f1);
        clientNameLbl = new JLabel("Name:");
        clientSurnameLbl = new JLabel("Surname:");
        titleLbl = new JLabel("Title:");
        phoneNumberLbl = new JLabel("Phone Number:");
        addressLbl = new JLabel("Address:");
        emailLbl = new JLabel("Email:");
        clientIdLbl = new JLabel("ID Number:");
        String requestID = String.valueOf(generateRequestID());
        requestIdLbl = new JLabel("Request ID:" + requestID);
        requestIdLbl.setFont(f2);

        deviceInfoHeading = new JLabel("Device Information:");
        deviceInfoHeading.setFont(f1);
        itemNameLbl = new JLabel("Item Name:");
        itemSizeLbl = new JLabel("Item Size/Model:");
        descriptionLbl = new JLabel("Device Problem(s) :");
        priorityLevelLbl = new JLabel("Priority Level:");
        costEstimateLbl = new JLabel("Cost Estimate:R");
        categoryLbl = new JLabel("Category:");
        requestStatusLbl = new JLabel("Request Status:");
//date
        String date = String.valueOf(currentDate());
        currentDateLbl = new JLabel("Date:" + date);
        currentDateLbl.setHorizontalAlignment(RIGHT);
        currentDateLbl.setFont(f2);
//TextFields
        nameField = new JTextField();
        surnameField = new JTextField();
        addressField = new JTextField();
        phoneNumberField = new JTextField();
        emailField = new JTextField();
        clientIdField = new JTextField();
        itemNameField = new JTextField();
        descriptionField = new JTextField();
        costEstimateField = new JTextField();
        itemSizeField = new JTextField();

//ComboBoxes
        titleCBO = new JComboBox(new String[]{"DR", "Prof", "Mr", "Ms", "Mrs"});
        priorityLevelCBO = new JComboBox(new String[]{"High", "Medium", "Low"});
        requestStatusCBO = new JComboBox(new String[]{"Unassigned", "Assigned", "In Progress", "Completed"});
        requestStatusCBO.setSelectedItem("Unassigned");
        categoryCBO = new JComboBox();
        populateCategoryCBO();

//Adding components to panels        
        requestNumNDatePanel.add(requestIdLbl);
        requestNumNDatePanel.add(currentDateLbl);

        newRequestPanelContainer.add(titleLbl);
        newRequestPanelContainer.add(titleCBO);
        newRequestPanelContainer.add(clientNameLbl);
        newRequestPanelContainer.add(nameField);
        newRequestPanelContainer.add(clientSurnameLbl);
        newRequestPanelContainer.add(surnameField);
        newRequestPanelContainer.add(emailLbl);
        newRequestPanelContainer.add(emailField);
        newRequestPanelContainer.add(phoneNumberLbl);
        newRequestPanelContainer.add(phoneNumberField);
        newRequestPanelContainer.add(clientIdLbl);
        newRequestPanelContainer.add(clientIdField);
        newRequestPanelContainer.add(addressLbl);
        newRequestPanelContainer.add(addressField);

        deviceInfoContainer.add(requestStatusLbl);
        deviceInfoContainer.add(requestStatusCBO);

        deviceInfoContainer.add(categoryLbl);
        deviceInfoContainer.add(categoryCBO);

        deviceInfoContainer.add(itemNameLbl);
        deviceInfoContainer.add(itemNameField);

        deviceInfoContainer.add(itemSizeLbl);
        deviceInfoContainer.add(itemSizeField);

        deviceInfoContainer.add(descriptionLbl);
        deviceInfoContainer.add(descriptionField);

        deviceInfoContainer.add(priorityLevelLbl);
        deviceInfoContainer.add(priorityLevelCBO);
        deviceInfoContainer.add(costEstimateLbl);
        deviceInfoContainer.add(costEstimateField);

        newRequestPanel.add(requestNumNDatePanel);
        newRequestPanel.add(clientInfoHeading);
        newRequestPanel.add(newRequestPanelContainer);
        newRequestPanel.add(deviceInfoHeading);
        newRequestPanel.add(deviceInfoContainer);

        newRequestPanel.setPreferredSize(new Dimension(850, 600));

        int result = JOptionPane.showConfirmDialog(null, newRequestPanel, "New Request", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {

            String title = titleCBO.getSelectedItem().toString();
            String clientName = nameField.getText();
            String surname = surnameField.getText();
            String idNumber = clientIdField.getText();
            String email = emailField.getText();
            String address = addressField.getText();
            String phoneNumber = phoneNumberField.getText();

            if (clientName.isEmpty() || surname.isEmpty() || idNumber.isEmpty() || address.isEmpty() || phoneNumber.isEmpty()) {

                JOptionPane.showMessageDialog(null, "Error!! Some client details Missing");
            } else {
                ArrayList<Client> clientList = dao.getAllClients();
                boolean found = false;
                for (Client client : clientList) {
                    //Check if the client already exists ,if the client exists then there is no need to save another instance of the client in the db if not then the client is saved
                    if (idNumber.equals(client.getIdNumber())) {
                        found = true;
                    }
                }
                if (!found) {
                    Client client = new Client(idNumber, title, clientName, surname, email, phoneNumber, address);
                    dao.saveClient(client);
                }

            }

            String deviceName = itemNameField.getText();
            String itemSize = itemSizeField.getText();
            String requestStatus = requestStatusCBO.getSelectedItem().toString();
            String description = descriptionField.getText();
            String priorityLevel = priorityLevelCBO.getSelectedItem().toString();
            String category = String.valueOf(categoryCBO.getSelectedItem().toString().charAt(0));

            System.out.println(category);
            String costEstimateString = costEstimateField.getText();

            if (deviceName.isEmpty() || itemSize.isEmpty() || description.isEmpty() || costEstimateField.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "Error!! Some Device details Missing");
            } else {
                double costEstimate = Double.parseDouble(costEstimateString);
                ServiceRequest serviceRequest = new ServiceRequest(requestID, currentDate(), category, deviceName, itemSize, description, requestStatus, priorityLevel, null, null, idNumber, costEstimate);
                System.out.println(serviceRequest);
                dao.saveNewRequest(serviceRequest);
            }

            populateTable();
        }

    }

    public String selectedTechnician() {
        String[] seperateStrings = techniciansCBO.getSelectedItem().toString().split(",");
        String name = seperateStrings[0];
        String surname = seperateStrings[1];

        boolean found = false;
        ArrayList<Technician> technicianList = dao.getAllTechnicians();
        for (Technician technician : technicianList) {
            if (name.equals(technician.getName()) && surname.equals(technician.getSurname())) {
                found = true;
                assignedTechnicianID = technician.getTechnicianID();
            }
        }
        System.out.println(assignedTechnicianID);
        return assignedTechnicianID;
    }

    public LocalDate currentDate() {
        LocalDate date = LocalDate.now();
        return date;
    }

    public int generateRequestID() {
        Random random = new Random();
        int randomNumber = random.nextInt(1000);
        return randomNumber;
    }

    public void signOutFunction() {
        int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to sign out?", "Log Out", JOptionPane.YES_NO_OPTION);
        if (choice == 0) {
            LoginGui runLoginGui = new LoginGui();
            runLoginGui.setGui();
            this.dispose();
        }
    }

    public void populateCategoryCBO() {
        ArrayList<Category> categoryList = dao.getAllCategories();
        for (Category category : categoryList) {
            categoryCBO.addItem(category.getCategoryNumber() + " - " + category.getCategoryDescription());
        }
    }

    private ArrayList<Technician> retrieveTechnicians() {
        ArrayList<Technician> technicianList = dao.getAllTechnicians();
        return technicianList;
    }

    private void populateTechniciansCBO() {
        techniciansCBO.removeAllItems();
        retrieveTechnicians();
        techniciansCBO.addItem("--Assign Technician--");
        for (int i = 0; i < retrieveTechnicians().size(); i++) {

            techniciansCBO.addItem(retrieveTechnicians().get(i).getName() + "," + retrieveTechnicians().get(i).getSurname());
        }
    }

    public void searchRequest() {

        serviceRequest = dao.getAllRequests();
        ArrayList<Category> categoryList = dao.getAllCategories();

        boolean found = false;

        for (ServiceRequest request : serviceRequest) {
            for (Category category : categoryList) {
                if (category.getCategoryNumber().equals(request.getCategoryId()) && searchField.getText().equals(request.getRequest_id())) {
                    tableModel.setRowCount(0);
                    found = true;
                    Object[] rowData = {false, request.getRequest_id(), request.getRequestDate().toString(), request.getRequestStatus(), category.getCategoryDescription(), request.getDescription(), request.getCostEstimate(), request.getPriorityLevel(), request.getTechnicianId()};
                    tableModel.addRow(rowData);
                }
            }

        }
        if (!found) {
            JOptionPane.showMessageDialog(null, "Request Not Found!!");
        }
    }

    public void setGui() {

//setting Panels Layout
        panelCenter.setLayout(new BorderLayout());
        northButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panelNorthContent.setLayout(new GridLayout(1, 2, 10, 10));
        panelNorth.setLayout(new BorderLayout());
        filterCBOSearchPanel.setLayout(new FlowLayout());
        centerContentGridPanel.setLayout(new BoxLayout(centerContentGridPanel, BoxLayout.Y_AXIS));
        newRequestBtnPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        newRequestLblPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        tablePanel.add(new JScrollPane(requestsTable));

        panelNorthContent.add(logoLbl);
        northButtonPanel.add(empIcon);
        northButtonPanel.add(signOutBtn);
        panelNorthContent.add(northButtonPanel);
        panelNorth.add(menuBar, BorderLayout.SOUTH);
        panelNorth.add(panelNorthContent, BorderLayout.NORTH);

        filterCBOSearchPanel.add(filterLbl);
        filterCBOSearchPanel.add(filterCBO);
        filterCBOSearchPanel.add(searchLbl);
        filterCBOSearchPanel.add(searchField);
        filterCBOSearchPanel.add(searchBtn);
        filterCBOSearchPanel.add(techniciansCBO);
        filterCBOSearchPanel.add(assignBtn);
        filterCBOSearchPanel.add(deleteBtn);

        panelCenter.add(filterCBOSearchPanel, BorderLayout.NORTH);
        newRequestBtnPanel.add(newRequestBtn);

        centerContentGridPanel.add(newRequestBtnPanel);
        newRequestLblPanel.add(allRequestsLbl);
        centerContentGridPanel.add(newRequestLblPanel);
        centerContentGridPanel.add(tablePanel);
        panelCenter.add(centerContentGridPanel);

//ActionListeners
        signOutBtn.addActionListener(this);
        searchBtn.addActionListener(this);
        newRequestBtn.addActionListener(this);
        options.addActionListener(this);
        createNew.addActionListener(this);
        viewAll.addActionListener(this);
        deleteRequest.addActionListener(this);
        viewTechnicians.addActionListener(this);
        settings.addActionListener(this);
        signOut.addActionListener(this);
        assignBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        filterCBO.addActionListener(this);
//Adding Panels to frames
        this.add(panelNorth, BorderLayout.NORTH);
        this.add(panelCenter, BorderLayout.CENTER);
        this.add(panelSouth, BorderLayout.SOUTH);

//searchField FocusListener
        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("--Request ID--")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("--Request ID--");
                    searchField.setForeground(Color.GRAY);
                }
            }

        });

//Frame settings
        this.setSize(1080, 700);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signOutBtn) {
            signOutFunction();
        } else if (e.getSource() == newRequestBtn) {
            newRequestOptionPane();
        } else if (e.getSource() == createNew) {
            newRequestOptionPane();
        } else if (e.getSource() == viewAll) {
            populateTable();
        } else if (e.getSource() == searchBtn) {
            searchRequest();
        } else if (e.getSource() == deleteBtn) {
            System.out.println("delete Btn pressed");
            ArrayList<String> requestIDs = new ArrayList<>();
            int count = 0;
            //count is a flag varieble that detects if a checkbox(s) are checked or not for a request or requests deletion.if not then the appropriate message is shown
            for (int i = 0; i < requestsTable.getRowCount(); i++) {
                boolean isChecked = (boolean) tableModel.getValueAt(i, 0);
                String requestId = (String) tableModel.getValueAt(i, 1);
                if (isChecked) {
                    requestIDs.add(requestId);
                    count = 1;
                }
            }
            requestsToDeleteList = (ArrayList<String>) requestIDs.clone();

            if (count == 0) {
                JOptionPane.showMessageDialog(null, "Error!! No Request Selected, select a request(s) to delete");
            } else {
                int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the following request(s):\n" + requestsToDeleteList.toString()+"\n deleting these requests may result in the deletion of client(s) info", "Delete Confirmation", JOptionPane.YES_NO_OPTION);
                if (choice == 0) {
                    System.out.println(" delete Confirm -yes-");
                    ArrayList<ServiceRequest> serviceRequestList = dao.getAllRequests();
                    ArrayList<String> clientIDs = new ArrayList<>();
                    String clientId;
                    for (int i = 0; i < requestIDs.size(); i++) {
                        for (int j = 0; j < serviceRequestList.size(); j++) {
                            if (requestIDs.get(i).equals(serviceRequest.get(j).getRequest_id())) {
                                clientIDs.add(serviceRequest.get(i).getClientID());
                            }
                        }
                    }

                    for (int i = 0; i < clientIDs.size(); i++) {
                        int clientIDCount = 0;
                        for (int j = 0; j < serviceRequest.size(); j++) {
                            if (clientIDs.get(i).equals(serviceRequest.get(j).getClientID())) {
                                System.out.println("------------match-------");
                                clientIDCount++;

                            }
                            if (clientIDCount > 1) {
                                System.out.println(clientIDCount);
                                dao.deleteServiceRequest(requestIDs);
                            } else if(clientIDCount==1) {
                                System.out.println(clientIDCount);
                                dao.deleteServiceRequest(requestIDs);

                                System.out.println(clientIDs.get(i));
                                dao.deleteClient(clientIDs.get(i));
                            }
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Sucess! Request(s):\n" + requestIDs.toString() + " successfully deleted.");
//                     JOptionPane.showMessageDialog(null, clientIDS + " Deleted Successfully");
                    populateTable();
                    populateTechniciansCBO();
                }
            }
        } else if (e.getSource() == viewTechnicians) {

        } else if (e.getSource() == settings) {

        } else if (e.getSource() == signOut) {

            signOutFunction();
        } else if (e.getSource() == assignBtn) {

            if (techniciansCBO.getSelectedItem().toString().equals("--Assign Technician--")) {
                JOptionPane.showMessageDialog(null, "Error!!!  Technician Not Selected");
            } else {
                ArrayList<String> assignedRequestIDs = new ArrayList<>();

                //Boolean ischecked[] = new Boolean[requestsTable.getRowCount()];
                for (int i = 0; i < requestsTable.getRowCount(); i++) {
                    boolean isChecked = (boolean) tableModel.getValueAt(i, 0);

                    String requestId = (String) tableModel.getValueAt(i, 1);
                    if (isChecked) {
                        assignedRequestIDs.add(requestId);

                    }
                }
                assignedRequestIDList = (ArrayList<String>) assignedRequestIDs.clone();
                int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to assign the following request(s):\n" + assignedRequestIDList.toString() + " To " + techniciansCBO.getSelectedItem().toString(), "Confirm Assignment", JOptionPane.YES_NO_OPTION);
                if (choice == 0) {
                    dao.assignTechnician(assignedRequestIDs, selectedTechnician());
                    populateTable();
                    populateTechniciansCBO();
                }
            }
        } else if (e.getSource() == filterCBO) {
            if (filterCBO.getSelectedItem().toString().equals("All My Requests")) {
                populateTable();
            }
        }

    }
}
