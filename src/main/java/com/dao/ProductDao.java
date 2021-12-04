package com.dao;

import com.entities.CategoryEntity;
import com.entities.KhachEntity;
import com.entities.ProductEntity;
import com.mvc.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ProductDao {
    private final static SessionFactory factory = HibernateUtility.getSessionFactory();

    public List<ProductEntity> getProduct() {
        try (Session session = factory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<ProductEntity> query = builder.createQuery(ProductEntity.class);
            Root<ProductEntity> root = query.from(ProductEntity.class);
            query.select(root);

            return  session.createQuery(query).getResultList();
        }
    }
    public List<ProductEntity> getListProduct()
    {
        Transaction transaction = null;
        List<ProductEntity> products = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try
        {
            transaction = session.beginTransaction();
            Query<ProductEntity> query = session.createQuery("FROM ProductEntity ");
            products = query.list();
            return products;
        }
        catch (Exception e)
        {
            if (transaction != null)
            {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally
        {
            session.close();
        }
        return products;
    }

    public List<ProductEntity> findProductById(int id)
    {
        Transaction transaction = null;
        List<ProductEntity> products = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try
        {
            transaction = session.beginTransaction();
            Query<ProductEntity> query = session.createQuery("Select u From ProductEntity u Where u.cateId.cid=:id");
            query.setParameter("id", id);
            products = query.list();
            return products;
        }
        catch (Exception e)
        {
            if (transaction != null)
            {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally
        {
            session.close();
        }
        return products;
    }

    public List<ProductEntity> findProductId(int id)
    {
        Transaction transaction = null;
        List<ProductEntity> products = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try
        {
            transaction = session.beginTransaction();
            Query<ProductEntity> query = session.createQuery("Select u From ProductEntity u Where u.id=:id");
            query.setParameter("id", id);
            products = query.list();
            return products;
        }
        catch (Exception e)
        {
            if (transaction != null)
            {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally
        {
            session.close();
        }
        return products;
    }
    public ProductEntity getProductById(String id) {
        Session session = factory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<ProductEntity> query = builder.createQuery(ProductEntity.class);
            Root<ProductEntity> root = query.from(ProductEntity.class);
            query.select(root);


            query.where(builder.equal(root.get("id").as(Integer.class), id));

            ProductEntity products = session.createQuery(query).getSingleResult();
            return products;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }
}
