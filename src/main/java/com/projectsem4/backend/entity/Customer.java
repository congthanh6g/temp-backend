package com.projectsem4.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String account_type;


    @OneToMany(mappedBy = "customer")
    private List<Comment> comments;

    @OneToMany(mappedBy = "customer")
    private List<Payment> payments;
}
