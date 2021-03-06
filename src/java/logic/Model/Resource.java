
package logic.Model;

import logic.IModel.IResource;
import java.util.ArrayList;
import java.util.HashMap;
import logic.DB;
import logic.DBcontroll;
import logic.ErrMgr.LogErrorManager;

/**
 *
 * @author adm
 */
public class Resource implements IResource
{

    private DB db = null;

    public Resource()
    {
        db = DBcontroll.getInstance();
    }
    /**
     * Возвращает ресурс ро указанному идентификатору
     * @param int id
     * @return String
     * Добавил Александр 23.05 23.23
     */
    public String getById(int id)
    {
        if (db.query("select title from resources where id=" + String.valueOf(id)) == false)
        {
            LogErrorManager.getInstance().addError(2, "resource.getById(int "+id+")",
                    "query failed(select title from resources where id=" + String.valueOf(id)+")");
            return null;
        }
        return db.getResultList().get(0).get("TITLE").toString();
    }

    /**
     * Удаляет ресурс по уканному идентификатору
     * @param int id
     * @return boolean

     * Добавил Александр 23.05 23.27
     */
    public boolean removeById(int id)
    {
        if (!db.query("delete from resources where id=" + id))
        {
            LogErrorManager.getInstance().addError(2, "resource.removeById(int "+id+")",
                    "query failed(delete from resources where id=" + id+")");
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
    public ArrayList<HashMap> getAll()
    {
        if (!db.query("Select * from resources"))
        {
            LogErrorManager.getInstance().addError(2, "resource.getAll()",
                    "query failed (Select * from resources)");
            return null;
        }
        return db.getResultList();
    }

    /**
     * Дбавляет ресурс
     *
     * @param String title
     * @return boolean
     *
     * Добавил Александр 23.05 23.35
     */
    public boolean add(String title)
    {
        if (!db.query("insert into resources values(null,'" + title + "')"))
        {
            LogErrorManager.getInstance().addError(2, "resource.add(String "+title+")",
                    "query failed (insert into resources values(null,'" + title + "'))");
            return false;
        }
        return true;
    }

    /**
     * Возвращает роли претендующие на указанный ресурс
     *
     * @param int Id
     * @return bollean
     *
     *  добавил Александр 24.05 15:58
     */
    public ArrayList<HashMap> getRoleOfRes(int id)
    {
        String q="select r.ID,r.title from res_role,resources e,role r where e.id=Res_id and r.id=role_id and res_id=" + id;
        if (!db.query(q))
        {
            LogErrorManager.getInstance().addError(2, "resource.getRoleOfRes(int "+id+")",
                    "query failed("+q+")");
            return null;
        }
        return db.getResultList();
    }

    /**
     * Изменяет ресурс по идентификатору
     *
     * @param int id
     * @param String title
     * @return bollean
     *
     *  добавил Александр 24.05 15:21
     */
    public boolean setById(int id, String title)
    {
        if (db.query("UPDATE resources SET title='" + title + "' WHERE id=" + id))
        {
            LogErrorManager.getInstance().addError(2, "resource.setById(int "+id+", String "+title+")",
                    "query failed(UPDATE resources SET title='" + title + "' WHERE id=" + id+")");
            return true;
        } else
        {
            return false;
        }
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
    public boolean setResRole(int resId, int roleId)
    {
        return new Role().setResRole(resId, roleId);
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
    public boolean rmvResRole(int resId, int roleId)
    {
        return new Role().rmvResRole(resId, roleId);
    }

}
