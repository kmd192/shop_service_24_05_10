package com.example.shop.category;

import com.example.shop.merchandise.Merchandise;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String gender;

    private String clothType;

    private String season;

    @OneToMany(mappedBy = "category")
    private final List<Merchandise> merchandiseList = new ArrayList<>();
}
