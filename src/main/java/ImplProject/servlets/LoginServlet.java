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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || password == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Optional<User> user = userService.getByEmail(email);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }
}