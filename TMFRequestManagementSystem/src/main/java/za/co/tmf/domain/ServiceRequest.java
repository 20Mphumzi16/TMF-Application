
package za.co.tmf.domain;

import java.time.LocalDate;

/**
 *
 * @author Mpumzi Mbula
 */
public class ServiceRequest {

    private String request_id;
    private LocalDate requestDate;
    private String categoryId;
    private String serviceItemName;
    private String itemSize;
    private String description;
    private String requestStatus;
    private String priorityLevel;
    private String technicianId;
    private LocalDate completionDate;
    private String clientID;
    private double costEstimate;

    public ServiceRequest() {
    }

    public ServiceRequest(String request_id, LocalDate requestDate, String categoryId, String serviceItemName, String itemSize, String description, String requestStatus, String priorityLevel, String technicianId, LocalDate completionDate, String clientID, double costEstimate) {
        this.request_id = request_id;
        this.requestDate = requestDate;
        this.categoryId = categoryId;
        this.serviceItemName = serviceItemName;
        this.itemSize = itemSize;
        this.description = description;
        this.requestStatus = requestStatus;
        this.priorityLevel = priorityLevel;
        this.technicianId = technicianId;
        this.completionDate = completionDate;
        this.clientID = clientID;
        this.costEstimate = costEstimate;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getServiceItemName() {
        return serviceItemName;
    }

    public void setServiceItemName(String serviceItemName) {
        this.serviceItemName = serviceItemName;
    }

    public String getItemSize() {
        return itemSize;
    }

    public void setItemSize(String itemSize) {
        this.itemSize = itemSize;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(String priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public String getTechnicianId() {
        return technicianId;
    }

    public void setTechnicianId(String technicianId) {
        this.technicianId = technicianId;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public double getCostEstimate() {
        return costEstimate;
    }

    public void setCostEstimate(double costEstimate) {
        this.costEstimate = costEstimate;
    }

    @Override
    public String toString() {
        return "ServiceRequest{" + "request_id=" + request_id + ", requestDate=" + requestDate + ", categoryId=" + categoryId + ", serviceItemName=" + serviceItemName + ", itemSize=" + itemSize + ", description=" + description + ", requestStatus=" + requestStatus + ", priorityLevel=" + priorityLevel + ", technicianId=" + technicianId + ", completionDate=" + completionDate + ", clientID=" + clientID + ", costEstimate=" + costEstimate + '}';
    }

}
