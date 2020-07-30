package com.dwh.payweb.Response;

import com.dwh.payweb.Response.entity.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserList {
    private int code;
    private String msg;
    private int count;
    private List<UserResponse> data;

    public static UserList getRespon(List<UserResponse> data){
        return new UserList(0,"",data.size(),data);
    }
}
