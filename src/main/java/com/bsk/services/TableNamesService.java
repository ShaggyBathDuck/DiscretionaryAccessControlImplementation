package com.bsk.services;

import com.bsk.configuration.UndisplayableTables;
import com.bsk.repositories.TableNamesRepository;
import com.bsk.util.EntityInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.tree.Tree;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class TableNamesService {
    private TableNamesRepository tableNamesRepository;

    private UndisplayableTables undisplayableTables;

    @Autowired
    public TableNamesService(TableNamesRepository tableNamesRepository, UndisplayableTables undisplayableTables) {
        this.tableNamesRepository = tableNamesRepository;
        this.undisplayableTables = undisplayableTables;
    }



    public SortedMap<String, EntityInfo> getDisplayableTableNames(){
        Map<String, EntityInfo> temp =tableNamesRepository.getTableNames().entrySet()
                .stream()
                .filter(p -> !undisplayableTables.getTables().contains(p.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return new TreeMap<>(temp);
    }

    public SortedMap<String, EntityInfo> getTableNames(){
        return tableNamesRepository.getTableNames();
    }


}
