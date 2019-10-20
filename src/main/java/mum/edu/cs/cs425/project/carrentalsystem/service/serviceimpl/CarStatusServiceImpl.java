package mum.edu.cs.cs425.project.carrentalsystem.service.serviceimpl;

import mum.edu.cs.cs425.project.carrentalsystem.model.CarStatus;
import mum.edu.cs.cs425.project.carrentalsystem.repositary.CarStatusRepository;
import mum.edu.cs.cs425.project.carrentalsystem.service.CarStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarStatusServiceImpl implements CarStatusService {

    @Autowired
    private CarStatusRepository carStatusRepository;

    @Override
    public CarStatus save(CarStatus carStatus) {
        return carStatusRepository.save(carStatus);
    }

    @Override
    public List<CarStatus> findAll() {
        return (List<CarStatus>)carStatusRepository.findAll();
    }

    @Override
    public CarStatus findById(Long id) {
        return carStatusRepository.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        carStatusRepository.deleteById(id);
    }
}
