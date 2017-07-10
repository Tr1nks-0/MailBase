package com.tr1nks.model.entities;


import javax.persistence.*;

@Entity
@Table(name = "user", schema = "base_v8")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long  id;

    public long  getId() {
        return id;
    }

    public void setId(long  id) {
        this.id = id;
    }
}
