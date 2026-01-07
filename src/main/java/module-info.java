module vicuna.core.lib {
    requires static lombok;
    requires jakarta.persistence;
    requires jakarta.inject;
    requires jakarta.validation;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;


    // opens to other projects
    opens com.scprojekt.stereotype to junit, vicuna.infra, vicuna.app;
    opens com.scprojekt.example to junit, vicuna.infra, vicuna.app;
    opens com.scprojekt.domain.core.model.user.entity to junit, vicuna.app, vicuna.infra;
    opens com.scprojekt.domain.core.model.user.exception to junit, vicuna.app, vicuna.infra;
    opens com.scprojekt.domain.core.model.user.event to junit, vicuna.app, vicuna.infra;
    opens com.scprojekt.domain.core.model.user.repository to junit, vicuna.app, vicuna.infra;
    opens com.scprojekt.domain.core.model.user.service to junit, vicuna.app, vicuna.infra;
    opens com.scprojekt.domain.core.shared.dto to junit, vicuna.app, vicuna.infra;
    opens com.scprojekt.domain.core.shared.database to junit, vicuna.app, vicuna.infra;
    opens com.scprojekt.domain.core.shared.messaging to junit, vicuna.app, vicuna.infra;
    opens com.scprojekt.domain.core.shared.misc to junit, vicuna.app, vicuna.infra;
    opens com.scprojekt.domain.core.model.customer.entity to junit, vicuna.app, vicuna.infra;
    opens com.scprojekt.domain.core.model.banking.entity to junit, vicuna.app, vicuna.infra;
    opens com.scprojekt.domain.core.model.assurance.entity to junit, vicuna.app, vicuna.infra;
    opens com.scprojekt.domain.core.model.banking.enums to junit, vicuna.app, vicuna.infra;
}