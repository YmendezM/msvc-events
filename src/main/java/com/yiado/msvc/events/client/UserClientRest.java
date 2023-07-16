package com.yiado.msvc.events.client;

import com.yiado.msvc.events.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
//call local
//@FeignClient(name="msvc-users", url="localhost:8001")
//call Docker container - call Docker container
//@FeignClient(name="msvc-users", url="msvc-users:8001")
//call Docker container - Docker container - Variable config
@FeignClient(name="msvc-users", url="${msvc.users.url}")
public interface UserClientRest {
    @GetMapping("/{id}")
    public User findById(@PathVariable Long id);

    @PostMapping
    public User create(@RequestBody User user);

    @GetMapping("/find-all-by-id")
    public List<User> findAllById(@RequestParam Iterable<Long> ids);

}
