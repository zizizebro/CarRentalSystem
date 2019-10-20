package mum.edu.cs.cs425.project.carrentalsystem.service;


import mum.edu.cs.cs425.project.carrentalsystem.model.Car;

import java.util.List;

public interface CarService {

    public Car save(Car car);
    public List<Car> findAll();
    public Car findById(Long id);
    public void deleteById(Long id);
}
