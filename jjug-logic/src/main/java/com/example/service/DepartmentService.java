package com.example.service;

import com.example.persistence.dao.DepartmentDao;
import com.example.persistence.entity.Department;
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
    private DepartmentDao departmentDao;

    @Transactional(Transactional.TxType.REQUIRED)
    public List<Department> findAll() {
        return departmentDao.findAll();
    }
    
    @Transactional(Transactional.TxType.REQUIRED)
    public Department findById(Integer deptId) {
        Department entity = departmentDao.findById(deptId);
        return entity;
    }
    
    @Transactional(Transactional.TxType.REQUIRED)
    public boolean exists(Integer deptId) {
        return departmentDao.countByDeptId(deptId) == 1L;
    }
    
}
