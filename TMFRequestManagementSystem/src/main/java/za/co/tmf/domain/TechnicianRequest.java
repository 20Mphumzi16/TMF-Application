
package za.co.tmf.domain;

import java.time.LocalDate;

/**
 *
 * @author Mpumzi Mbula
 */
public class TechnicianRequest {
    private String technicianID;
    private String serviceRequestId;
    private String adminId;
    private LocalDate assignmentDate;

    public TechnicianRequest(String technicianID, String serviceRequestId, String adminId, LocalDate assignmentDate) {
        this.technicianID = technicianID;
        this.serviceRequestId = serviceRequestId;
        this.adminId = adminId;
        this.assignmentDate = assignmentDate;
    }

    public String getTechnicianID() {
        return technicianID;
    }

    public void setTechnicianID(String technicianID) {
        this.technicianID = technicianID;
    }

    public String getServiceRequestId() {
        return serviceRequestId;
    }

    public void setServiceRequestId(String serviceRequestId) {
        this.serviceRequestId = serviceRequestId;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public LocalDate getAssignmentDate() {
        return assignmentDate;
    }

    public void setAssignmentDate(LocalDate assignmentDate) {
        this.assignmentDate = assignmentDate;
    }
    
}
