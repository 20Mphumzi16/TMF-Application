
package za.co.tmf.domain;

/**
 *
 * @author AI Bot2
 */
public class Admin {
    private String employeeNumber;
    private String idNumber;
    private String title;
    private String name;
    private String surname;
    private String gender;
    private String phoneNumber;
    private String email;
    private String address;
    private String password;
  

    public Admin() {
    }

    public Admin(String employeeNumber, String idNumber, String title, String name, String surname, String gender, String phoneNumber, String email, String address, String password) {
        this.employeeNumber = employeeNumber;
        this.idNumber = idNumber;
        this.title = title;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.password = password;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" + "employeeNumber=" + employeeNumber + ", idNumber=" + idNumber + ", title=" + title + ", name=" + name + ", surname=" + surname + ", gender=" + gender + ", phoneNumber=" + phoneNumber + ", email=" + email + ", address=" + address + ", password=" + password + '}';
    }

}