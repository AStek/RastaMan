package logic.ErrMgr;

/**
 *
 * @author Elizabeth
 */
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileLogger
{

    private Writer writer;
    SimpleDateFormat formatter;

    public FileLogger(String path, String encoding, boolean truncate) throws UnsupportedEncodingException, FileNotFoundException
    {
        formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm s.SSS");
        writer = new OutputStreamWriter(new FileOutputStream(path, !truncate), encoding);
    }

    private void Write(String text, Date date) throws IOException
    {
        writer.write(String.format("[%s] %s\n", formatter.format(date), text));
        writer.flush();//очистка буффера
    }

    public void Write(String text) throws IOException
    {
        Write(text, new Date());
    }
    public void Write(Exception e) throws IOException
    {
        Write(String.format("%s: %s", e.getClass().toString(), e.getMessage()), new Date());
    }
    public void Write(ErrorItem e) throws IOException
    {
        Write(String.format("<%d> %s. Msg: %s", e.getCode(), e.getSource(), e.getMessage()), e.getDate());
    }
    public void WriteLogin(String login) throws IOException
    {
        Write(String.format("%s was logged in", login), new Date());
    }
    public void WriteLogout(String login) throws IOException
    {
        Write(String.format("%s was logged out", login), new Date());
    }
}