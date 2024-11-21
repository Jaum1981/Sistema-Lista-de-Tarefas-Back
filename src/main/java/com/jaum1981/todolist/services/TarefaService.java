package com.jaum1981.todolist.services;

import com.jaum1981.todolist.services.exceptions.DataBaseException;
import com.jaum1981.todolist.models.Tarefa;
import com.jaum1981.todolist.repositories.TarefaRepository;
import com.jaum1981.todolist.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository repository;

    //List Operation
    public List<Tarefa> listAll() {
        return repository.findAll();
    }

    public Tarefa findById(Long id) {
        Optional<Tarefa> tarefa = repository.findById(id);
        return tarefa.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    //Add Operation
    public Tarefa addTarefa(Tarefa tarefa) {
        long ordem = repository.count()+1;
        tarefa.setOrdem(ordem);
        return repository.save(tarefa);
    }

    //Delete Operation
    @Transactional
    public void delete(Long id) {
        Optional<Tarefa> tarefa = repository.findById(id);
        if (tarefa.isPresent()) {
            try {
                repository.deleteById(id);
                List<Tarefa> tarefas = repository.findAllByOrdemAfterOrderByOrdem(tarefa.get().getOrdem());
                List<Tarefa> tarefasAtualizadas = tarefas.stream()
                        .peek(tarefa1 -> tarefa1.setOrdem(tarefa1.getOrdem() - 1))
                        .toList();
                repository.saveAll(tarefasAtualizadas);
            } catch (DataIntegrityViolationException e) {
                throw new DataBaseException(e.getMessage());
            }
        } else {
            throw new ResourceNotFoundException(id);
        }
    }

    //Update Operation
    public Tarefa updateTarefa(Long id, Tarefa tarefa) {
        try {
            Tarefa entity = repository.getReferenceById(id); //prepara o obj
            updateData(entity, tarefa);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(id);
        }
    }

    //reordenar
    public void reordenarTarefas(List<Tarefa> tarefas) {
        for (Tarefa tarefa: tarefas) {
            Tarefa existente = repository.findById(tarefa.getId())
                    .orElseThrow(() -> new RuntimeException("Tarefa não encontrada " + tarefa.getId()));
            existente.setOrdem(tarefa.getOrdem());
            repository.save(existente);
        }
    }

    //Metodo auxiliar de update
    private void updateData(Tarefa request, Tarefa tarefa) {
        request.setName(tarefa.getName());
        request.setCost(tarefa.getCost());
        request.setLimitDate(tarefa.getLimitDate());
    }

}
