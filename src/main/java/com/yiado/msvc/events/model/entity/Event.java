package com.yiado.msvc.events.model.entity;

import com.yiado.msvc.events.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "EVENTS")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;
    @NotEmpty
    @Column(name="NAME")
    private String name;

    //ADD - DELETE IN CASCADE
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="EVENT_ID")
    private List<EventUser> eventUsers;

    @Transient
    private List<User> users;

    public Event() {
        eventUsers = new ArrayList<>();
        users = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addEventUser(EventUser eventUser){
        eventUsers.add(eventUser);
    }

    public void removeEventUser(EventUser eventUser){
        eventUsers.remove(eventUser);
    }

    public List<EventUser> getEventUsers() {
        return eventUsers;
    }

    public void setEventUsers(List<EventUser> eventUsers) {
        this.eventUsers = eventUsers;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
