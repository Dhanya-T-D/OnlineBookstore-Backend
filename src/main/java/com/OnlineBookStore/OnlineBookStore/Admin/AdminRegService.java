package com.OnlineBookStore.OnlineBookStore.Admin;

import com.OnlineBookStore.OnlineBookStore.Category.CategoryModel;
import com.OnlineBookStore.OnlineBookStore.Category.CategoryRepo;
import com.OnlineBookStore.OnlineBookStore.DtoClasses.LoginRequest;
import com.OnlineBookStore.OnlineBookStore.Language.LanguageModel;
import com.OnlineBookStore.OnlineBookStore.Language.LanguageRepo;
import com.OnlineBookStore.OnlineBookStore.status.StatusModel;
import com.OnlineBookStore.OnlineBookStore.status.StatusRepo;
import com.OnlineBookStore.OnlineBookStore.DtoClasses.LoginDto;
import com.OnlineBookStore.OnlineBookStore.Publisher.PubModel;
import com.OnlineBookStore.OnlineBookStore.Publisher.PubRepo;
import com.OnlineBookStore.OnlineBookStore.Role.RoleModel;
import com.OnlineBookStore.OnlineBookStore.Role.RoleRepo;
import com.OnlineBookStore.OnlineBookStore.User.UserModel;
import com.OnlineBookStore.OnlineBookStore.User.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.management.openmbean.OpenMBeanInfo;
import java.util.List;
import java.util.Optional;

@Service
public class AdminRegService {
    @Autowired
    private AdminRegRepo adminRegRepo;

    @Autowired
    private PubRepo pubRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private StatusRepo statusRepo;

    @Autowired
    private LanguageRepo languageRepo;



    //    admin registration
    public ResponseEntity<?> addAdmin(AdminRegModel adminRegModel) {
        AdminRegModel adminRegModel1 = new AdminRegModel();
        adminRegModel1.setAdmin_name(adminRegModel.getAdmin_name());
        adminRegModel1.setRoleid(adminRegModel.getRoleid());
        adminRegModel1.setEmail(adminRegModel.getEmail());
        adminRegModel1.setPassword(adminRegModel.getPassword());
        adminRegRepo.save(adminRegModel1);
        return new ResponseEntity<>(adminRegModel1, HttpStatus.OK);
    }


    //   --------------login---------------------------
    

    public ResponseEntity<?> login(LoginRequest loginRequest) {
        Optional<UserModel> userModelOptional = userRepo.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
        Optional<PubModel> pubModelOptional = pubRepo.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
        Optional<AdminRegModel> adminRegModelOptional = adminRegRepo.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());

        if (adminRegModelOptional.isPresent()) {
            AdminRegModel adminRegModel = adminRegModelOptional.get();
            LoginDto loginDto = new LoginDto();
            loginDto.setId(adminRegModel.getAdmin_id());
            Optional<RoleModel> roleModelOptional = roleRepo.findById(adminRegModel.getRoleid());
            if (roleModelOptional.isPresent()) {
                RoleModel roleModel = roleModelOptional.get();
                loginDto.setRoleId(adminRegModel.getRoleid());
                loginDto.setRole(roleModel.getRoleName());
            }
            loginDto.setName(adminRegModel.getAdmin_name());

            return new ResponseEntity<>(loginDto, HttpStatus.OK);
        } else if (pubModelOptional.isPresent()) {
            PubModel pubModel = pubModelOptional.get();
            LoginDto loginDto = new LoginDto();
            loginDto.setId(pubModel.getPubId());
            Optional<RoleModel> roleModelOptional = roleRepo.findById(pubModel.getRoleid());
            if (roleModelOptional.isPresent()) {
                RoleModel roleModel = roleModelOptional.get();
                loginDto.setRoleId(pubModel.getRoleid());
                loginDto.setRole(roleModel.getRoleName());
            }

            loginDto.setName(pubModel.getPub_name());
            loginDto.setStatusId(pubModel.getStatusId());
            Optional<StatusModel> statusModelOptional = statusRepo.findById(pubModel.getStatusId());
            if (statusModelOptional.isPresent()){
                StatusModel statusModel=statusModelOptional.get();
                loginDto.setStatusName(statusModel.getStatusName());
            }


            return new ResponseEntity<>(loginDto, HttpStatus.OK);
        } else if (userModelOptional.isPresent()) {
            UserModel userModel = userModelOptional.get();
            LoginDto loginDto = new LoginDto();
            loginDto.setId(userModel.getUserid());
            Optional<RoleModel> roleModelOptional = roleRepo.findById(userModel.getRoleid());
            if (roleModelOptional.isPresent()) {
                RoleModel roleModel = roleModelOptional.get();
                loginDto.setRoleId(roleModel.getRoleid());
                loginDto.setRole(roleModel.getRoleName());
            }
            loginDto.setName(userModel.getUsername());

            return new ResponseEntity<>(loginDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Incorrect Login Details", HttpStatus.BAD_REQUEST);
        }

    }

