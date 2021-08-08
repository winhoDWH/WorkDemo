package com.dwh.patterns.chainPattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 请求类
 * @author dengwenhao
 * date 2020-11-02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    /**假期天数**/
    private Integer dayCount;
    /**请求类型**/
    private TypeEnum type;
    /**加薪薪酬数**/
    private Integer money;
    /**请求人**/
    private String name;
}
