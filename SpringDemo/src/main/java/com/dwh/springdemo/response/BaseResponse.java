package com.dwh.springdemo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: dwh
 * @DATE: 2020/9/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {
    private String code;
    private String message;
}
