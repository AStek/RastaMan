/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package logic;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * Класс для отправки писем
 * @author Андрей
 */
public class SendMail {

  private String email = null;
  private String password = null;
  private String smtp_server = null;
  private String port = null;
  private String messageTo = null; //Адресат
  private String messageSubject = null; //Тема письма
  private String messageBody = null; //Тело письма
  private Properties prop = null; //Объект свойств

  /**
   * Конструктор письма
   * @param messageTo String Адрес адресата
   * @param messageSubject String Тема
   * @param messageBody String Тело в формате разметки html
   */
  public SendMail(String messageTo, String messageSubject, String messageBody)
  {
        email = CfgMgr.getValue("email");
        password = CfgMgr.getValue("password");
        smtp_server = CfgMgr.getValue("smtp_server");
        port = CfgMgr.getValue("port");

        this.messageTo = messageTo;
        this.messageSubject = messageSubject;
        this.messageBody = messageBody;
        prop = new Properties();
  }

  /**
   * Отправка сообщения
   */
  public void sendSSLEmail()
  {

    //Устанавливаем необходимые свойства
    prop.put("mail.smtp.host", smtp_server);
    prop.put("mail.port.socketFactory.port", port);
    prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    prop.put("mail.smtp.auth", "true");
    prop.put("mail.smtp.port", port);

    //Создаем новую сессию для текущих параметров
    Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator(){
      @Override
      protected PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication(email, password);
      }
    });

    /*
     * Если авторизоваться на сервере получилось,
     * то создаем новое сообщение
     */
    try{
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress(email));
      //'MIME-Version: 1.0' . "\r\n" . 'Content-type: text/html; charset=UTF-8' . "\r\n"
      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(messageTo));
      message.setSubject(messageSubject);
      message.setText(messageBody);
      message.setHeader("MIME-Version", "1.0");
      message.setHeader("Content-type", "text/html");

      //Отправляем сообщение на сервер
      Transport.send(message);

    } catch(MessagingException e){
        //To-Do: Error manager hear
    }
  }

}
