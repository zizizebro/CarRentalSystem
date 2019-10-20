package mum.edu.cs.cs425.project.carrentalsystem.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Car {
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Make cannot be blank")
    @Column(nullable = false)
    private String carMake;
    @NotBlank(message = "Brand cannot be blank")
    @Column(nullable = false)
    private String carBrand;
    @NotBlank(message = "Plate Number cannot be blank")
    @Column(nullable = false)
    private String plateNumber;
    @NotBlank(message = "Price cannot be blank")
    @Column(nullable = false)
    private double rentPrice;
    @NotBlank(message = "Model cannot be blank")
    private String model;
    @NotBlank(message = "Color cannot be blank")
    private String color;

    @OneToOne
    private CarStatus carStatus;

    public  Car(){
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public double getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(double rentPrice) {
        this.rentPrice = rentPrice;
    }

    public Car(String carMake, String carBrand, String plateNumber, CarStatus carStatus, double rentPrice, String model, String color) {
        this.carMake = carMake;
        this.carBrand = carBrand;
        this.plateNumber = plateNumber;
        this.carStatus = carStatus;
        this.rentPrice = rentPrice;
        this.model = model;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public double getPrice() {
        return rentPrice;
    }

    public void setPrice(double rentPrice) {
        this.rentPrice = rentPrice;
    }

    public CarStatus getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(CarStatus carStatus) {
        this.carStatus = carStatus;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
