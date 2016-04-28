package com.example.persistence.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

@Dependent
public class EmployeeDao implements Serializable {
    @Inject
    private EntityManager em;

    /**
     * 
     * @return 全社員のリスト（社員が0名の場合は空のリスト）
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public List<com.example.persistence.entity.Employee> findAll() {
        return em.createNamedQuery("Employee.findAll", com.example.persistence.entity.Employee.class)
                .getResultList();
    }
    
    /**
     * 
     * @param empId 社員ID
     * @return 社員1件、該当する社員がいない場合はnull
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public com.example.persistence.entity.Employee findByEmpId(Integer empId) {
        try {
            return em.createNamedQuery("Employee.findByEmpIdJoinFetchDepartment", com.example.persistence.entity.Employee.class)
                    .setParameter("empId", empId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    /**
     * 
     * @return 名前にnameKeyを含む社員のリスト（該当する社員が0名の場合は空のリスト）
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public List<com.example.persistence.entity.Employee> findByName(String nameKey) {
        return em.createNamedQuery("Employee.findByNameJoinFetchDepartment", com.example.persistence.entity.Employee.class)
                .setParameter("name", "%" + nameKey + "%")
                .getResultList();
//        throw new RuntimeException(); // 実行時例外が発生するとロールバックされる
    }
    
    @Transactional(Transactional.TxType.REQUIRED)
    public void insert(com.example.persistence.entity.Employee employee) {
        em.persist(employee);
    }
    
    @Transactional(Transactional.TxType.REQUIRED)
    public void update(com.example.persistence.entity.Employee employee) {
        em.merge(employee);
    }
    
    @Transactional(Transactional.TxType.REQUIRED)
    public void delete(Integer empId) {
        Integer rows = em.createNamedQuery("Employee.deleteByEmpId", Integer.class)
                .setParameter("empId", empId)
                .executeUpdate();
        if (rows != 1) {
            // 実行時例外が発生するとロールバックされる
            throw new com.example.persistence.exception.DeleteFailureException();
        }
    }
    
    @Transactional(Transactional.TxType.REQUIRED)
    public Long countByEmpId(Integer empId) {
        return em.createNamedQuery("Employee.countByEmpId", Long.class)
                .setParameter("empId", empId)
                .getSingleResult();
    }
    
}
