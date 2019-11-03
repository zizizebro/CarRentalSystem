package mum.edu.cs.cs425.project.carrentalsystem.repositary;

import mum.edu.cs.cs425.project.carrentalsystem.model.Car;
import mum.edu.cs.cs425.project.carrentalsystem.model.CarStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

    public List<Car> findByCarStatusIs(CarStatus carStatus);
}
