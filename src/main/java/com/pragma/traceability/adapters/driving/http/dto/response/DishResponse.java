package com.pragma.traceability.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class  DishResponse {
    private Long id;
    private String name;
    private int price;
    private String description;
    private String imageUrl;
    private String category;
}
