package com.bsk.repositories;


import com.bsk.configuration.UndisplayableTables;
import com.bsk.util.EntityInfo;
import org.hibernate.Session;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.*;

@Repository
public class TableNamesRepository {

    private SortedMap<String, EntityInfo> tableNames;

    private EntityManager entityManager;

    private UndisplayableTables undisplayableTables;

    @Autowired
    public TableNamesRepository(EntityManager entityManager, UndisplayableTables undisplayableTables) {
        this.tableNames = new TreeMap<>();
        this.entityManager = entityManager;
        this.undisplayableTables = undisplayableTables;
    }

    public SortedMap<String, EntityInfo> getTableNames(){
        if(tableNames.isEmpty())
            this.tableNames= this.getTablesFromDB();
        return tableNames;
    }

    private SortedMap<String, EntityInfo> getTablesFromDB() {
        SortedMap<String, EntityInfo> entitiesInfo = new TreeMap<>();
        Session session = entityManager.unwrap(Session.class);
        Map<String, ClassMetadata> hibernateMetadata = session.getSessionFactory().getAllClassMetadata();
        for (ClassMetadata classMetadata : hibernateMetadata.values()) {
            AbstractEntityPersister aep = (AbstractEntityPersister) classMetadata;
                int propertiesCounter = classMetadata.getPropertyNames().length;
                ArrayList<String> columnNamesInDb = new ArrayList<>();
                columnNamesInDb.add(((AbstractEntityPersister) classMetadata).getKeyColumnNames()[0]);
                for (int i = 0; i < propertiesCounter; i++) {
                    if (((AbstractEntityPersister) classMetadata).getPropertyColumnNames(i).length != 0) {
                        columnNamesInDb.add(((AbstractEntityPersister) classMetadata).getPropertyColumnNames(i)[0]);
                    }
                }
                EntityInfo entityInfo = new EntityInfo();
                entityInfo.setTableNameInDb(aep.getTableName().toLowerCase());
                entityInfo.setColumnNamesInDb(columnNamesInDb);
                ArrayList<String> columnNamesInHb = new ArrayList<>();
                columnNamesInHb.add(classMetadata.getIdentifierPropertyName());
                columnNamesInHb.addAll(Arrays.asList(classMetadata.getPropertyNames()));
                String entityName = aep.getRootEntityName().substring(aep.getRootEntityName().lastIndexOf('.') + 1);
                entityInfo.setTableNameInHb(entityName.toLowerCase());
                entityInfo.setColumnNamesInHb(columnNamesInHb);
                entitiesInfo.put(entityInfo.getTableNameInDb(), entityInfo);
        }
        return entitiesInfo;
    }

}
