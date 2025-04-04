package com.example.taskManage.controller;

import com.example.taskManage.entity.Task;
import com.example.taskManage.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task newTask = taskService.createTask(task);
        return ResponseEntity.ok(newTask);
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getTasks();
    }


    @GetMapping("{id}")
    public ResponseEntity getTasksById(@PathVariable Long id) {
        return new ResponseEntity<>(taskService.getTasks(), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity updateTask(@RequestParam long id, @PathVariable String title) {
        return new ResponseEntity<>(taskService.updateTask(id, title), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteTask(@PathVariable Long id) {
        return new ResponseEntity<>(taskService.deleteTaskById(id), HttpStatus.NO_CONTENT);
    }

    @PutMapping("{id}/complete")
    public ResponseEntity makeTaskCompleted(@PathVariable Long id) {
        return new ResponseEntity<>(taskService.makeTaskCompleted(id), HttpStatus.OK);
    }
}
