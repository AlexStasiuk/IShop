package ImplProject.servlets;


import ImplProject.entities.Bucket;
import ImplProject.services.BucketService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@WebServlet("/api/buckets")
public class BucketController extends HttpServlet {

    private BucketService bucketService = BucketService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String productId = req.getParameter("productId");
        int userId = (int) req.getSession().getAttribute("userId");

        bucketService.create(new Bucket(0, userId, Integer.parseInt(productId), new Date()));
    }
}