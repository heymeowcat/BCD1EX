/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iotsimulator;

import com.google.gson.Gson;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

/**
 *
 * @author heymeowcat
 */
public class Topic_Client extends javax.swing.JFrame {

    @Resource(mappedName = "myTopic")
    private static Topic myTopic;

    @Resource(mappedName = "jmsTopicConnFactory")
    private static ConnectionFactory topicConnFactory;

    Train train;

    Gson gson = new Gson();

    String jsonInString;

    public Topic_Client() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        trainid = new javax.swing.JTextField();
        setBtn = new javax.swing.JButton();
        temp = new javax.swing.JSlider();
        speed = new javax.swing.JSlider();
        humidity = new javax.swing.JSlider();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        latSetBtn = new javax.swing.JButton();
        lonSetBtn = new javax.swing.JButton();
        latInput = new javax.swing.JFormattedTextField();
        lonInput = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("National Train System");
        setBackground(new java.awt.Color(153, 153, 153));
        setResizable(false);
        getContentPane().setLayout(new java.awt.CardLayout());

        jLabel1.setText("Train Id");

        setBtn.setText("Set");
        setBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setBtnActionPerformed(evt);
            }
        });

        temp.setMaximum(500);
        temp.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tempStateChanged(evt);
            }
        });

        speed.setMaximum(800);
        speed.setToolTipText("");
        speed.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                speedStateChanged(evt);
            }
        });

        humidity.setValue(0);
        humidity.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                humidityStateChanged(evt);
            }
        });

        jLabel2.setText("Latitude");

        jLabel3.setText("Longitude");

        jLabel4.setText("Speed");

        jLabel5.setText("Temperature");

        jLabel6.setText("Humidity");

        latSetBtn.setText("Set");
        latSetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                latSetBtnActionPerformed(evt);
            }
        });

        lonSetBtn.setText("Set");
        lonSetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lonSetBtnActionPerformed(evt);
            }
        });

        latInput.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("##.####"))));

        lonInput.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("##.####"))));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(trainid, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(setBtn))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(speed, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(temp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(humidity, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(latInput)
                                    .addComponent(lonInput))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(latSetBtn))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lonSetBtn)))))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(trainid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(setBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(latSetBtn))
                    .addComponent(latInput))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lonSetBtn)
                    .addComponent(lonInput))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(speed, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(temp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(humidity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(jPanel2, "card3");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void setBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setBtnActionPerformed
        if (!trainid.getText().isEmpty()) {
            train = new Train(trainid.getText(), 0, 0, 0, 0, 0);
            trainid.setEnabled(false);
            setBtn.setEnabled(false);
            jsonInString = gson.toJson(train);
            try {
                sendJMSMessageToMyTopic(jsonInString);
            } catch (JMSException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_setBtnActionPerformed

    private void latSetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_latSetBtnActionPerformed
        if (!latInput.getText().isEmpty()) {
            train.setLatitude(Double.parseDouble(latInput.getText()));
            jsonInString = gson.toJson(train);
            try {
                sendJMSMessageToMyTopic(jsonInString);
            } catch (JMSException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_latSetBtnActionPerformed

    private void lonSetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lonSetBtnActionPerformed
        if (!lonInput.getText().isEmpty()) {
            train.setLongitude(Double.parseDouble(lonInput.getText()));
            jsonInString = gson.toJson(train);
            try {
                sendJMSMessageToMyTopic(jsonInString);
            } catch (JMSException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_lonSetBtnActionPerformed

    private void speedStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_speedStateChanged
        double speedVal = (double) speed.getValue();
        train.setSpeed(speedVal);
        jsonInString = gson.toJson(train);
        try {
            sendJMSMessageToMyTopic(jsonInString);
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_speedStateChanged

    private void tempStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tempStateChanged
        double tempVal = (double) temp.getValue();
        train.setTemperature(tempVal);
        jsonInString = gson.toJson(train);
        try {
            sendJMSMessageToMyTopic(jsonInString);
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_tempStateChanged

    private void humidityStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_humidityStateChanged
        double humidityVal = (double) humidity.getValue();
        train.setHumidity(humidityVal);
        jsonInString = gson.toJson(train);
        try {
            sendJMSMessageToMyTopic(jsonInString);
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_humidityStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Topic_Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Topic_Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Topic_Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Topic_Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Topic_Client().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSlider humidity;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JFormattedTextField latInput;
    private javax.swing.JButton latSetBtn;
    private javax.swing.JFormattedTextField lonInput;
    private javax.swing.JButton lonSetBtn;
    private javax.swing.JButton setBtn;
    private javax.swing.JSlider speed;
    private javax.swing.JSlider temp;
    private javax.swing.JTextField trainid;
    // End of variables declaration//GEN-END:variables

    private Message createJMSMessageFormyTopic(Session session, Object messageData) throws JMSException {
        // TODO create and populate message to send
        TextMessage tm = session.createTextMessage();
        tm.setText(messageData.toString());
        return tm;
    }

    private void sendJMSMessageToMyTopic(Object messageData) throws JMSException {
        Connection connection = null;
        Session session = null;
        try {
            connection = topicConnFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(myTopic);
            messageProducer.send(createJMSMessageFormyTopic(session, messageData));
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot close session", e);
                }
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
