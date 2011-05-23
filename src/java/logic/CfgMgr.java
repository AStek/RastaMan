package logic;

import java.io.*;
import java.util.*;

/**
 *
 * @author andrey
 */
public class CfgMgr {

    /**
     *
     */
    private static HashMap config = null;

    /**
     *
     */
    public static void loadConfig()
    {
        String fileName = "config.prop";
        String curDir = new File(".").getAbsolutePath();
        fileName = curDir+"/"+fileName;
        Object[] ValuesArray = null;
        Object[] KeysArray = null;
        try {
            Properties p = new Properties();
            p.load(new FileInputStream(fileName));
            ValuesArray = p.values().toArray();
            KeysArray = p.keySet().toArray();
        } catch (IOException iOException) {
            //To-Do: Add call from error maneger
        }
        for (int i = 0; i < KeysArray.length; i++) {
            config.put(KeysArray[i].toString(), ValuesArray[i].toString());
        }
    }

    /**
     *
     * @param key
     * @return
     */
    public static String getValue(String key)
    {
        if (config == null){
            loadConfig();
        }
        if (config.get(key)!=null){
            return config.get(key).toString();
        } else {
            return "";
        }
    }
}
