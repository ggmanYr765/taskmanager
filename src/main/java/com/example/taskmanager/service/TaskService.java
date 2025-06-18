package com.example.taskmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.entity.User;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepo;
    private final UserRepository userRepo;

    public Task createTask(Task task, String username) {
        User user = userRepo.findByUsername(username).orElseThrow();
        task.setUser(user);
        return taskRepo.save(task);
    }

    public List<Task> getAllTasks(String username) {
        User user = userRepo.findByUsername(username).orElseThrow();
        return taskRepo.findByUserId(user.getId());
    }

   public Task updateTask(Long id, Task updatedTask, String username) {
    Task task = taskRepo.findById(id).orElseThrow();

    if (!task.getUser().getUsername().equals(username)) {
        throw new RuntimeException("Unauthorized");
    }

    // ✅ Status transition logic
    if (task.getStatus() != null && updatedTask.getStatus() != null) {
        if (!task.getStatus().canTransitionTo(updatedTask.getStatus())) {
            throw new IllegalArgumentException(
                "Invalid status transition: " + task.getStatus() + " → " + updatedTask.getStatus()
            );
        }
    }

    task.setTitle(updatedTask.getTitle());
    task.setDescription(updatedTask.getDescription());
    task.setDueDate(updatedTask.getDueDate());
    task.setStatus(updatedTask.getStatus());

    return taskRepo.save(task);
}

    public void deleteTask(Long id, String username) {
        Task task = taskRepo.findById(id).orElseThrow();
        if (!task.getUser().getUsername().equals(username)) throw new RuntimeException("Unauthorized");
        taskRepo.delete(task);
    }
}
