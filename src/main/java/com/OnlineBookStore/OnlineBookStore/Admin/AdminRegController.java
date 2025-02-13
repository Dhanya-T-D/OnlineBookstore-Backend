package com.OnlineBookStore.OnlineBookStore.Admin;

import com.OnlineBookStore.OnlineBookStore.Category.CategoryModel;
import com.OnlineBookStore.OnlineBookStore.DtoClasses.LoginRequest;
import com.OnlineBookStore.OnlineBookStore.status.StatusModel;
import com.OnlineBookStore.OnlineBookStore.Role.RoleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(path = "/api/Admin")

public class AdminRegController {
    @Autowired
    private AdminRegService adminRegService;

//    add new admin
    @PostMapping(path = "/addAdmin")
    public ResponseEntity<?>addA(@RequestBody AdminRegModel adminRegModel){
        try{
            return adminRegService.addAdmin(adminRegModel);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }


////    login
//    @PostMapping(path = "/Login")
//    public ResponseEntity<?>log(@RequestParam String email,@RequestParam String password){
//        try{
//            return adminRegService.adminLogin(email,password);
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>("Something Went Wrong",HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    //---------------login--------------------
    @PostMapping(path = "/log")
    public ResponseEntity<?>login(@RequestBody LoginRequest loginRequest){
        try{
            return adminRegService.login(loginRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Invalid Login",HttpStatus.BAD_REQUEST);
    }



// update publisher approval
    @PutMapping(path = "/updatePermission")
    public ResponseEntity<?>publisherStatus(@RequestParam Long pubId,@RequestParam Long statusId){
        try{
            return adminRegService.updatePermission(pubId,statusId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }


//    add category
    @PostMapping(path = "/addCategory")
    public ResponseEntity<?>addCat(@RequestBody CategoryModel categoryModel){
        try{
            return adminRegService.addCategory(categoryModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    delete category
    @DeleteMapping(path = "/deleteCategory")
    public ResponseEntity<?>delCat(@RequestParam Long cat_id){
        try{
            return adminRegService.deleteCategory(cat_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    update category
    @PutMapping(path = "/updateCategory")
    public ResponseEntity<?>updateCat(@RequestParam Long cat_id,@RequestParam String cat_name){
        try{
            return adminRegService.updateCategory(cat_id,cat_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

//  display all category
    @GetMapping(path = "/listCategory")
    public ResponseEntity<List<CategoryModel>>displayCat(){
        return adminRegService.displayCategory();
    }


//    add role
    @PostMapping(path = "/addRole")
    public ResponseEntity<?>addrole(@RequestBody RoleModel roleModel){
        try{
            return adminRegService.addRole(roleModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    display role
    @GetMapping(path = "/displayRole")
    public ResponseEntity<List<RoleModel>>disRole(){
        return adminRegService.displayRole();
    }


    //    add status
    @PostMapping(path = "/addstatus")
    public ResponseEntity<?>addstats(@RequestBody StatusModel statusModel){
        try{
            return adminRegService.addingstatus(statusModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    update status
    @PutMapping(path = "/updateStatus")
    public ResponseEntity<?>updateStat(@RequestParam Long statusId, @RequestParam String statusName){
        try{
            return adminRegService.updateStatus(statusId,statusName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    delete status
    @DeleteMapping(path = "/deleteStatus")
    public ResponseEntity<?>delStatus(@RequestParam Long statusId){
        try{
            return adminRegService.deleteStatus(statusId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    display status
@GetMapping(path = "/displayStatus")
    public ResponseEntity<List<StatusModel>>displaySta(){
        return adminRegService.displayStatus();
}



}





















