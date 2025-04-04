package com.example.taskManage.service;

import com.example.taskManage.entity.Task;
import com.example.taskManage.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public String deleteTaskById(long id){
        Task task = getTaskById(id);
        taskRepository.delete(task);
        return "Delete Successful!";
    }

    public Task updateTask(long id,String title) {
        Task existingTask = getTaskById(id);
        existingTask.setTitle(title);
        existingTask.setDescription(existingTask.getDescription());
        existingTask.setCompleted(existingTask.isCompleted());
        return taskRepository.save(existingTask);
    }
    public String makeTaskCompleted(long id) {
        Task task = getTaskById(id);
        task.setCompleted(true);
        taskRepository.save(task);
        return "Task Completed!";
    }
}
