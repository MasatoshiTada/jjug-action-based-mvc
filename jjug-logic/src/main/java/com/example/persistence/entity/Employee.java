package com.example.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e JOIN FETCH e.department ORDER BY e.empId ASC"),
    @NamedQuery(name = "Employee.findByEmpIdJoinFetchDepartment", query = "SELECT e FROM Employee e JOIN FETCH e.department WHERE e.empId = :empId"),
    @NamedQuery(name = "Employee.findByNameJoinFetchDepartment", query = "SELECT e FROM Employee e JOIN FETCH e.department WHERE LOWER(e.name) LIKE LOWER(:name) OR UPPER(e.name) LIKE UPPER(:name) ORDER BY e.empId ASC"),
    @NamedQuery(name = "Employee.deleteByEmpId", query = "DELETE FROM Employee e WHERE e.empId = :empId"),
    @NamedQuery(name = "Employee.countByEmpId", query = "SELECT COUNT(e) FROM Employee e WHERE e.empId = :empId")
})
public class Employee implements Serializable {
    @Id
    @Column(name = "emp_id")
    @GeneratedValue(strategy = GenerationType.TABLE,
            generator = "employeeIdGenerator")
    @TableGenerator(
            name = "employeeIdGenerator",
            table = "NUMBERING_TABLE",
            pkColumnName = "ID_TYPE",
            pkColumnValue = "EMPLOYEE_ID",
            valueColumnName = "LAST_ID",
            initialValue = 10,
            allocationSize = 1
    )
    private Integer empId;
    
    @Column(length = 40, nullable = false)
    private String name;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "joined_date")
    private Date joinedDate;
    
    @ManyToOne
    @JoinColumn(name = "dept_id", referencedColumnName = "dept_id")
    private Department department;

    /**
     * @return the empId
     */
    public Integer getEmpId() {
        return empId;
    }

    /**
     * @param empId the empId to set
     */
    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the joinedDate
     */
    public Date getJoinedDate() {
        return joinedDate;
    }

    /**
     * @param joinedDate the joinedDate to set
     */
    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    /**
     * @return the department
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.empId);
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.joinedDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employee other = (Employee) obj;
        if (!Objects.equals(this.empId, other.empId)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.joinedDate, other.joinedDate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Employee{" + "empId=" + empId + ", name=" + name + ", joinedDate=" + joinedDate + ", department=" + department + '}';
    }
    
}
