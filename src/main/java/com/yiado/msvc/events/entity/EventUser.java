package com.yiado.msvc.events.entity;

import javax.persistence.*;

@Entity
@Table(name="EVENT_USER")
public class EventUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @Column(name="USER_ID", unique = true)
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(!(obj instanceof EventUser)){
            return false;
        }
        EventUser o = (EventUser) obj;
        return this.userId != null && this.userId.equals(o.userId);
    }
}
