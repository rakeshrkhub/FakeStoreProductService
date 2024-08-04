package com.rakesh.productservices.services;

import com.rakesh.productservices.dtos.FakeStoreProductDTO;
import com.rakesh.productservices.models.Product;
import com.rakesh.productservices.utility.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {

    private RestTemplate restTemplate;
    FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long id) {
        //call FakeStore API here to get the Product with given Id
        FakeStoreProductDTO productDTO= restTemplate.getForObject("https://fakestoreapi.com/products/"+id, FakeStoreProductDTO.class);
        if(productDTO==null){
            return null;
        }
        return Mapper.FakeStoreToProductMapper(productDTO);
    }

    @Override
    public List<Product> getAllProducts() {

        return List.of();
    }
}
