package qldt.daoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import qldt.dao.NhaCungCapDAO;
import qldt.models.NhaCungCap;

import java.util.List;

public class NhaCungCapDAOImpl implements NhaCungCapDAO {
    private EntityManager entityManager;

    public NhaCungCapDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public NhaCungCap save(NhaCungCap nhaCungCap) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(nhaCungCap);
            transaction.commit();
            return nhaCungCap;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
        return null;
    }

    @Override
    public NhaCungCap update(NhaCungCap nhaCungCap) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(nhaCungCap);
            transaction.commit();
            return nhaCungCap;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
        return null;
    }

    @Override
    public boolean delete(String maNCC) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            NhaCungCap nhaCungCap = entityManager.find(NhaCungCap.class, maNCC);
            if (nhaCungCap != null) {
                entityManager.remove(entityManager.contains(nhaCungCap) ? nhaCungCap : entityManager.merge(nhaCungCap));
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
        return false;
    }

    @Override
    public NhaCungCap findById(String maNCC) {
        return entityManager.find(NhaCungCap.class, maNCC);
    }

    @Override
    public List<NhaCungCap> findAll() {
        return entityManager.createQuery("FROM NhaCungCap", NhaCungCap.class).getResultList();
    }
}
