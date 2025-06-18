package com.example.taskmanager.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.taskmanager.entity.Comment;
import com.example.taskmanager.entity.Task;
import com.example.taskmanager.entity.User;
import com.example.taskmanager.repository.CommentRepository;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepo;
    private final TaskRepository taskRepo;
    private final UserRepository userRepo;

    public Comment addComment(Long taskId, String content, String username) {
        Task task = taskRepo.findById(taskId).orElseThrow();
        User user = userRepo.findByUsername(username).orElseThrow();

        Comment comment = Comment.builder()
                .task(task)
                .user(user)
                .content(content)
                .createdAt(LocalDateTime.now())
                .build();

        return commentRepo.save(comment);
    }

    public List<Comment> getCommentsForTask(Long taskId) {
        return commentRepo.findByTaskId(taskId);
    }
}
