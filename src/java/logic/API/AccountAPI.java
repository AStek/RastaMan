package logic.API;

import java.util.*;
import logic.IModel.IAccount;
import logic.Model.Account;
import logic.SendMail;
import logic.validator;

/**
 * Первая версия написана Лизой.
 * Рефакторинг, фиксы и доработка: Андрей
 */
public class AccountAPI {

    public IAccount model = new Account();


    /**
     * Создание учетной записи
     * @param login String Строка с логином, не более 20 символ
     * @param password String Строка с паролем, не более 20 символ
     * @param name String Строка с именем, не более 50 символ
     * @param roleId String Число идентификатор роли
     * @param email String E-Mail адрес, не более 32 символ
     * @return Boolean
     *
     *  Добавлен Андреем 21.05.2011 19:52
     */
    public boolean add(String login, String password, String name, String roleId,  String email)
    {
        //Проверки на валидность
        Boolean loginFlag = validator.validateString(login, 20) && !validator.hasSpecialChars(login);
        Boolean passwordFlag = validator.validateString(password, 20) && !validator.hasSpecialChars(password);
        Boolean nameFlag = validator.validateString(name, 50) && !validator.hasSpecialChars(name);
        Boolean emailFlag = validator.validateEmail(email);
        Boolean roleIdFlag = validator.validateInteger(roleId);

        if (loginFlag && passwordFlag && nameFlag && emailFlag  && roleIdFlag){

                return model.add(login, password, name,
                        Integer.parseInt(roleId),
                        email);

        } else {
            return false;
        }
    }

    public String autorize(String login, String paswd)
    {
        String log=validator.protectString(login);
        String pas=validator.protectString(paswd);
        if(validator.validateString(log, 12)&&
                validator.validateString(pas, 15))
        {
            return model.autorize(log, pas);
        }
        else {
            return null;
        }
    }

    /**
     * Удаление учетной записи пользователя
     * @param id String Число идентификатор записи
     * @return Boolean
     *
     * Добавлен Андреем 21.05.2011 19:52
     */
    public boolean deleteById(String id)
    {
        if (validator.validateInteger(id)){
                return model.deleteById(Integer.parseInt(id));
        } else {
            return false;
        }
    }

    /**
     * Возвращает существующих пользователей
     * @return ArrayList<HashMap>
     */
    public ArrayList<HashMap> getAll()
    {
        return model.getAll();
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
        Boolean nameFlag = validator.validateString(name, 50) && !validator.hasSpecialChars(name);
        Boolean roleFlag = validator.validateString(role, 20) && !validator.hasSpecialChars(role);
        if (nameFlag && roleFlag){
            return model.getByNameAndRole(name, role);
        } else
        {
            return null;
        }
    }

    /**
     * Получение приоритета пользователя
     *
     * @return String возвращает приоритет пользователя. (адекватно работает после вызова функции авторизаци
     * @see checkAccount)
     * в случаее ошибки возвращает -1
     */
    public int getPriority()
    {
         return model.getPriority();
    }

    /**
     * Получение приоритета пользователя
     *
     * @return String возвращает приоритет пользователя, который зарегестрирован под указанным логином.
     * в случаее ошибки возвращает -1
     * Добавил Александр 18.05.2011 20:16
     */
    public int getPriorityByLogin(String login)
    {
      Boolean loginFlag = validator.validateString(login, 20) && !validator.hasSpecialChars(login);
      if (loginFlag){
                return model.getPriority(login);
        } else{
         return -1;
        }
    }

    public int getPriorityById(String id)
    {
         if (validator.validateInteger(id)){
             return model.getPriority(Integer.parseInt(id));
         } else {
             return -1;
         }
    }

    /**
     * Проверяет на уникальность логинв
     * @param String login
     * @return boolean
     */
    public boolean isUnique(String login)
    {
       Boolean loginFlag = validator.validateString(login, 20) && !validator.hasSpecialChars(login);
       if (loginFlag)
       {
           return model.isUnique(login);
       }else
       {
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
    public boolean setById(String id, String login, String password,
            String name, String roleId,  String email)
    {
        //Проверки на валидность
        Boolean loginFlag = validator.validateString(login, 20) && !validator.hasSpecialChars(login);
        Boolean passwordFlag = validator.validateString(password, 20) && !validator.hasSpecialChars(password);
        Boolean nameFlag = validator.validateString(name, 50) && !validator.hasSpecialChars(name);
        Boolean emailFlag = validator.validateEmail(email);
        Boolean roleIdFlag = validator.validateInteger(roleId);
        Boolean idFlag = validator.validateInteger(id);

        if (loginFlag && passwordFlag && nameFlag && emailFlag  && roleIdFlag && idFlag){

                return model.setById(Integer.parseInt(id), login,
                        password, name, Integer.parseInt(roleId), email);

        } else {
            return false;
        }
    }

    public HashMap getInfo(String login)
    {
        Boolean s = !validator.hasSpecialChars(login);
        Boolean l = validator.validateString(login, 20);
        if (s && l){
            return model.getInfo(login);
        } else {
            return null;
        }
    }

    /**
     * Восстановление пароля
     * @param String login
     * @return
     * Добавил Андрей
     */
    public boolean resurection(String login){
        HashMap info = model.getInfo(login);
        if (info==null){return false;}
        String text = "<b>Восстановление пароля на ресурсе рассписания</b><br>Ваш пароль: ";
        text = text+info.get("PASSWORD").toString();
        SendMail mail = new SendMail(info.get("EMAIL").toString(), "Восстановление пароля", text);
        mail.sendSSLEmail();
        return true;
    }
}
