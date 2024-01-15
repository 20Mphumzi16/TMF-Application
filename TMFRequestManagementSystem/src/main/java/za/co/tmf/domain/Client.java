package za.co.tmf.domain;

/**
 *
 * @author Mpumzi Mbula
 */
public class Client {

    private String IdNumber;
    private String title;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String address;

  

    public Client() {
    }

    public Client(String IdNumber, String title, String name, String surname, String email, String phoneNumber, String address) {
        this.IdNumber = IdNumber;
        this.title = title;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getIdNumber() {
        return IdNumber;
    }

    public void setIdNumber(String IdNumber) {
        this.IdNumber = IdNumber;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Client{" + "IdNumber=" + IdNumber + ", title=" + title + ", name=" + name + ", surname=" + surname + ", email=" + email + ", phoneNumber=" + phoneNumber + ", address=" + address + '}';
    }
    
    
    
}