package com.dwh.payweb.Response.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayList {
    private int code;
    private String msg;
    private int count;
    private List<PayResponse> data;
}
