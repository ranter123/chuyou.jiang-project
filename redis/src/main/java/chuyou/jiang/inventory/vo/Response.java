package chuyou.jiang.inventory.vo;

/**
 * @Author: ranter
 * @Date: 2020/10/7 5:53 下午
 * @Description:
 */
public class Response {

    public static final String SUCCESS = "success";

    public static final String FAIL = "failure";

    private String status;

    private String message;

    public Response() {
    }

    public Response(String status) {
        this.status = status;
    }

    public Response(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
