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

    public boolean isEmpty(GrantPrivilege checkedPrivilege){
        if (!checkedPrivilege.getTake())
            return false;
        for(Privilege p: getPrivilegesList(checkedPrivilege)){
            if(!PrivilegesUtilities.isEmpty(p))
                return false;
        }
        return true;
    }

    public static boolean haveCommonPart(GrantPrivilegeDTO dto, GrantPrivilege userPrivilege){
        GrantPrivilege privilegeDTO= new GrantPrivilege(
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
        List<Privilege> privilegeDTOList = GrantPrivilegesUtilities.getPrivilegesList(privilegeDTO);
        List<Privilege> userPrivilegeList = GrantPrivilegesUtilities.getPrivilegesList(userPrivilege);
        for (int i=0; i<privilegeDTOList.size(); i++){
            List<String> privilegeModeDTO= PrivilegesUtilities.getListOfModes(privilegeDTOList.get(i));
            List<String> userPrivilegeModes= PrivilegesUtilities.getListOfModes(userPrivilegeList.get(i));
            for (int j=0; j<privilegeModeDTO.size(); j++){
                if((userPrivilegeModes.get(j).equals("GRANT") || userPrivilegeModes.get(j).equals("ACCESS"))&&
                        (privilegeModeDTO.get(j).equals("GRANT") || privilegeModeDTO.get(j).equals("ACCESS")))
                    return true;
            }
        }
        return false;
    }

    public static GrantPrivilege connect(GrantPrivilege base, GrantPrivilege connected){
        List<Privilege> privilegeBaseList = GrantPrivilegesUtilities.getPrivilegesList(base);
        List<Privilege> privilegeConnectedList = GrantPrivilegesUtilities.getPrivilegesList(connected);

        for (int i=0; i<privilegeBaseList.size(); i++){
            privilegeBaseList.set(i, PrivilegesUtilities.connect(privilegeBaseList.get(i), privilegeConnectedList.get(i)));
        }
        GrantPrivilegePK grantPrivilegePK=base.getGrantPrivilegePK();
        if (connected.getGrantPrivilegePK().isAdmin())
            grantPrivilegePK=connected.getGrantPrivilegePK();
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

    private static List<Privilege> getPrivilegesList(GrantPrivilege grantPrivilege){
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

}
