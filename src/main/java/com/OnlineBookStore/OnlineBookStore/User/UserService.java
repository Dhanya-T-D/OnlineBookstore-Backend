package com.OnlineBookStore.OnlineBookStore.User;

import com.OnlineBookStore.OnlineBookStore.Publisher.PubModel;
import com.OnlineBookStore.OnlineBookStore.Publisher.PubRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PubRepo pubRepo;



//    add user
    public ResponseEntity<?> addUser(UserModel userModel) {

        UserModel userModel1 = new UserModel();
        userModel1.setRoleid(userModel.getRoleid());
        userModel1.setUsername(userModel.getUsername());
        userModel1.setEmail(userModel.getEmail());
        if (isEmailAlreadyRegistered(userModel.getEmail())||isEmailAlreadyRegistered(userModel.getEmail())){
            return new ResponseEntity<>("Email is Already Registered",HttpStatus.BAD_REQUEST);
        }

        userModel1.setPhone(userModel.getPhone());
        userModel1.setPassword(userModel.getPassword());
        userRepo.save(userModel1);
        return new ResponseEntity<>(userModel1, HttpStatus.OK);

    }
    public boolean isEmailAlreadyRegistered(String email){
        return (userRepo.existsByEmail(email)||pubRepo.existsByEmail(email));
    }


//    user login
//    public ResponseEntity<?> userLogin(String email, String password) {
//        Optional<UserModel> userModelOptional=userRepo.findByEmailAndPassword(email,password);
//        if(userModelOptional.isPresent()){
//            return new ResponseEntity<>("Login Successful",HttpStatus.OK);
//        }
//        else{
//            return new ResponseEntity<>("Incorrect Login Details",HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
