module vicuna.core.lib {
    requires static lombok;
    requires jakarta.persistence;
    requires jakarta.inject;
    requires jakarta.validation;
    requires com.fasterxml.jackson.annotation;


    exports com.scprojekt.domain.core.model.assurance;
    exports com.scprojekt.domain.core.model.banking;
    exports com.scprojekt.domain.core.model.customer;

    exports com.scprojekt.domain.core.service;
    exports com.scprojekt.stereotype;

    opens com.scprojekt.domain.core.model.user to vicuna.infra, vicuna.app;
    opens com.scprojekt.domain.core.model.banking to vicuna.infra, vicuna.app;
    opens com.scprojekt.domain.core.model.assurance to vicuna.infra, vicuna.app;
    opens com.scprojekt.domain.core.model.customer to vicuna.infra, vicuna.app;

    opens com.scprojekt.stereotype to vicuna.infra, vicuna.app;
    opens com.scprojekt.domain.core.service to vicuna.infra, vicuna.app;
    opens com.scprojekt.domain.core.model.user.entity to vicuna.app, vicuna.infra;
    exports com.scprojekt.domain.core.model.user.entity;
    exports com.scprojekt.domain.core.model.user.exception;
    opens com.scprojekt.domain.core.model.user.exception to vicuna.app, vicuna.infra;
    exports com.scprojekt.domain.core.model.user.event;
    opens com.scprojekt.domain.core.model.user.event to vicuna.app, vicuna.infra;
    opens com.scprojekt.domain.core.model.user.repository to vicuna.app, vicuna.infra;
    exports com.scprojekt.domain.core.model.user.repository;
    exports com.scprojekt.domain.core.model.user.service;
    opens com.scprojekt.domain.core.model.user.service to vicuna.app, vicuna.infra;
    exports com.scprojekt.domain.core.shared.dto;
    opens com.scprojekt.domain.core.shared.dto to vicuna.app, vicuna.infra;
    exports com.scprojekt.domain.core.shared.database;
    opens com.scprojekt.domain.core.shared.database to vicuna.app, vicuna.infra;
    exports com.scprojekt.domain.core.shared.messaging;
    opens com.scprojekt.domain.core.shared.messaging to vicuna.app, vicuna.infra;
    exports com.scprojekt.domain.core.shared.misc;
    opens com.scprojekt.domain.core.shared.misc to vicuna.app, vicuna.infra;
}