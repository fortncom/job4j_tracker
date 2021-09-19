package ru.job4j.config;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private Boolean status;
    private Integer timeToDeadline;
    private Timestamp created = new Timestamp(System.currentTimeMillis());

    public Item() {
    }

    public Item(String name) {
        this.name = name;
    }

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Item(String name, String description, boolean status) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.created = created;
    }

    public Item(String name, String description, boolean status, int timeToDeadline) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.timeToDeadline = timeToDeadline;
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTimeToDeadline() {
        return timeToDeadline;
    }

    public void setTimeToDeadline(int timeToDeadline) {
        this.timeToDeadline = timeToDeadline;
    }

    @Override
    public String toString() {
        return "Item{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", description='" + description + '\''
                + ", status=" + status
                + ", timeToDeadline=" + timeToDeadline
                + ", created=" + created
                + '}';
    }
}
