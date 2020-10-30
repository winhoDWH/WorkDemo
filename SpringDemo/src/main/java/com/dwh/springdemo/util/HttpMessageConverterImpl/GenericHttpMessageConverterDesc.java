package com.dwh.springdemo.util.HttpMessageConverterImpl;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * GenericHttpMessageConverter接口说明类
 * 描述该接口的方法的作用
 * @author dengwenhao
 * date 2020-10-30
 */
abstract class GenericHttpMessageConverterDesc implements GenericHttpMessageConverter<Object> {

    public GenericHttpMessageConverterDesc() {
        super();
    }

    /**
     * 结合AbstractMessageConverterMethodArgumentResolver类的readWithMessageConverters方法中的调用进行理解
     * 用来判断该converter是否能解析当前请求的参数
     * @param type 转换的目标类，即controller中接收请求的参数
     * @param aClass controller类
     * @param mediaType 请求的content-Type属性
     * @return
     */
    @Override
    public boolean canRead(Type type, Class<?> aClass, MediaType mediaType) {
        return false;
    }

    @Override
    public Object read(Type type, Class<?> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    public boolean canWrite(Type type, Class<?> aClass, MediaType mediaType) {
        return false;
    }

    @Override
    public void write(Object o, Type type, MediaType mediaType, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {

    }
}
