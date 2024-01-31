import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	
	//"OpenAI. (2023). Java Servlet Coding and Implementation Details. ChatGPT Session. General Reference"

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String confirmPassword = request.getParameter("confirmPassword");

        JSONObject json = new JSONObject();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

       
        if (!isValidEmail(email)) {
            json.put("status", "fail");
            json.put("message", "Invalid email address.");
            response.getWriter().write(json.toString());
            return;
        }

       
        if (!password.equals(confirmPassword)) {
            json.put("status", "fail");
            json.put("message", "Passwords do not match.");
            response.getWriter().write(json.toString());
            return;
        }

        JDBCConnector dbConnector = new JDBCConnector();
        if (dbConnector.checkUserExists(username)) {
            json.put("status", "fail");
            json.put("message", "Username already exists.");
        } else {
            dbConnector.insertNewUser(username, password, email);
            json.put("status", "success");
            json.put("message", "Account created successfully.");
        }

        response.getWriter().write(json.toString());
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pattern.matcher(email).matches();
    }
}
