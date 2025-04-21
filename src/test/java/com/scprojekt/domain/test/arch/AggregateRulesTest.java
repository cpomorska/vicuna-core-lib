package com.scprojekt.domain.test.arch;

import com.scprojekt.domain.core.shared.aggregate.BaseAggregate;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.theClass;

class AggregateRulesTest {

    private final JavaClasses classes = new ClassFileImporter().importPackages("com.scprojekt.domain");

    @Test
    void aggregates_must_reside_in_aggregates_package() {
        classes().that().haveNameMatching(".*Aggregate")
                .and().resideOutsideOfPackage("..shared..")
                .should().resideInAPackage("..aggregate..")
                .as("Aggregates should reside in a package '..aggregate..'")
                .check(classes);
    }

    @Test
    void aggreagtes_must_extend_baseaggregate_interface() {
        classes().that().haveNameMatching(".*Aggregate")
                .and().resideOutsideOfPackage("..shared..")
                .and().resideInAPackage("..aggregate..")
                .should().beAssignableTo(BaseAggregate.class)
                .as("Aggregates should implement BaseAggregate")
                .check(classes);
    }

    @Test
    void only_one_baseaggregate_interface() {
        theClass(BaseAggregate.class)
                .should().resideInAPackage("..shared..")
                .andShould().resideInAPackage("..aggregate..")
                .as("BaseAggregate Interface in shared .. aggregate")
                .check(classes);
    }
}
