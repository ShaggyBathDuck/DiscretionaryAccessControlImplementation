package com.bsk.services;

import com.bsk.configuration.UndisplayableTables;
import com.bsk.domain.GrantPrivilege;
import com.bsk.domain.Privilege;
import com.bsk.repositories.TableNamesRepository;
import com.bsk.util.EntityInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class TableNamesService {
    private TableNamesRepository tableNamesRepository;

    private UndisplayableTables undisplayableTables;

    private GrantPrivilegeService grantPrivilegeService;

    public TableNamesService(TableNamesRepository tableNamesRepository, UndisplayableTables undisplayableTables, GrantPrivilegeService grantPrivilegeService) {
        this.tableNamesRepository = tableNamesRepository;
        this.undisplayableTables = undisplayableTables;
        this.grantPrivilegeService = grantPrivilegeService;
    }

    public SortedMap<String, EntityInfo> getDisplayableTableNames(){
        Map<String, EntityInfo> tables = tableNamesRepository.getTableNames().entrySet()
                .stream()
                .filter(p -> !undisplayableTables.getTables().contains(p.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        GrantPrivilege userPrivilege = grantPrivilegeService.getUserPrivilege(authentication.getName());
        for (EntityInfo entityInfo : tables.values()) {
            switch (entityInfo.getTableNameInHb()) {
                case "customer": {
                    entityInfo.setPrivilege(userPrivilege.getCustomer());
                    break;
                }
                case "purchase": {
                    entityInfo.setPrivilege(userPrivilege.getPurchase());
                    break;
                }
                case "purchaseposition": {
                    entityInfo.setPrivilege(userPrivilege.getPurchasePosition());
                    break;
                }
                case "sale": {
                    entityInfo.setPrivilege(userPrivilege.getSale());
                    break;
                }
                case "saleposition": {
                    entityInfo.setPrivilege(userPrivilege.getSalePosition());
                    break;
                }
                case "vendor": {
                    entityInfo.setPrivilege(userPrivilege.getVendor());
                    break;
                }
                case "ware": {
                    entityInfo.setPrivilege(userPrivilege.getWare());
                    break;
                }
                case "warehouseitem": {
                    entityInfo.setPrivilege(userPrivilege.getWarehouseProduct());
                    break;
                }
                case "user":{
                    if (Objects.nonNull(userPrivilege.getGrantPrivilegePK()) && userPrivilege.getGrantPrivilegePK().isAdmin())
                        entityInfo.setPrivilege(new Privilege(41, "ACCESS", "ACCESS", "ACCESS", "ACCESS"));
                    else
                        entityInfo.setPrivilege(new Privilege(0, "NONE", "NONE", "NONE", "NONE"));
                    break;
                }
                default: {
                    entityInfo.setPrivilege(new Privilege(0, "NONE", "NONE", "NONE", "NONE"));
                }
            }
        }
        return new TreeMap<>(tables);
    }

    public SortedMap<String, EntityInfo> getTableNames(){
        return tableNamesRepository.getTableNames();
    }


}
