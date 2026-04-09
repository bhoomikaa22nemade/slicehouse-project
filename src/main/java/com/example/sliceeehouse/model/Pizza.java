package com.example.sliceeehouse.model;

// import com.example.sliceeehouse.model.enums.Category;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // @Enumerated(EnumType.STRING)
    // private Category category;   // enum category

    @ManyToOne
@JoinColumn(name = "category_id")
private Category category;

    private String smallDescription;

    @Lob
    private String description;

    private Integer sellingPrice;
    private Integer discountedPrice;

   @Column(name = "image_url")
   private String productImage; // store image in DB

    public void setProductImage(String toString) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
