package logic.API;

import java.util.*;
import logic.IModel.IRole;
import logic.Model.Role;
import logic.validator;

/**
 * Первая версия написана Лизой.
 * Рефакторинг, фиксы и доработка: Андрей
 */
public class RoleAPI {

    IRole model = new Role();

    /**
     * Возвращает список ролей
     * @return ArrayList
     *
     * Добавлен Андреем 22.05.2011 12:05
     */
    public ArrayList<HashMap> getRoleList()
    {
        return model.getAll();
    }

    /**
     * Дбавляет роль
     * @param String title
     * @return boolean
     */
    public boolean add(String title)
    {
        Boolean titleFlag = validator.validateString(title, 50)
                && !validator.hasSpecialChars(title);
        if (titleFlag){
            return model.add(title);
        } else {
            return false;
        }
    }

    /**
     * Дбавляет роль со ссвязкой с ресурсом по идентификатору
     * @param String title
     * @param String resId
     * @return boolean
     *
     */
    public boolean addWithRes(String title, String resId)
    {
        Boolean titleFlag = validator.validateString(title, 50)
                && !validator.hasSpecialChars(title)
                && validator.validateInteger(resId);
        if (titleFlag){
            return model.add(title, Integer.parseInt(resId));
        } else {
            return false;
        }
    }

    /**
     * Дбавляет роль со ссвязкой с ресурсом по идентификатору
     * @param String title Название
     * @param String priority Приоритет
     * @return boolean
     *
     */
    public boolean add(String title, String priority)
    {
        Boolean titleFlag = validator.validateString(title, 50)
                && !validator.hasSpecialChars(title)
                && validator.validateInteger(priority);
        if (titleFlag){
            return model.add(title, Integer.parseInt(priority));
        } else {
            return false;
        }
    }

    /**
     * Возвращает роль ро указанному идентификатору
     * @param String id
     * @return String
     */
    public HashMap getById(String id)
    {
        if (validator.validateInteger(id)){
            return model.getById(Integer.parseInt(id));
        } else {
            return null;
        }
    }

    /**
     * возврвщает ресурсы назначеные роли
     * @param id идентификатор роли
     * @return ArrayList<HashMap>
     *
     */
    public ArrayList<HashMap> getResOfRole(String id)
    {
        if (validator.validateInteger(id)){
            return model.getResOfRole(Integer.parseInt(id));
        } else {
            return null;
        }
    }

    /**
     * Удаляет роль по уканному идентификатору
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
     * изменяет название роли по идентификатору
     * @param id
     * @param title название роли
     * @return boolean
     *
     */
    public boolean setById(String id, String title, String weight)
    {
        Boolean titleFlag = validator.validateString(title, 50)
                && !validator.hasSpecialChars(title)
                && validator.validateInteger(id)
                && validator.validateInteger(weight);
        if (titleFlag){
            return model.setById(Integer.parseInt(id), title, Integer.parseInt(weight));
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
