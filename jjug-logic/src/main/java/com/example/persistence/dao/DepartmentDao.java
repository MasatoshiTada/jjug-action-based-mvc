package com.example.persistence.dao;

import com.example.persistence.entity.Department;
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
    public Department findById(Integer deptId) {
        return em.find(Department.class, deptId);
    }
    
    @Transactional(Transactional.TxType.REQUIRED)
    public List<Department> findAll() {
        return em.createNamedQuery("Department.findAll", Department.class)
                .getResultList();
    }
    
    @Transactional(Transactional.TxType.REQUIRED)
    public Long countByDeptId(Integer deptId) {
        return em.createNamedQuery("Department.countByDeptId", Long.class)
                .setParameter("deptId", deptId)
                .getSingleResult();
    }
}
