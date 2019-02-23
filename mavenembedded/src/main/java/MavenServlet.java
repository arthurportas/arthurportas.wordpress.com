import org.apache.maven.cli.MavenCli;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Paths;

/**
 * Created by arthurportas on 20/02/2019.
 */
@WebServlet(
        name = "MavenServlet",
        urlPatterns = {"/mvn"}
)
public class MavenServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final PrintStream printStream = new PrintStream(resp.getOutputStream());
        final MavenCli cli = new MavenCli();
        cli.doMain(new String[]{"dependency:tree"},
                Paths.get(".").toAbsolutePath().toString(), printStream
             , printStream);
    }
}
