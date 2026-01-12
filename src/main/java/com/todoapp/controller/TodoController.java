package com.todoapp.controller;

import com.todoapp.dto.TodoRequest;
import com.todoapp.dto.TodoResponse;
import com.todoapp.model.Todo;
import com.todoapp.model.User;
import com.todoapp.repository.TodoRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    private User getUser(HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) throw new RuntimeException("Please login first");
        return user;
    }

    // ✅ GET ALL
    @GetMapping
    public List<TodoResponse> getTodos(HttpSession session) {
        User user = getUser(session);
        return todoRepository.findByUser(user)
                .stream()
                .map(t -> new TodoResponse(
                        t.getId(),
                        t.getTitle(),
                        t.isCompleted()
                ))
                .toList();
    }

    // ✅ CREATE
    @PostMapping
    public TodoResponse addTodo(
            @RequestBody TodoRequest request,
            HttpSession session) {

        User user = getUser(session);

        if (request.getTitle() == null || request.getTitle().isBlank()) {
            throw new RuntimeException("Title cannot be empty");
        }

        Todo todo = new Todo();
        todo.setTitle(request.getTitle());
        todo.setCompleted(false);
        todo.setUser(user);

        Todo saved = todoRepository.save(todo);

        return new TodoResponse(
                saved.getId(),
                saved.getTitle(),
                saved.isCompleted()
        );
    }

    // ✅ UPDATE (TITLE / STATUS)
    @PutMapping("/{id}")
    public TodoResponse updateTodo(
            @PathVariable Long id,
            @RequestBody TodoRequest request,
            HttpSession session) {

        User user = getUser(session);

        Todo todo = todoRepository
                .findByIdAndUser(id, user)
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        if (request.getTitle() != null) {
            todo.setTitle(request.getTitle());
        }
        if (request.getCompleted() != null) {
            todo.setCompleted(request.getCompleted());
        }

        Todo updated = todoRepository.save(todo);

        return new TodoResponse(
                updated.getId(),
                updated.getTitle(),
                updated.isCompleted()
        );
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public void deleteTodo(
            @PathVariable Long id,
            HttpSession session) {

        User user = getUser(session);

        Todo todo = todoRepository
                .findByIdAndUser(id, user)
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        todoRepository.delete(todo);
    }
}
