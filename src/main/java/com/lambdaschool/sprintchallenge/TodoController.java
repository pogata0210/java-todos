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
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

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

    @ApiOperation(value = "Gets a user based on id", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved user"),
            @ApiResponse(code = 401, message = "You are not authorized to the view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/users/userid/{userid}")
    public User getUserBasedOffUserID(@PathVariable Integer userid) {
        return userRepository.findById(userid).orElseThrow();
    }

    @ApiOperation(value = "Gets a user based on username", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved user"),
            @ApiResponse(code = 401, message = "You are not authorized to the view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/users/username/{username}")
    public User getUserBasedOffUserName(@PathVariable String username) {
        return userRepository.findByUsername(username);
    }

    @ApiOperation(value = "Updates todo id", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated"),
            @ApiResponse(code = 401, message = "You are not authorized to the view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PutMapping("/todos/todoid/{todoid}")
    public Todo updateTodo(@RequestBody Todo newTodo, @PathVariable int todoid) throws URISyntaxException {
        Optional<Todo> todoUpdate = todoRepository.findById(todoid);
        if (todoUpdate.isPresent()) {
            newTodo.setTodoid(todoid);
            todoRepository.save(newTodo);
            return newTodo;
        }
        return null;
    }
    @GetMapping("/todos/todoid/{todoid}")
    public Todo getTodoBasedOffTodoId(@PathVariable Integer todoid){
        return todoRepository.findByTodoid(todoid);
    }

    @ApiOperation(value = "Gets a list of todo tasks assigned to each user", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to the view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/todos/users")
    public List<Object[]> getTodoWithAssignedUser() {
        return todoRepository.todosWithUserOrderedByDateStarted();
    }

    @ApiOperation(value = "Deletes a user with given userid", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted"),
            @ApiResponse(code = 401, message = "You are not authorized to the view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @DeleteMapping("/users/userid/{userid}")
    public User deleteUserByID(@PathVariable int userid) {
        var foundUser = userRepository.findById(userid);
        if (foundUser.isPresent()) {
            List<Todo> todoFromUser = todoRepository.getUserTodos(userid);
            for (Todo todo : todoFromUser) {
                todoRepository.deleteById(todo.getTodoid());
            }
            userRepository.deleteById(userid);
            return foundUser.get();
        } else {
            return null;
        }

    }
    @DeleteMapping("/todos/todoid/{todoid}")
    public Todo deleteTodoByTodoid(@PathVariable Integer todoid){
        var foundTodo = todoRepository.findById(todoid);
        if(foundTodo.isPresent()){
            todoRepository.deleteById(todoid);
            return foundTodo.get();
        }
        return null;
    }
    @ApiOperation(value = "Adds a user", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added"),
            @ApiResponse(code = 401, message = "You are not authorized to the view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping("/users")
    public User addANewUser(@RequestBody User users) throws URISyntaxException{
        return userRepository.save(users);
    }

    @ApiOperation(value = "Adds a todo task", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added"),
            @ApiResponse(code = 401, message = "You are not authorized to the view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping("/todos")
    public Todo addANewTodo(@RequestBody Todo todo) throws URISyntaxException {
        return todoRepository.save(todo);
    }

    @ApiOperation(value = "Updatuser id", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated"),
            @ApiResponse(code = 401, message = "You are not authorized to the view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PutMapping("/users/userid/{userid}")
    public User updateUser(@RequestBody User newUser, @PathVariable Integer userid) throws URISyntaxException{
        Optional<User> userUpdate = userRepository.findById(userid);
        if(userUpdate.isPresent()){
            newUser.setUserid(userid);
            userRepository.save(newUser);
            return newUser;
        }
        return null;
    }

}