package com.bsk.controllers;


import com.bsk.domain.Customer;
import com.bsk.domain.EntityInfo;
import com.bsk.domain.User;
import com.bsk.services.CustomerService;
import com.bsk.services.UserService;
import javafx.util.Pair;
import org.hibernate.Session;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import java.util.*;

@Controller
public class DashboardController {

    private CustomerService customerService;

    private UserService userService;

    private EntityManager entityManager;

    public DashboardController(CustomerService customerService, UserService userService, EntityManager entityManager) {
        this.customerService = customerService;
        this.userService = userService;
        this.entityManager = entityManager;
    }


    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @ModelAttribute("customers")
    @ResponseBody
    public List<Customer> getCustomers() {
        return customerService.read();
    }

    @ModelAttribute("users")
    @ResponseBody
    public List<User> getUsers() {
        return userService.read();
    }

    @PostMapping("/table")
    public String table(Model model, String activeTabName) {
        TreeMap<String, EntityInfo> entitiesInfo = getTables();
        Pair<String, TreeMap<String, EntityInfo>> data = new Pair<>(activeTabName, entitiesInfo);
        model.addAttribute("data", data);
        return "fragments/table :: tableDiv";
    }

    @PostMapping("/frag1")
    public String frag1(Model model) {
        model.addAttribute("add", "hello");
        return "fragments/frag1 :: common";
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
            entityInfo.setTableNameInDb(aep.getTableName().toLowerCase());
            entityInfo.setColumnNamesInDb(columnNamesInDb);
            ArrayList<String> columnNamesInHb = new ArrayList<>();
            columnNamesInHb.add(classMetadata.getIdentifierPropertyName());
            columnNamesInHb.addAll(Arrays.asList(classMetadata.getPropertyNames()));
            String entityName = aep.getRootEntityName().substring(aep.getRootEntityName().lastIndexOf('.')+1);
            entityInfo.setTableNameInHb(entityName.toLowerCase());
            entityInfo.setColumnNamesInHb(columnNamesInHb);
            entitiesInfo.put(entityInfo.getTableNameInDb(), entityInfo);
        }
        return entitiesInfo;
    }

}
