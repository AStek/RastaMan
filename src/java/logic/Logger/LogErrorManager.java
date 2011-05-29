package Logger;

/**
 *
 * @author Elizabeth
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class LogErrorManager implements ErrorManager
{
    private ArrayList<ErrorItem> errors;
    private int maxCode;
    private FileLogger logger;

    public void addError(Integer code, String source, String message) throws IllegalArgumentException
    {
        if (source == null || message == null)
            throw new IllegalArgumentException();
        ErrorItem i = new ErrorItem(code, source, message);
        errors.add(i);
        try
        {
            logger.Write(i);
        }
        catch (IOException ex)
             
        {
        }
        if (maxCode < code)
            maxCode = code;
    }

    public LogErrorManager(String logPath, boolean truncate) throws UnsupportedEncodingException, FileNotFoundException
    {
        maxCode = ErrorItem.INFO_MESSAGE;
        errors = new ArrayList<ErrorItem>();
        logger = new FileLogger(logPath, "utf8", truncate);
    }
    public int getErrorsCount()
    {
        return errors.size();
    }

    public int getHighCide()
    {
        return maxCode;
    }

    public ArrayList<ErrorItem> getErrorList()
    {
        return errors;
    }

    public ArrayList<ErrorItem> getErrorList(int code)
    {
        ArrayList<ErrorItem> ret = new ArrayList<ErrorItem>();
        for(ErrorItem e: errors)
        {
            if(e.getCode() == code)
                ret.add(e);
        }
        return ret;
    }

    public ArrayList<ErrorItem> getErrorList(String source)
    {
        ArrayList<ErrorItem> ret = new ArrayList<ErrorItem>();
        for(ErrorItem e: errors)
        {
            if(e.getSource().equals(source))
                ret.add(e);
        }
        return ret;
    }

    public int getHighCode() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
