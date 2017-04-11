package com.bsk.controllers;


import com.bsk.domain.Customer;
import com.bsk.services.CustomerService;
import org.hibernate.Session;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DashboardController {

    private CustomerService customerService;

    private EntityManager entityManager;

    public DashboardController(CustomerService customerService, EntityManager entityManager) {
        this.customerService = customerService;
        this.entityManager = entityManager;
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @ModelAttribute("allCustomers")
    @ResponseBody
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @ModelAttribute("tables")
    @ResponseBody
    public Map<String, List<String>> getTables() {
        Session session = entityManager.unwrap(Session.class);
        Map<String, ClassMetadata> hibernateMetadata = session.getSessionFactory().getAllClassMetadata();
        Map<String, List<String>> tableNames = new HashMap<>();
        for (ClassMetadata classMetadata : hibernateMetadata.values()) {
            AbstractEntityPersister aep = (AbstractEntityPersister) classMetadata;
            int propertiesCounter = ((AbstractEntityPersister) classMetadata).getPropertyNames().length;
            List<String> columnNames = new ArrayList<>();
            for (int i=0; i<propertiesCounter; i++){
                columnNames.add(((AbstractEntityPersister) classMetadata).getPropertyColumnNames(i)[0]);
            }
            tableNames.put(aep.getTableName(), columnNames);
        }
        return tableNames;
    }
}
