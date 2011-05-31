package Logger;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Elizabeth
 */
public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException
    {
        LogErrorManager mgr=LogErrorManager.getInstance();
        mgr.addError(ErrorItem.ERROR_INPUT, "Screen login", "login: \")' or 1 = 1\"");
        mgr.addError(ErrorItem.ERROR_INPUT, "Screen pass", "pass: \")' or 1 = 1\"");
        mgr.addError(ErrorItem.ERROR_COMMAND_FAIL, "API Layer", "record adding was fail");
        for(ErrorItem e: mgr.getErrorList())
        {
            System.out.println(String.format("%s: %s", e.getSource(), e.getMessage()));
        }
    }
}
