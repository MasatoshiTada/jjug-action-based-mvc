package com.example.persistence.dao;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

/**
 *
 * @author tada
 */
@Dependent
public class DepartmentDao implements Serializable {
    
    @Inject
    private EntityManager em;
    
    @Transactional(Transactional.TxType.REQUIRED)
    public com.example.persistence.entity.Department findById(Integer deptId) {
        return em.find(com.example.persistence.entity.Department.class, deptId);
    }
    
    @Transactional(Transactional.TxType.REQUIRED)
    public List<com.example.persistence.entity.Department> findAll() {
        return em.createNamedQuery("Department.findAll", com.example.persistence.entity.Department.class)
                .getResultList();
    }
    
    @Transactional(Transactional.TxType.REQUIRED)
    public Long countByDeptId(Integer deptId) {
        return em.createNamedQuery("Department.countByDeptId", Long.class)
                .setParameter("deptId", deptId)
                .getSingleResult();
    }
}
