package ImplProject.services;

import ImplProject.daos.ProductDao;
import ImplProject.entities.Product;

import java.util.List;

public class ProductService {
    private ProductDao productDao;
    private static ProductService productService;

    private ProductService() {
        productDao = new ProductDao();
    }
    public static ProductService getInstance(){
        if(productService == null){
            productService = new ProductService();
        }
        return  productService;
    }
    public Product create(Product t) {
        return productDao.create(t);
    }

    public Product read(int id) {
        return productDao.read(id);
    }

    public void update(Product t) {
        productDao.update(t);
    }

    public void delete(Integer id) {
        productDao.delete(id);
    }

    public List<Product> readAll() {
        return productDao.readAll();
    }
}
