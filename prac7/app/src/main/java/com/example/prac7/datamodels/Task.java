package com.example.prac7.datamodels;

import java.util.Date;

public class Task {
    private int id;
    private String description;
    private String colour;          // R - red, G - Green, B - blue
    private String status;          // ACTIVE / COMPLETE
    private Date dateDue;

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getColour() {
        return colour;
    }

    public String getStatus() {
        return status;
    }

    public Date getDateDue() {
        return dateDue;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDateDue(Date dateDue) {
        this.dateDue = dateDue;
    }

    public Task(int id, String description, String colour, String status, Date dateDue)
    {
        this.id = id;
        this.description = description;
        this.colour = colour;
        this.status = status;
        this.dateDue = dateDue;

    }
}
