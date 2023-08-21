package com.training.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private double price;
}
