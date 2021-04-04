package cn.edu.just.bbmb.util;

public class JsonResult<T> {
    private Integer state;
    private T data;
    private String message;
    public JsonResult() {
        super();
    }

    /**
     * 返回异常信息
     * @param e
     */
    public JsonResult(Throwable e){
        this.message=e.getMessage();
    }

    /**
     * 返回状态码和data响应数据类型
     * @param state
     * @param data
     */
    public JsonResult(Integer state, T data) {
        this.state = state;
        this.data = data;
    }

    public JsonResult(Integer state, String message) {
        this.state = state;
        this.message = message;
    }

    public JsonResult(Integer state) {
        this.state = state;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
