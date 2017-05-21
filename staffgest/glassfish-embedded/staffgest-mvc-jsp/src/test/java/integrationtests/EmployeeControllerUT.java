package integrationtests;

import com.arthurportas.presentation.servlets.EmployeeController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by arthurportas on 08/05/2017.
 */
public class EmployeeControllerUT {

    private EmployeeController servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @Before
    public void setUp() {
        servlet = new EmployeeController();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
    }

    @Test
    public void correctUsernameInRequest() throws ServletException, IOException {

    }
}
