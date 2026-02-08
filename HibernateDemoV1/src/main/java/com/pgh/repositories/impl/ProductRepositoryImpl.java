/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pgh.repositories.impl;

import com.pgh.hibernatedemov1.HibernateUtils;
import com.pgh.pojo.Product;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;

/**
 *
 * @author HUY
 */
public class ProductRepositoryImpl {
    private static final int PAGE_SIZE = 6;
    
    
    public List<Product> getProducts(Map<String,String> params){
        
        try(Session session = HibernateUtils.getFACTORY().openSession()){
            
            CriteriaBuilder b =  session.getCriteriaBuilder();
            CriteriaQuery<Product> q = b.createQuery(Product.class);
            
            Root root = q.from(Product.class);
            q.select(root);
            
            List<Predicate> predicates = new ArrayList<>();
            if(params!=null){
               String kw = params.get("kw");
               if(kw != null && !kw.isEmpty()){
                   predicates.add(
                           b.like(b.lower(root.get("name")),
                                   "%"+kw.toLowerCase()+"%")
                   );
               }
               
               String cateId = params.get("cate_id");
               if(cateId != null && !cateId.isEmpty()){
                   predicates.add(
                           b.equal(root.get("category").get("id"),
                                   Integer.parseInt(cateId))
                   );
               }
               
               String fromPrice = params.get("fromPrice");
                if (fromPrice != null && !fromPrice.isEmpty()) {
                    predicates.add(
                        b.ge(root.get("price"), Double.parseDouble(fromPrice))
                    );
                }
                
                String toPrice = params.get("toPrice");
                if (toPrice != null && !toPrice.isEmpty()) {
                    predicates.add(
                        b.le(root.get("price"), Double.parseDouble(toPrice))
                    );
                }
                String sort = params.get("sort");
                if (sort != null && !sort.isEmpty()) {
                    switch (sort) {
                        case "id":
                            q.orderBy(b.asc(root.get("id")));
                            break;
                        case "name":
                            q.orderBy(b.asc(root.get("name")));
                            break;
                        case "price":
                            q.orderBy(b.asc(root.get("price")));
                            break;
                        default:
                            q.orderBy(b.desc(root.get("id")));
                    }
                }
                
            }
            
            q.where(predicates.toArray(Predicate[]::new));
            Query query = session.createQuery(q);
            
            if(params != null){
                String p = params.getOrDefault("page", "1");
                int page = Integer.parseInt(p);
                int start = (page - 1) * PAGE_SIZE;
                query.setMaxResults(PAGE_SIZE);
                query.setFirstResult(start);
            }
            
            return query.getResultList();
        }
    }
    
}
