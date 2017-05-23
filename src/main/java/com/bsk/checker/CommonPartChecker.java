package com.bsk.checker;


import com.bsk.connectors.PrivilegesConnector;
import com.bsk.domain.GrantPrivilege;
import com.bsk.domain.GrantPrivilegePK;
import com.bsk.domain.Privilege;
import com.bsk.dto.GrantPrivilegeDTO;

import java.util.List;

public class CommonPartChecker {

    public static boolean haveCommonPart(GrantPrivilegeDTO dto, GrantPrivilege userPrivilege){
        PrivilegesConnector privilegesConnector = new PrivilegesConnector();
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
        List<Privilege> privilegeDTOList = privilegesConnector.getPrivilegesList(privilegeDTO);
        List<Privilege> userPrivilegeList = privilegesConnector.getPrivilegesList(userPrivilege);
        for (int i=0; i<privilegeDTOList.size(); i++){
            List<String> privilegeModeDTO= privilegesConnector.getPrivilegeModes(privilegeDTOList.get(i));
            List<String> userPrivilegeModes= privilegesConnector.getPrivilegeModes(userPrivilegeList.get(i));
            for (int j=0; j<privilegeModeDTO.size(); j++){
                if((userPrivilegeModes.get(j).equals("GRANT") || userPrivilegeModes.get(j).equals("ACCESS"))&&
                        (privilegeModeDTO.get(j).equals("GRANT") || privilegeModeDTO.get(j).equals("ACCESS")))
                    return true;
            }
        }
        return false;
    }
}
