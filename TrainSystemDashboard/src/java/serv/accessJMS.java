/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serv;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.proxy.dwr.Util;

/**
 *
 * @author heymeowcat
 */
public class accessJMS {

    Gson gson = new Gson();
    Map<String, Train> trainMap = new HashMap<>();
    String wholeTrainList = "";

    private Message createJMSMessageFormyTopic(Session session, Object messageData) throws JMSException {
        TextMessage tm = session.createTextMessage();
        tm.setText(messageData.toString());
        return tm;
    }

    public void sendJMSMessageToMyTopic(String messageData) throws JMSException, NamingException {
        Context c = new InitialContext();
        ConnectionFactory cf = (ConnectionFactory) c.lookup("jmsTopicConnFactory");
        Connection conn = null;
        Session s = null;
        try {
            conn = cf.createConnection();
            s = conn.createSession(false, s.AUTO_ACKNOWLEDGE);
            Destination destination = (Destination) c.lookup("myTopic");
            MessageProducer mp = s.createProducer(destination);
            mp.send(createJMSMessageFormyTopic(s, messageData));
        } finally {
            if (s != null) {
                try {
                    s.close();
                } catch (JMSException e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot close session", e);
                }
            }
            if (conn != null) {
                conn.close();
            }
        }

    }

    public void readJMSMessageToMyTopic() throws JMSException, NamingException {
        WebContext wctx = WebContextFactory.get();
        String currentPage = wctx.getCurrentPage();
        Collection sessions = wctx.getScriptSessionsByPage(currentPage);
        Util utilAll = new Util(sessions);
        Context c = new InitialContext();
        String data = "";
        ConnectionFactory topicConnectionFactory = (ConnectionFactory) c.lookup("jmsTopicConnFactory");
        Connection conn = null;
        Session s = null;
        Session session = null;
        try {
            conn = topicConnectionFactory.createConnection();
            session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic myTopic = (Topic) c.lookup("myTopic");
            MessageConsumer messageConsumer = session.createSharedDurableConsumer(myTopic, "WEBconsumer1");
            conn.start();
            while (true) {
                Message m = messageConsumer.receiveNoWait();
                if (m != null) {
                    if (m instanceof TextMessage) {
                        TextMessage message = (TextMessage) m;
                        data = message.getText();

                        Train train = gson.fromJson(data, Train.class);
                        trainMap.put(train.getTrainid(), train);

                        wholeTrainList = "";
                        for (String key : trainMap.keySet()) {
                            wholeTrainList += "<div class=\"col s12 m12 l12\">\n"
                                    + "                <div class=\"card blue-grey darken-1\">\n"
                                    + "                    <div class=\"card-content white-text\">\n"
                                    + "                        <span id=\"trainid" + key + "\" class=\"card-title\">Train Id :  " + trainMap.get(key).getTrainid() + "</span>\n"
                                    + "                        <div  class=\"row\">\n"
                                    + "                            <div class=\"col s12 m6 l3\">\n"
                                    + "                                <div style='cursor: pointer;' onclick=\" window.open('https://www.google.com/maps/search/?api=1&query=" + trainMap.get(key).getLatitude() + " ," + trainMap.get(key).getLongitude() + "');\" class=\"card blue-grey lighten-1\">\n"
                                    + "                                    <span class=\"card-title center-align\">GPS Location</span>\n"
                                    + "                                    <p class=\"center-align\" style=\"font-size: 15px\">Latitude</p>\n"
                                    + "                                    <p  class=\"center-align\" style=\"font-size: 20px\"><b id=\"latVal" + key + "\">" + trainMap.get(key).getLatitude() + "</b></p>\n"
                                    + "                                    <p class=\"center-align\" style=\"font-size: 15px\">Longitude</p>\n"
                                    + "                                    <p class=\"center-align\" style=\"font-size: 20px\"><b id=\"lonVal" + key + "\">" + trainMap.get(key).getLongitude() + "</b></p>\n"
                                    + "                                </div>\n"
                                    + "                            </div>\n"
                                    + "                            <div class=\"col s12 m6 l3\">\n"
                                    + "                                <div class=\"card blue-grey lighten-1\">\n"
                                    + "                                    <span class=\"card-title center-align\">Speed</span>\n"
                                    + "                                    <p class=\"center-align\" style=\"font-size: 45px\"><b id=\"speedVal" + key + "\">" + trainMap.get(key).getSpeed() + "</b></p>\n"
                                    + "                                    <p class=\"center-align\" style=\"font-size: 25px\">km/h</p>\n"
                                    + "                                </div>\n"
                                    + "                            </div>\n"
                                    + "                            <div class=\"col s12 m6 l3\">\n"
                                    + "                                <div class=\"card blue-grey lighten-1\">\n"
                                    + "                                    <span class=\"card-title center-align\">Temperature</span>\n"
                                    + "                                    <p class=\"center-align\" style=\"font-size: 45px\"><b id=\"tempVal" + key + "\">" + trainMap.get(key).getTemperature() + "</b></p>\n"
                                    + "                                    <p class=\"center-align\" style=\"font-size: 25px\">°C</p>\n"
                                    + "                                </div>\n"
                                    + "                            </div>\n"
                                    + "                            <div class=\"col s12 m6 l3\">\n"
                                    + "                                <div class=\"card blue-grey lighten-1\">\n"
                                    + "                                    <span class=\"card-title center-align\">Humidity</span>\n"
                                    + "                                    <p class=\"center-align\" style=\"font-size: 45px\"><b id=\"HumidityVal" + key + "\">" + trainMap.get(key).getHumidity() + "</b></p>\n"
                                    + "                                    <p class=\"center-align\" style=\"font-size: 25px\">kg<sup>-1</sup></p>\n"
                                    + "                                </div>\n"
                                    + "                            </div>\n"
                                    + "                        </div>\n"
                                    + "                    </div>\n"
                                    + "\n"
                                    + "                </div>\n"
                                    + "            </div>";

                            utilAll.setValue("trainContainer", wholeTrainList);

                        }
                    }
                }
            }

        } catch (JMSException | NamingException e) {
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot close session", e);
                }
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

}
