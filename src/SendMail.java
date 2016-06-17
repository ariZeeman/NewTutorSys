/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/** Ari
 *
 * @author Ari
 */
public class SendMail {

    protected final String username = "NHSTutorDoNotReply@gmail.com";
    protected final String password = "HAYDN1ari2LEN3";
    //source page: http://stackoverflow.com/questions/3649014/send-email-using-java

    /**
     * This method will be responsible for sending mail...eventually. Will
     * absolutely require extensive bug testing. i love u guys xoxoxo.
     *
     * @param teacher the teacher who is being e-mailed
     */
    public void SendMail(Teacher teacher) {

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "2525");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(teacher.getEmail()));
            message.setSubject("Someone requires your permission to be a tutor!");
            message.setText("Dear Mr./Ms." + teacher.getLastName() + "," + "\n\nA student requires your permission in order to be registered as a tutor.");
            Transport.send(message);
            System.out.println("Done");
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This is the method which e-mails a tutor if a peer has been matched to
     * them, and it takes in a tutor and their peer as parameters.
     *
     * @param tutor the tutor being e-mailed
     * @param peer the peer who is the subject of the message
     */
    public void SendMail(Tutor tutor, Peer peer) {

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "2525");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(tutor.getEmail()));
            message.setSubject("Someone has been matched to you for tutoring!");
            message.setText("Hello " + tutor.getFirstName() + "," + "\nA new"
                    + " student has been matched as your tutor!." + "\nYour new"
                    + " tutor-ee is named: " + peer.getFirst() + peer.getLast()
                    + ". Their phone number is " + peer.getPhoneNumber());
            Transport.send(message);
            System.out.println("Done");
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
        }
    }
}
