package com.wllr.WllrGerenciadorDeTarefas.repository;

import com.wllr.WllrGerenciadorDeTarefas.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    java.util.List<Task> findByTitleContainingIgnoreCase(String title);
}
