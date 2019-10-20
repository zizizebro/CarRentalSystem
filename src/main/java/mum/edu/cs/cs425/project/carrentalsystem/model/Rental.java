package mum.edu.cs.cs425.project.carrentalsystem.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "pick_up date can not be empty")
    @DateTimeFormat(pattern = "MM-DD_YYYY")
    private LocalDate pickupDate;
    @NotBlank(message = "return_Date can not be empty")
    @DateTimeFormat(pattern = "MM-DD_YYYY")
    private LocalDate returnDate;

    @OneToOne(cascade = CascadeType.ALL)
    Car car;

    @OneToOne(cascade = CascadeType.ALL)
    Customer customer;


    public Rental(){

    }

    public Rental(LocalDate pickupDate, LocalDate returnDate, Car car, Customer customer ) {
        this.pickupDate = pickupDate;
        this.returnDate = returnDate;
        this.car = car;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(LocalDate pickupDate) {
        this.pickupDate = pickupDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
