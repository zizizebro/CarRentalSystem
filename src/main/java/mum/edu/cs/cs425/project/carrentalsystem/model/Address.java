package mum.edu.cs.cs425.project.carrentalsystem.model;

import org.hibernate.cache.cfg.internal.AbstractDomainDataCachingConfig;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    @NotBlank(message = "Street can not be blank")
    @Column(nullable = false)
    private String street;
    @NotBlank(message = "City can not be blank")
    @Column(nullable = false)
    private String city;
    @NotBlank(message = "State can not be blank")
    @Column(nullable = false)
    private String state;
    @NotBlank(message = "ZipCode can not be blank")
    @Column(nullable = false)
    private int zipCode;


    public Address(){

    }
    public Address(String street,String city, String state,int zipCode){
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
  return street + ", " + city + ", " + state + " " + zipCode;


    }
}
