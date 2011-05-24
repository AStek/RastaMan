/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Класс бизнесс логики
 *
 * @author Плахтий Александр Сергеевич
 */
public class RMCore
{

    private static RMCore instance = null;

    private RMCore() //private constructor for singleton pattern
    {
    }

    /**
     * Статический метод который создаёт объект
     *
     * @return RMCore
     * Добавил Александр 18.05.2011 19:22
     */
    public static RMCore getInstance()
    {
        if (instance == null)
        {
            instance = new RMCore();
            DB.getInstance().openConnection();
        }
        return instance;
    }
//───────────────────────┤account├─────────────────────────────

    /**
     * Проверка авторизации
     * @param String login логин пользователя (уникальный)
     * @param String paswd пароль для логина
     *
     * @return String имя пользователля который зарегестрирован под данным логином если операция прошла успешно
     * и такой пользователь существует, иначе null
     * Добавил Александр 18.05.2011 19:42
     */
    public String checkAccount(String login, String paswd)
    {
        String q = null;
        q = "select * from Account where login='" + login + "' and password='" + paswd + "'";
        DB.getInstance().query(q);
        if (DB.getInstance().getRowcount() == 0)
        {
            return null;//if don't found any then returns null
        }
        return DB.getInstance().getResultList().get(0).get("NAME").toString(); //if all OK returns name of user
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
            return Integer.valueOf(DB.getInstance().getResultList().get(0).get("PRIORITY").toString());
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
        DB.getInstance().query("select priority from Account where login='" + login + "'");
        if (DB.getInstance().getRowcount() == 0)
        {
            return -1;
        }
        return Integer.valueOf(DB.getInstance().getResultList().get(0).get("PRIORITY").toString());
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
    public boolean addAccount(String login, String password, String name,int roleId, int priority, String email)
    {
        return DB.getInstance().query("INSERT INTO account"
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
    public boolean updateAccountById(int id, String login, String password, String name, int roleId, int priority, String email)
    {
        return DB.getInstance().query("UPDATE account SET NAME='" + name + "', "
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
    public boolean deleteAccountById(int id)
    {
        return DB.getInstance().query("DELETE FROM account WHERE ID=" + Integer.toString(id) + ";");
    }

    /**
     * Возвращает учетную запись пользователя по имени и роли
     * @param name String Строка с именем, не более 50 символ
     * @param role String Строка с названием роли, не более 20 символ
     * @return ArrayList
     *
     * Добавлен Андреем 22.05.2011 12:05
     */
    public ArrayList<HashMap> getAccountByNameAndRole(String name, String role)
    {
        if (!DB.getInstance().query("SELECT COUNT(a.id) FROM account a, role r WHERE "
                + "a.name='" + name + "' AND a.role_id=r.id AND r.title='" + role + "';"))
        {
            return null;
        }
        return DB.getInstance().getResultList();
    }

    public boolean isUnique(String login)
    {
        DB.getInstance().query("select * from account where login='" + login + "'");
        if (DB.getInstance().getResultList().size() == 1)
        {
            return true;
        } else
        {
            return false;
        }
    }

    public ArrayList<HashMap> getAccounts()
    {
        if (!DB.getInstance().query("Select * from account"))
        {
            //error is here
            return null;
        }
        return DB.getInstance().getResultList();
    }

//───────────────────────┤ROLE├─────────────────────────────
    /**
     * Возвращает роль ро указанному идентификатору
     * @param int id
     * @return String
     * Добавил Александр 23.05 22.30
     */
    public String getRoleById(int id)
    {
        if (DB.getInstance().query("select title from role where id=" + String.valueOf(id)) == false)
        {
            // error is here
            return null;
        }
        return DB.getInstance().getResultList().get(0).get("TITLE").toString();
    }

    /**
     * Дбавляет роль
     * @param String title
     * @return boolean
     * Добавил Александр 23.05 22.45
     */
    public boolean addRole(String title)
    {
        if (!DB.getInstance().query("insert into role values(null,'" + title + "')"))
        {
            //error is here
            return false;
        }
        return true;
    }

    /**
     * Дбавляет роль со ссвязкой с ресурсом по идентификатору
     * @param String title
     * @param int resId
     * @return boolean
     *
     * Добавил Александр 23.05 23.00
     */
    public boolean addRoleRes(String title, int resId)
    {


        boolean f = true;
        try
        {
            f = f && DB.getInstance().query("insert into role values(null,'" + title + "')");
            f = f && DB.getInstance().query("select id from role where title='" + title + "'");
            System.out.println(DB.getInstance().getResultList().get(0).get("ID").toString());

            Integer id = new Integer(DB.getInstance().getResultList().get(0).get("ID").toString());
            f = f && DB.getInstance().query("insert into res_role values(null,'" + resId + "','" + id + "')");
        } catch (Exception e)
        {
            System.out.println(e.toString());
            //error is here
            return false;
        }
        return f;
    }

    /**
     * Удаляет роль по уканному идентификатору
     * @param int id
     * @return boolean

     * Добавил Александр 23.05 23.06
     */
    public boolean removeRoleById(int id)
    {
        if (!DB.getInstance().query("delete from role where id=" + id))
        {
            // error is here
            return false;
        }
        return true;
    }

    /**
     * Возвращает все существующиее роли в системе
     *
     * @return ArrayList<HashMap>
     * Добавил Александр 19.05.2011 17:03
     */
    public ArrayList<HashMap> getRoles()
    {
        if (!DB.getInstance().query("Select * from role"))
        {
            // error is here
            return null;
        }
        return DB.getInstance().getResultList();
    }

    public ArrayList<HashMap> getResOfRole(int id)
    {
        if (!DB.getInstance().query("select e.ID,e.title from res_role,resources e,role r where e.id=Res_id and r.id=role_id and role_id=" + id))
        {
            // error is here
            return null;
        }
        return DB.getInstance().getResultList();
    }

    public boolean setRole(int id, String title)
    {
        if (DB.getInstance().query("UPDATE role SET title='" + title + "' WHERE id=" + id))
        {
            return true;
        } else
        {
            return false;
        }
    }

//───────────────────────┤Resource├─────────────────────────────
    /**
     * Возвращает ресурс ро указанному идентификатору
     * @param int id
     * @return String
     * Добавил Александр 23.05 23.23
     */
    public String getResById(int id)
    {
        if (DB.getInstance().query("select title from resources where id=" + String.valueOf(id)) == false)
        {
            // error is here
            return null;
        }
        return DB.getInstance().getResultList().get(0).get("TITLE").toString();
    }

    /**
     * Удаляет ресурс по уканному идентификатору
     * @param int id
     * @return boolean

     * Добавил Александр 23.05 23.27
     */
    public boolean removeResById(int id)
    {
        if (!DB.getInstance().query("delete from resources where id=" + id))
        {
            //error is here
            return false;
        }
        return true;
    }

    /**
     * Возвращает все существующиее ресурсы в системе
     *
     * @return ArrayList<HashMap>
     * Добавил Александр 19.05.2011 17:01
     */
    public ArrayList<HashMap> getResources()
    {
        if (!DB.getInstance().query("Select * from resources"))
        {
            //error is here
            return null;
        }
        return DB.getInstance().getResultList();
    }

    /**
     * Дбавляет ресурс
     * @param String title
     * @return boolean
     * Добавил Александр 23.05 23.35
     */
    public boolean addRes(String title)
    {
        if (!DB.getInstance().query("insert into resources values(null,'" + title + "')"))
        {
            //error is here
            return false;
        }
        return true;
    }

    public ArrayList<HashMap> getRoleOfRes(int id)
    {
        if (!DB.getInstance().query("select r.ID,r.title from res_role,resources e,role r where e.id=Res_id and r.id=role_id and res_id=" + id))
        {
            // error is here
            return null;
        }
        return DB.getInstance().getResultList();
    }

    public boolean setRes(int id, String title)
    {
        if (DB.getInstance().query("UPDATE resources SET title='" + title + "' WHERE id=" + id))
        {
            return true;
        } else
        {
            return false;
        }
    }
//───────────────────────┤Res_role├─────────────────────────────
    public boolean rmvResRole(int resId, int roleId)
    {
        if (DB.getInstance().query("delete from res_role where res_id=" + resId + " and role_id=" + roleId))
        {
            return true;
        } else
        {
            return false;
        }
    }

    public boolean addResRole(int resId, int roleId)
    {

        if (DB.getInstance().query("insert into res_role values(null,'" + resId + "','" + roleId + "')"))
        {
            return true;
        } else
        {
            return false;
        }
    }
}
