package com.jaum1981.todolist.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.Internal;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "tb_tarefa")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    @Column(name = "cost", nullable = false)
    @NotNull
    @Min(0)
    private Double cost;

    @Column(name = "limit_date", nullable = false)
    private LocalDate limitDate = LocalDate.now();

    @Column(name ="ordem", nullable = false)
    private long ordem;

    public Tarefa() {
    }

    public Tarefa(Long id, String name, Double cost, LocalDate limitDate, long ordem) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.limitDate = limitDate;
        this.ordem = ordem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public LocalDate getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(LocalDate limitDate) {
        this.limitDate = limitDate;
    }

    public long getOrdem() {
        return ordem;
    }

    public void setOrdem(long ordem) {
        this.ordem = ordem;
    }

}
