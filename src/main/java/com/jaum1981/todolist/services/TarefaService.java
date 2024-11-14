package com.jaum1981.todolist.services;

import com.jaum1981.todolist.services.exceptions.DataBaseException;
import com.jaum1981.todolist.models.Tarefa;
import com.jaum1981.todolist.repositories.TarefaRepository;
import com.jaum1981.todolist.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository repository;

    //Delete Operation
    public void delete(Long id) {
        Optional<Tarefa> tarefa = repository.findById(id);
        if (tarefa.isPresent()) {
            try {
                repository.deleteById(id);
            } catch (DataIntegrityViolationException e) {
                throw new DataBaseException(e.getMessage());
            }
        } else {
            throw new ResourceNotFoundException(id);
        }
    }

    //

}
