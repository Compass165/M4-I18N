package com.cms.formatter;

import com.cms.model.ProvinceEntity;
import com.cms.service.province.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class ProvinceFormatter implements Formatter<ProvinceEntity> {
    private IProvinceService provinceService;
    @Autowired
    public ProvinceFormatter(IProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    @Override
    public ProvinceEntity parse(String text, Locale locale) throws ParseException {
        return provinceService.findById(Integer.parseInt(text));
    }

    @Override
    public String print(ProvinceEntity object, Locale locale) {
        return "[" + object.getId() + ", " + object.getName() + "]";
    }
}
