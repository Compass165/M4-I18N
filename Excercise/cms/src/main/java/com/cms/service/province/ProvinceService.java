package com.cms.service.province;

import com.cms.model.ProvinceEntity;
import com.cms.repository.IProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ProvinceService implements IProvinceService{
    @Autowired
    IProvinceRepository provinceRepository;

    @Override
    public Iterable<ProvinceEntity> findAll() {
        return provinceRepository.findAll();
    }

    @Override
    public ProvinceEntity findById(Integer id) {
        return provinceRepository.findOne(id);
    }

    @Override
    public void save(ProvinceEntity province) {
        provinceRepository.save(province);
    }

    @Override
    public void remove(Integer id) {
        provinceRepository.delete(id);
    }

}
