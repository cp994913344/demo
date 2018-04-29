package com.ljs.demo.common.mail;

public interface MailService {


    /**
     * 发送简单邮件
     *
     * @param to
     * @param subject 邮件主题
     * @param content 字符串信息
     */
    void sendSimpleMail(String to, String subject, String content);

    /**
     * 发送html 类型消息
     *
     * @param to
     * @param subject 邮件主题
     * @param content html类型字符串
     */
    void sendMimeMessageMail(String to, String subject, String content);


    /**
     * 发送带附件的邮件
     *
     * @param to
     * @param subject 邮件主题
     * @param content html类型字符串
     * @param filePath 文件路径
     */
    void sendAttachmentsMail(String to, String subject, String content, String filePath);
}
