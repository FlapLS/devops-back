package com.example.labonebackend.entity;

import com.example.labonebackend.model.Brand;
import com.example.labonebackend.model.TypeColor;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "product")
@Validated
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Article cannot be blank")
    @Column(name = "article")
    private String article;

    @NotNull(message = "Brand cannot be blank")
    @Enumerated(EnumType.STRING)
    @Column(name = "brand")
    private Brand brand;

    @NotNull(message = "Type color cannot be blank")
    @Enumerated(EnumType.STRING)
    @Column(name = "type_color")
    private TypeColor typeColor;

    @NotBlank(message = "Name cannot be blank")
    @Column(name = "name")
    private String name;

    @Pattern(regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$", message = "Invalid HEX color code")
    @Column(name = "code_color")
    private String codeColor;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @NotNull(message = "Amount cannot be null")
    @Min(value = 0, message = "Amount must be greater than or equal to 0")
    @Column(name = "amount")
    private Float amount;

    @NotNull(message = "Actual amount cannot be null")
    @Min(value = 0, message = "Actual amount must be greater than or equal to 0")
    @Column(name = "actual_amount")
    private Float actualAmount;

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", article='" + article + '\'' +
                ", brand=" + brand +
                ", typeColor=" + typeColor +
                ", name='" + name + '\'' +
                ", codeColor='" + codeColor + '\'' +
                ", creationDate=" + creationDate +
                ", updateDate=" + updateDate +
                ", amount=" + amount +
                ", actualAmount=" + actualAmount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(article, that.article) && brand == that.brand && typeColor == that.typeColor && Objects.equals(name, that.name) && Objects.equals(codeColor, that.codeColor) && Objects.equals(creationDate, that.creationDate) && Objects.equals(updateDate, that.updateDate) && Objects.equals(amount, that.amount) && Objects.equals(actualAmount, that.actualAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, article, brand, typeColor, name, codeColor, creationDate, updateDate, amount, actualAmount);
    }
}
