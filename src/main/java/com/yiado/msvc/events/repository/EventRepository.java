package com.yiado.msvc.events.repository;

import com.yiado.msvc.events.entity.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {
}
