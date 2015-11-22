package com.zabador.motherspills.alarm.model;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * The Alarm model.
 * Created on 11/21/15.
 *
 * @author Adrian Pena
 */
public class Alarm extends SugarRecord<Alarm> {
    private String name;
    private String content;
    private String recipientNumber;
    private Date date;

    public Alarm() {
    }

    public Alarm(String name, String content, String recipient, Date date) {
        this.name = name;
        this.content = content;
        this.recipientNumber = recipient;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRecipient() {
        return recipientNumber;
    }

    public void setRecipient(String recipient) {
        this.recipientNumber = recipient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
