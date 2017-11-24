/**
 * @author Jose Polo
 * @date 11/2017
 * 
 * Class that adds emailing functionality to listed items.
 * 
 */


package p1;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;


public class Email
{
   public static void main(String [] args)
   {
      // Recipient's email ID needs to be mentioned.
      String to = "xyz@gmail.com";

      // Sender's email ID needs to be mentioned
      String from = "abcd@gmail.com";

      // Assuming you are sending email from localhost
      String host = "localhost";

      // Get system properties
      Properties properties = System.getProperties();
      properties.put("mail.smtp.from", "mnop@gmail.com");

      // Setup mail server
      properties.setProperty("mail.smtp.host", host);

      // Get the default Session object.
      Session session = Session.getDefaultInstance(properties);

      try
      {
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO,
                                  new InternetAddress(to));

         // Set Subject: header field
         message.setSubject("This is the Subject Line!");

         // Now set the actual message
         message.setText("New Message goes here");

         // Send message
         Transport.send(message);
         System.out.println("Sent message successfully....");
      }
      
      catch (MessagingException mex) 
      {
         mex.printStackTrace();
      }
   }
}
