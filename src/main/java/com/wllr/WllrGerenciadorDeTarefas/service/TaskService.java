package com.wllr.WllrGerenciadorDeTarefas.service;

import com.wllr.WllrGerenciadorDeTarefas.model.Task;
import com.wllr.WllrGerenciadorDeTarefas.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> listAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> findTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> findTaskByTitle(String title) {
        return taskRepository.findByTitleContainingIgnoreCase(title);
    }
}
