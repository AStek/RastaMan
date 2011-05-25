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
public interface IJournal {

    ArrayList<HashMap> getAll();

    ArrayList<HashMap> getInfoOfTime(String startTime, String endTime);

    ArrayList<HashMap> getInfoOfTime(int accId, int resId, String startTime, String endTime);

    ArrayList<HashMap> getInfoOfTime(int accId, String startTime, String endTime);

    ArrayList<HashMap> getInfoOfTimeByRes(int resId, String startTime, String endTime);

    boolean remove(int accId, int resID);

    int reserveRes(int accId, int resId, String startTime, String endTime);

}
