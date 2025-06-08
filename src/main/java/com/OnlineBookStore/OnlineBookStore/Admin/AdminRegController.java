package com.OnlineBookStore.OnlineBookStore.Admin;

import com.OnlineBookStore.OnlineBookStore.Category.CategoryModel;
import com.OnlineBookStore.OnlineBookStore.DtoClasses.BookDetailsDto;
import com.OnlineBookStore.OnlineBookStore.DtoClasses.DashboardStatsDto;
import com.OnlineBookStore.OnlineBookStore.DtoClasses.LoginRequest;
import com.OnlineBookStore.OnlineBookStore.DtoClasses.UpdateBookDto;
import com.OnlineBookStore.OnlineBookStore.Language.LanguageModel;
import com.OnlineBookStore.OnlineBookStore.Publisher.PubModel;
import com.OnlineBookStore.OnlineBookStore.User.UserModel;
import com.OnlineBookStore.OnlineBookStore.status.StatusModel;
import com.OnlineBookStore.OnlineBookStore.Role.RoleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(path = "/api/Admin")

public class AdminRegController {
    @Autowired
    private AdminRegService adminRegService;

//    admin registration
    @PostMapping(path = "/admin/registration")
    public ResponseEntity<?>addA(@RequestBody AdminRegModel adminRegModel){
        try{
            return adminRegService.addAdmin(adminRegModel);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }



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



// forgot password
    @PutMapping("/forgot/password")
    public ResponseEntity<?>forgotPassword(@RequestParam String email, @RequestParam String password){
        try{
            return adminRegService.forgotPassword(email,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Invalid user details",HttpStatus.BAD_REQUEST);
    }

// update publisher approval
    @PutMapping(path = "/update/permission")
    public ResponseEntity<?>publisherStatus(@RequestParam Long pubId,@RequestParam Long statusId){
        try{
            return adminRegService.updatePermission(pubId,statusId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }


//    add category
    @PostMapping(path = "/add/category")
    public ResponseEntity<?>addCat(@RequestBody CategoryModel categoryModel){
        try{
            return adminRegService.addCategory(categoryModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    delete category
    @DeleteMapping(path = "/delete/category")
    public ResponseEntity<?>delCat(@RequestParam Long cat_id){
        try{
            return adminRegService.deleteCategory(cat_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    update category
    @PutMapping(path = "/update/category")
    public ResponseEntity<?>updateCat(@RequestParam Long cat_id,@RequestParam String cat_name){
        try{
            return adminRegService.updateCategory(cat_id,cat_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

//  display all category
    @GetMapping(path = "/list/category")
    public ResponseEntity<List<CategoryModel>>displayCat(){
        return adminRegService.displayCategory();
    }


//    add role
    @PostMapping(path = "/add/role")
    public ResponseEntity<?>addrole(@RequestBody RoleModel roleModel){
        try{
            return adminRegService.addRole(roleModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    display role
    @GetMapping(path = "/display/role")
    public ResponseEntity<List<RoleModel>>disRole(){
        return adminRegService.displayRole();
    }


    //    add status
    @PostMapping(path = "/add/status")
    public ResponseEntity<?>addstats(@RequestBody StatusModel statusModel){
        try{
            return adminRegService.addingstatus(statusModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    update status
    @PutMapping(path = "/update/status")
    public ResponseEntity<?>updateStat(@RequestParam Long statusId, @RequestParam String statusName){
        try{
            return adminRegService.updateStatus(statusId,statusName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    delete status
    @DeleteMapping(path = "/delete/status")
    public ResponseEntity<?>delStatus(@RequestParam Long statusId){
        try{
            return adminRegService.deleteStatus(statusId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    display status
@GetMapping(path = "/display/status")
    public ResponseEntity<List<StatusModel>>displaySta()
{
        return adminRegService.displayStatus();

}

// add language
    @PostMapping(path = "/add/language")
    public ResponseEntity<?>addLanguage(@RequestBody LanguageModel languageModel){
        try{
            return adminRegService.addLanguage(languageModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    display language
    @GetMapping(path = "/display/language")
    public ResponseEntity<List<LanguageModel>>listLanguage(){

        return adminRegService.listLanguage();
    }

//    delete language
    @DeleteMapping(path = "/delete/language")
    public ResponseEntity<?>deleteLanguage(@RequestParam Long languageId){
        try{
            return  adminRegService.deleteLanguage(languageId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    update language
    @PutMapping(path = "/update/language")
    public ResponseEntity<?>updateLanguage(@RequestParam Long languageId, @RequestParam String languageName){
        try{
            return adminRegService.updateLanguage(languageId,languageName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    dashboard stats
@GetMapping("/dashboard-stats")
public ResponseEntity<DashboardStatsDto> getDashboardStats() {
    DashboardStatsDto stats = adminRegService.getDashboardStats();
    return ResponseEntity.ok(stats);
}

// update book

    @PutMapping(path = "/update/book")
    public ResponseEntity<?>updateBook(@RequestPart Long bookId,@RequestPart UpdateBookDto updateBookDto,
                                       @RequestPart(value = "coverImage", required = false) MultipartFile coverImage){
        try{
            return adminRegService.updateBook(bookId,updateBookDto,coverImage);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }


//    --------------------delete book----------------------------------------------------

    @DeleteMapping(path = "/delete/book")
    public ResponseEntity<?>delBook(@RequestParam Long bookId){
        try{
            return  adminRegService.bookDeletion(bookId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    list all publishers
@GetMapping(path = "/display/publishers")
public ResponseEntity<List<PubModel>>listPublisher(){

    return adminRegService.listPublisher();
}

// list all users

    @GetMapping(path = "/display/users")
    public ResponseEntity<List<UserModel>>listUser(){

        return adminRegService.listUser();
    }

//    delete publisher

    @DeleteMapping(path = "/delete/publisher")
    public ResponseEntity<?>deletePublisher(@RequestParam Long pubId){
        try{
            return  adminRegService.deletePublisher(pubId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}






















