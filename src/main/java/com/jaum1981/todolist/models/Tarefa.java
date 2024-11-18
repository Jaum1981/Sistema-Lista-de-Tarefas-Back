package com.jaum1981.todolist.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tb_tarefa")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cost", nullable = false)
    private Double cost;

    @Column(name = "limit_date", nullable = false)
    private Date limitDate;

    @Column(name = "ordem", nullable = false)
    private Integer ordem;

    public Tarefa() {
    }

    public Tarefa(Long id, String name, Double cost, Date limitDate, Integer ordem) {
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

    public Date getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(Date limitDate) {
        this.limitDate = limitDate;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }
}
