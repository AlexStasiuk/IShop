package ImplProject.servlets;

import ImplProject.entities.User;
import ImplProject.entities.UserRole;
import ImplProject.services.UserService;
import org.apache.commons.lang3.ObjectUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("name");
        String lastName = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        //Todo create Session

        if (ObjectUtils.allNotNull(firstName, lastName, email, password)) {
            userService.create(new User(0,firstName, lastName, email, UserRole.USER.toString(), password));
            request.setAttribute("userEmail", email);
            request.getRequestDispatcher("cabinet.jsp").forward(request, response);
        }

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}