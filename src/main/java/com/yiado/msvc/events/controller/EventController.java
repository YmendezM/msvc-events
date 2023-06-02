package com.yiado.msvc.events.controller;

import com.yiado.msvc.events.entity.Event;
import com.yiado.msvc.events.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public List<Event> findAll(){
        return  eventService.findAllEvent();
    };

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Event> eventOptional =  eventService.findById(id);
        if(eventOptional.isPresent()){
            return ResponseEntity.ok().body(eventOptional.get());
        }
        return (ResponseEntity<?>) ResponseEntity.notFound().build();
    };

    @PostMapping
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?>  create(@RequestBody Event event){
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.save(event));
    }

    @PutMapping("/{id}")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?>  update(@RequestBody Event user, @PathVariable Long id){
        Optional<Event> eventOptional =  eventService.findById(id);
        if(eventOptional.isPresent()){
            Event EventCurrent =  eventOptional.get();
            EventCurrent.setName(user.getName());
            return ResponseEntity.status(HttpStatus.CREATED).body(eventService.save(EventCurrent));
        }
        return (ResponseEntity<?>) ResponseEntity.notFound().build();
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Event> eventOptional =  eventService.findById(id);
        if(eventOptional.isPresent()){
            eventService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
