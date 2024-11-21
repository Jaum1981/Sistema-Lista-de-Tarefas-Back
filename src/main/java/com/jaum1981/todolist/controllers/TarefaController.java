package com.jaum1981.todolist.controllers;

import com.jaum1981.todolist.models.Tarefa;
import com.jaum1981.todolist.services.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService service;
    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public ResponseEntity<List<Tarefa>> listAll() {
        List<Tarefa> tarefas = service.listAll();
        return ResponseEntity.ok().body(tarefas);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Tarefa> findById(@PathVariable Long id) {
        Tarefa tarefa = service.findById(id);
        return ResponseEntity.ok().body(tarefa);
    }

    @PostMapping
    public ResponseEntity<Tarefa> addTarefa(@RequestBody Tarefa tarefa) {
        service.addTarefa(tarefa);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tarefa.getId()).toUri();
        return ResponseEntity.created(uri).body(tarefa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> updateTarefa(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        tarefa = service.updateTarefa(id, tarefa);
        return ResponseEntity.ok().body(tarefa);
    }

    @PutMapping("/reordenar")
    public ResponseEntity<Void> reordenarTarefas(@RequestBody List<Tarefa> tarefas) {
        service.reordenarTarefas(tarefas); // Lógica será implementada no serviço
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/completed")
    public ResponseEntity<Tarefa> marcarComoCncluída(@PathVariable Long id, @RequestBody Boolean completed) {
        Tarefa tarefa = tarefaService.atualizarStatusConcluida(id, completed);
        return ResponseEntity.ok(tarefa);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarefa(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build(); //cod 204
    }

}
