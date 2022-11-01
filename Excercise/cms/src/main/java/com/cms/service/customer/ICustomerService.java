package com.cms.service.customer;

import com.cms.model.CustomerEntity;
import com.cms.model.ProvinceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICustomerService {
    public Page<CustomerEntity> findAll(Pageable pageable);

    public Page<CustomerEntity> findAllByFirstNameContaining(String firstName, Pageable pageable);

    public CustomerEntity findById(Integer id);

    public void save(CustomerEntity province);

    public void  remove(Integer id);

    public Iterable<CustomerEntity> findAllByProvinceByProvinceId(ProvinceEntity province);
}
