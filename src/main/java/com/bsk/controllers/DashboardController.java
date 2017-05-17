package com.bsk.controllers;

import com.bsk.configuration.UndisplayableTables;
import com.bsk.domain.Customer;
import com.bsk.domain.User;
import com.bsk.dto.CustomerDTO;
import com.bsk.services.CustomerService;
import com.bsk.services.UserService;
import com.bsk.util.EntityInfo;
import javafx.util.Pair;
import org.hibernate.Session;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.*;

@Controller
public class DashboardController {

    private CustomerService customerService;

    private UserService userService;

    private EntityManager entityManager;

    private UndisplayableTables undisplayableTables;

    public DashboardController(CustomerService customerService, UserService userService, EntityManager entityManager, UndisplayableTables undisplayableTables) {
        this.customerService = customerService;
        this.userService = userService;
        this.entityManager = entityManager;
        this.undisplayableTables = undisplayableTables;
    }


    @GetMapping("/")
    public String dashboard(Model model,
                            @RequestParam(required = false) String tabName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", authentication.getName());
        if (tabName == null)
            return "dashboard";
        else {
            model.addAttribute("tabName", tabName);
            return "dashboard";
        }
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
        SortedMap<String, EntityInfo> entitiesInfo = getTables();
        Pair<String, SortedMap<String, EntityInfo>> data = new Pair<>(activeTabName, entitiesInfo);
        model.addAttribute("data", data);
        model.addAttribute("user", new User());
        model.addAttribute("customer", new Customer());
        model.addAttribute("customerDTO", new CustomerDTO());
        model.addAttribute("customers", customerService.read());
        model.addAttribute("users", userService.read());
        return "fragments/table :: tableDiv";
    }

    @ModelAttribute("entitiesInfo")
    @ResponseBody
    public SortedMap<String, EntityInfo> getTables() {
        SortedMap<String, EntityInfo> entitiesInfo = new TreeMap<>();
        Session session = entityManager.unwrap(Session.class);
        Map<String, ClassMetadata> hibernateMetadata = session.getSessionFactory().getAllClassMetadata();
        for (ClassMetadata classMetadata : hibernateMetadata.values()) {
            AbstractEntityPersister aep = (AbstractEntityPersister) classMetadata;
            if (!undisplayableTables.getTables().contains(aep.getTableName().toLowerCase())){
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
        }
        return entitiesInfo;
    }

}
