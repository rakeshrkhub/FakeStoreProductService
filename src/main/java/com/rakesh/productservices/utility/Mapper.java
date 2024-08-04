package com.rakesh.productservices.utility;

import com.rakesh.productservices.dtos.FakeStoreProductDTO;
import com.rakesh.productservices.models.Category;
import com.rakesh.productservices.models.Product;

public class Mapper {
    public static Product FakeStoreToProductMapper(FakeStoreProductDTO fakeStoreProductDTO){
        Product product = new Product();
        product.setId(fakeStoreProductDTO.getId());
        product.setTitle(fakeStoreProductDTO.getTitle());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setDescription(fakeStoreProductDTO.getDescription());
        product.setImage(fakeStoreProductDTO.getImage());
        Category category=new Category();
        category.setDesc(fakeStoreProductDTO.getCategory());
        product.setCategory(category);
        return product;
    }
}
