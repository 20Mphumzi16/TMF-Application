
package za.co.tmf.domain;

/**
 *
 * @author Mpumzi Mbula
 */
public class Technician {

    private String technicianID;
    private String idNumber;
    private String title;
    private String name;
    private String surname;
    private String gender;
    private String phoneNumber;
    private String email;
    private String address;
    private int nrOfAssignedRequests;
    private String password;
    private boolean isAvailable;
    
    public Technician() {
    }

    public Technician(String technicianID, String idNumber, String title, String name, String surname, String gender, String phoneNumber, String email, String address, int nrOfAssignedRequests, String password,boolean isAvailable) {
        this.technicianID = technicianID;
        this.idNumber = idNumber;
        this.title = title;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.nrOfAssignedRequests = nrOfAssignedRequests;
        this.password = password;
        this.isAvailable=isAvailable;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getTechnicianID() {
        return technicianID;
    }

    public void setTechnicianID(String technicianID) {
        this.technicianID = technicianID;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNrOfAssignedRequests() {
        return nrOfAssignedRequests;
    }

    public void setNrOfAssignedRequests(int nrOfAssignedRequests) {
        this.nrOfAssignedRequests = nrOfAssignedRequests;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Technician{" + "technicianID=" + technicianID + ", idNumber=" + idNumber + ", title=" + title + ", name=" + name + ", surname=" + surname + ", gender=" + gender + ", phoneNumber=" + phoneNumber + ", email=" + email + ", address=" + address + ", nrOfAssignedRequests=" + nrOfAssignedRequests + ", password=" + password + ", isAvailable=" + isAvailable + '}';
    }

    
    
}

