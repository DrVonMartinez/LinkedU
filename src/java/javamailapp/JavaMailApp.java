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
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
//import model.UserBean;

public class JavaMailApp {
/*
    public static void scheduleAppointmentEmail(UserBean aUser) {

        // Recipient's email ID needs to be mentioned.
        String to = aUser.getEmail();

        // Sender's email ID needs to be mentioned
        String from = "no.reply.linkedu@gmail.com";
        
        // Assuming you are sending email from this host
        String host = "www.gmail.com";

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
            message.setSubject("Thank you for signing up with ISU!");

            // Send the actual HTML message, as big as you like
            String str = "<img src='https://universitymarketing.illinoisstate.edu/images/logos/ISU-Seal_1C_186.png' align='middle' style='width: 150px; height: auto;'/><br/>";
            str += "<h3>Welcome " + aUser.getFirstName() + " " + aUser.getLastName() + ",</h3>";
            str += "<p>Thank you for creating an account with Illinois State University. We have created your account with the following information you listed:</p>";
            str += "<ul><li style=\"margin-left: 40px\">User ID: " + aUser.getUserID() + 
                    "</li><li style=\"margin-left: 40px\">Security Question: " + aUser.getSecurityQuestion() + 
                    "</li><li style=\"margin-left: 40px\">Security Answer: " + aUser.getSecurityAnswer() + "</li></ul>";
            str += "<h4>Thank you,</h4>";
            str += "<h4>Illinois State University IT</h4>";
                    
            message.setContent(str,"text/html");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
    */
}
