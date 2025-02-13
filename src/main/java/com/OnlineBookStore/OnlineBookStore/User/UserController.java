package com.OnlineBookStore.OnlineBookStore.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/User")

public class UserController {
    @Autowired
    private UserService userService;

//    add registration
    @PostMapping(path = "/addUser")
    public ResponseEntity<?>addusr(@RequestBody UserModel userModel){
        try {
            return userService.addUser(userModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

//  user login

//    @PostMapping(path = "/userLogin")
//    public ResponseEntity<?>usrLogin(@RequestParam String email,@RequestParam String password){
//        try{
//            return userService.userLogin(email,password);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>("Something Went Wrong",HttpStatus.INTERNAL_SERVER_ERROR);
//    }




}


































