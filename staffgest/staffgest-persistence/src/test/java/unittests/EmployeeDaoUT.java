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

        EmployeeDaoCustom employeeDaoCustom = new EmployeeDaoImpl();
        Employee entity = employeeDaoCustom.save(getEmployee());
        Assert.assertNotNull(entity);
    }

    @Test
    public void saveEmployee_returnsEntityWithId_success() {

        EmployeeDaoCustom employeeDaoCustom = new EmployeeDaoImpl();
        Employee entity = employeeDaoCustom.save(getEmployee());
        Assert.assertNotNull(entity.getId());
    }

    @Test
    public void saveEmployee_returnsEntityWithFirstNameNotNull_success() {

        EmployeeDaoCustom employeeDaoCustom = new EmployeeDaoImpl();
        Employee entity = employeeDaoCustom.save(getEmployee());
        Assert.assertNotNull(entity.getFirstName());
    }

    @Test
    public void saveEmployee_returnsEntityWithFirstName_success() {

        EmployeeDaoCustom employeeDaoCustom = new EmployeeDaoImpl();
        Employee employee = getEmployee();
        Employee entity = employeeDaoCustom.save(employee);
        Assert.assertEquals(employee.getFirstName(), entity.getFirstName());
    }

    @Test
    public void saveEmployee_returnsEntityWithLastNameNotNull_success() {

        EmployeeDaoCustom employeeDaoCustom = new EmployeeDaoImpl();
        Employee entity = employeeDaoCustom.save(getEmployee());
        Assert.assertNotNull(entity.getLastName());
    }

    @Test
    public void saveEmployee_returnsEntityWithLastName_success() {

        EmployeeDaoCustom employeeDaoCustom = new EmployeeDaoImpl();
        Employee employee = getEmployee();
        Employee entity = employeeDaoCustom.save(employee);
        Assert.assertEquals(employee.getLastName(), entity.getLastName());
    }

    @Test
    public void updateEmployee_returnsEntityWithFirstNameUpdated_success() {

        EmployeeDaoCustom employeeDaoCustom = new EmployeeDaoImpl();
        Employee entity = employeeDaoCustom.save(getEmployee());

        entity.setFirstName("foo");
        employeeDaoCustom.update(entity);
        Assert.assertEquals("foo", entity.getFirstName());
    }

    @Test
    public void searchEmployeeByPattern_returnsEntity_success() {

        EmployeeDaoCustom employeeDaoCustom = new EmployeeDaoImpl();
        Employee entity = employeeDaoCustom.save(getEmployee());
        String firstName = entity.getFirstName();

        Assert.assertNotNull(employeeDaoCustom.findByName(StringUtils.substring(firstName, firstName.length() > 3 ? 3 : 1)));
    }

    private Employee getEmployee() {
        return Employee.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .build();
    }
}
