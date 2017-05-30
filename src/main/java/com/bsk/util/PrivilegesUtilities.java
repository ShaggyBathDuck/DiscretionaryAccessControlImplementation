package com.bsk.util;

import com.bsk.domain.Privilege;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PrivilegesUtilities {

    public static Privilege connect(Privilege base, Privilege connected) {
        List<String> baseList = PrivilegesUtilities.getListOfModes(base);
        List<String> connectedList = PrivilegesUtilities.getListOfModes(connected);

        for (int i = 0; i < baseList.size(); i++) {
            if (baseList.get(i).equals("NONE") || (baseList.get(i).equals("ACCESS") && connectedList.get(i).equals("GRANT")))
                baseList.set(i, connectedList.get(i));
        }
        return new Privilege(0, baseList.get(0), baseList.get(1), baseList.get(2), baseList.get(3));
    }

    public static List<String> getListOfModes(Privilege privilege) {
        List<String> modeList = new ArrayList<>(4);
        modeList.add(privilege.getCreate());
        modeList.add(privilege.getRead());
        modeList.add(privilege.getUpdate());
        modeList.add(privilege.getDelete());
        return modeList;
    }

    public static boolean isEmpty(Privilege privilege) {
        return (privilege.getCreate().equalsIgnoreCase("NONE") &&
                privilege.getRead().equalsIgnoreCase("NONE") &&
                privilege.getUpdate().equalsIgnoreCase("NONE") &&
                privilege.getDelete().equalsIgnoreCase("NONE"));
    }
}
