/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.pgh.hibernatedemov1;

import com.pgh.pojo.Category;
import com.pgh.pojo.Product;
import com.pgh.repositories.impl.CategoryRepositoryImpl;
import com.pgh.repositories.impl.ProductRepositoryImpl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HUY
 */
public class HibernateDemoV1 {

    public static void main(String[] args) {
        CategoryRepositoryImpl c = new CategoryRepositoryImpl();
        ProductRepositoryImpl p = new ProductRepositoryImpl();
        List<Category> cates = c.getCates();
        
       
    }
}
