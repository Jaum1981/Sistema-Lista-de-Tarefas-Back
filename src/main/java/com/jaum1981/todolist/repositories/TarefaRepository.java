package com.jaum1981.todolist.repositories;

import com.jaum1981.todolist.models.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findAllByOrdemAfterOrderByOrdem(Long ordem);
}
