/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javamailapp;

/**
 *
 * @author BillyLim
 * Code snippet courtesy of your fellow student Dinesh Daultani
 */
import Controller.*;
import Model.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
//import model.UserBean;

public class JavaMailApp {

    public static void scheduleAppointmentEmail(AppointmentBean ab) {

        // Recipient's email ID needs to be mentioned.
        String to = ab.getStudent().getEmail();

        // Sender's email ID needs to be mentioned
        String from = "no.reply.linkedu@gmail.com";
        
        // Assuming you are sending email from this host
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.port", "587");
        // Get the default Session object.
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("no.reply.linkedu@gmail.com", "it353it353");
            }
        });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Appointment Scheduled with " + ab.getUniversity() + "!");
            int month = ab.getDate().get(Calendar.MONTH) + 1;
            // Send the actual HTML message, as big as you like
            //String str = "<img src='https://universitymarketing.illinoisstate.edu/images/logos/ISU-Seal_1C_186.png' align='middle' style='width: 150px; height: auto;'/><br/>";
            String str = "<h3>" + ab.getStudent().getFirstName() + " " + ab.getStudent().getLastName() + ",</h3>";
            str += "<p>Thank you for scheduling an appointment with " + ab.getUniversity() + " through LinkedU. We have created your appointment with the following date:</p>";
            str += "<ul><li style=\"margin-left: 40px\">Date: " + month + "/" + ab.getDate().get(Calendar.DAY_OF_MONTH) + "/" + ab.getDate().get(Calendar.YEAR) +
                    "</li><li style=\"margin-left: 40px\">Time: " + ab.getTime() + "</li></ul>";
            str += "<h4>Thank you,</h4>";
            str += "<h4>LinkedU</h4>";
                    
            message.setContent(str,"text/html");

            // Send message
            Transport.send(message);
            
            //System.out.println(ab.getDate().get(Calendar.MONTH) + 1 + "/" + ab.getDate().get(Calendar.DAY_OF_MONTH) + "/" + ab.getDate().get(Calendar.YEAR));
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
    
}
