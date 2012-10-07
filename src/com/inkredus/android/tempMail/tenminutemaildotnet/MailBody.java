package com.inkredus.android.tempMail.tenminutemaildotnet;

class Body {
    private String content, charset, bodylength, body;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getBodylength() {
        return bodylength;
    }

    public void setBodylength(String bodylength) {
        this.bodylength = bodylength;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

class Attachment {
    private String xattachmentid, contenttype, filename, img;
    
    public String getXattachmentid() {
        return xattachmentid;
    }
    
    public void setXattachmentid(String xattachmentid) {
        this.xattachmentid = xattachmentid;
    }
    
    public String getContenttype() {
        return contenttype;
    }
    
    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }
    
    public String getFilename() {
        return filename;
    }
    
    public void setFilename(String filename) {
        this.filename = filename;
    }
    
    public String getImg() {
        return img;
    }
    
    public void setImg(String img) {
        this.img = img;
    }
}

public class MailBody {
    private String from, to, subject, datetime;
    private String[] urls;
    private Body[] body;
    private Attachment[] attachment;
    private String[] plain;
    private String[] plain_link;
    private String[] raw_html;
    private String[] html;
    private String[] html_to_plain;
    
    public String getFrom() {
        return from;
    }
    
    public void setFrom(String from) {
        this.from = from;
    }
    
    public String getTo() {
        return to;
    }
    
    public void setTo(String to) {
        this.to = to;
    }
    
    public String getSubject() {
        return subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public String getDatetime() {
        return datetime;
    }
    
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
    
    public String[] getUrls() {
        return urls;
    }
    
    public void setUrls(String[] urls) {
        this.urls = urls;
    }
    
    public Body[] getBody() {
        return body;
    }
    
    public void setBody(Body[] body) {
        this.body = body;
    }
    
    public Attachment[] getAttachment() {
        return attachment;
    }
    
    public void setAttachment(Attachment[] attachment) {
        this.attachment = attachment;
    }
    
    public String[] getPlain() {
        return plain;
    }
    
    public void setPlain(String[] plain) {
        this.plain = plain;
    }
    
    public String[] getPlain_link() {
        return plain_link;
    }
    
    public void setPlain_link(String[] plain_link) {
        this.plain_link = plain_link;
    }
    
    public String[] getRaw_html() {
        return raw_html;
    }
    
    public void setRaw_html(String[] raw_html) {
        this.raw_html = raw_html;
    }
    
    public String[] getHtml() {
        return html;
    }
    
    public void setHtml(String[] html) {
        this.html = html;
    }
    
    public String[] getHtml_to_plain() {
        return html_to_plain;
    }
    
    public void setHtml_to_plain(String[] html_to_plain) {
        this.html_to_plain = html_to_plain;
    }
}
