package com.bsk.services;

import com.bsk.repositories.TableNamesRepository;
import com.bsk.util.EntityInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.SortedMap;

@Service
public class TableNamesService {
    private TableNamesRepository tableNamesRepository;

    @Autowired
    public TableNamesService(TableNamesRepository tableNamesRepository) {
        this.tableNamesRepository = tableNamesRepository;
    }

    public SortedMap<String, EntityInfo> getTableNames(){
        return tableNamesRepository.getTableNames();
    }


}
