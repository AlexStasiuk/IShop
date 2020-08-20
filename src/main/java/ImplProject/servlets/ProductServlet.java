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

@WebServlet("/product")
public class ProductServlet extends HttpServlet {

    ProductService productService = ProductService.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String price = request.getParameter("price");

        Object userEmail = request.getSession().getAttribute("userEmail");

        Optional<String> errorMessage = getErrorMessage(price);
        if (errorMessage.isPresent()) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(errorMessage.get());

            return;
        }
        Product product = new Product(name, description, Double.parseDouble(price));
        productService.create(product);

        response.setStatus(HttpServletResponse.SC_OK);
    }

    private Optional<String> getErrorMessage(String price) {
        if (Strings.isNullOrEmpty(price)) {
            return Optional.of("Price can't be empty");
        }
        try {
            double parsedPrice = Double.parseDouble(price);
            return parsedPrice > 0 ? Optional.empty() : Optional.of("Price can't less then zero");
        } catch (NumberFormatException e) {
            return Optional.of("Price should be numeric");
        }
    }

    // to get resource (product)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    // to update resource (product)
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        super.doPut(req, resp);
    }

    // to delete resource (product)
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        super.doDelete(req, resp);
    }

}