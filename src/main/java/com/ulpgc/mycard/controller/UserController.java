package com.ulpgc.mycard.controller;

import com.ulpgc.mycard.dto.LoginDto;
import com.ulpgc.mycard.dto.UserDto;
import com.ulpgc.mycard.models.Card;
import com.ulpgc.mycard.models.Users;
import com.ulpgc.mycard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/api/editAccount/{user_id}")
    public ResponseEntity saveDto(@RequestBody UserDto userDto, @PathVariable("user_id") Long id) {
        try{
            userService.updateUser(userDto, id);
            return ResponseEntity.ok("200");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("error");
        }
    }
    @PostMapping("/api/register")
    public ResponseEntity registerDto(@RequestBody UserDto userDto){
        System.out.println(userDto);
        try{
            if(userService.registerUser(userDto) == 1){
                return ResponseEntity.ok("User Registered Successfully");
            }
            return ResponseEntity.badRequest().body("bad");
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().body("bad");
        }
    }
    //this here is very poor practice never do it
    @PostMapping("/api/login")
    public ResponseEntity loginUser(@RequestBody LoginDto loginDto){
        try{
            String res = userService.login(loginDto);
            if(res != "err"){
                return ResponseEntity.ok(res);
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().body("wrong username or password");
        }
        return ResponseEntity.badRequest().body("wrong username or password");
    }

    @GetMapping("/api/user/{user_id}")
    public ResponseEntity<?> getUser(@PathVariable("user_id") Long id){
        try{
            UserDto user = userService.getUser(id);
            if(Objects.isNull(user)){
                return ResponseEntity.badRequest().body("bad");
            }
            return ResponseEntity.ok(user);

        } catch (Exception e){
            return ResponseEntity.badRequest().body("bad");
        }
    }
}
