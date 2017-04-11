package com.bsk.controllers;


import com.bsk.domain.Customer;
import com.bsk.domain.EntityInfo;
import com.bsk.services.CustomerService;
import org.hibernate.Session;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import java.util.*;

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

    @ModelAttribute("entitiesInfo")
    @ResponseBody
    public TreeMap<String, EntityInfo> getTables() {
        TreeMap<String, EntityInfo> entitiesInfo = new TreeMap<>();
        Session session = entityManager.unwrap(Session.class);
        Map<String, ClassMetadata> hibernateMetadata = session.getSessionFactory().getAllClassMetadata();
        for (ClassMetadata classMetadata : hibernateMetadata.values()) {
            AbstractEntityPersister aep = (AbstractEntityPersister) classMetadata;
            int propertiesCounter = classMetadata.getPropertyNames().length;
            ArrayList<String> columnNamesInDb = new ArrayList<>();
            columnNamesInDb.add(((AbstractEntityPersister) classMetadata).getKeyColumnNames()[0]);
            for (int i = 0; i < propertiesCounter; i++) {
                columnNamesInDb.add(((AbstractEntityPersister) classMetadata).getPropertyColumnNames(i)[0]);
            }
            EntityInfo entityInfo = new EntityInfo();
            entityInfo.setTableNameInDb(aep.getTableName());
            entityInfo.setColumnNamesInDb(columnNamesInDb);
            ArrayList<String> columnNamesInHb = new ArrayList<>();
            columnNamesInHb.add(classMetadata.getIdentifierPropertyName());
            columnNamesInHb.addAll(Arrays.asList(classMetadata.getPropertyNames()));
            String entityName = aep.getRootEntityName().substring(aep.getRootEntityName().lastIndexOf('.')+1);
            entityInfo.setTableNameInHb(entityName);
            entityInfo.setColumnNamesInHb(columnNamesInHb);
            entitiesInfo.put(entityInfo.getTableNameInDb(), entityInfo);
        }
        return entitiesInfo;
    }

}
