package com.cms.service.province;

import com.cms.model.ProvinceEntity;

public interface IProvinceService {
    public Iterable<ProvinceEntity> findAll();

    public ProvinceEntity findById(Integer id);

    public void save(ProvinceEntity province);

    public void  remove(Integer id);
}
