package com.scprojekt.domain.test.arch;

import com.scprojekt.domain.core.shared.database.BaseRepository;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.theClass;

class RepositoryRulesTest {

    private final JavaClasses classes = new ClassFileImporter().importPackages("com.scprojekt.domain");

    @Test
    void repositories_must_reside_in_repository_package() {
        classes().that().haveNameMatching(".*Repository")
                .and().resideOutsideOfPackage("..shared..")
                .should().resideInAPackage("..repository..")
                .as("Repositories should reside in a package '..repository..'")
                .check(classes);
    }

    @Test
    void repositories_must_extend_baserepository_interface() {
        classes().that().haveNameMatching(".*Repository")
                .and().resideOutsideOfPackage("..shared..")
                .and().resideInAPackage("..repository..")
                .should().beAssignableTo(BaseRepository.class)
                .as("Repositories should implement BaseRepository")
                .check(classes);
    }

    @Test
    void only_one_baserepository_interface() {
        theClass(BaseRepository.class)
                .should().resideInAPackage("..shared..")
                .andShould().resideInAPackage("..database..")
                .as("BaseRepository Interface in shared .. database")
                .check(classes);
    }
}
