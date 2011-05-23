package logic;

/**
 * Класс валидатор
 * @author andrey
 *
 * 21.05.2011 19:52 Внесены правки в метод hasSpecialChars
 * 
 */
public class validator {

    /**
     * Провер0000яет на пустую строку
     * @param val String Обект для проверки
     * @return Boolean
     */
    public static boolean isEmpty(String val)
    {
        if ((val==null)||(val.isEmpty())){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Проверяет строку
     * @param val String Обект для проверки
     * @param length Integer Предельная длина строки
     * @return Boolean
     */
    public static boolean validateString(String val, int length)
    {
        return (!isEmpty(val))&&(val.length()<=length);
    }

    /**
     * Проверка целого числа
     * @param val String Переменная для проверки
     * @return Boolean
     */
    public static boolean validateInteger(String val)
    {
        return (!isEmpty(val))
                &&(val.length()<24)
                &&val.matches("^(\\d)+");
    }

    /**
     * Проверка дробного числа
     * @param val String
     * @return Boolean
     */
    public static boolean validateFloat(String val)
    {
        return (!isEmpty(val))
                &&(val.length()<24)
                &&val.matches("^(\\d)+(.(\\d)+)?");
    }

    /**
     * Проверка правильности ввода почтового адреса
     * @param val String Строка с адресом
     * @return Boolean
     */
    public static boolean validateEmail(String val)
    {
        return (!isEmpty(val))
                &&(val.length()<64)
                &&val.matches("^((\\d)|(\\w)){2,}@((\\d)|(\\w)){2,}.((\\d)|(\\w)){2,6}");
    }

    /**
     * Проверка формата времени
     * @param val String 
     * @return Boolean
     */
    public static boolean validateTime(String val)
    {
        return (!isEmpty(val))
                &&(val.length()==8)
                &&val.matches("^(\\d){2}:(\\d){2}:(\\d){2}");
    }

    /**
     * Проверка формата даты
     * @param val String
     * @return Boolean
     */
    public static boolean validateDate(String val)
    {
        return (!isEmpty(val))
                &&(val.length()==10)
                &&val.matches("^(\\d){2}:(\\d){2}:(\\d){4}");
    }

    /**
     * Защита строки от спец-символов
     * @param val String Строка
     * @return String
     */
    public static String protectString(String val)
    {
        if (!hasSpecialChars(val)) {
          return(val);
        }
        StringBuilder filtered = new StringBuilder(val.length());
        char c;
        for(int i=0; i<val.length(); i++) {
          c = val.charAt(i);
          switch(c) {
            case '<': filtered.append("&lt;"); break;
            case '>': filtered.append("&gt;"); break;
            case '"': filtered.append("&quot;"); break;
            case '&': filtered.append("&amp;"); break;
            case '\'': filtered.append("&prime;"); break;
            default: filtered.append(c);
          }
        }
        return(filtered.toString());
    }

    /**
     * Проверка на наличие спец-символов
     * @param val String
     * @return Boolean
     */
    public static boolean hasSpecialChars(String val)
    {
        boolean flag = false;
        if ((val != null) && (val.length() > 0)) {
          char c;
          for(int i=0; i<val.length(); i++) {
            c = val.charAt(i);
            switch(c) {
              case '<': flag = true; break;
              case '>': flag = true; break;
              case '"': flag = true; break;
              case '&': flag = true; break;
              case '\'': flag = true; break;
            }
          }
        }
        return(flag);
    }
}