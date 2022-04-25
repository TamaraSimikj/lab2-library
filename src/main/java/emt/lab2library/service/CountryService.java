package emt.lab2library.service;

import emt.lab2library.model.Country;

import java.util.List;

public interface CountryService {

    Country findById(Long id);

    Country create(String name, String continent);

    Country update(Long id,String name, String continent);

    void delete(Long id);

    List<Country> listCountries();

}
