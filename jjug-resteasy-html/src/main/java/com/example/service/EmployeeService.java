package com.example.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 *
 * @author tada
 */
@Dependent
public class EmployeeService implements Serializable {
    
    @Inject
    private com.example.persistence.dao.EmployeeDao employeeDao;
    
    @Transactional(Transactional.TxType.REQUIRED)
    public List<com.example.persistence.entity.Employee> findAll() {
        return employeeDao.findAll();
    }
    
    @Transactional(Transactional.TxType.REQUIRED)
    public com.example.persistence.entity.Employee findByEmpId(Integer empId) {
        return employeeDao.findByEmpId(empId);
    }
    
    @Transactional(Transactional.TxType.REQUIRED)
    public List<com.example.persistence.entity.Employee> findByName(String nameKey) {
        return employeeDao.findByName(nameKey);
    }
    
    @Transactional(Transactional.TxType.REQUIRED)
    public void insert(com.example.persistence.entity.Employee employee) {
        employeeDao.insert(employee);
    }
    
    @Transactional(Transactional.TxType.REQUIRED)
    public void update(com.example.persistence.entity.Employee employee) {
        employeeDao.update(employee);
    }
    
    @Transactional(Transactional.TxType.REQUIRED)
    public void delete(Integer empId) {
        employeeDao.delete(empId);
    }
    
    @Transactional(Transactional.TxType.REQUIRED)
    public boolean exists(Integer empId) {
        return employeeDao.countByEmpId(empId) == 1L;
    }
}
