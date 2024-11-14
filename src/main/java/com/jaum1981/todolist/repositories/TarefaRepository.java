package com.jaum1981.todolist.repositories;

import com.jaum1981.todolist.models.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
