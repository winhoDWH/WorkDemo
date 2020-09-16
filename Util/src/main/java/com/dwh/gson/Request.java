package com.dwh.gson;

import lombok.Data;

/**
 * 带泛型识别的接口实体类
 * @author: dwh
 * @DATE: 2020/9/16
 */
@Data
public class Request<T> {
    private String code;
    private String message;
    private T data;
}
