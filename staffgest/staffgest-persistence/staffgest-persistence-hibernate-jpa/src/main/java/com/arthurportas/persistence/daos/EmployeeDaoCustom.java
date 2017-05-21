package com.arthurportas.persistence.daos;

import java.util.List;

/**
 * Created by arthurportas on 10/05/2017.
 */
public interface EmployeeDaoCustom<Employee> {

    List<Employee> findByName(String pattern);

}
