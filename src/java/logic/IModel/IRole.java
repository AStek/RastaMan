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
public interface IRole {

    /**
     * Дбавляет роль
     * @param String title
     * @return boolean
     * Добавил Александр 23.05 22.45
     */
    boolean add(String title);

    /**
     * Дбавляет роль со ссвязкой с ресурсом по идентификатору
     * @param String title
     * @param int resId
     * @return boolean
     *
     * Добавил Александр 23.05 23.00
     */
    boolean add(String title, int resId);

    public boolean add(String title,String prior);//-----------------<===[ prior string ! ]


    /**
     * Возвращает все существующиее роли в системе
     *
     * @return ArrayList<HashMap>
     * Добавил Александр 19.05.2011 17:03
     */
    ArrayList<HashMap> getAll();

    /**
     * Возвращает роль ро указанному идентификатору
     * @param int id
     * @return String
     * Добавил Александр 23.05 22.30
     */
    String getById(int id);

    /**
     * возврвщает ресурсы назначеные роли
     * @param id идентификатор роли
     * @return ArrayList<HashMap>
     *
     * добавил Александр 24.05 15:01
     */
    ArrayList<HashMap> getResOfRole(int id);

    /**
     * Удаляет роль по уканному идентификатору
     * @param int id
     * @return boolean
     *
     * Добавил Александр 23.05 23.06
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
     * изменяет название роли по идентификатору
     * @param id
     * @param title название роли
     * @return boolean
     *
     * добавил Александр 24.05 15:09
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

    public boolean setPriority(int id, int prior);

}
