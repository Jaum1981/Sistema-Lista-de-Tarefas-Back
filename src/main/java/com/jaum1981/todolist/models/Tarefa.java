package com.jaum1981.todolist.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tb_tarefa")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double cost;
    private Date limitDate;
    private Integer ordem; //mudar pra inglês

}
