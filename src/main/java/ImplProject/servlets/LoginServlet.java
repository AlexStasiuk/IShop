package ImplProject.servlets;

import ImplProject.entities.User;
import ImplProject.services.UserService;
import org.apache.commons.lang3.ObjectUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (!ObjectUtils.allNotNull(email, password)) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        Optional<User> user = userService.getByEmail(email);

        if (user.isPresent() && user.get().getPassword().equalsIgnoreCase(password)) {
            request.setAttribute("userEmail", email);
            request.getRequestDispatcher("cabinet.jsp").forward(request, response);
            return;
        }
        // Todo Redirect?
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}