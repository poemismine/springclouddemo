package com.example.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: tjy
 * @date: 2021/12/27 17:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    private Long id;

    private String serial;
}
