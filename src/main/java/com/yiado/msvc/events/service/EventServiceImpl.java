package com.yiado.msvc.events.service;

import com.yiado.msvc.events.client.UserClientRest;
import com.yiado.msvc.events.model.User;
import com.yiado.msvc.events.model.entity.Event;
import com.yiado.msvc.events.model.entity.EventUser;
import com.yiado.msvc.events.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService{
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserClientRest userClientRest;

    @Override
    @Transactional(readOnly = true)
    public List<Event> findAllEvent() {
        return (List<Event>) eventRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    @Transactional
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<User> assignUser(User user, Long idEvent) {
        Optional<Event> event = eventRepository.findById(idEvent);
        if(event.isPresent()){
            User UserClient = userClientRest.findById(user.getId());

            Event eventAssign = event.get();
            EventUser eventUser = new EventUser();
            eventUser.setUserId(UserClient.getId());
            eventAssign.addEventUser(eventUser);
            eventRepository.save(eventAssign);
            return Optional.of(UserClient);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<User> addUser(User user, Long idEvent) {
        Optional<Event> event = eventRepository.findById(idEvent);
        if(event.isPresent()){
            User UserNewClient = userClientRest.create(user);
            Event eventAssign = event.get();
            EventUser eventUser = new EventUser();
            eventUser.setUserId(UserNewClient.getId());
            eventAssign.addEventUser(eventUser);
            eventRepository.save(eventAssign);
            return Optional.of(UserNewClient);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<User> unassignUser(User user, Long idEvent) {
        Optional<Event> event = eventRepository.findById(idEvent);
        if(event.isPresent()){
            User UserClient = userClientRest.findById(user.getId());

            Event eventAssign = event.get();
            EventUser eventUser = new EventUser();
            eventUser.setUserId(UserClient.getId());
            eventAssign.removeEventUser(eventUser);
            eventRepository.save(eventAssign);
            return Optional.of(UserClient);
        }
        return Optional.empty();
    }
}
