package mum.edu.cs.cs425.project.carrentalsystem.service.serviceimpl;

import mum.edu.cs.cs425.project.carrentalsystem.model.Customer;
import mum.edu.cs.cs425.project.carrentalsystem.repositary.CustomerRepository;
import mum.edu.cs.cs425.project.carrentalsystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> findAll() {
        return (List<Customer>) customerRepository.findAll();
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }
}
