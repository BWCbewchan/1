package qldt.daoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import qldt.dao.DienThoaiDAO;
import qldt.models.DienThoai;

import java.util.List;

public class DienThoaiDAOImpl implements DienThoaiDAO {
    private EntityManager entityManager;

    public DienThoaiDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public DienThoai save(DienThoai dienThoai) {
    	EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(dienThoai);
            transaction.commit();
            return dienThoai;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
        return null;
    }

    @Override
    public DienThoai update(DienThoai dienThoai) {
    	EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            
            // Kiểm tra xem thực thể có tồn tại không trước khi cập nhật
            DienThoai existingDienThoai = entityManager.find(DienThoai.class, dienThoai.getMaDT());
            if (existingDienThoai != null) {
                entityManager.merge(dienThoai); // Cập nhật thực thể
                transaction.commit();
                return dienThoai; // Trả về thực thể đã cập nhật
            } else {
                transaction.rollback(); // Nếu không tồn tại, rollback
                return null; // Trả về null nếu không tìm thấy
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null && transaction.isActive()) {
                transaction.rollback(); // rollback nếu có lỗi
            }
        }
        return null; // Trả về null nếu xảy ra lỗi
    }



    @Override
    public boolean delete(String maDT) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            DienThoai dienThoai = entityManager.find(DienThoai.class, maDT);
            if (dienThoai != null) {
                entityManager.remove(dienThoai);
                transaction.commit();
                return true;
            }
            transaction.rollback();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        return false;
    }


    @Override
    public DienThoai findById(String maDT) {
        System.out.println("Finding DienThoai with ID: " + maDT);
        return entityManager.find(DienThoai.class, maDT);
    }

    @Override
    public List<DienThoai> findAll() {
        return entityManager.createQuery("FROM DienThoai", DienThoai.class).getResultList();
    }
    @Override
    public List<DienThoai> searchByKeyword(String keyword) {
        // Use a parameterized query to avoid SQL injection
        return entityManager.createQuery(
            "SELECT d FROM DienThoai d WHERE d.maDT LIKE :keyword OR d.tenDT LIKE :keyword", DienThoai.class)
            .setParameter("keyword", "%" + keyword + "%")
            .getResultList();
    }

}
