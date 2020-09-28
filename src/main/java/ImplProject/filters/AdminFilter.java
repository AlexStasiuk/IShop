package ImplProject.filters;

import ImplProject.entities.UserRole;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Arrays;

@WebFilter({"/createProduct"})
public class AdminFilter implements Filter {

    private FilterService filterService = FilterService.getInstance();

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        filterService.validateCallForPage(request, response, chain, Arrays.asList(UserRole.ADMIN));
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

}