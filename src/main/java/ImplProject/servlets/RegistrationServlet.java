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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");


        if (ObjectUtils.allNotNull(name, surname, email, password)) {
            userService.create(email,name,surname,password);
            response.setStatus(HttpServletResponse.SC_CREATED);
            return;
        }
        response.setContentType("text/plain");
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }
}