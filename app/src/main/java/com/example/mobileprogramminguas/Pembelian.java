package com.example.mobileprogramminguas;

public class Pembelian {

    public String invoice;
    public String title;
    public String duration;
    public String method;

    public Pembelian(){

    }

    public Pembelian(String invoice, String title, String duration, String method) {
        this.invoice = invoice;
        this.title = title;
        this.duration = duration;
        this.method = method;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
