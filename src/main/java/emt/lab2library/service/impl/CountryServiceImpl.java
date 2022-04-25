package emt.lab2library.service.impl;

import emt.lab2library.model.Country;
import emt.lab2library.model.exceptions.CountryNotFoundException;
import emt.lab2library.repository.CountryRepository;
import emt.lab2library.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Country findById(Long id) {
        return this.countryRepository.findById(id).orElseThrow(()-> new CountryNotFoundException(id));
    }

    @Override
    public List<Country> listCountries() {
        return this.countryRepository.findAll();
    }

    @Override
    public Country create(String name, String continent) {
        if (name==null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Country c = new Country(name,continent);
        countryRepository.save(c);
        return c;
    }

    @Override
    public Country update(Long id, String name, String continent) {
        Country c =  this.findById(id);
        c.setName(name);
        c.setContinent(continent);
        countryRepository.save(c);
        return c;
    }

    @Override
    public void delete(Long id) {
        Country c = this.findById(id);
        this.countryRepository.delete(c);
    }

}
