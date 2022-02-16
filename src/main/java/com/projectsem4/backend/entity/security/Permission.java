package com.projectsem4.backend.entity.security;

import com.projectsem4.backend.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "permissions")
public class Permission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String permission;

    @Column(name = "role_id")
    private int roleId;

    @ManyToOne
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private Role role;
}
