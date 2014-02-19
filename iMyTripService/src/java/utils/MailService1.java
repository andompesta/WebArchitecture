/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author tlmarco
 */
public class MailService1
{
    public boolean SendMessage(String sendTo,String subject,String msgBody) throws MessagingException {
        
        String host = "smtp.gmail.com";
	String port = "465";
	final String maillogin = "ando.cavallari@gmail.com";
	final String password = "vela1990";
        boolean isSSL = true;
        //leggo i parametri dal web.xml
         
	Properties props = new Properties();
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port",port);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        try {
            Authenticator authenticator = new Authenticator () {
                public PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication(maillogin, password);
                }
            };
            Session session = Session.getInstance(props, authenticator);

            session.setDebug(true); 

            // Sender's email depending on login userid for GOOGLE MAIL
            // String from = "ando.grasa@gmail.com";

            Message message = new MimeMessage(session);
            //message.setFrom(new InternetAddress(from));
            InternetAddress [] iaTo = new InternetAddress[1];
            iaTo[0] = new  InternetAddress(sendTo);
            message.setRecipients(Message.RecipientType.TO,iaTo);
            message.setSubject(subject);
            //msgBody = "<HTML>"+"<a href='http://localhost:8080/NewProgettoAfrica//"+msgBody+"'>"+"Pdf </a>"+"</HTML>";
            message.setContent(msgBody,"text/html");

            int iPort = Integer.parseInt(port);
            Transport transport = session.getTransport("smtps");
            transport.connect(host, iPort, maillogin, password);
            Transport.send(message);
            
            return true;
        }
        catch (MessagingException mex) 
        {
            mex.printStackTrace();
            throw mex;
        }
    }
    
    public boolean SendMessagePDF(String sendTo,String subject,String fileName) throws MessagingException {
        
        String host = "smtp.gmail.com";
	String port = "465";
	final String maillogin = "ando.cavallari@gmail.com";
	final String password = "vela1990";
        boolean isSSL = true;
        //leggo i parametri dal web.xml
         
	Properties props = new Properties();
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port",port);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        Authenticator authenticator = new Authenticator () {
            public PasswordAuthentication getPasswordAuthentication(){
            	return new PasswordAuthentication(maillogin, password);
            }
        };
        Session session = Session.getInstance(props, authenticator);
        
        session.setDebug(true); 
	try {

            // Sender's email depending on login userid for GOOGLE MAIL
            // String from = "ando.grasa@gmail.com";

            Message message = new MimeMessage(session);
            //message.setFrom(new InternetAddress(from));
            InternetAddress [] iaTo = new InternetAddress[1];
            iaTo[0] = new  InternetAddress(sendTo);
            message.setRecipients(Message.RecipientType.TO,iaTo);
            message.setSubject(subject);
            Multipart multiparte = new MimeMultipart();
            
            //crea la parte testuale 
            BodyPart messageBodyPart1 = new MimeBodyPart(); 
            messageBodyPart1.setText("In questa mail √® presente il biglietto comprato per i trasporti pubblici");
            
            //crea l'allegato txt 
            DataSource source = new FileDataSource(fileName); 
            BodyPart messageBodyPart2 = new MimeBodyPart();
            messageBodyPart2.setDataHandler( new DataHandler(source) ); 
            messageBodyPart2.setFileName( "Biglietto.pdf" );
            
            //aggiunge le parti all'oggetto multipart 
            multiparte.addBodyPart( messageBodyPart1 ); 
            multiparte.addBodyPart( messageBodyPart2 );
            //imposta come contenuto del messaggio l'oggetto multipart 
            message.setContent(multiparte);

            int iPort = Integer.parseInt(port);
            Transport transport = session.getTransport("smtps");
            transport.connect(host, iPort, maillogin, password);
            Transport.send(message);
            
            return true;
        }
        catch (MessagingException mex) 
        { 
            throw mex;
        }
    }
    
}
