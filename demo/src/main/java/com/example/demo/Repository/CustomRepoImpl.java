package com.example.demo.Repository;

import com.example.demo.model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public class CustomRepoImpl<employee> implements CustomRepo<employee> {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public List<Employee> SearchByEmailLastName (String email, String lastName){


    }

}
