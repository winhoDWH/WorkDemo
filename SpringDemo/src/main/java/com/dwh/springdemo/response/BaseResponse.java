package com.dwh.springdemo.response;

import lombok.Data;

/**
 * @author: dwh
 * @DATE: 2020/9/22
 */
@Data
public class BaseResponse {
    private String code;
    private String message;
}
