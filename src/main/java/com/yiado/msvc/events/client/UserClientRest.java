package com.yiado.msvc.events.client;

import com.yiado.msvc.events.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Optional;

@FeignClient(name="msvc-users", url="localhost:8001")
public interface UserClientRest {

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id);

    @PostMapping
    public User create(@RequestBody User user);

}
