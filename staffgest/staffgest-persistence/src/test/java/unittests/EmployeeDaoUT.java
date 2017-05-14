package unittests;

import com.arthurportas.persistence.daos.EmployeeDaoCustom;
import com.arthurportas.persistence.daos.EmployeeDaoImpl;
import com.arthurportas.persistence.entities.Employee;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by arthurportas on 10/05/2017.
 */
public class EmployeeDaoUT {

    private Faker faker;

    @Before
    public void init() {
        faker = new Faker();
    }

    @Test
    public void saveEmployee_returnsEntityNotNull_success() {

        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        Employee entity = employeeDao.save(getEmployee());
        Assert.assertNotNull(entity);
    }

    @Test
    public void saveEmployee_returnsEntityWithId_success() {

        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        Employee entity = employeeDao.save(getEmployee());
        Assert.assertNotNull(entity.getId());
    }

    @Test
    public void saveEmployee_returnsEntityWithFirstNameNotNull_success() {

        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        Employee entity = employeeDao.save(getEmployee());
        Assert.assertNotNull(entity.getFirstName());
    }

    @Test
    public void saveEmployee_returnsEntityWithFirstName_success() {

        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        Employee employee = getEmployee();
        Employee entity = employeeDao.save(employee);
        Assert.assertEquals(employee.getFirstName(), entity.getFirstName());
    }

    @Test
    public void saveEmployee_returnsEntityWithLastNameNotNull_success() {

        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        Employee entity = employeeDao.save(getEmployee());
        Assert.assertNotNull(entity.getLastName());
    }

    @Test
    public void saveEmployee_returnsEntityWithLastName_success() {

        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        Employee employee = getEmployee();
        Employee entity = employeeDao.save(employee);
        Assert.assertEquals(employee.getLastName(), entity.getLastName());
    }

    @Test
    public void updateEmployee_returnsEntityWithFirstNameUpdated_success() {

        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        Employee entity = employeeDao.save(getEmployee());

        entity.setFirstName("foo");
        employeeDao.update(entity);
        Assert.assertEquals("foo", entity.getFirstName());
    }

    @Test
    public void searchEmployeeByPattern_returnsEntity_success() {

        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        Employee entity = employeeDao.save(getEmployee());
        String firstName = entity.getFirstName();

        Assert.assertNotNull(employeeDao.findByName(StringUtils.substring(firstName, firstName.length() > 3 ? 3 : 1)));
    }

    private Employee getEmployee() {
        return Employee.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .build();
    }
}
