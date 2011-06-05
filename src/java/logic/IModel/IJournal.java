/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package logic.IModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Александр
 */
public interface IJournal {

    /**
     * Возвращает все существующие записи
     * @return ArrayList<HashMap>
     */
    ArrayList<HashMap> getAll();

    /**
     * Возвращает все строки по указанному диапазону дат
     *
     * @param startTime
     * @param endTime
     * @return ArrayList<HashMap>
     * Добавил Александр
     */
    ArrayList<HashMap> getInfoOfTime(String startTime, String endTime);

    /**
     * Возвращает строки по указанному диапазону дат для пользователя и для указанного ресурса
     * @param accId
     * @param resId
     * @param startTime
     * @param endTime
     * @return ArrayList<HashMap>
     * Добавил Александр
     */
    ArrayList<HashMap> getInfoOfTime(int accId, int resId, String startTime, String endTime);

    /**
     * Возвращает информацию по указанному диапазону дат для указанного пользователя
     * @param accId
     * @param startTime
     * @param endTime
     * @return ArrayList<HashMap>
     * Добавил Александр
     */
    ArrayList<HashMap> getInfoOfTime(int accId, String startTime, String endTime);

    /**
     * Возвращает информацию по указанному ресурсу и диапазону дат
     * @param resId
     * @param startTime
     * @param endTime
     * @return ArrayList<HashMap>
     * Добавил Александр
     */
    ArrayList<HashMap> getInfoOfTimeByRes(int resId, String startTime, String endTime);
    public HashMap getInfo(int id);

    /**
     * Удаляет строку с оповещением пользователя об удалении из расписания
     * @param accId
     * @param resID
     * @param uName
     * @param time
     * @param res
     * @param mail
     * @return boolean
     * Добавил Александр
     */
    boolean remove(int accId, int resID, String uName, String time, String res, String mail);

    /**
     * Удаляет запись из журнала
     * @param accId
     * @param resID
     * @return
     * Добавил Александр
     */
    boolean remove(int accId, int resID);

    public boolean remove(int Id);

    /**
     * Перерезервирует занятость для указанного ресурса указанным пользователем на указанный диапазон
     * если ресурс небыл занят добавляет новю запись.
     *
     * @param accId
     * @param resId
     * @param startTime
     * @param endTime
     * @return int если успешно =1 если не возможно перерегистрировать =0 если ошибка =-1
     * Добавил Александр
     */
    int reserveRes(int accId, int resId, String startTime, String endTime);

}
