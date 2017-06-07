package com.bsk.util;

import com.bsk.domain.GrantPrivilege;
import com.bsk.domain.GrantPrivilegePK;
import com.bsk.domain.Privilege;
import com.bsk.dto.GrantPrivilegeDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GrantPrivilegesUtilities {


    public static boolean isEmpty(GrantPrivilege checkedPrivilege) {
        if (checkedPrivilege.getTake())
            return false;
        for (Privilege p : getPrivilegesList(checkedPrivilege)) {
            if (!PrivilegesUtilities.isEmpty(p))
                return false;
        }
        return true;
    }

    public static boolean haveCommonPart(GrantPrivilege first, GrantPrivilege second) {
        List<Privilege> firstList = GrantPrivilegesUtilities.getPrivilegesList(first);
        List<Privilege> secondList = GrantPrivilegesUtilities.getPrivilegesList(second);
        for (int i = 0; i < firstList.size(); i++) {
            List<String> firstModeList = PrivilegesUtilities.getListOfModes(firstList.get(i));
            List<String> secondModeList = PrivilegesUtilities.getListOfModes(secondList.get(i));
            for (int j = 0; j < firstModeList.size(); j++) {
                if ((secondModeList.get(j).equals("GRANT") || secondModeList.get(j).equals("ACCESS")) &&
                        (firstModeList.get(j).equals("GRANT") || firstModeList.get(j).equals("ACCESS")))
                    return true;
            }
        }
        return false;
    }

    public static boolean haveCommonPart(GrantPrivilegeDTO dto, GrantPrivilege userPrivilege) {
        GrantPrivilege privilegeDTO = new GrantPrivilege(
                new GrantPrivilegePK(),
                dto.getCustomer(),
                dto.getPurchase(),
                dto.getPurchasePosition(),
                dto.getWare(),
                dto.getWarehouseProduct(),
                dto.getSale(),
                dto.getSalePosition(),
                dto.getVendor(),
                false);
        return haveCommonPart(privilegeDTO, userPrivilege);
    }

    public static GrantPrivilege connect(GrantPrivilege base, GrantPrivilege connected) {
        List<Privilege> privilegeBaseList = GrantPrivilegesUtilities.getPrivilegesList(base);
        List<Privilege> privilegeConnectedList = GrantPrivilegesUtilities.getPrivilegesList(connected);

        for (int i = 0; i < privilegeBaseList.size(); i++) {
            privilegeBaseList.set(i, PrivilegesUtilities.connect(privilegeBaseList.get(i), privilegeConnectedList.get(i)));
        }
        GrantPrivilegePK grantPrivilegePK = base.getGrantPrivilegePK();
        if (connected.getGrantPrivilegePK().isAdmin())
            grantPrivilegePK = connected.getGrantPrivilegePK();
        return new GrantPrivilege(grantPrivilegePK,
                privilegeBaseList.get(0),
                privilegeBaseList.get(1),
                privilegeBaseList.get(2),
                privilegeBaseList.get(3),
                privilegeBaseList.get(4),
                privilegeBaseList.get(5),
                privilegeBaseList.get(6),
                privilegeBaseList.get(7),
                base.getTake() || connected.getTake());
    }

    public static List<Privilege> getPrivilegesList(GrantPrivilege grantPrivilege) {
        List<Privilege> privilegeList = new ArrayList<>(8);
        privilegeList.add(grantPrivilege.getCustomer());
        privilegeList.add(grantPrivilege.getPurchase());
        privilegeList.add(grantPrivilege.getPurchasePosition());
        privilegeList.add(grantPrivilege.getWare());
        privilegeList.add(grantPrivilege.getWarehouseProduct());
        privilegeList.add(grantPrivilege.getSale());
        privilegeList.add(grantPrivilege.getSalePosition());
        privilegeList.add(grantPrivilege.getVendor());
        return privilegeList;
    }

    public static GrantPrivilegeDTO mapTo(GrantPrivilege grantPrivilege){
        return new GrantPrivilegeDTO(grantPrivilege.getReceiver().getLogin(),
                grantPrivilege.getCustomer(),
                grantPrivilege.getPurchase(),
                grantPrivilege.getPurchasePosition(),
                grantPrivilege.getWare(),
                grantPrivilege.getWarehouseProduct(),
                grantPrivilege.getSale(),
                grantPrivilege.getSalePosition(),
                grantPrivilege.getVendor(),
                grantPrivilege.getTake());
    }

    public static GrantPrivilege removeAddedPrivileges(GrantPrivilege grantPrivilege, List<Integer> differences){
        List<Privilege> privilegeList = GrantPrivilegesUtilities.getPrivilegesList(grantPrivilege);
        for(int i=0; i< privilegeList.size(); i++)
            if(differences.get(i)==-1)
                privilegeList.set(i, new Privilege(81, "GRANT", "GRANT", "GRANT", "GRANT"));
            else
                privilegeList.set(i, new Privilege(1, "NONE", "NONE", "NONE", "NONE"));
        return new GrantPrivilege(grantPrivilege.getGrantPrivilegePK(),
                privilegeList.get(0),
                privilegeList.get(1),
                privilegeList.get(2),
                privilegeList.get(3),
                privilegeList.get(4),
                privilegeList.get(5),
                privilegeList.get(6),
                privilegeList.get(7),
                grantPrivilege.getTake());
    }
}
