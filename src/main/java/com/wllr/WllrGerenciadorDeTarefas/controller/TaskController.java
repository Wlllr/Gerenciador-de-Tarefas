package com.wllr.WllrGerenciadorDeTarefas.controller;

import com.wllr.WllrGerenciadorDeTarefas.model.Task;
import com.wllr.WllrGerenciadorDeTarefas.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public String listAllTasks(Model model) {
        List<Task> tasks = taskService.listAllTasks();
        model.addAttribute("tarefas", tasks);
        model.addAttribute("tarefa", new Task());
        return "lista-tarefas";
    }

    @GetMapping("/adicionar")
    public String exibirFormularioAdicionar(Model model) {
        model.addAttribute("tarefa", new Task());
        return "form-tarefa";
    }

    @PostMapping("/salvar")
    public String saveTask(@ModelAttribute Task task, RedirectAttributes resolve) {
        taskService.saveTask(task);
        resolve.addFlashAttribute("mensagemSucesso", "Tarefa Salva com Sucesso!");
        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Task task = taskService.findTaskById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de tarefa inválido:" + id));
        model.addAttribute("tarefa", task);
        return "form-tarefa";
    }

    @GetMapping("/excluir/{id}")
    public String deleteTask(@PathVariable Long id, RedirectAttributes resolve) {
        taskService.deleteTaskById(id);
        resolve.addFlashAttribute("mensagemSucesso", "Tarefa excluida com sucesso");
        return "redirect:/";
    }

    @PostMapping("/buscar")
    public String findByTitle(@ModelAttribute Task task, Model model) {
        List<Task> tasks = taskService.findTaskByTitle(task.getTitle());
        model.addAttribute("tarefas", tasks);
        model.addAttribute("tarefa", new Task());
        return "lista-tarefas";
    }
}
