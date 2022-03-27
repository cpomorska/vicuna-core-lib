package com.scprojekt.domain.model.user;

import com.scprojekt.domain.validation.SQLInjectionSafe;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class UserHandlingEventType {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    String eventType;

    @SQLInjectionSafe
    @Column(name="benutzerdescription")
    String eventDescription;
}
