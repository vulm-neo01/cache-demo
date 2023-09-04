package com.example.cachedemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Airport {
    @Id
    @Generated
    private String id;
    private String code;
    private String name;
}
