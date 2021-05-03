package com.ulpgc.mycard.service;

import com.ulpgc.mycard.dto.LoginDto;
import com.ulpgc.mycard.dto.UserDto;
import com.ulpgc.mycard.models.Users;
import com.ulpgc.mycard.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CardService cardService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public int updateUser(UserDto userDto){
        String pass = passwordEncoder
                .encode(userDto.getPassword());
        userDto.setPassword(pass);
        try{
            Users user = userRepository.findByUsername(userDto.getUsername());
            user.setName(userDto.getName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());

            String password = userDto.getPassword();
            if(password.isEmpty() || password == null){
               userRepository.save(user);
            } else{
                user.setPassword(password);
                userRepository.save(user);
            }
            return 1;

        } catch (Exception e){
            return 0;
        }
    }

    public int registerUser(UserDto userDto){
        String pass = passwordEncoder
                .encode(userDto.getPassword());
        userDto.setPassword(pass);
        try{
            Users user = userRepository.findByUsername(userDto.getUsername());
            if(Objects.nonNull(user)){
                return 0;
            }
            if(userDto.getUsername().isBlank() ||
                    userDto.getName().isBlank() ||
                    userDto.getEmail().isBlank() ||
                    userDto.getLastName().isBlank() ||
                    userDto.getPassword().isBlank()){
                return 0;
            }
            user = new Users();

            user.setUsername(userDto.getUsername());
            user.setName(userDto.getName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());

            if(!cardService.createCard(user))
                return 0;

            userRepository.save(user);

        } catch (Exception e){
            return 0;
        }
        return 1;
    }

    public String login(LoginDto loginDto){
        try{
            Users user = userRepository.findByUsername(loginDto.getUsername());
            Boolean valid = passwordEncoder.matches(loginDto.getPassword(), user.getPassword());

            if(Objects.nonNull(user) && valid){

                return user.getId().toString();
            }
            return "err";
        } catch (Exception e){
            return "err";
        }
    }

    public UserDto getUser(Long id){
        try{
            Optional<Users> user = userRepository.findById(id);
            if(user.isPresent()){
                Users u = user.get();
                UserDto userDto = new UserDto();
                userDto.setUsername(u.getUsername());
                userDto.setEmail(u.getEmail());
                userDto.setLastName(u.getLastName());
                userDto.setName(u.getName());
                return userDto;
            }
            return null;
        } catch (Exception e){
            return null;
        }
    }
}
