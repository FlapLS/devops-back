package com.example.labonebackend.model;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductDto {

    private Long id;

    private String article;

    private Brand brand;

    private TypeColor typeColor;

    private String name;

    private String codeColor;

    private LocalDateTime creationDate;

    private LocalDateTime updateDate;

    private Float amount;

    private Float actualAmount;
}
