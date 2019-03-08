package com.lambdaschool.sprintchallenge;

import com.lambdaschool.sprintchallenge.Repo.TodoRepo;
import com.lambdaschool.sprintchallenge.Repo.UserRepo;
import com.lambdaschool.sprintchallenge.model.Todo;
import com.lambdaschool.sprintchallenge.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "Java Todo Application", description = "Todo backend")

@RestController
@RequestMapping(path = {}, produces = MediaType.APPLICATION_JSON_VALUE)
public class TodoController {

    @Autowired
    TodoRepo todoRepository;
    @Autowired
    UserRepo userRepository;

    @ApiOperation(value = "Gets all the users", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieves list"),
            @ApiResponse(code = 401, message = "You are not authorized to the view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @ApiOperation(value = "Gets all the todos", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieves list"),
            @ApiResponse(code = 401, message = "You are not authorized to the view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/todos")
    public List<Todo> getAllTodo() {
        return todoRepository.findAll();

    }
    @ApiOperation(value = "Gets a user based on name", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved user"),
            @ApiResponse(code = 401, message = "You are not authorized to the view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/users/userid/{userid}")
    public User getUserBasedOffUserID(@PathVariable Integer userid){
        return //userRepository.findById(userid);
    }

}