package com.lambdaschool.sprintchallenge.Repo;

import com.lambdaschool.sprintchallenge.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TodoRepo extends JpaRepository<Todo, Integer>

{


    @Query(value = "SELECT users.username, users.userid, todo.description, todo.datestarted, todo.completed FROM users\" +\n" +
            "           \" JOIN todo ON users.userid = todo.userid", nativeQuery = true)
    List<Object[]> gettodosWithUsers();

    @Query(value = "SELECT u.username, t.description, t.completed FROM todo t, users u WHERE t.completed = 0", nativeQuery = true)
    List<Object[]> getTodosActive();

    @Query(value ="SELECT * FROM todo WHERE todo.userid = :userid", nativeQuery = true)
    List<Todo> getUserTodos(@Param("userid") int userid);


}
