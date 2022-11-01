package com.cms.repository;

import com.cms.model.CustomerEntity;
import com.cms.model.ProvinceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ICustomerRepository extends PagingAndSortingRepository<CustomerEntity, Integer> {
    public Iterable<CustomerEntity> findAllByProvinceByProvinceId(ProvinceEntity province);
    public Page<CustomerEntity> findAllByFirstNameContaining(String firstName, Pageable pageable);
}
