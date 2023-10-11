module vicuna.core.lib {
    requires static lombok;
    requires jakarta.persistence;
    requires jakarta.inject;
    requires jakarta.validation;
    requires com.fasterxml.jackson.annotation;

    opens com.scprojekt.stereotype to vicuna.infra, vicuna.app;
    opens com.scprojekt.example to vicuna.infra, vicuna.app;
    opens com.scprojekt.domain.core.model.user.entity to vicuna.app, vicuna.infra;
    opens com.scprojekt.domain.core.model.user.exception to vicuna.app, vicuna.infra;
    opens com.scprojekt.domain.core.model.user.event to vicuna.app, vicuna.infra;
    opens com.scprojekt.domain.core.model.user.repository to vicuna.app, vicuna.infra;
    opens com.scprojekt.domain.core.model.user.service to vicuna.app, vicuna.infra;
    opens com.scprojekt.domain.core.shared.dto to vicuna.app, vicuna.infra;
    opens com.scprojekt.domain.core.shared.database to vicuna.app, vicuna.infra;
    opens com.scprojekt.domain.core.shared.messaging to vicuna.app, vicuna.infra;
    opens com.scprojekt.domain.core.shared.misc to vicuna.app, vicuna.infra;
    opens com.scprojekt.domain.core.model.customer.entity to vicuna.app, vicuna.infra;
    opens com.scprojekt.domain.core.model.banking.entity to vicuna.app, vicuna.infra;
    opens com.scprojekt.domain.core.model.assurance.entity to vicuna.app, vicuna.infra;
}