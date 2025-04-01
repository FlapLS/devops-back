package com.example.labonebackend;

import com.example.labonebackend.controller.ProductController;
import com.example.labonebackend.entity.ProductEntity;
import com.example.labonebackend.model.Brand;
import com.example.labonebackend.model.ProductDto;
import com.example.labonebackend.model.TypeColor;
import com.example.labonebackend.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getAllProducts() throws Exception {

        ProductDto productDto = new ProductDto();
        when(productService.getAllProducts()).thenReturn(Collections.singletonList(productDto));


        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
        verify(productService, times(1)).getAllProducts();
    }

    @Test
    void getProductById() throws Exception {

        Long id = 1L;
        ProductDto productDto = new ProductDto();
        when(productService.getProductById(id)).thenReturn(productDto);


        mockMvc.perform(get("/products/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
        verify(productService, times(1)).getProductById(id);
    }

    @Test
    void createProduct() throws Exception {

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1L);
        productEntity.setArticle("12345");
        productEntity.setBrand(Brand.TAMIYA);
        productEntity.setTypeColor(TypeColor.OIL);
        productEntity.setName("iPhone 12");
        productEntity.setCodeColor("#000000");
        productEntity.setAmount(100.0f);
        productEntity.setActualAmount(100.0f);

        when(productService.createProduct(productEntity)).thenReturn(productEntity);


        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productEntity)))
                .andExpect(status().isOk());
        verify(productService, times(1)).createProduct(productEntity);
    }

    @Test
    void updateProduct() throws Exception {

        Long id = 1L;
        ProductDto productDto = new ProductDto();
        productDto.setArticle("12345");
        productDto.setBrand(Brand.TAMIYA);
        productDto.setTypeColor(TypeColor.OIL);
        productDto.setName("iPhone 12");
        productDto.setCodeColor("#000000");
        productDto.setAmount(100.0f);
        productDto.setActualAmount(100.0f);

        ProductEntity updatedProduct = new ProductEntity();
        when(productService.updateProduct(id, productDto)).thenReturn(updatedProduct);


        mockMvc.perform(put("/products/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isOk());
        verify(productService, times(1)).updateProduct(id, productDto);
    }

    @Test
    void deleteProduct() throws Exception {

        Long id = 1L;
        doNothing().when(productService).deleteProduct(id);


        mockMvc.perform(delete("/products/{id}", id))
                .andExpect(status().isNoContent());
        verify(productService, times(1)).deleteProduct(id);
    }
}
