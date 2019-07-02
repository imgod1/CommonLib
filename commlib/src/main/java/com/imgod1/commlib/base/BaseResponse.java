package com.imgod1.commlib.base;
/**
 * BaseResponse.java
 *
 * @author gaokang
 * @version 1.0 2019/7/2 16:25
 * @update gaokang 2019/7/2 16:25
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
public class BaseResponse<T>  {
    private String code;
    private String msg;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
