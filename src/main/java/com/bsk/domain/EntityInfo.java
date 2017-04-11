package com.bsk.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
