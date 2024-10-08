package com.scprojekt.domain.core.model.user.entity;

import com.scprojekt.domain.core.shared.database.BaseEntity;
import com.scprojekt.domain.core.shared.database.NoSQLInjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_type")
public class UserType extends BaseEntity {
    @Id
    @Column(name = "user_type_id")
    long userTypeId;

    @NoSQLInjection
    @Column(name = "user_role_type")
    String userRoleType;

    @NoSQLInjection
    @Column(name = "user_type_description")
    String userTypeDescription;
}
