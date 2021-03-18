package com.app.repository.impl;

import com.app.dto.info.ProductInfo;
import com.app.repository.ProductDao;
import com.app.repository.generic.AbstractGenericDao;
import com.app.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductDaoImpl extends AbstractGenericDao<Product> implements ProductDao {


    @Override
    public Optional<Product> getByNameCategoryProducent(String productName, String categoryName, String producentName) {
        Optional<Product> productOp = Optional.empty();
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
              EntityTransaction tx = entityManager.getTransaction();


            try {
                tx.begin();
                Query query = entityManager.createQuery("select p " +
                        "from Product p " +
                        "join p.category c " +
                        "join p.producer pr " +
                        "where p.name = :product_name " +
                        "and c.name = :category_name " +
                        "and pr.name = :producent_name ");

                query.setParameter("product_name", productName);
                query.setParameter("category_name", categoryName);
                query.setParameter("producent_name", producentName);

                productOp = Optional.ofNullable((Product) query.getSingleResult());


                tx.commit();
            } catch (NoResultException e) {
                if (tx != null) {
                    tx.rollback();
                }
            } finally {
                if (entityManager != null) {
                    entityManager.close();
                }
            }

        return productOp;
    }

    @Override
    public Optional<Product> getByNameandCategory(String productName, String categoryName) {
        Optional<Product> productOp = Optional.empty();
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();


            try {
                tx.begin();
                Query query = entityManager.createQuery("select p " +
                        "from Product p " +
                        "join p.category c " +
                        " where p.name = :product_name and c.name = :category_name "
                      );

                query.setParameter("product_name", productName);
                query.setParameter("category_name", categoryName);


                productOp = Optional.ofNullable((Product) query.getSingleResult());


                tx.commit();
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
                e.printStackTrace();
            } finally {
                if (entityManager != null) {
                    entityManager.close();
                }
            }

        return productOp;
    }

    @Override
    public List<ProductInfo> getProductsByClientCountryAndAge(String name, Integer age1, Integer age2){
        List<ProductInfo> products = null;
        List<Object[]> objects = null;


        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
            try {
                tx.begin();


                Query query1 = entityManager.createQuery("select p.name, p.price, cat.name, prod.name, cou.city " +
                        "from " + "CustomerOrder co " +
                        "JOIN co.customer c "+
                        "JOIN c.country cou " +
                        "JOIN co.product p " +
                        "JOIN p.producer prod " +
                        "JOIN p.category cat " +
                        "WHERE cou.city = :customer_country and c.age between :customer_age1 and :customer_age2 " +
                        "ORDER BY p.price desc");
                query1.setParameter("customer_country", name);
                query1.setParameter("customer_age1",age1);
                query1.setParameter("customer_age2",age2);


                objects = (List<Object[]>)query1.getResultList();

                products = objects
                        .stream()
                        .map(o -> new ProductInfo((String)o[0], (BigDecimal)o[1], (String)o[2], (String)o[3], (String)o[4]))
                        .collect(Collectors.toList());


                tx.commit();
            }
            catch (Exception e)
            {
                if (tx != null)
                {
                    tx.rollback();
                }
            }
            finally {
                if (entityManager != null)
                {
                    entityManager.close();
                }
            }

        return products;

    }

    @Override
    public List<ProductInfo> getProductsWithMaxPrizeInCategory(){
        List<ProductInfo> products = null;
        List<Object[]> objects = null;


        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
            try {
                tx.begin();


                Query query1 = entityManager.createQuery("select p.name, max (p.price), cat.name, prod.name, cou.city " +
                        "from Product p " +
                        "JOIN p.producer prod " +
                        "JOIN p.category cat " +
                        "JOIN prod.country cou " +
                        "GROUP BY cat.name "
                );

                objects = (List<Object[]>)query1.getResultList();

                products = objects
                        .stream()
                        .map(o -> new ProductInfo((String)o[0], (BigDecimal)o[1], (String)o[2], (String)o[3], (String)o[4]))
                        .collect(Collectors.toList());


                tx.commit();
            }
            catch (Exception e)
            {
                if (tx != null)
                {
                    tx.rollback();
                }
            }
            finally {
                if (entityManager != null)
                {
                    entityManager.close();
                }
            }

        return products;

    }

    @Override
    public List<Product> getProductsWithMaxPrizeInCategoryy() {
        return null;
    }


   /* @Override
    public List<Product> getProductsWithMaxPrizeInCategoryy() {
        List<Product> products = null;
        List<Object[]> objects = null;


        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();


            Query query1 = entityManager.createQuery("select p.name, Max(p.price), cat.name, prod.name, cou.city " +
                    "from Product p " +
                    "JOIN p.producer prod " +
                    "JOIN p.category cat " +
                    "JOIN prod.country cou " +
                    "GROUP BY cat.name" *//*+
                        "HAVING MAX(p.price) >= ALL (select max (pr.price) from Product pr where pr.category.id = cat.id)"*//*);


            objects = (List<Object[]>)query1.getResultList();
            objects.forEach(o -> System.out.println(o[0] + " " + o[1]));





            tx.commit();
        }
        catch (Exception e)
        {
            if (tx != null)
            {
                tx.rollback();
            }
        }
        finally {
            if (entityManager != null)
            {
                entityManager.close();
            }
        }

        return products;

    }*/
}



