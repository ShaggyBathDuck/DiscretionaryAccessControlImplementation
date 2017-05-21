package com.bsk.configuration;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class UndisplayableTables {
    private List<String> tables;

    public UndisplayableTables() {
        tables = new ArrayList<>(2);
        tables.add("przekazywanieuprawnien");
        tables.add("uprawnienia");
    }

    public List<String> getTables() {
        return tables;
    }

}
