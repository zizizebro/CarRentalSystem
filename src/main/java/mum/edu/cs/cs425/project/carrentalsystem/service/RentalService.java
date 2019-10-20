package mum.edu.cs.cs425.project.carrentalsystem.service;



import mum.edu.cs.cs425.project.carrentalsystem.model.Rental;

import java.util.List;

public interface RentalService {
    public Rental save(Rental rental);
    public List<Rental> findAll();
    public Rental findById(Long id);
    public void deleteById(Long id);
}
