//package ImplProject.filters;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebFilter("*.jsp")
//public class JspFilter implements Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) {
//    }
//
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//
//        String requestURI = httpRequest.getRequestURI();
//        if (requestURI.endsWith(".jsp")) {
//            httpResponse.sendRedirect(httpRequest.getContextPath());
//            return;
//        }
//
//        chain.doFilter(request, response);
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//
//}
