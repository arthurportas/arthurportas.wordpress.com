package com.arthurportas.persistence.daos;

import com.arthurportas.persistence.entities.Employee;

import java.util.List;

/**
 * Created by arthurportas on 10/05/2017.
 */
public class EmployeeDaoImpl extends GenericDaoImpl<Employee> implements EmployeeDaoCustom<Employee>  {

    @Override
    public List<Employee> findByName(String pattern) {

        return getEntityManager()
                .createQuery("select e from Employee e where e.firstName LIKE :firstNamePlaceholder OR e.lastName LIKE :lastNamePlaceholder ")
                .setParameter("firstNamePlaceholder", "%" + pattern + "%")
                .setParameter("lastNamePlaceholder", "%" + pattern + "%")
                .getResultList();
    }
}
