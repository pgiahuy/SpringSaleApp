/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pgh.repositories.impl;

import com.pgh.hibernatedemov1.HibernateUtils;
import com.pgh.pojo.Category;
import jakarta.persistence.Query;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;

/**
 *
 * @author HUY
 */
public class CategoryRepositoryImpl {
    public List<Category> getCates(){
        try(Session session = HibernateUtils.getFACTORY().openSession()){
            Query query = session.createQuery("FROM Category", Category.class);
            return query.getResultList();
        }
    }
    
}
