package logic.Model;

import logic.IModel.IAccount;
import java.util.ArrayList;
import java.util.HashMap;
import logic.DB;
import logic.DBcontroll;
import logic.ErrMgr.LogErrorManager;
import logic.SendMail;

/**
 *
 * @author adm
 */
public class Account implements IAccount {

    private DB db = null;

    public Account() {
        db = DBcontroll.getInstance();

    }

    public String autorize(String login, String paswd) {
//        db=new DB();
        String q = null;
        q = "select a.*,r.priority as r_prior from Account a,role r where a.id=r.id and login='" + login + "' and password='" + paswd + "'";
        db.query(q);
        if (db.getResultList().isEmpty()) {
            LogErrorManager.getInstance().addError(2, "Account.autorize(String " + login + ", String " + paswd + ")",
                    "select all from table journal failed");
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
    public int getPriority() {
        try {
            return Integer.valueOf(db.getResultList().get(0).get("R_PRIOR").toString());
        } catch (Exception e) {
            LogErrorManager.getInstance().addError(0, "Account.getPriority()",
                    "WARNING record did not found");
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
    public int getPriority(String login) {
        db.query("select r.priority from Account a,role r where a.id=r.id and a.login='" + login + "'");
        if (db.getRowcount() == 0) {
            LogErrorManager.getInstance().addError(0, "Account.getPriority(String " + login + ")",
                    "WARNING record did not found");
            return -1;
        }
        return Integer.valueOf(db.getResultList().get(0).get("PRIORITY").toString());
    }

    public int getPriority(int id) {
        db.query("select r.priority from Account a,role r where a.id=r.id and a.id='" + id + "'");
        if (db.getRowcount() == 0) {
            LogErrorManager.getInstance().addError(0, "Account.getPriority(int " + id + ")",
                    "WARNING record did not found");
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
    public boolean add(String login, String password, String name, int roleId, String email) {
        String q = "insert into account values(null,'" + login + "','" + password + "','" + name + "','" + roleId + "','" + email + "')";
        if (!db.query(q)) {
//            LogErrorManager.getInstance().addError(2, "Account.add(String " + login + ", String " + password + ", String " + name + ", int " + roleId + ", String " + email + ")",
//                    "query failed. record did not add (" + q + ")");
            return false;
        }
        return true;

    }

    /**
     * Редактирование учетной записи
     * @param id Integer Идентификатор записи
     * @param login String Строка с логином, не более 20 символ
     * @param password String Строка с паролем, не более 20 символ
     * @param name String Строка с именем, не более 50 символ
     * @param roleId Integer Идентификатор роли
     * @param email String E-Mail адрес, не более 32 символ
     * @return Boolean
     * @throws SQLException
     *
     *  Добавлен Андреем 21.05.2011 19:52
     */
    public boolean setById(int id, String login, String password, String name, int roleId, String email) {
        String q = "UPDATE account SET NAME='" + name + "', "
                + "LOGIN='" + login + "', PASSWORD='" + password + "', ROLE_ID=" + Integer.toString(roleId) + ","
                + "EMAIL='" + email + "'  WHERE ID=" + Integer.toString(id);
        if (!db.query(q)) {
            LogErrorManager.getInstance().addError(2, "Account.setById(int " + id + ", String " + login + ", String " + password + ", String " + name + ", int " + roleId + ", String " + email + ")",
                    "query failed. Data did not set (" + q + ")");
            return false;
        }
        return true;
    }

    /**
     * Удаление учетной записи
     * @param id Integer  Идентификатор записи
     * @return Boolean
     * @throws SQLException
     *
     * Добавлен Андреем 21.05.2011 19:52
     */
    public boolean deleteById(int id) {
        return db.query("DELETE FROM account WHERE ID=" + Integer.toString(id));
    }

    /**
     * Возвращает учетную запись пользователя по имени и роли
     * @param name String Строка с именем, не более 50 символ
     * @param role String Строка с названием роли, не более 20 символ
     * @return ArrayList
     *
     * Добавлен Андреем 22.05.2011 12:05
     */
    public ArrayList<HashMap> getByNameAndRole(String name, String role) {
        if (!db.query("SELECT COUNT(a.id) FROM account a, role r WHERE "
                + "a.name='" + name + "' AND a.role_id=r.id AND r.title='" + role + "';")) {
            return null;
        }
        return db.getResultList();
    }

    /**
     * Проверяет на уникальность логинв
     * @param String login
     * @return boolean
     */
    public boolean isUnique(String login) {
        db.query("select * from account where login='" + login + "'");
        if (db.getResultList().size() == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *Возвращает существующих пользователей
     * @return ArrayList<HashMap>
     */
    public ArrayList<HashMap> getAll() {
        if (!db.query("Select * from account")) {
            //error is here
            return null;
        }
        return db.getResultList();
    }

    public HashMap getInfo(String login) {
        if (!db.query("Select * from account where login='" + login + "'")) {
            //error is here
            return null;
        }
        try {
            return db.getResultList().get(0);
        } catch (IndexOutOfBoundsException e) {
            LogErrorManager.getInstance().addError(0, "Account.getInfo(String " + login + ")",
                    "WARNING Data did not found (Select * from account where login='" + login + "')");

            System.out.println("not found");
            return null;
        }
    }

    public boolean sendMail(String addr,String theme,String name, String res, String from,String to) {
        try {
            String messageBody = (""
                    + "<html>"
                    + "<head>"
                    + "<style>"
                    + "h1{"
                    + "background-color: #549abf;"
                    + "}"
                    + "</style>"
                    + "</head>"
                    + "<body>"
                    + "<div>"
                    + "<h1>Уважаемый" + name + "</h1><br>"
                    + "К сожалению время, на которое вы забронировали ресурс <b>" + res + "</b> в связи с непредвиденными обстоятельствами недоступно.<br>"
                    + "Ресурс будет занят с <b>" + from + "</b> и до <b>" + to + "</b>. Перебронируйте ресурс на другое время.<br>"
                    + "С уважением, администратор."
                    + "</div>"
                    + "</body>"
                    + "</html>");
            SendMail sm = new SendMail(addr, theme, messageBody);
            sm.sendSSLEmail();

        } catch (Exception e) {
            LogErrorManager.getInstance().addError(3, "SandMail", "FATAL: sending mail is failed");
            System.out.println("SandMail Error: " + e.toString());
        }
        return false;
    }
}
