package com.example.service;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 *
 * @author tada
 */
@Dependent
public class DepartmentService implements Serializable {
    
    @Inject
    private com.example.persistence.dao.DepartmentDao departmentDao;

    @Transactional(Transactional.TxType.REQUIRED)
    public List<com.example.persistence.entity.Department> findAll() {
        return departmentDao.findAll();
    }
    
    @Transactional(Transactional.TxType.REQUIRED)
    public com.example.persistence.entity.Department findById(Integer deptId) {
        com.example.persistence.entity.Department entity = departmentDao.findById(deptId);
        return entity;
    }
    
    @Transactional(Transactional.TxType.REQUIRED)
    public boolean exists(Integer deptId) {
        return departmentDao.countByDeptId(deptId) == 1L;
    }
    
}
