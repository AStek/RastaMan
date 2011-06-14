package logic.Model;

import logic.IModel.IRole;
import java.util.ArrayList;
import java.util.HashMap;
import logic.DB;
import logic.DBcontroll;
import logic.ErrMgr.LogErrorManager;

/**
 *
 * @author Администратор
 */
public class Role implements IRole {

    private DB db = null;

    public Role() {
        db = DBcontroll.getInstance();
    }

    /**
     * Возвращает роль ро указанному идентификатору
     * @param int id
     * @return String
     * Добавил Александр 23.05 22.30
     */
    public HashMap getById(int id) {
        if (db.query("select * from role where id=" + String.valueOf(id)) == false) {
            LogErrorManager.getInstance().addError(2, "role.getById(int "+id+")",
                    "query failed(select * from role where id=" + String.valueOf(id)+")");

            return null;
        }
        return db.getResultList().get(0);
    }

    public boolean add(String title, int prior) {
        if (!db.query("insert into role values(null,'" + title + "','" + prior + "')")) {
            LogErrorManager.getInstance().addError(2, "role.add(String "+title+", int "+prior+")",
                    "query failed(insert into role values(null,'" + title + "," + prior + "')");
            return false;
        }
        return true;
    }

    /**
     * Дбавляет роль
     * @param String title
     * @return boolean
     * Добавил Александр 23.05 22.45
     */
    public boolean add(String title) {
        if (!db.query("insert into role values(null,'" + title + "',0)")) {
            LogErrorManager.getInstance().addError(2, "role.add(String "+title+")",
                    "query failed(insert into role values(null,'" + title + "',0)");
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
    public boolean add(String title, int prior, int resId) {


        boolean f = true;
        try {
            f = f && db.query("insert into role values(null,'" + title + "'," + prior + ")");
            f = f && db.query("select id from role where title='" + title + "'");
            System.out.println(db.getResultList().get(0).get("ID").toString());

            Integer id = new Integer(db.getResultList().get(0).get("ID").toString());
            f = f && db.query("insert into res_role values(null,'" + resId + "','" + id + "')");
        } catch (Exception e) {
            System.out.println(e.toString());
            LogErrorManager.getInstance().addError(2, "role.add(String "+title+", int "+prior+", int "+resId+")",
                    "query failed ");
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
    public boolean removeById(int id) {
        if (!db.query("delete from role where id=" + id)) {
            LogErrorManager.getInstance().addError(2, "role.removeById(int "+id+")",
                    "query failed(delete from role where id=" + id+")");
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
    public ArrayList<HashMap> getAll() {
        if (!db.query("Select * from role")) {
            // error is here
            return null;
        }
        return db.getResultList();
    }

    /**
     * возврвщает ресурсы назначеные роли
     * @param id идентификатор роли
     * @return ArrayList<HashMap>
     *
     * добавил Александр 24.05 15:01
     */
    public ArrayList<HashMap> getResOfRole(int id) {
        if (!db.query("select e.ID,e.title from res_role,resources e,role r where e.id=Res_id and r.id=role_id and role_id=" + id)) {
            // error is here
            return null;
        }
        return db.getResultList();
    }

    /**
     * изменяет название роли по идентификатору
     * @param id
     * @param title название роли
     * @return boolean
     *
     * добавил Александр 24.05 15:09
     */
    public boolean setById(int id, String title) {
        if (db.query("UPDATE role SET title='" + title + "' WHERE id=" + id)) {
            return true;
        }

        return false;
    }

    /**
     * изменяет название роли по идентификатору
     * @param id
     * @param title название роли
     * @param prior приоритет
     * @return boolean
     *
     */
    public boolean setById(int id, String title, int prior) {
        if (db.query("UPDATE role SET title='" + title + "' priority='" + prior + "' WHERE id=" + id)) {
            return true;
        }
        return false;
    }

    public boolean setPriority(int id, int prior) {
        if (db.query("UPDATE role SET priority='" + prior + "' WHERE id=" + id)) {
            return true;
        }
        return false;

    }

    /**
     * Связывает ресурс с ролью или роль с ресурсом по идентификаторам
     *
     * @param int resId
     * @param int roleId
     * @return bollean
     *
     *  добавил Александр 24.05 15:43
     */
    public boolean setResRole(int resId, int roleId) {

        if (db.query("insert into res_role values(null,'" + resId + "','" + roleId + "')")) {
            return true;
        }
        return false;

    }

    /**
     * Удаляет связь ресурса с ролью по идентификаторам указаных в параметрах
     *
     * @param int resId
     * @param int roleId
     * @return bollean
     *
     *  добавил Александр 24.05 15:31
     */
    public boolean rmvResRole(int resId, int roleId) {
        if (db.query("delete from res_role where res_id=" + resId + " and role_id=" + roleId)) {
            return true;
        }
        return false;

    }
}
