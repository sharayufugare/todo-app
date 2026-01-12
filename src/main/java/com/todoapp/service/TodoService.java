//package com.todoapp.service;
//
//import com.todoapp.model.Todo;
//import com.todoapp.model.User;
//import com.todoapp.repository.TodoRepository;
//import org.springframework.stereotype.Service;
//import java.util.List;
//
//@Service
//public class TodoService {
//    private final TodoRepository taskRepo;
//
//    public TodoService(TodoRepository taskRepo) {
//        this.taskRepo = taskRepo;
//    }
//
//    public Todo addTask(Todo task) {
//        return taskRepo.save(task);
//    }
//
//    public List<Todo> getTasks(User user) {
//        return taskRepo.findByUser(user);
//    }
//
//    public void deleteTask(Long id) {
//        taskRepo.deleteById(id);
//    }
//}
