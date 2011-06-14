package logic.ErrMgr;

/**
 *
 * @author Elizabeth
 */
import java.util.ArrayList;

public class LogErrorManager implements ErrorManager
{
    private static String path = "log.log";
    private static LogErrorManager instance = null;

    public static void setpath(String path)
    {
        LogErrorManager.path = path;//меняем путь к файлу, вызов метода до первого вызова GetInstance
    }

    public static LogErrorManager getInstance()
    {
        if (instance == null)
            instance = new LogErrorManager(path, false);
        return (LogErrorManager) instance;
    }

    private ArrayList<ErrorItem> errors;
    private int maxCode;
    private FileLogger logger;

    public void addError(Integer code, String source, String message)
    {

        if (source == null || message == null)
            return;
        ErrorItem i = new ErrorItem(code, source, message);
        errors.add(i);
        try
        {
            logger.Write(i);
        }
        catch (Exception ex)
        {
            System.err.println(ex.getMessage());// выводим в stderr ошибку
        }
        if (maxCode < code)
            maxCode = code;
    }

    private LogErrorManager(String logPath, boolean truncate)
    {
        try
        {
            maxCode = ErrorItem.INFO_MESSAGE;
            errors = new ArrayList<ErrorItem>();
            logger = new FileLogger(logPath, "utf8", truncate);
        }
        catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }
    public int getErrorsCount()
    {
        return errors.size();
    }

    public int getHighCode()
    {
        return maxCode;
    }

    public ArrayList<ErrorItem> getErrorList()
    {

        return (ArrayList<ErrorItem>) errors.clone();
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
            if(e.getSource().equals(source))//СРАВНЕНИЕ ЗАДАННОГО КОДА С ТЕКУЩИМ
                ret.add(e);
        }
        return ret;
    }
     public void Clear()
        {
          errors.clear();
          maxCode = ErrorItem.INFO_MESSAGE;// УДАЛИЛИ ВСЕ СУЩЕСТВУЮЩИЕ СООБЩЕНИЯ И УСТАНОВИЛИ
          //КОД ОШИБКИ НА 0
         }
}
