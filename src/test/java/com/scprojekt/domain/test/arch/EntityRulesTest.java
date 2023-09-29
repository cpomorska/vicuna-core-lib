package com.scprojekt.domain.test.arch;

import com.scprojekt.domain.core.shared.database.BaseEntity;
import com.scprojekt.domain.core.shared.database.SQLInjectionSafe;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Table;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;

class EntityRulesTest {

    private final JavaClasses classes = new ClassFileImporter().importPackages("com.scprojekt.domain");

    @Test
    void entities_must_reside_in_domain_and_entity_package() {
        classes().that().areAnnotatedWith(Entity.class).should()
                .resideInAPackage("..domain..")
                .andShould().resideInAPackage("..entity..")
                .as("Entities should reside in package '..domain..' and then in '..entity..'")
                .allowEmptyShould(true)
                .check(classes);
    }

    @Test
    void entities_must_extend_baseentity() {
        classes().that().areAnnotatedWith(Entity.class)
                .should().beAssignableTo(BaseEntity.class)
                .as("Entities should extend BaseEntity")
                .allowEmptyShould(true)
                .check(classes);
    }

    @Test
    void entities_must_be_annotated_with_entity_and_table_or_embeddable() {
        classes().that().resideInAPackage("..entity..")
                .should().beAnnotatedWith(Entity.class)
                .andShould().beAnnotatedWith(Table.class)
                .orShould().beAnnotatedWith(Embeddable.class)
                .as("Entities use @Entity and @Table or @Embeddable")
                .allowEmptyShould(true)
                .check(classes);
    }

    @Test
    void string_members_must_annotated_with_sqlinjectionsafe() {
        fields().that().arePublic().or()
                .arePackagePrivate()
                .and().areDeclaredInClassesThat().resideInAPackage("..entity..")
                .and().haveRawType(String.class)
                .should().beAnnotatedWith(SQLInjectionSafe.class)
                .as("Fields with String type should be SQLInjection safe")
                .allowEmptyShould(true)
                .check(classes);
    }

    @Test
    void only_repositories_use_the_EntityManager_directly() {
        noClasses().that().resideInAPackage("..entity..")
                .should().accessClassesThat().areAssignableTo(EntityManager.class)
                .as("Only Repositories may use the " + EntityManager.class.getSimpleName())
                .allowEmptyShould(true)
                .check(classes);
    }

    @Test
    void Entity_must_not_throw_SQLException() {
        noMethods().that().areDeclaredInClassesThat().resideInAPackage("..entity..")
                .should().declareThrowableOfType(SQLException.class)
                .allowEmptyShould(true)
                .check(classes);
    }
}
