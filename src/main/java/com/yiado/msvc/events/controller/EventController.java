package com.yiado.msvc.events.controller;

import com.yiado.msvc.events.model.User;
import com.yiado.msvc.events.model.entity.Event;
import com.yiado.msvc.events.service.EventService;
import feign.FeignException;
import javafx.print.Collation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

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
    public ResponseEntity<?>  create(@Valid @RequestBody Event event, BindingResult result ){
        ResponseEntity<Map<String, String>> errors = getMapResponseEntity(result);
        if (errors != null) return errors;
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.save(event));
    }

    @PutMapping("/{id}")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?>  update(@Valid @RequestBody Event user, BindingResult result, @PathVariable Long id){
        ResponseEntity<Map<String, String>> errors = getMapResponseEntity(result);
        if (errors != null) return errors;

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

    @PutMapping("/assign-user/{id}")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?>  assignUser(@RequestBody User user, @PathVariable Long id){
           Optional<User> userResponse = null;
           try {
               userResponse = eventService.assignUser(user, id);
           } catch (FeignException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Mensaje: ","No existe el usuario "+
                        "el id o error en la comunicación " + e.getMessage()));
           }
           if(userResponse.isPresent()){
               return ResponseEntity.status(HttpStatus.CREATED).body(userResponse.get());
           }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/assign-create-user/{id}")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?>  addUser(@RequestBody User user, @PathVariable Long id){
        Optional<User> userResponse = null;
        try {
            userResponse = eventService.addUser(user, id);
        } catch (FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Mensaje: ","No existe el usuario "+
                    "el id o error en la comunicación " + e.getMessage()));
        }
        if(userResponse.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(userResponse.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/unassign-user/{id}")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?>  unassignUser(@RequestBody User user, @PathVariable Long id){
        Optional<User> userResponse = null;
        try {
            userResponse = eventService.unassignUser(user, id);
        } catch (FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Mensaje: ","No existe el usuario "+
                    "el id o error en la comunicación " + e.getMessage()));
        }
        if(userResponse.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(userResponse.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete-user-event/{id}")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?>  deleteUserEvent(@PathVariable Long id){
            eventService.deleteUserEvent(id);
            return ResponseEntity.noContent().build();

    }

    @GetMapping("/find-event-with-user-detail/{id}")
    public ResponseEntity<?> findEventWithUserDetail(@PathVariable Long id){
            return ResponseEntity.ok().body(eventService.findEventWithUserDetail(id));
    };


    private static ResponseEntity<Map<String, String>> getMapResponseEntity(BindingResult result) {
        if(result.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(err -> {
                errors.put(err.getField(), " " + err.getField() + " " + err.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errors);
        }
        return null;
    }

}
