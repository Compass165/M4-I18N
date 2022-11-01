package com.cms.service.customer;

import com.cms.model.CustomerEntity;
import com.cms.model.ProvinceEntity;
import com.cms.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class CustomerService implements ICustomerService{
    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public Page<CustomerEntity> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Page<CustomerEntity> findAllByFirstNameContaining(String firstName, Pageable pageable) {
        return customerRepository.findAllByFirstNameContaining(firstName, pageable);
    }

    @Override
    public CustomerEntity findById(Integer id) {
        return customerRepository.findOne(id);
    }

    @Override
    public void save(CustomerEntity customer) {
        customerRepository.save(customer);
    }

    @Override
    public void remove(Integer id) {
        customerRepository.delete(id);
    }

    @Override
    public Iterable<CustomerEntity> findAllByProvinceByProvinceId(ProvinceEntity province) {
        return customerRepository.findAllByProvinceByProvinceId(province);
    }

}
