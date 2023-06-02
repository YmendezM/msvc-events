package com.yiado.msvc.events.service;

import com.yiado.msvc.events.entity.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> findAllEvent();

    Optional<Event> findById(Long id);

    Event save(Event event);

    void deleteById(Long id);


}
