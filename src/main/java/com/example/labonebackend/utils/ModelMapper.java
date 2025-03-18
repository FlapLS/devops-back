package com.example.labonebackend.utils;

import com.example.labonebackend.entity.ProductEntity;
import com.example.labonebackend.model.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class ModelMapper {
    public ProductDto map(ProductEntity productEntity){
        ProductDto productDto = new ProductDto();
        productDto.setId(productEntity.getId());
        productDto.setArticle(productEntity.getArticle());
        productDto.setBrand(productEntity.getBrand());
        productDto.setTypeColor(productEntity.getTypeColor());
        productDto.setName(productEntity.getName());
        productDto.setCodeColor(productEntity.getCodeColor());
        productDto.setCreationDate(productEntity.getCreationDate());
        productDto.setUpdateDate(productEntity.getUpdateDate());
        productDto.setAmount(productEntity.getAmount());
        productDto.setActualAmount(productEntity.getActualAmount());
        return productDto;
    }
}
