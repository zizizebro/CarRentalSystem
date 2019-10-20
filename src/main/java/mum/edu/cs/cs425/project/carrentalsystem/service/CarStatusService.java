package mum.edu.cs.cs425.project.carrentalsystem.service;



import mum.edu.cs.cs425.project.carrentalsystem.model.CarStatus;

import java.util.List;

public interface CarStatusService {

    public CarStatus save(CarStatus carStatus);
    public List<CarStatus> findAll();
    public CarStatus findById(Long id);
    public void deleteById(Long id);
}
