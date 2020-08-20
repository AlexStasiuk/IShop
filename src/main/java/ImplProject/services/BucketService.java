package ImplProject.services;

import ImplProject.daos.BucketDao;
import ImplProject.entities.Bucket;

import java.util.List;

public class BucketService {
    private BucketDao bucketDao;
    private static BucketService bucketService;

    public static BucketService getInstance() {
        if (bucketService == null) {
            bucketService = new BucketService();
        }
        return bucketService;
    }

    private BucketService() {
        bucketDao = new BucketDao();
    }

    public Bucket create(Bucket t) {
        return bucketDao.create(t);
    }

    public Bucket read(int id) {
        return bucketDao.read(id);
    }

    public void update(Bucket t) {
        bucketDao.update(t);
    }

    public void delete(Integer id) {
        bucketDao.delete(id);
    }

    public List<Bucket> readAllByUserId(int userId) {
        return bucketDao.readAllByUserId(userId);
    }
    public List<Bucket> readAll() {
        return bucketDao.readAll();
    }
}
