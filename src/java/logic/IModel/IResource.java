/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package logic.IModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author adm
 */
public interface IResource {

    /**
     * Дбавляет ресурс
     *
     * @param String title
     * @return boolean
     *
     * Добавил Александр 23.05 23.35
     */
    boolean add(String title);

    /**
     * Возвращает все существующиее ресурсы в системе
     *
     * @return ArrayList<HashMap>
     * Добавил Александр 19.05.2011 17:01
     */
    ArrayList<HashMap> getAll();

    /**
     * Возвращает ресурс ро указанному идентификатору
     * @param int id
     * @return String
     * Добавил Александр 23.05 23.23
     */
    String getById(int id);

    /**
     * Возвращает роли претендующие на указанный ресурс
     *
     * @param int Id
     * @return bollean
     *
     * добавил Александр 24.05 15:58
     */
    ArrayList<HashMap> getRoleOfRes(int id);

    /**
     * Удаляет ресурс по уканному идентификатору
     * @param int id
     * @return boolean
     *
     * Добавил Александр 23.05 23.27
     */
    boolean removeById(int id);

    /**
     * Удаляет связь ресурса с ролью по идентификаторам указаных в параметрах
     *
     * @param int resId
     * @param int roleId
     * @return bollean
     *
     * добавил Александр 24.05 15:31
     */
    boolean rmvResRole(int resId, int roleId);

    /**
     * Изменяет ресурс по идентификатору
     *
     * @param int id
     * @param String title
     * @return bollean
     *
     * добавил Александр 24.05 15:21
     */
    boolean setById(int id, String title);

    /**
     * Связывает ресурс с ролью или роль с ресурсом по идентификаторам
     *
     * @param int resId
     * @param int roleId
     * @return bollean
     *
     * добавил Александр 24.05 15:43
     */
    boolean setResRole(int resId, int roleId);

}
