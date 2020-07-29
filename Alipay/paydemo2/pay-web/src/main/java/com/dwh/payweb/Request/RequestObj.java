package com.dwh.payweb.Request;

import com.dwh.payweb.common.Constants_dwh;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestObj {
    public static final RequestObj LOGIN_SUCESS =new RequestObj("登陆成功",Constants_dwh.SUCCESSCODE);
    public static final RequestObj LOGIN_ERROR_PASS =new RequestObj("用户密码或用户名不正确",Constants_dwh.ERRORCODE);
    private String msg;
    private Integer code;
}
