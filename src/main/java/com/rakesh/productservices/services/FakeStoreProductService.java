package com.rakesh.productservices.services;

import com.rakesh.productservices.dtos.FakeStoreProductDTO;
import com.rakesh.productservices.models.Product;
import com.rakesh.productservices.utility.Mapper;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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
        FakeStoreProductDTO[] productsDTO= restTemplate.getForObject("https://fakestoreapi.com/products/", FakeStoreProductDTO[].class);
        /*restTemplate.getForObject("https://fakestoreapi.com/products/", List<FakeStoreProductDTO>.class);
        Why we can't write List instead of array? --- because List has generics type so initially it is List only at
        compile time and run type, datatype is not defined but array has no any generics that's why array works.
         */
        List<Product> response=new ArrayList<>();
        for(FakeStoreProductDTO productDTO:productsDTO){
            response.add(Mapper.FakeStoreToProductMapper(productDTO));
        }
        return response;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        FakeStoreProductDTO fakeStoreProductDto = new FakeStoreProductDTO();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setImage(product.getImage());
        fakeStoreProductDto.setDescription(product.getDescription());

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto, FakeStoreProductDTO.class);
        HttpMessageConverterExtractor<FakeStoreProductDTO> responseExtractor =
                new HttpMessageConverterExtractor<>(FakeStoreProductDTO.class,
                        restTemplate.getMessageConverters());
        FakeStoreProductDTO response = restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);
        if(response==null){
            return null;
        }
        return Mapper.FakeStoreToProductMapper(response);
    }
}
