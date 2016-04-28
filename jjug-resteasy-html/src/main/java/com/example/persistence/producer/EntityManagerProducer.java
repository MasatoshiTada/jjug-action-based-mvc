package com.example.persistence.producer;

import java.io.Serializable;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Dependent
public class EntityManagerProducer implements Serializable {
    
    @PersistenceContext(unitName = "jjugPU")
    private EntityManager em;
    
    @Produces
    public EntityManager getEntityManager() {
        return em;
    }
}
