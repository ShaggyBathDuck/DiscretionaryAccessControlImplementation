package com.bsk.services;

import com.bsk.domain.Privilege;
import com.bsk.repositories.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivilegeService {

    private PrivilegeRepository privilegeRepository;

    @Autowired
    public PrivilegeService(PrivilegeRepository privilegeRepository) {
        this.privilegeRepository = privilegeRepository;
    }

    public List<Privilege> read(){
        return privilegeRepository.findAll();
    }

    public Privilege findFirstByCRUD(Privilege privilege){
        return this.read().stream().filter(p ->p.equals(privilege)).findFirst().get();
    }
    public Privilege findById(Integer id){
        return this.privilegeRepository.findOne(id);
    }
}
