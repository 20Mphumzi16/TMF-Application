package za.co.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import za.co.tmf.DBConnection.DBConnection;
import za.co.tmf.domain.Admin;
import za.co.tmf.domain.Category;
import za.co.tmf.domain.Client;
import za.co.tmf.domain.ServiceRequest;
import za.co.tmf.domain.Technician;

/**
 *
 * @author Mpumzi Mbula
 */
public class TmfDAO {

    private PreparedStatement pstmt;
    private Connection con;

    public TmfDAO() {

        try {
            con = DBConnection.getConnection();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error!!!: " + ex.getMessage());
        }
    }

    public Client saveClient(Client client) {

        String sqlQuery = "INSERT INTO CLIENT VALUES(?,?,?,?,?,?,?)";
        int ok;
        try {

            pstmt = con.prepareStatement(sqlQuery);

            pstmt.setString(1, client.getIdNumber());
            pstmt.setString(2, client.getName());
            pstmt.setString(3, client.getSurname());
            pstmt.setString(4, client.getEmail());
            pstmt.setString(5, client.getPhoneNumber());
            pstmt.setString(6, client.getAddress());
            pstmt.setString(7, client.getTitle());

            ok = pstmt.executeUpdate();

            if (ok > 0) {
                return client;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error saving client!! " + ex.getMessage());
        } finally {

            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        return null;
    }

    public ArrayList<Client> getAllClients() {

        ArrayList<Client> clientList = new ArrayList<>();
        String sqlQuery = "SELECT*FROM CLIENT";
        ResultSet rs;

        try {
            pstmt = con.prepareStatement(sqlQuery);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    clientList.add(new Client(rs.getString(1), rs.getString(7), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
                }
                rs.close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error!!" + ex.getMessage());
        } finally {

            try {
                pstmt.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error closing statement in Finally block");
            }

        }

        return clientList;
    }

    public void deleteClient(String clientID) {
        try {
            String sqlQuery = "DELETE FROM CLIENT WHERE CLIENT_ID=?";
            pstmt = con.prepareStatement(sqlQuery);
            pstmt.setString(1, clientID);
            pstmt.executeUpdate();
            System.out.println(clientID + " Deleted Successfully");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public ArrayList<Technician> getAllTechnicians() {

        ArrayList<Technician> techniciansList = new ArrayList<>();
        String sqlQuery = "SELECT*FROM TECHNICIAN";
        ResultSet rs;

        try {
            pstmt = con.prepareStatement(sqlQuery);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    techniciansList.add(new Technician(rs.getString(1), rs.getString(2), rs.getString(9), rs.getString(3), rs.getString(4), rs.getString(10), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(11), rs.getBoolean(12)));
                }
                rs.close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            //closeConnections();
        }
        return techniciansList;
    }

    public ArrayList<Admin> getAllAdmins() {
        ArrayList<Admin> adminList = new ArrayList<>();
        try {

            String sqlQuery = "SELECT*FROM ADMIN_CLECK";
            ResultSet rs;
            pstmt = con.prepareStatement(sqlQuery);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    adminList.add(new Admin(rs.getString(1), rs.getString(7), rs.getString(9), rs.getString(2), rs.getString(3), rs.getString(8), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(10)));
                }
                rs.close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            try {
                pstmt.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }

        return adminList;
    }

    public ServiceRequest saveNewRequest(ServiceRequest serviceRequest) {

        String sqlQuery = "INSERT INTO SERVICE_REQUEST VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

        String requestDate = String.valueOf(serviceRequest.getRequestDate());
        String completionDate = String.valueOf(serviceRequest.getCompletionDate());
        int ok;

        try {
            pstmt = con.prepareStatement(sqlQuery);
            pstmt.setString(1, serviceRequest.getRequest_id());
            pstmt.setDate(2, Date.valueOf(serviceRequest.getRequestDate()));
            pstmt.setString(3, serviceRequest.getRequestStatus());
            pstmt.setString(4, serviceRequest.getServiceItemName());
            pstmt.setString(5, serviceRequest.getItemSize());
            pstmt.setString(6, serviceRequest.getDescription());
            pstmt.setDouble(7, serviceRequest.getCostEstimate());
            pstmt.setString(8, serviceRequest.getPriorityLevel());
            pstmt.setString(9, serviceRequest.getClientID());
            pstmt.setString(10, serviceRequest.getTechnicianId());
            pstmt.setDate(11, null);
            pstmt.setString(12, serviceRequest.getCategoryId());

            ok = pstmt.executeUpdate();

            if (ok > 0) {
                JOptionPane.showMessageDialog(null, "Request Successfully Created and Logged");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return serviceRequest;
    }

    public ArrayList<ServiceRequest> getAllRequests() {

        ArrayList<ServiceRequest> allRequests = new ArrayList<>();
        ResultSet rs;
        String sqlQuery = "SELECT *FROM SERVICE_REQUEST";

        try {

            pstmt = con.prepareStatement(sqlQuery);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {

                    LocalDate requestDate = rs.getDate(2).toLocalDate();
                    Date date = rs.getDate(11);

                    if (date != null) {
                        LocalDate completionDate = date.toLocalDate();
                        allRequests.add(new ServiceRequest(rs.getString(1), requestDate, rs.getString(12), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(3), rs.getString(8), rs.getString(10), completionDate, rs.getString(9), rs.getDouble(7)));

                    } else {

                        date = new Date(1900, 1, 1);
                        LocalDate completionDate = date.toLocalDate();
                        allRequests.add(new ServiceRequest(rs.getString(1), requestDate, rs.getString(12), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(3), rs.getString(8), rs.getString(10), completionDate, rs.getString(9), rs.getDouble(7)));
                    }

                }
                rs.close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return allRequests;
    }

    public void deleteServiceRequest(ArrayList<String> requestID) {
        System.out.println("Inside delete method in DAO class");
        String sqlQuery = "DELETE FROM SERVICE_REQUEST WHERE REQUEST_ID=?";
        try {

            pstmt = con.prepareStatement(sqlQuery);
            for (int i = 0; i < requestID.size(); i++) {
                pstmt.setString(1, requestID.get(i));
                pstmt.executeUpdate();

            }

          System.out.println("Sucess! Request(s):\n" + requestID.toString() + " successfully deleted.");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            closePreparedStatement();
        }
    }

    public ArrayList<Category> getAllCategories() {

        ResultSet rs;
        ArrayList<Category> categoryList = new ArrayList<>();
        String sqlQuery = "SELECT*FROM CATEGORY";
        try {

            pstmt = con.prepareStatement(sqlQuery);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    categoryList.add(new Category(rs.getString(1), rs.getString(2)));
                }
                rs.close();
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return categoryList;
    }

    public void assignTechnician(ArrayList<String> requestIDs, String technicianID) {
        String sqlQuery = "UPDATE SERVICE_REQUEST SET TECHNICIAN_ID=?,REQUEST_STATUS='Assigned' WHERE REQUEST_ID=?";
        try {
            int ok;
            pstmt = con.prepareStatement(sqlQuery);
            for (int i = 0; i < requestIDs.size(); i++) {
                pstmt.setString(1, technicianID);
                pstmt.setString(2, requestIDs.get(i));
                ok = pstmt.executeUpdate();
            }
            JOptionPane.showMessageDialog(null, requestIDs.toString() + " Assigned to Technician:" + technicianID);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    public void closePreparedStatement() {
        try {
            pstmt.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

}
