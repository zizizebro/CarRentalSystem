package mum.edu.cs.cs425.project.carrentalsystem.service.serviceimpl;

import mum.edu.cs.cs425.project.carrentalsystem.model.Rental;
import mum.edu.cs.cs425.project.carrentalsystem.repositary.RentalRepository;
import mum.edu.cs.cs425.project.carrentalsystem.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalServiceImpl implements RentalService {

    @Autowired
    private RentalRepository rentalRepository;


    @Override
    public Rental save(Rental rental) {
        return rentalRepository.save(rental);
    }

    @Override
    public List<Rental> findAll() {
        return (List<Rental>) rentalRepository.findAll();
    }

    @Override
    public Rental findById(Long id) {
        return rentalRepository.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        rentalRepository.deleteById(id);
    }
}
