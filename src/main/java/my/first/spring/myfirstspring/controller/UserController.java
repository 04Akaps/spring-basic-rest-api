package my.first.spring.myfirstspring.controller;


import my.first.spring.myfirstspring.manager.UserManager;
import my.first.spring.myfirstspring.request.UserRequest;
import my.first.spring.myfirstspring.response.Response;
import my.first.spring.myfirstspring.type.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private final UserManager userManager;

    @Autowired
    public UserController(UserManager userManager) {
        this.userManager = userManager;
    }

    @PostMapping("/add-user")
    @ResponseBody
    public Object addUserApi(
            @RequestBody UserRequest userRequest
    ) {
        if (this.userManager.addUser(userRequest.name,userRequest.age) == null) {
            return Response.generateResponse("this user not existed", HttpStatus.BAD_REQUEST, null);
        }else {
            return Response.generateResponse("success to create user", HttpStatus.OK, new User(userRequest.name, userRequest.age));
        }
    }

    @GetMapping("/get-user-by-name")
    @ResponseBody
    public Object getUserApi(
            @RequestParam("name") String name
    )
    {
        User currentUser = this.userManager.getUserByName(name);
                
        if (currentUser == null) {
            return Response.generateResponse("name by user", HttpStatus.OK, currentUser);
        }else {
            return Response.generateResponse("success to create user", HttpStatus.OK, currentUser);
        }
    }

    @DeleteMapping("/delete-user-by-name")
    @ResponseBody
    public Object deleteUserApi(
            @RequestParam("name") String name
    ) {
        User currentUser = this.userManager.deleteUser(name);

        if (currentUser == null) {
            return Response.generateResponse("name by user", HttpStatus.OK, currentUser);
        }else {
            return Response.generateResponse("success to create user", HttpStatus.OK, currentUser);
        }
    }
}
