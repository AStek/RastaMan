package logic.API;

import java.util.ArrayList;
import java.util.HashMap;
import logic.IModel.IResource;
import logic.Model.Resource;
import logic.validator;

/**
 * Первая версия написана Лизой.
 * Рефакторинг, фиксы и доработка: Андрей
 */
public class ResourceAPI {

    IResource model = new Resource();

    /**
     * Дбавляет ресурс
     *
     * @param String title
     * @return boolean
     *
     */
    public boolean add(String title)
    {
        if (!validator.hasSpecialChars(title)
                &&
            validator.validateString(title, 20)
            ){
            return model.add(title);
        } else {
            return false;
        }
    }

    /**
     * Возвращает все существующиее ресурсы в системе
     *
     * @return ArrayList<HashMap>
     */
    public ArrayList<HashMap> resoursesReturn()
    {
     return model.getAll();
    }

    /**
     * Возвращает ресурс ро указанному идентификатору
     * @param int id
     * @return String
     */
    public String getById(String id)
    {
        if (validator.validateInteger(id)){
            return model.getById(Integer.parseInt(id));
        } else {
            return null;
        }
    }

    /**
     * Возвращает роли претендующие на указанный ресурс
     *
     * @param String Id
     * @return bollean
     *
     */
    public ArrayList<HashMap> getRoleOfRes(String id)
    {
        if (validator.validateInteger(id)){
            return model.getRoleOfRes(Integer.parseInt(id));
        } else {
            return null;
        }
    }

    /**
     * Удаляет ресурс по уканному идентификатору
     * @param String id
     * @return boolean
     *
     */
    public boolean removeById(String id)
    {
        if (validator.validateInteger(id)){
            return model.removeById(Integer.parseInt(id));
        } else {
            return false;
        }
    }

    /**
     * Удаляет связь ресурса с ролью по идентификаторам указаных в параметрах
     *
     * @param String resId
     * @param String roleId
     * @return bollean
     *
     */
    public boolean rmvResRole(String resId, String roleId)
    {
        if (validator.validateInteger(roleId)&&validator.validateInteger(resId)){
            return model.rmvResRole(Integer.parseInt(resId), Integer.parseInt(roleId));
        } else {
            return false;
        }
    }

    /**
     * Изменяет ресурс по идентификатору
     *
     * @param String id
     * @param String title
     * @return bollean
     *
     */
    public boolean setById(String id, String title)
    {
        if (validator.validateInteger(id)
                &&
            !validator.hasSpecialChars(title)
                &&
            validator.validateString(title, 20)
            ){
            return model.setById(Integer.parseInt(id), title);
        } else {
            return false;
        }
    }

    /**
     * Связывает ресурс с ролью или роль с ресурсом по идентификаторам
     *
     * @param String resId
     * @param String roleId
     * @return bollean
     *
     */
    public boolean setResRole(String resId, String roleId)
    {
        if (validator.validateInteger(roleId)&&validator.validateInteger(resId)){
            return model.setResRole(Integer.parseInt(resId), Integer.parseInt(roleId));
        } else {
            return false;
        }
    }

}
