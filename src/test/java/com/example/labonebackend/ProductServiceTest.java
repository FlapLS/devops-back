package com.example.labonebackend;

import com.example.labonebackend.entity.ProductEntity;
import com.example.labonebackend.model.Brand;
import com.example.labonebackend.model.ProductDto;
import com.example.labonebackend.model.TypeColor;
import com.example.labonebackend.repository.ProductRepository;
import com.example.labonebackend.service.ProductService;
import com.example.labonebackend.utils.ModelMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProducts() {
        // Arrange
        ProductEntity productEntity = new ProductEntity();
        ProductDto productDto = new ProductDto();
        when(productRepository.findAll()).thenReturn(Collections.singletonList(productEntity));
        when(modelMapper.map(productEntity)).thenReturn(productDto);

        // Act
        List<ProductDto> result = productService.getAllProducts();

        // Assert
        assertEquals(1, result.size());
        assertEquals(productDto, result.get(0));
        verify(productRepository, times(1)).findAll();
        verify(modelMapper, times(1)).map(productEntity);
    }

    @Test
    void getProductById() {
        // Arrange
        Long id = 1L;
        ProductEntity productEntity = new ProductEntity();
        ProductDto productDto = new ProductDto();
        when(productRepository.findById(id)).thenReturn(Optional.of(productEntity));
        when(modelMapper.map(productEntity)).thenReturn(productDto);

        // Act
        ProductDto result = productService.getProductById(id);

        // Assert
        assertNotNull(result);
        assertEquals(productDto, result);
        verify(productRepository, times(1)).findById(id);
        verify(modelMapper, times(1)).map(productEntity);
    }

    @Test
    void getProductById_NotFound() {
        // Arrange
        Long id = 1L;
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> productService.getProductById(id));
        verify(productRepository, times(1)).findById(id);
    }

    @Test
    void createProduct() {
        // Arrange
        ProductEntity product = new ProductEntity();
        product.setId(1L);
        product.setArticle("12345");
        product.setBrand(Brand.TAMIYA);
        product.setTypeColor(TypeColor.OIL);
        product.setName("Product Name");
        product.setCodeColor("#FFFFFF");
        product.setAmount(10.0f);
        product.setActualAmount(10.0f);

        when(productRepository.save(any(ProductEntity.class))).thenReturn(product);

        // Act
        ProductEntity result = productService.createProduct(product);

        // Assert
        assertNotNull(result);
        assertEquals(product.getId(), result.getId());
        assertEquals(product.getArticle(), result.getArticle());
        assertEquals(product.getBrand(), result.getBrand());
        assertEquals(product.getTypeColor(), result.getTypeColor());
        assertEquals(product.getName(), result.getName());
        assertEquals(product.getCodeColor(), result.getCodeColor());
        assertEquals(product.getAmount(), result.getAmount());
        assertEquals(product.getActualAmount(), result.getActualAmount());
        verify(productRepository, times(1)).save(any(ProductEntity.class));
    }

    @Test
    void updateProduct() {
        // Arrange
        Long id = 1L;
        ProductEntity existingProduct = new ProductEntity();
        existingProduct.setId(id);
        existingProduct.setArticle("12345");
        existingProduct.setBrand(Brand.TAMIYA);
        existingProduct.setTypeColor(TypeColor.OIL);
        existingProduct.setName("Product Name");
        existingProduct.setCodeColor("#FFFFFF");
        existingProduct.setAmount(10.0f);
        existingProduct.setActualAmount(10.0f);

        ProductDto productDto = new ProductDto();
        productDto.setArticle("54321");
        productDto.setBrand(Brand.MR_HOBBY);
        productDto.setTypeColor(TypeColor.ENEMAL);
        productDto.setName("Updated Product Name");
        productDto.setCodeColor("#000000");
        productDto.setAmount(20.0f);
        productDto.setActualAmount(20.0f);

        when(productRepository.findById(id)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(any(ProductEntity.class))).thenReturn(existingProduct);

        // Act
        ProductEntity result = productService.updateProduct(id, productDto);

        // Assert
        assertNotNull(result);
        assertEquals(productDto.getArticle(), result.getArticle());
        assertEquals(productDto.getBrand(), result.getBrand());
        assertEquals(productDto.getTypeColor(), result.getTypeColor());
        assertEquals(productDto.getName(), result.getName());
        assertEquals(productDto.getCodeColor(), result.getCodeColor());
        assertEquals(productDto.getAmount(), result.getAmount());
        assertEquals(productDto.getActualAmount(), result.getActualAmount());
        assertNotNull(result.getUpdateDate());
        verify(productRepository, times(1)).findById(id);
        verify(productRepository, times(1)).save(any(ProductEntity.class));
    }

    @Test
    void updateProduct_NotFound() {
        // Arrange
        Long id = 1L;
        ProductDto productDto = new ProductDto();
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NullPointerException.class, () -> productService.updateProduct(id, productDto));
        verify(productRepository, times(1)).findById(id);
    }

    @Test
    void deleteProduct() {
        // Arrange
        Long id = 1L;
        ProductEntity product = new ProductEntity();
        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        // Act
        productService.deleteProduct(id);

        // Assert
        verify(productRepository, times(1)).findById(id);
        verify(productRepository, times(1)).delete(product);
    }

    @Test
    void deleteProduct_NotFound() {
        // Arrange
        Long id = 1L;
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> productService.deleteProduct(id));
        verify(productRepository, times(1)).findById(id);
    }
}