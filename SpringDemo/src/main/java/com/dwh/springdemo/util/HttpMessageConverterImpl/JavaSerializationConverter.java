package com.dwh.springdemo.util.HttpMessageConverterImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Base64;

/**
 * 模拟springMVC报文->实体类的转换类
 * 支持application/x-java-serialization类型解析和序列化
 * 参考：https://blog.csdn.net/qq_16605855/article/details/89213206?biz_id=102&utm_term=HttpMessageConverter%E5%9C%A8%E5%93%AA&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduweb~default-2-89213206&spm=1018.2118.3001.4187
 * @author dengwenhao
 * date 2020-10-29
 */
@Slf4j
public class JavaSerializationConverter extends AbstractHttpMessageConverter<Serializable> {

    public JavaSerializationConverter(){
        super(new MediaType("application","x-java-serialization", Charset.forName("UTF-8")));
    }

    @Override
    protected boolean supports(Class aClass) {
        // 使用Serializable，这里可以直接返回true
        // 使用object，这里还要加上Serializable接口实现类判断
        // 根据自己的业务需求加上其他判断
        return true;
    }

    @Override
    protected Serializable readInternal(Class aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        byte[] bytes = StreamUtils.copyToByteArray(httpInputMessage.getBody());
        // base64使得二进制数据可视化，便于测试
        ByteArrayInputStream bytesInput = new ByteArrayInputStream(Base64.getDecoder().decode(bytes));
        ObjectInputStream objectInput = new ObjectInputStream(bytesInput);
        try {
            return (Serializable) objectInput.readObject();
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    @Override
    protected void writeInternal(Serializable serializable, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        ByteArrayOutputStream bytesOutput = new ByteArrayOutputStream();
        ObjectOutputStream objectOutput = new ObjectOutputStream(bytesOutput);
        objectOutput.writeObject(serializable);
        // base64使得二进制数据可视化，便于测试
        httpOutputMessage.getBody().write(Base64.getEncoder().encode(bytesOutput.toByteArray()));
    }
}
