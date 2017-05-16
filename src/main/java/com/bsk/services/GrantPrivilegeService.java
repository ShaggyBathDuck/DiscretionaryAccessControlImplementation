package com.bsk.services;

import com.bsk.domain.GrantPrivilege;
import com.bsk.repositories.GrantPrivilegesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrantPrivilegeService {
    private GrantPrivilegesRepository repository;

    @Autowired
    public GrantPrivilegeService(GrantPrivilegesRepository repository) {
        this.repository = repository;
    }
    public List<GrantPrivilege> read() {
        return repository.findAll();
    }

    public void save(GrantPrivilege grantPrivilege) {
        repository.save(grantPrivilege);
    }
}
