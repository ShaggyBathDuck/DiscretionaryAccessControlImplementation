package com.bsk.util;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class EntityInfo {
    private String tableNameInDb;
    private String tableNameInHb;
    private ArrayList<String> columnNamesInDb;
    private ArrayList<String> columnNamesInHb;

    public static ArrayList<String> capitalizeColumnNames(ArrayList<String> columnNames) {
        ArrayList<String> newList = new ArrayList<>();
        for (String columnName : columnNames) {
            String newColumnName = StringUtils.capitalize(columnName);
            newList.add(newColumnName);
        }
        return newList;
    }
}
