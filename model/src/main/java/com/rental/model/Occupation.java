package com.rental.model;

import java.util.Date;

/**
 * Created by Dasha on 27.05.15.
 */
public class Occupation {
    private int id;
    private Property property;
    private Date startDate;
    private Date endDate;

    public Occupation(int id, Property property, Date startDate, Date endDate) {
        this.id = id;
        this.property = property;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
