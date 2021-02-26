package com.tts.ecomspring.service;

import com.tts.ecomspring.model.Product;
import com.tts.ecomspring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> findAll(){return productRepository.findAll();}

    public Product findById(long id){
        return productRepository.findById(id);
    }

    public List<String> findDistinctBrands(){
        return productRepository.findDistinctBrands();
    }
    public List<String> findDistinctCatagories(){
        return productRepository.findDistinctCatagories();
    }
    public void save(Product product){
        productRepository.save(product);
    }
    public void deleteById(long id){
        productRepository.deleteById(id);
    }

    public List<Product>findByBrandAndOrCategory(String brand, String category){
        if(category == null && brand == null) {
            return (List<Product>) productRepository.findAll();
        }else if(category == null){
            return productRepository.findByBrand(brand);
        }else if (brand == null){
            return productRepository.findByCategory(category);
        }
        else {
            return productRepository.findByBrandAndCategory(brand, category);
        }

    }

}
