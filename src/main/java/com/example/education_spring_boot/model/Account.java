package com.example.education_spring_boot.model;

import jakarta.persistence.*;


@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;


    @Column(name = "username", unique = true, nullable = true, length = 20)
    private String username;

    @Column(name = "password", unique = false, nullable = true)
    private String password;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}