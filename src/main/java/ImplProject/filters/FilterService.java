package ImplProject.filters;

import ImplProject.entities.UserRole;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class FilterService {

    private static FilterService filterService;

    private FilterService() {
    }

    public static FilterService getInstance() {
        if (filterService == null)
            filterService = new FilterService();
        return filterService;
    }

    public void validateCallForPage(ServletRequest request,
                                    ServletResponse response,
                                    FilterChain chain,
                                    List<UserRole> userRoles) throws ServletException, IOException {

        try {
            HttpSession session = ((HttpServletRequest) request).getSession();
            UserRole role = UserRole.valueOf((String) session.getAttribute("userRole"));

            if (userRoles.contains(role)) {
                chain.doFilter(request, response);
            } else {
                request.getRequestDispatcher("/").forward(request, response);
            }
        } catch (Exception e) {
            request.getRequestDispatcher("/").forward(request, response);
        }
    }
}