    // --------------------forgot password ---------------------
    public ResponseEntity<?> forgotPassword(String email, String password) {
        Optional<AdminRegModel>adminRegModelOptional=adminRegRepo.findByEmail(email);
        Optional<PubModel>pubModelOptional=pubRepo.findByEmail(email);
        Optional<UserModel>userModelOptional=userRepo.findByEmail(email);
        if(adminRegModelOptional.isPresent()){
            AdminRegModel adminRegModel=adminRegModelOptional.get();
            adminRegModel.setPassword(password);
            adminRegRepo.save(adminRegModel);
            return new ResponseEntity<>("Password Updated Successfully",HttpStatus.OK);
        } else if (pubModelOptional.isPresent()) {
            PubModel pubModel=pubModelOptional.get();
            pubModel.setPassword(password);
            pubRepo.save(pubModel);
            return new ResponseEntity<>("Password updated successfully",HttpStatus.OK);
        }
        else if (userModelOptional.isPresent()){
            UserModel userModel=userModelOptional.get();
            userModel.setPassword(password);
            userRepo.save(userModel);
            return new ResponseEntity<>("Password updated successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid user details",HttpStatus.NOT_FOUND);
    }

    //    add category
    public ResponseEntity<?> addCategory(CategoryModel categoryModel) {
        CategoryModel categoryModel1 = new CategoryModel();
        categoryModel1.setCatName(categoryModel.getCatName());
        categoryRepo.save(categoryModel1);
        return new ResponseEntity<>(categoryModel1, HttpStatus.OK);
    }

    //delete category
    public ResponseEntity<?> deleteCategory(Long catId) {
        Optional<CategoryModel> categoryModelOptional = categoryRepo.findById(catId);
        if (categoryModelOptional.isPresent()) {
            CategoryModel categoryModel = categoryModelOptional.get();
            categoryRepo.delete(categoryModel);
            return new ResponseEntity<>("Category Deleted Successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Category Not Found", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    update category

    public ResponseEntity<?> updateCategory(Long catId, String catName) {
        Optional<CategoryModel> categoryModelOptional = categoryRepo.findById(catId);
        if (categoryModelOptional.isPresent()) {
            CategoryModel categoryModel = categoryModelOptional.get();
            categoryModel.setCatName(catName);
            categoryRepo.save(categoryModel);
            return new ResponseEntity<>("Category Updated Successfully", HttpStatus.OK);
        } else
            return new ResponseEntity<>("CategoryId Not Found", HttpStatus.NOT_FOUND);
    }


    //    display category list
    public ResponseEntity<List<CategoryModel>> displayCategory() {
        List<CategoryModel> categoryModelList = categoryRepo.findAll();
        return new ResponseEntity<>(categoryModelList, HttpStatus.OK);
    }

    //    add role
    public ResponseEntity<?> addRole(RoleModel roleModel) {
        RoleModel roleModel1 = new RoleModel();
        roleModel1.setRoleName(roleModel.getRoleName());
        roleRepo.save(roleModel1);
        return new ResponseEntity<>(roleModel1, HttpStatus.OK);
    }

//    display role

    public ResponseEntity<List<RoleModel>> displayRole() {
        List<RoleModel> roleModelList = roleRepo.findAll();
        return new ResponseEntity<>(roleModelList, HttpStatus.OK);
    }

//    update publisher approval

    public ResponseEntity<?> updatePermission(Long pubId, Long statusId) {
        Optional<PubModel> optionalPubModel = pubRepo.findById(pubId);
        if (optionalPubModel.isPresent()) {
            Optional<StatusModel> optionalStatusModel = statusRepo.findById(statusId);
            if (optionalStatusModel.isPresent()) {
                PubModel pubModel1 = optionalPubModel.get();
                StatusModel statusModel = optionalStatusModel.get();
                pubModel1.setStatusId(statusId);
                pubRepo.save(pubModel1);
                return new ResponseEntity<>(pubModel1, HttpStatus.OK);
            } else
                return new ResponseEntity<>("Statusid not present", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Publisher Not Found", HttpStatus.NOT_FOUND);
    }

    //    add status

    public ResponseEntity<?> addingstatus(StatusModel statusModel) {
        StatusModel statusModel1 = new StatusModel();
        statusModel1.setStatusName(statusModel.getStatusName());
        statusRepo.save(statusModel1);
        return new ResponseEntity<>(statusModel1, HttpStatus.OK);
    }

//    update status

    public ResponseEntity<?> updateStatus(Long statusId, String statusName) {
        Optional<StatusModel> statusModelOptional = statusRepo.findById(statusId);
        if (statusModelOptional.isPresent()) {
            StatusModel statusModel = statusModelOptional.get();
            statusModel.setStatusName(statusName);
            statusRepo.save(statusModel);
            return new ResponseEntity<>("Status Updated Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.NOT_FOUND);
    }

    //    delete status
    public ResponseEntity<?> deleteStatus(Long statusId) {
        Optional<StatusModel> statusModelOptional = statusRepo.findById(statusId);
        if (statusModelOptional.isPresent()) {
            StatusModel statusModel = statusModelOptional.get();
            statusRepo.delete(statusModel);
            return new ResponseEntity<>("Status deleted Successfully", HttpStatus.OK);
        } else
            return new ResponseEntity<>("Status not found", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //    display status
    public ResponseEntity<List<StatusModel>> displayStatus() {
        List<StatusModel> statusModels = statusRepo.findAll();
        return new ResponseEntity<>(statusModels, HttpStatus.OK);
    }

// add language
    public ResponseEntity<?> addLanguage(LanguageModel languageModel) {
        LanguageModel languageModel1=new LanguageModel();
        languageModel1.setLanguageName(languageModel.getLanguageName());
       languageRepo.save(languageModel1);
        return new ResponseEntity<>(languageModel1,HttpStatus.OK);
    }

// list languages
    public ResponseEntity<List<LanguageModel>> listLanguage() {
        List<LanguageModel>languageModels=languageRepo.findAll();
        return new ResponseEntity<>(languageModels,HttpStatus.OK);
    }

//    delete language
    public ResponseEntity<?> deleteLanguage(Long languageId) {
        Optional<LanguageModel>optionalLanguageModel=languageRepo.findById(languageId);
        if (optionalLanguageModel.isPresent()){
            LanguageModel languageModel=optionalLanguageModel.get();
            languageRepo.delete(languageModel);
            return new ResponseEntity<>("Language deleted successfully",HttpStatus.OK);
        }

            return new ResponseEntity<>("Language is not present",HttpStatus.NOT_FOUND);
    }

// update language

    public ResponseEntity<?> updateLanguage(Long languageId, String languageName) {
        Optional<LanguageModel>optionalLanguageModel=languageRepo.findById(languageId);
        if (optionalLanguageModel.isPresent()){
            LanguageModel languageModel=optionalLanguageModel.get();
            languageModel.setLanguageName(languageName);
            languageRepo.save(languageModel);
            return new ResponseEntity<>("Language updated successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Language not found",HttpStatus.NOT_FOUND);

    }
}
