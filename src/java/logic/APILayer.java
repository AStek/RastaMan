/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author 1
 */
public class APILayer {

    private  static APILayer instance=null;
    private APILayer()
    {
    }

    public static APILayer getInstance()
    {
        if (instance==null)
            instance=new APILayer();
        return instance;
    }


    public String checkLogin(String login,String pass)
    {
        String log=validator.protectString(login);
        String pas=validator.protectString(pass);
        if(validator.validateString(log, 12)&&
                validator.validateString(pas, 15))
        {
            return RMCore.getInstance().checkAccount(log, pas);
        }
        else
       return null;
    }



    public ArrayList<HashMap> resoursesReturn()
    {
     return RMCore.getInstance().getResources();
    }


    public int priorityReturn()
    {
        return 1;
    }

    public ArrayList<String> usersReturn()
    {
        return null;
    }

    public ArrayList<String> resoursesFromDate(String date)
    {
        return null;
    }

    /**
     * Удаление учетной записи пользователя
     * @param id String Число идентификатор записи
     * @return Boolean
     *
     * Добавлен Андреем 21.05.2011 19:52
     */
    public boolean deleteAccount(String  id)
    {
        if (validator.validateInteger(id)){
            try {
                return RMCore.getInstance().deleteAccountById(Integer.parseInt(id));
            } catch (SQLException ex) {
                //To-Do: Сдесь неплохо бы вставить обращение к менеджеру 
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Создание учетной записи
     * @param login String Строка с логином, не более 20 символ
     * @param password String Строка с паролем, не более 20 символ
     * @param name String Строка с именем, не более 50 символ
     * @param roleId String Число идентификатор роли
     * @param priority String Число приоритет записи
     * @param email String E-Mail адрес, не более 32 символ
     * @return Boolean
     *
     *  Добавлен Андреем 21.05.2011 19:52
     */
    public boolean addAccount(String login, String password, String name,
            String roleId, String priority, String email)
    {
        //Проверки на валидность
        Boolean loginFlag = validator.validateString(login, 20) && validator.hasSpecialChars(login);
        Boolean passwordFlag = validator.validateString(password, 20) && validator.hasSpecialChars(password);
        Boolean nameFlag = validator.validateString(name, 50) && validator.hasSpecialChars(name);
        Boolean emailFlag = validator.validateString(email, 20) && validator.hasSpecialChars(email);
        Boolean priorityFlag = validator.validateInteger(priority);
        Boolean roleIdFlag = validator.validateInteger(roleId);

        if (loginFlag && passwordFlag && nameFlag && emailFlag && priorityFlag && roleIdFlag){
            try {
                return RMCore.getInstance().addAccount(login, password, name,
                        Integer.parseInt(roleId), Integer.parseInt(priority),
                        email);
            } catch (SQLException ex) {
                //To-Do: Сдесь неплохо бы вставить обращение к менеджеру
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Редактирование учетной записи
     * @param id String Число идентификатор записи
     * @param login String Строка с логином, не более 20 символ
     * @param password String Строка с паролем, не более 20 символ
     * @param name String Строка с именем, не более 50 символ
     * @param roleId String Число идентификатор роли
     * @param priority String Число приоритет записи
     * @param email String E-Mail адрес, не более 32 символ
     * @return Boolean
     * Добавлен Андреем 21.05.2011 19:52
     */
    public boolean updateAccountById(String id, String login, String password, String name,
            String roleId, String priority, String email)
    {
        //Проверки на валидность
        Boolean loginFlag = validator.validateString(login, 20) && validator.hasSpecialChars(login);
        Boolean passwordFlag = validator.validateString(password, 20) && validator.hasSpecialChars(password);
        Boolean nameFlag = validator.validateString(name, 50) && validator.hasSpecialChars(name);
        Boolean emailFlag = validator.validateString(email, 20) && validator.hasSpecialChars(email);
        Boolean priorityFlag = validator.validateInteger(priority);
        Boolean roleIdFlag = validator.validateInteger(roleId);
        Boolean idFlag = validator.validateInteger(id);

        if (loginFlag && passwordFlag && nameFlag && emailFlag && priorityFlag && roleIdFlag && idFlag){
            try {
                return RMCore.getInstance().updateAccountById(Integer.parseInt(id), login,
                        password, name, Integer.parseInt(roleId),
                        Integer.parseInt(priority), email);
            } catch (SQLException ex) {
                //To-Do: Сдесь неплохо бы вставить обращение к менеджеру
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Возвращает список ролей
     * @return ArrayList
     *
     * Добавлен Андреем 22.05.2011 12:05
     */
    public ArrayList<HashMap> getRoleList()
    {
        return RMCore.getInstance().getRoleList();
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
        Boolean nameFlag = validator.validateString(name, 50) && validator.hasSpecialChars(name);
        Boolean roleFlag = validator.validateString(role, 20) && validator.hasSpecialChars(role);
        if (nameFlag && roleFlag){
            return RMCore.getInstance().getAccountByNameAndRole(name, role);
        } else {
            return null;
        }
    }

}
