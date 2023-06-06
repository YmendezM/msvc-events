package com.yiado.msvc.events.repository;

import com.yiado.msvc.events.model.entity.Event;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {
    @Modifying
    @Query("delete from EventUser eu where eu.userId=?1")
    void deleteUserEvent(Long id);

}
