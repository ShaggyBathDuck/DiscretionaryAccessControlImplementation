package com.bsk.connectors;

import com.bsk.domain.GrantPrivilege;
import com.bsk.domain.Privilege;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PrivilegesConnector {

    public GrantPrivilege connect(GrantPrivilege base, GrantPrivilege connected){
        List<Privilege> privilegeBaseList = this.getPrivilegesList(base);
        List<Privilege> privilegeConnectedList = this.getPrivilegesList(connected);
        for (int i=0; i<privilegeBaseList.size(); i++){
            privilegeBaseList.set(i, this.connectPrivileges(privilegeBaseList.get(i), privilegeConnectedList.get(i)));
        }

        return new GrantPrivilege(null,
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

    private  List<Privilege> getPrivilegesList(GrantPrivilege grantPrivilege){
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
    private List<String> getPrivilegeModes(Privilege privilege){
        List<String> modeList = new ArrayList<>(4);
        modeList.add(privilege.getCreate());
        modeList.add(privilege.getRead());
        modeList.add(privilege.getUpdate());
        modeList.add(privilege.getDelete());
        return modeList;
    }

    private Privilege connectPrivileges(Privilege base, Privilege connected){
        List<String> baseList =this.getPrivilegeModes(base);
        List<String> connectedList =this.getPrivilegeModes(connected);

        for (int i=0; i<baseList.size();i++){
            if(baseList.get(i).equals("NONE") || (baseList.get(i).equals("ACCESS") && connectedList.get(i).equals("GRANT")))
                baseList.set(i,connectedList.get(i));
        }
        return new Privilege(0, baseList.get(0),baseList.get(1),baseList.get(2),baseList.get(3));
    }
}
