package com.bsk.controllers;


import com.bsk.domain.*;
import com.bsk.services.*;
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

    private VendorService vendorService;

    private WareService wareService;

    private WarehouseItemService warehouseItemService;

    private PurchaseService purchaseService;

    private PurchasePositionService purchasePositionService;

    private SaleService saleService;

    private SalePositionService salePositionService;

    private EntityManager entityManager;

    private TableNamesService tableNamesService;

    public DashboardController(CustomerService customerService, UserService userService, VendorService vendorService,
                               WareService wareService, WarehouseItemService warehouseItemService,
                               PurchaseService purchaseService, PurchasePositionService purchasePositionService,
                               SaleService saleService, SalePositionService salePositionService,
                               EntityManager entityManager, TableNamesService tableNamesService) {
        this.customerService = customerService;
        this.userService = userService;
        this.vendorService = vendorService;
        this.wareService = wareService;
        this.warehouseItemService = warehouseItemService;
        this.purchaseService = purchaseService;
        this.purchasePositionService = purchasePositionService;
        this.saleService = saleService;
        this.salePositionService = salePositionService;
        this.entityManager = entityManager;
        this.tableNamesService = tableNamesService;
    }

    @GetMapping("/")
    public String dashboard(Model model, @RequestParam(required = false) String tabName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", authentication.getName());
        if (tabName != null)
            model.addAttribute("tabName", tabName);
        return "dashboard";
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
    public SortedMap<String, EntityInfo> getTables(){
        return tableNamesService.getDisplayableTableNames();
    }

    private void addCommonModelAttributes(Model model) {
        model.addAttribute("customers", customerService.read());
        model.addAttribute("users", userService.read());
        model.addAttribute("vendors", vendorService.read());
        model.addAttribute("wares", wareService.read());
        model.addAttribute("warehouseitems", warehouseItemService.read());
        model.addAttribute("purchases", purchaseService.read());
        model.addAttribute("purchasepositions", purchasePositionService.read());
        model.addAttribute("sales", saleService.read());
        model.addAttribute("salepositions", salePositionService.read());
    }


    private void addEntitiesModelAttributes(Model model, Integer id) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("customer", customerService.findById(id));
        model.addAttribute("vendor", vendorService.findById(id));
        model.addAttribute("ware", wareService.findById(id));
        model.addAttribute("warehouseitem", warehouseItemService.findById(id));
        model.addAttribute("purchase", purchaseService.findById(id));
        model.addAttribute("purchaseposition", purchasePositionService.findById(id));
        model.addAttribute("sale", saleService.findById(id));
        model.addAttribute("saleposition", salePositionService.findById(id));
        addCommonModelAttributes(model);
    }

    private void addEntitiesModelAttributes(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("customer", new Customer());
        model.addAttribute("vendor", new Vendor());
        model.addAttribute("ware", new Ware());
        model.addAttribute("warehouseitem", new WarehouseItem());
        model.addAttribute("purchase", new Purchase());
        model.addAttribute("purchaseposition", new PurchasePosition());
        model.addAttribute("sale", new Sale());
        model.addAttribute("saleposition", new SalePosition());
        addCommonModelAttributes(model);
    }

}
