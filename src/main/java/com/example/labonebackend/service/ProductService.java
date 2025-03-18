package com.example.labonebackend.service;


import com.example.labonebackend.entity.ProductEntity;
import com.example.labonebackend.model.ProductDto;
import com.example.labonebackend.repository.ProductRepository;
import com.example.labonebackend.utils.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream().map(modelMapper::map).collect(Collectors.toList());
    }

    public ProductDto getProductById(Long id) {
        return modelMapper.map(productRepository.findById(id).orElseThrow());
    }

    public ProductEntity createProduct(ProductEntity product) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(product.getId());
        productEntity.setArticle(product.getArticle());
        productEntity.setBrand(product.getBrand());
        productEntity.setTypeColor(product.getTypeColor());
        productEntity.setName(product.getName());
        productEntity.setCodeColor(product.getCodeColor());
        productEntity.setCreationDate(LocalDateTime.now());
        productEntity.setUpdateDate(LocalDateTime.now());
        productEntity.setAmount(product.getAmount());
        productEntity.setActualAmount(product.getActualAmount());
        return productRepository.save(productEntity);
    }

    public ProductEntity updateProduct(Long id, ProductDto productDto) {
        ProductEntity product = productRepository.findById(id).orElseThrow(null);
        product.setArticle(productDto.getArticle());
        product.setBrand(productDto.getBrand());
        product.setTypeColor(productDto.getTypeColor());
        product.setName(productDto.getName());
        product.setCodeColor(productDto.getCodeColor());
        product.setUpdateDate(LocalDateTime.now());
        product.setAmount(productDto.getAmount());
        product.setActualAmount(productDto.getActualAmount());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        ProductEntity product = productRepository.findById(id).orElseThrow();
        productRepository.delete(product);
    }
}
