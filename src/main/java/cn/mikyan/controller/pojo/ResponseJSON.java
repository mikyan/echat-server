package cn.mikyan.controller.pojo;

/**
 * @Description: 自定义响应数据结构
 * 				前端接受此类数据后需要使用本类的方法转换成对于的数据类型格式（类，或者list）
 * 				200：表示成功
 * 				500：表示错误，错误信息在msg字段中
 * 				501：bean验证错误，不管多少个错误都以map形式返回
 * 				502：拦截器拦截到用户token出错
 * 				555：异常抛出信息
 */


public class ResponseJSON {
    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;
    
    private String ok;	// 不使用

    public static ResponseJSON build(Integer status, String msg, Object data) {
        return new ResponseJSON(status, msg, data);
    }

    //返回正确，并携带数据体，如返回好友信息等
    public static ResponseJSON ok(Object data) {
        return new ResponseJSON(data);
    }

    //返回正确，不携带数据体，如返回拒绝好友请求
    public static ResponseJSON ok() {
        return new ResponseJSON(null);
    }
    
    //返回错误和错误信息
    public static ResponseJSON errorMsg(String msg) {
        return new ResponseJSON(500, msg, null);
    }
    
    //返回bean验证错误
    public static ResponseJSON errorMap(Object data) {
        return new ResponseJSON(501, "error", data);
    }
    
    //返回token错误
    public static ResponseJSON errorTokenMsg(String msg) {
        return new ResponseJSON(502, msg, null);
    }
    

    //返回异常
    public static ResponseJSON errorException(String msg) {
        return new ResponseJSON(555, msg, null);
    }

    public ResponseJSON() {

    }

//    public static LeeJSONResult build(Integer status, String msg) {
//        return new LeeJSONResult(status, msg, null);
//    }

    public ResponseJSON(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ResponseJSON(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public Boolean isOK() {
        return this.status == 200;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

	public String getOk() {
		return ok;
	}

	public void setOk(String ok) {
		this.ok = ok;
	}
}