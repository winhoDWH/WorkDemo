package com.dwh.payweb.Response.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayResponse {
    private String payid;
    private String paytime;
    private int price;
    private String paylist;
    private String username;
    private String Goodslist;
    private String Numlist;
}
