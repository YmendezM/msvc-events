package com.yiado.msvc.events.service;

import com.yiado.msvc.events.model.User;
import com.yiado.msvc.events.model.entity.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> findAllEvent();
    Optional<Event> findById(Long id);
    Event save(Event event);
    void deleteById(Long id);
    Optional<User> assignUser(User user, Long idEvent);
    Optional<User> addUser(User user, Long idEvent);
    Optional<User> unassignUser(User user, Long idEvent);


}
