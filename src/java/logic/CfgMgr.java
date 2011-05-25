package logic;

import java.io.*;
import java.util.*;

/**
 * Менеджер конфигурации
 * @author Андрей
 */
public class CfgMgr {

    /**
     *Хеш меп хранящий масив настроек
     */
    private static HashMap<String, String> config = null;

    /**
     * Загрузка настроек из конфига
     *
     * Примечание:
     * необходимо скоректировать путь к файлу с настройками
     */
    public static void loadConfig()
    {
        config = new HashMap();
        String fileName = "config.props";
        String curDir = new File("config.props").getAbsolutePath();
        fileName = "/"+fileName; //To-Do:Прененести из корня в директорию
        Object[] ValuesArray = null;
        Object[] KeysArray = null;
        try {
            Properties p = new Properties();
            p.load(new FileInputStream(fileName));
            ValuesArray = p.values().toArray();
            KeysArray = p.keySet().toArray();
            for (int i = 0; i < KeysArray.length; i++) {
                config.put(KeysArray[i].toString(),ValuesArray[i].toString());
            }
        } catch (Exception iOException) {
            //To-Do: Add call from error maneger
            
        }
    }

    /**
     * Получить значение по ключу
     * @param key String Строка содержащая ключ
     * @return String
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
