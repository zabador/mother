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
    private Object recipient;
    private Date date;

    public Alarm() {
    }

    public Alarm(String name, String content, Object recipient, Date date) {
        this.name = name;
        this.content = content;
        this.recipient = recipient;
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

    public Object getRecipient() {
        return recipient;
    }

    public void setRecipient(Object recipient) {
        this.recipient = recipient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
