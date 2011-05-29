package Logger;
/**
 *
 * @author Elizabeth
 */

import java.util.Date;

public class ErrorItem
{
    private Integer code;
    private Date date;
    private String source;
    private String message;

    public ErrorItem(Integer code, String source, String message)
    {
        this.code = code;
        this.message = message;
        this.source = source;
        this.date = new Date();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Date getDate() {
        return date;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static int INFO_MESSAGE  = 0;
    public static int ERROR_INPUT = 1;
    public static int ERROR_COMMAND_FAIL = 2;
    public static int ERROR_CRITICAL = 3;
}
