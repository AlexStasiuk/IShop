package ImplProject.services;

import ImplProject.daos.BucketDao;
import ImplProject.entities.Bucket;

import java.util.List;

public class BucketService {
    private BucketDao bucketDao;

    public BucketService() {
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

    public List<Bucket> readAll() {
        return bucketDao.readAll();
    }
}
