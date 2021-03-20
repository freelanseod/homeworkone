package appmanager;

import model.MailMessage;
import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MailHelper {
    private ApplicationManager app;
    private Wiser wiser;

    public MailHelper(ApplicationManager app) {
        this.app = app;
        wiser = new Wiser(); //post server
    }

    public List<MailMessage> waitForMail(int countEmails, long timeoutWaitForEmails) throws InterruptedException {
        long start = System.currentTimeMillis();

        while (System.currentTimeMillis() < start + timeoutWaitForEmails) { //the timeout hasn't expired yet
            if (wiser.getMessages().size() >= countEmails) {
                return wiser.getMessages().stream().map((m) -> toModelMail(m)).collect(Collectors.toList());
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        throw new Error("No mail");
    }

    public static MailMessage toModelMail(WiserMessage m) {
        try {
            MimeMessage mm = m.getMimeMessage();
            return new MailMessage(mm.getAllRecipients()[0].toString(), (String) mm.getContent());
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void start() {
        wiser.start();
    }

    public void stop() {
        wiser.stop();
    }

}
