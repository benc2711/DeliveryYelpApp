import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        JDBCConnector dbConnector = new JDBCConnector();
        boolean loginSuccess = dbConnector.loginUser(username, password);

        JSONObject json = new JSONObject();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        if (loginSuccess) {
            json.put("status", "success");
            json.put("message", "Login successful.");
        } else {
            json.put("status", "fail");
            json.put("message", "Invalid username or password.");
        }

        response.getWriter().write(json.toString());
    }
}
