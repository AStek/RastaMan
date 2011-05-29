package logic.API;

import java.util.ArrayList;
import java.util.HashMap;
import logic.IModel.IJournal;
import logic.Model.Journal;
import logic.validator;

/**
 * @author Андрей, по мотивам написаного Лизой
 */
public class JournalAPI {

    IJournal model = new Journal();

    /**
     * Возвращает все существующие записи
     * @return ArrayList<HashMap>
     */
    public ArrayList<HashMap> getAll()
    {
        return model.getAll();
    }

    /**
     * Возвращает все строки по указанному диапазону дат
     *
     * @param startTime
     * @param endTime
     * @return ArrayList<HashMap>
     */
    public ArrayList<HashMap> getInfoOfTime(String startTime, String endTime)
    {
        if (validator.validateDateTime(startTime) && validator.validateDateTime(endTime)){
            return getInfoOfTime(startTime, endTime);
        } else{
            return null;
        }
    }

    /**
     * Возвращает строки по указанному диапазону дат для пользователя и для указанного ресурса
     * @param accId
     * @param resId
     * @param startTime
     * @param endTime
     * @return ArrayList<HashMap>
     */
    public ArrayList<HashMap> getInfoOfTime(String accId, String resId, String startTime, String endTime)
    {
        if (
            validator.validateInteger(accId) && validator.validateInteger(resId) &&
            validator.validateDateTime(startTime) && validator.validateDateTime(endTime)
        ){
            return model.getInfoOfTime(Integer.parseInt(accId), Integer.parseInt(resId),
                    startTime, endTime);
        } else {
            return null;
        }
    }

    /**
     * Возвращает информацию по указанному диапазону дат для указанного пользователя
     * @param accId
     * @param startTime
     * @param endTime
     * @return ArrayList<HashMap>
     */
    public ArrayList<HashMap> getInfoOfTime(String accId, String startTime, String endTime)
    {
        if (
            validator.validateInteger(accId) &&
            validator.validateDateTime(startTime) && validator.validateDateTime(endTime)
        ){
            return model.getInfoOfTime(Integer.parseInt(accId),
                    startTime, endTime);
        } else {
            return null;
        }
    }

    /**
     * Возвращает информацию по указанному ресурсу и диапазону дат
     * @param resId
     * @param startTime
     * @param endTime
     * @return ArrayList<HashMap>
     */
    public ArrayList<HashMap> getInfoOfTimeByRes(String resId, String startTime, String endTime)
    {
        if (
            validator.validateInteger(resId) &&
            validator.validateDateTime(startTime) && validator.validateDateTime(endTime)
        ){
            return model.getInfoOfTimeByRes(Integer.parseInt(resId),
                    startTime, endTime);
        } else {
            return null;
        }
    }

    /**
     * Удаляет строку с оповещением пользователя об удалении из расписания
     * @param accId
     * @param resID
     * @param uName
     * @param time
     * @param res
     * @param mail
     * @return boolean
     */
    public boolean remove(String accId, String resId, String uName, String time, String res, String mail)
    {
        if (
            validator.validateInteger(accId) && validator.validateInteger(resId) &&
            validator.validateString(uName, 50) && validator.hasSpecialChars(uName) &&
            validator.validateString(mail, 32) && validator.hasSpecialChars(mail) &&
            validator.validateString(time, 50) && validator.validateString(res, 60) &&
            validator.hasSpecialChars(res)
        ){
            return model.remove(Integer.parseInt(accId), Integer.parseInt(resId),
                    uName, time, res, mail);
        } else {
            return false;
        }
    }

    /**
     * Удаляет запись из журнала
     * @param accId
     * @param resID
     * @return
     */
    public boolean remove(String accId, String resId)
    {
        if (
            validator.validateInteger(accId) && validator.validateInteger(resId)
        ){
            return model.remove(Integer.parseInt(accId), Integer.parseInt(resId));
        } else {
            return false;
        }
    }

    /**
     * Перерезервирует занятость для указанного ресурса указанным пользователем на указанный диапазон
     * если ресурс небыл занят добавляет новю запись.
     *
     * @param accId
     * @param resId
     * @param startTime
     * @param endTime
     * @return int если успешно =1 если не возможно перерегистрировать =0 если ошибка =-1
     */
    public int reserveRes(String accId, String resId, String startTime, String endTime)
    {
        if (
            validator.validateInteger(accId) && validator.validateInteger(resId) &&
            validator.validateDateTime(startTime) && validator.validateDateTime(endTime)
        ){
            return model.reserveRes(Integer.parseInt(accId), Integer.parseInt(resId),
                    startTime, endTime);
        } else {
            return -1;
        }
    }

}
