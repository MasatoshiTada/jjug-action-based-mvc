package com.example.persistence.dao;

import com.example.persistence.entity.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * このテストクラス実行前にWildFlyを起動してください。
 * 例：
 * /path/to/wildfly/bin/standalone.sh
 */
public class EmployeeDaoTest {
    private EntityManager entityManager;
    private EmployeeDao employeeDao;

    @Before
    public void setup() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("testPU");
        entityManager = factory.createEntityManager();
        employeeDao = new EmployeeDao();
        employeeDao.entityManager = entityManager;
    }

    @After
    public void tearDown() {
        entityManager.close();
    }

    @Test
    public void 社員IDが1の社員はYumi_Wakatsuki() {
        int empId = 1;
        Employee employee = employeeDao.findByEmpId(empId);
        assertThat(employee.getName(), is("Yumi Wakatsuki"));
    }

    @Test
    public void 社員IDが99の社員は存在しない() {
        int empId = 99;
        Employee employee = employeeDao.findByEmpId(empId);
        assertNull(employee);
    }

}
