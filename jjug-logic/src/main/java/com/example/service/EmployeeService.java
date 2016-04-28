package com.example.service;

import com.example.persistence.dao.EmployeeDao;
import com.example.persistence.entity.Employee;
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
    private EmployeeDao employeeDao;
    
    @Transactional(Transactional.TxType.REQUIRED)
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }
    
    @Transactional(Transactional.TxType.REQUIRED)
    public Optional<Employee> findByEmpId(Integer empId) {
        Optional<Employee> entity = employeeDao.findByEmpId(empId);
        return entity;
    }
    
    @Transactional(Transactional.TxType.REQUIRED)
    public List<Employee> findByName(String nameKey) {
        return employeeDao.findByName(nameKey);
    }
    
    @Transactional(Transactional.TxType.REQUIRED)
    public void insert(Employee employee) {
        employeeDao.insert(employee);
    }
    
    @Transactional(Transactional.TxType.REQUIRED)
    public void update(Employee employee) {
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
