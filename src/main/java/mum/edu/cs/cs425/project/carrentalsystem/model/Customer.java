package mum.edu.cs.cs425.project.carrentalsystem.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "First Name Can Not Be Empty")
    @Column(nullable = false)
    private String firstName;
    @NotBlank(message = "Last Name Can Not Be Empty")
    @Column(nullable = false)
    private String lastName;
//    @NotBlank(message = "Email Address Can Not Be Empty")
//    @Column(nullable = false)
//    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
//    @NotBlank(message = "Gender  Can Not Be Empty")
//    @Column(nullable = false)
//    private String gender;
    @NotBlank(message = "License Number  Can Not Be Empty")
    @Column(nullable = false)
    private String licenceNumber;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    Address address;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    private Credential credential;

    public Customer(){

    }
    public Customer(String firstName, String lastName, LocalDate dob, String licenceNumber, Address address, Credential credential){

        this.firstName = firstName;
        this.lastName = lastName;
//        this.email = email;
        this.dob = dob;
//        this.gender = gender;
        this.licenceNumber = licenceNumber;
        this.address = address;
        this.credential = credential;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", licenceNumber='" + licenceNumber + '\'' +
                ", address=" + address +
                ", credential=" + credential +
                '}';
    }
}
