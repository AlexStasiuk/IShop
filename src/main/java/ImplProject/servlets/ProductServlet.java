package ImplProject.servlets;

import ImplProject.entities.Product;
import ImplProject.services.ProductService;
import com.google.common.base.Strings;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    ProductService productService = ProductService.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("id");
        Product product = productService.read(Integer.parseInt(productId));
        request.setAttribute("productName", product.getName());
        request.setAttribute("productD", product.getDescription());
        request.setAttribute("productP", product.getPrice());
        request.setAttribute("productId", product.getId());

        request.getRequestDispatcher("singleProduct.jsp").forward(request, response);

    }
}