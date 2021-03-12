package com.example.cloud.eureka.client.controller;

import com.example.cloud.eureka.client.domain.User;
import com.example.cloud.eureka.client.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author jxiu
 * @date 2021/1/13
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor_={@Autowired})
@Slf4j
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/{id}")
    public User getById(@PathVariable("id") Long id){
        log.info("id: {}", id);
        Optional<User> optionalUser =  userRepository.findById(id);
        if (optionalUser.isPresent()){
            return userRepository.findById(id).get();
        }
        return null;
    }

    @GetMapping
    public List<User> listAll(){
        return (List<User>) userRepository.findAll();
    }

    @PostMapping
    public Long save(@RequestBody @Validated User user){
        userRepository.save(user);
        if (Objects.isNull(user.getId())){
            return null;
        }
        return user.getId();
    }
}
