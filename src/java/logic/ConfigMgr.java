/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package logic;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elynor
 */
public class ConfigMgr {
    Object[] ValuesArray = null;
    Object[] KeysArray = null;

    public ConfigMgr(String file){
        ArrFilling(file);
    }

    public void ArrFilling(String file){
                try {
            Properties p = new Properties();
            p.load(new FileInputStream(file));
            ValuesArray = p.values().toArray();
            KeysArray = p.keySet().toArray();
        } catch (IOException ex) {
            Logger.getLogger(ConfigMgr.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getValueByKey(String key)
    {
        for (int i=0;i<this.KeysArray.length;i++){
            if (this.KeysArray[i].toString().equals(key)){
                return this.ValuesArray[i].toString();
            }
        }
        return null;
    }

}
