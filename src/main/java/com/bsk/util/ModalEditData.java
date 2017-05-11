package com.bsk.util;


import com.bsk.domain.EntityInfo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.SortedMap;

@Data
@AllArgsConstructor
public class ModalEditData {
    SortedMap<String, EntityInfo> tables;
    String activeTabName;
    int objectId;
}
