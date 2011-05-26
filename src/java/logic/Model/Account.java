/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.Model;

import logic.IModel.IAccount;
import java.util.ArrayList;
import java.util.HashMap;
import logic.DB;

/**
 *
 * @author Александр
 */
public class Account implements IAccount
{

    private DB db = null;

    public Account()
    {
        db = DBcontroll.getInstance();

    }

    public String autorize(String login, String paswd)
    {
//        db=new DB();
        String q = null;
        q = "select * from Account where login='" + login + "' and password='" + paswd + "'";
        db.query(q);
        if (db.getRowcount() == 0)
        {
            return null;//if don't found any then returns null
        }
        return db.getResultList().get(0).get("NAME").toString(); //if all OK returns name of user
    }

    /**
     * Получение приоритета пользователя
     *
     * @return int возвращает приоритет пользователя. (адекватно работает после вызова функции авторизаци
     *      @see checkAccount)
     *      в случаее ошибки возвращает -1
     * Добавил Александр 18.05.2011 20:16
     */
    public int getPriority()
    {
        try
        {
            return Integer.valueOf(db.getResultList().get(0).get("PRIORITY").toString());
        } catch (Exception e)
        {
            return -1;
        }
    }

    /**
     * Получение приоритета пользователя
     *
     * @return int возвращает приоритет пользователя, который зарегестрирован под указанным логином.
     *      в случаее ошибки возвращает -1
     * Добавил Александр 18.05.2011 20:16
     */
    public int getPriority(String login)
    {
        db.query("select priority from Account where login='" + login + "'");
        if (db.getRowcount() == 0)
        {
            return -1;
        }
        return Integer.valueOf(db.getResultList().get(0).get("PRIORITY").toString());
    }

    public int getPriority(int id)
    {
        db.query("select priority from Account where id='" + id + "'");
        if (db.getRowcount() == 0)
        {
            return -1;
        }
        return Integer.valueOf(db.getResultList().get(0).get("PRIORITY").toString());
    }

    /**
     * Создание учетной записи
     * @param login String Строка с логином, не более 20 символ
     * @param password String Строка с паролем, не более 20 символ
     * @param name String Строка с именем, не более 50 символ
     * @param roleId Integer Идентификатор роли
     * @param priority Integer Приоритет
     * @param email String E-Mail адрес, не более 32 символ
     * @return Boolean
     * @throws SQLException
     *
     * Добавлен Андреем 21.05.2011 19:52
     */
    public boolean add(String login, String password, String name, int roleId, int priority, String email)
    {
        return db.query("INSERT INTO account"
                + " VALUES (null,'" + name + "', '" + login + "', '" + password + "', " + Integer.toString(roleId)
                + ", " + Integer.toString(priority) + ", '" + email + "')");
    }

    /**
     * Редактирование учетной записи
     * @param id Integer Идентификатор записи
     * @param login String Строка с логином, не более 20 символ
     * @param password String Строка с паролем, не более 20 символ
     * @param name String Строка с именем, не более 50 символ
     * @param roleId Integer Идентификатор роли
     * @param priority Integer Приоритет
     * @param email String E-Mail адрес, не более 32 символ
     * @return Boolean
     * @throws SQLException
     *
     *  Добавлен Андреем 21.05.2011 19:52
     */
    public boolean setById(int id, String login, String password, String name, int roleId, int priority, String email)
    {
        return db.query("UPDATE account SET NAME='" + name + "', "
                + "LOGIN='" + login + "', PASSWORD='" + password + "', ROLE_ID=" + Integer.toString(roleId)
                + ", PRIORITY=" + Integer.toString(priority) + ", "
                + "EMAIL='" + email + "'  WHERE ID=" + Integer.toString(id) + ";");
    }

    /**
     * Удаление учетной записи
     * @param id Integer  Идентификатор записи
     * @return Boolean
     * @throws SQLException
     *
     * Добавлен Андреем 21.05.2011 19:52
     */
    public boolean deleteById(int id)
    {
        return db.query("DELETE FROM account WHERE ID=" + Integer.toString(id) + ";");
    }

    /**
     * Возвращает учетную запись пользователя по имени и роли
     * @param name String Строка с именем, не более 50 символ
     * @param role String Строка с названием роли, не более 20 символ
     * @return ArrayList
     *
     * Добавлен Андреем 22.05.2011 12:05
     */
    public ArrayList<HashMap> getByNameAndRole(String name, String role)
    {
        if (!db.query("SELECT COUNT(a.id) FROM account a, role r WHERE "
                + "a.name='" + name + "' AND a.role_id=r.id AND r.title='" + role + "';"))
        {
            return null;
        }
        return db.getResultList();
    }

    /**
     * Проверяет на уникальность логинв
     * @param String login
     * @return boolean
     */
    public boolean isUnique(String login)
    {
        db.query("select * from account where login='" + login + "'");
        return db.getResultList().size() == 1 ? true : false;
    }

    /**
     *Возвращает существующих пользователей
     * @return ArrayList<HashMap>
     */
    public ArrayList<HashMap> getAll()
    {
        if (!db.query("Select * from account"))
        {
            //error is here
            return null;
        }
        return db.getResultList();
    }

    /**
     * возвращает информацию о пользователе по логину
     * @return null если не удалось найти
     */
    public HashMap getByLogin(String login)
    {
        if (!db.query("select * from account where login='" + login + "'"))
        {
            return null;
        }
        try
        {
            HashMap hm = db.getResultList().get(0);
            return hm;
        } catch (NullPointerException e)
        {
            return null;
        } catch (Exception e)
        {
            return null;
        }
    }
/**
 * Возвращает информацию о используемых ресурсах, электронной почте и о занимаемой роли
 * @param login
 * @return HashMap ключ "MAIL" - почта, "ROLE" - роль, "RESOURCES"-ArrayList<String> -список ресурсов
 *          null - если пользователь не найден или произошла ошибка
 *
 * Добавил Александр
 */
    public HashMap getInfo(String login)
    {
        String q = "select a.mail as mail,r.title as res,o.title as role from "
                + "account a, journal j, resources r,role o where a.id=j.account_id and "
                + "r.id=j.resource_id and a.ROLE_id=o.id and login='" + login + "'";
        if (!db.query(q))
        {
            //Q error
            return null;
        }
        ArrayList<HashMap> users = db.getResultList();
        HashMap hm = new HashMap();
        try
        {
            hm.put("MAIL", users.get(0).get("MAIL"));
            hm.put("ROLE", users.get(0).get("ROLE"));
        } catch (IndexOutOfBoundsException e)
        {
            //user not found
            return null;
        }
        ArrayList<String> ress = new ArrayList();
        for (HashMap u : users)
        {
            ress.add(u.get("RES").toString());
        }
        hm.put("RESOURCES", ress.clone());

        return hm;
    }
}