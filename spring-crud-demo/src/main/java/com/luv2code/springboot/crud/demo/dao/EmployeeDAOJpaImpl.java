package com.luv2code.springboot.crud.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.crud.demo.enitity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {
	//constructor injection
	private EntityManager entityManager;
	
	@Autowired
	public EmployeeDAOJpaImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	@Override
	public List<Employee> findAll() {
		//create query
		Query theQuery = 
				entityManager.createQuery("from Employee");
		//execute query and get result list
		List<Employee> employees = theQuery.getResultList();
		//return the results
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		//get employee
		Employee theEmployee = entityManager.find(Employee.class,theId);
		//return employee
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		
		//save or update the employee
		Employee dbEmployee = entityManager.merge(theEmployee);
		//update with id from db ... so we can get generated id for save/insert
		theEmployee.setId(0);

	}

	@Override
	public void deleteById(int theId) {
		
		//delete object with pk
		Query theQuery = entityManager.createQuery("delete from Employee where id=:employeeId");
		theQuery.setParameter("employeeId", theId);
		theQuery.executeUpdate();

	}

}
