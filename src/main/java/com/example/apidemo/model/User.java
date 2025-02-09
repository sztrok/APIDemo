package com.example.apidemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Simple user class
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String username;
}
