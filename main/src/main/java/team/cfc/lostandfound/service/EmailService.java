package team.cfc.lostandfound.service;

public interface EmailService {
    public void sendEmail(String msg, String emailAddr, String subject);
}
