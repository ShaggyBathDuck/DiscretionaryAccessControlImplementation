package com.bsk.services;

import com.bsk.domain.Ware;
import com.bsk.repositories.WareRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WareService {

    private WareRepository wareRepository;

    public WareService(WareRepository wareRepository) {
        this.wareRepository = wareRepository;
    }

    public void save(Ware ware) {
        wareRepository.save(ware);
    }

    public List<Ware> read() {
        return wareRepository.findAll();
    }

    public void delete(Integer id) {
        wareRepository.delete(id);
    }

    public Ware findById(Integer id) {
        return wareRepository.findOne(id);
    }

    public List<Ware> findByAllAttributes(String content) {
        return wareRepository.findByUnitContainsOrCategoryContainsOrManufacturerContainsOrNameContainsOrDescriptionContains(
                content, content, content, content, content);
    }
}
