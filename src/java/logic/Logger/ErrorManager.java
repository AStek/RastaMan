package Logger;
/**
 *
 * @author Elizabeth
 */

import java.util.ArrayList;

public interface ErrorManager
{
    void addError(Integer code, String source, String message);
    int getErrorsCount();
    int getHighCode();
    ArrayList<ErrorItem> getErrorList();
    ArrayList<ErrorItem> getErrorList(int code);
    ArrayList<ErrorItem> getErrorList(String source);
}
