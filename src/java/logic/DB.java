/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

/**
 * Класс управления базой данных
 *
 * @author Плахтий Александр Сергеевич
 */
interface DBIface
{

    public boolean openConnection();

    public boolean query(String q);

    public ArrayList<HashMap> getResultList();

    public ArrayList getColumns();

    public void closeConnection();
}

public class DB implements DBIface
{

    private int rowCount = 0;
    private ArrayList<HashMap> resList = new ArrayList();
    private ArrayList<String> columnList = new ArrayList();
    private Connection connection = null;
    private String connectionConf = null;
    private String connectionLogin = null;
    private String connectionPass = null;
    private boolean iserror = false;

    /**
     * инициализация соеденения с базой даных, чтение конфигурации
     * @exception IOException
     *
     * Добавил Александр 19.05.2011 10:02
     */
    public DB()
    {
        String host = CfgMgr.getValue("Host");
        String port = CfgMgr.getValue("port");
        String sid = CfgMgr.getValue("sid");
        connectionLogin = CfgMgr.getValue("login");
        connectionPass = CfgMgr.getValue("password");
        connectionConf = host + ":" + port + ":" + sid;
    }

    /**
     * Открывает соеденеие с базой данных на основе данных из файла конфигурации
     * @return boolean true если успешно выполнено подключение к БД
     * @exception ClassNotFoundException на случай ошибки подключения драйвера
     * @exception SQLException
     *
     * Добавил Александр 19.05.2011 10:28
     */
    public boolean openConnection()
    {
        try
        {
            // Load the JDBC driver
            Locale.setDefault(Locale.ENGLISH);
            String driverName = "oracle.jdbc.driver.OracleDriver";
            Class.forName(driverName);

            String url = "jdbc:oracle:thin:@" + connectionConf;
            connection = DriverManager.getConnection(url, connectionLogin, connectionPass);
        } catch (ClassNotFoundException e)
        {
            System.out.println(e.toString());
        } catch (SQLException e)
        {
            System.out.println(e.toString());
        }
        //connection.close();

        return true;
    }

    /**
     * Выполняет запрос к базе данных и сохраняет результат в список resList,columnList,rowCount
     * @return boolean true если если запрос выполнен успешно
     * @exception SQLException
     *
     * Добавил Александр 19.05.2011 13:42
     */
    public boolean query(String q)
    {
        iserror = false;
        try
        {
            Statement stmt = connection.createStatement();
            //System.out.println("-----------------" + q);
            ResultSet rset = stmt.executeQuery(q);
            ResultSetMetaData rsmt = null;
            try
            {
                rsmt = rset.getMetaData();
            } catch (SQLException e)
            {
                //warning is here
                rset.close();
                return true;
            }
            String cName;
            rowCount = 0;
            resList.clear();
            HashMap<String, String> result = new HashMap();
            while (rset.next())
            {
                rowCount++;
                for (int i = 1; i < rsmt.getColumnCount() + 1; i++)
                {
                    cName = rsmt.getColumnName(i);
                    result.put(cName, rset.getString(cName));
                }
                resList.add((HashMap) result.clone());

            }
            for (int i = 1; i < rsmt.getColumnCount() + 1; i++)
            {
                columnList.add(rsmt.getColumnName(i));
            }
            rset.close();

        } catch (SQLException e)
        {
            System.out.println("DB.Query " + e.toString());
            iserror = true;
            //error is here
            return false;
        }

        return true;
    }

    /**
     * возвращает список ассоциативных масивов в которых хранится (key = имя колонки,value = значение колонки)
     * @return ArrayList<HashMap>
     *
     * Добавил Александр 19.05.2011 15:13
     */
    public ArrayList<HashMap> getResultList()
    {
        return resList;
    }

    /**
     * возвращает список полученных колонок при выполнении запроса к БД
     * @return ArrayList
     *
     * Добавил Александр 19.05.2011 15:20
     */
    public ArrayList getColumns()
    {
        return columnList;
    }

    /**
     * возвращает количество, полученых при выполнении запросса к БД, строк
     * @return int
     *
     * Добавил Александр 19.05.2011 15:22
     */
    public int getRowcount()
    {
        return rowCount;
    }

    /**
     * закрытие соеденения с БД в деструкторе
     * @return int
     *
     * Добавил Александр 22.05.2011 22:11
     */
    public void finalize()
    {
        closeConnection();
    }

    public void printRes()
    {
        if (iserror)
        {
            System.out.println("Error");
            return;
        }
        for (HashMap t : resList)
        {
            System.out.println(t);
        }
    }

    public void printRes(String key)
    {
        if (iserror)
        {
            System.out.println("Error");
            return;
        }
        for (HashMap t : resList)
        {
            System.out.println(t.get(key));
        }
    }

    public void closeConnection()
    {
        try
        {
            connection.close();
        } catch (SQLException ex)
        {
            // error manager function here
        }

    }

    class testclass
    {
    }
}
