package com.bsk.controllers;

import com.bsk.domain.Customer;
import com.bsk.domain.User;
import com.bsk.dto.CustomerDTO;
import com.bsk.services.CustomerService;
import com.bsk.services.UserService;
import com.bsk.util.EntityInfo;
import com.bsk.util.ModalEditData;
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

    public DashboardController(CustomerService customerService, UserService userService, EntityManager entityManager) {
        this.customerService = customerService;
        this.userService = userService;
        this.entityManager = entityManager;
    }


    @GetMapping("/")
    public String dashboard(Model model,
                            @RequestParam(required = false) String tabName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", authentication.getName());
        if (tabName != null)
            model.addAttribute("tabName", tabName);
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
        SortedMap<String, EntityInfo> entitiesInfo = getTables();
        Pair<String, SortedMap<String, EntityInfo>> data = new Pair<>(activeTabName, entitiesInfo);
        model.addAttribute("data", data);
        addEntitiesModelAttributes(model);
        return "fragments/table :: tableDiv";
    }

    @RequestMapping("/modalEdit")
    public String modalEdit(Model model, String activeTabName, Integer id) {
        ModalEditData modalEditData = new ModalEditData(getTables(), activeTabName, id);
        model.addAttribute("data", modalEditData);
        addEntitiesModelAttributes(model, id);
        return "fragments/modalEdit :: modalEdit";
    }

    @ModelAttribute("entitiesInfo")
    @ResponseBody
    public SortedMap<String, EntityInfo> getTables() {
        SortedMap<String, EntityInfo> entitiesInfo = new TreeMap<>();
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
            String entityName = aep.getRootEntityName().substring(aep.getRootEntityName().lastIndexOf('.') + 1);
            entityInfo.setTableNameInHb(entityName.toLowerCase());
            entityInfo.setColumnNamesInHb(columnNamesInHb);
            entitiesInfo.put(entityInfo.getTableNameInDb(), entityInfo);
        }
        return entitiesInfo;
    }

    private void addCommonModelAttributes(Model model) {
        model.addAttribute("customers", customerService.read());
        model.addAttribute("users", userService.read());
    }

    private void addEntitiesModelAttributes(Model model, Integer id) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("customer", customerService.findById(id));
        model.addAttribute("customerDTO", new CustomerDTO());
        addCommonModelAttributes(model);
    }

    private void addEntitiesModelAttributes(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("customer", new Customer());
        model.addAttribute("customerDTO", new CustomerDTO());
        addCommonModelAttributes(model);
    }

}
