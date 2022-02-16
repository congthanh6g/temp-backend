package com.projectsem4.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.annotation.PreDestroy;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@FilterDef(name = "deletedItemFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedItemFilter", condition = "deleted = :isDeleted")
public class BaseEntity {
    private Date createdAt;
    private Date updateAt;
    private boolean deleted = Boolean.FALSE;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updateAt = new Date();
    }
}
