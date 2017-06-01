package com.bsk.services;

import com.bsk.domain.Privilege;
import com.bsk.repositories.PrivilegeRepository;
import com.bsk.util.PrivilegesUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivilegeService {

    private PrivilegeRepository privilegeRepository;

    @Autowired
    public PrivilegeService(PrivilegeRepository privilegeRepository) {
        this.privilegeRepository = privilegeRepository;
    }

    public List<Privilege> read(){
        return privilegeRepository.findAll();
    }

    public Privilege findFirstByCRUD(Privilege privilege){
        return this.read().stream().filter(p ->p.equals(privilege)).findFirst().get();
    }
    public Privilege findFirstByCRUD(List<String> privilegeList){
        return this.read().stream().filter(p ->
            (p.getCreate().equals(privilegeList.get(0))&&
                    p.getRead().equals(privilegeList.get(1))&&
                    p.getUpdate().equals(privilegeList.get(2))&&
                    p.getDelete().equals(privilegeList.get(3)))).findFirst().get();
    }

    public Privilege findById(Integer id) {
        return this.privilegeRepository.findOne(id);
    }

    public Privilege difference(Privilege minuend, Privilege subtrahend ){
        List<String> minuendList = PrivilegesUtilities.getListOfModes(minuend);
        List<String> subtrahendList = PrivilegesUtilities.getListOfModes(subtrahend);
        for(int i = 0; i<minuendList.size(); i++){
            if((minuendList.get(i).equals("ACCESS")&&subtrahendList.get(i).equals("NONE"))||(minuendList.get(i).equals("GRANT")&&subtrahendList.get(i).equals("ACCESS")))
                minuendList.set(i, "ACCESS");
            else if(minuendList.get(i).equals("GRANT")&&subtrahendList.get(i).equals("NONE"))
                minuendList.set(i, "GRANT");
            else
                minuendList.set(i, "NONE");
        }
        return this.findFirstByCRUD(minuendList);
    }

    public Privilege changeAccessesToGrants(Privilege privilege){
        List<String> modesList= PrivilegesUtilities.getListOfModes(privilege);
        for(int i=0; i<modesList.size(); i++)
            if(modesList.get(i).equals("ACCESS"))
                modesList.set(i, "GRANT");
        return findFirstByCRUD(modesList);
    }
}
