package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Result<T>{
    private Integer code;
    private String message;
    private T data;

    public  static <T> Result<T> success(){
        return new Result<>(200,"操作成功",null);
    }
    public  static <T> Result<T> success(T data){
        return new Result<>(200,"操作成功",data);
    }
    public  static <T> Result<T> failure(T data){
        return new Result<>(401,"操作失败",data);
    }
    public  static <T> Result<T> failure(){
        return new Result<>(401,"操作失败",null);
    }

}
