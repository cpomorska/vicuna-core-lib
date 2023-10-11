package com.scprojekt.domain.test.arch;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.library.Architectures.onionArchitecture;

class HexaArcitectureTest {
    private final JavaClasses classes = new ClassFileImporter().importPackages(
            "com.scprojekt.domain");

    @Test
    void onion_architecture_is_respected() {
        onionArchitecture()
                .domainModels("..domain.model..")
                .domainServices("..domain.service..")
                .applicationServices("..application..")
                .adapter("cli", "..adapter.cli..")
                .adapter("persistence", "..adapter.persistence..")
                .adapter("rest", "..adapter.rest..")
                .allowEmptyShould(true)
                .check(classes);
    }

    @Test
    void onion_architecture_is_respected_with_exception() {
        onionArchitecture()
                .domainModels("..domain.model..")
                .domainServices("..domain.service..")
                .applicationServices("..application..")
                .adapter("cli", "..adapter.cli..")
                .adapter("persistence", "..adapter.persistence..")
                .adapter("rest", "..adapter.rest..")
                .allowEmptyShould(true)
                .check(classes);
    }

//    @Test
//    public void onion_architecture_defined_by_annotations() {
//        onionArchitecture()
//                .domainModels(byAnnotation(DomainModel.class))
//                .domainServices(byAnnotation(DomainService.class))
//                .applicationServices(byAnnotation(Application.class))
//                .adapter("cli", byAnnotation(adapter("cli")))
//                .adapter("persistence", byAnnotation(adapter("persistence")))
//                .adapter("rest", byAnnotation(adapter("rest")))
//                .check(classes);
//    }
//
//    private static DescribedPredicate<JavaClass> byAnnotation(Class<? extends Annotation> annotationType) {
//        DescribedPredicate<CanBeAnnotated> annotatedWith = annotatedWith(annotationType);
//        return belongTo(annotatedWith).as(annotatedWith.getDescription());
//    }
//
//    private static DescribedPredicate<JavaClass> byAnnotation(DescribedPredicate<? super JavaAnnotation<?>> annotationType) {
//        DescribedPredicate<CanBeAnnotated> annotatedWith = annotatedWith(annotationType);
//        return belongTo(annotatedWith).as(annotatedWith.getDescription());
//    }
//
//    private static DescribedPredicate<JavaAnnotation<?>> adapter(String adapterName) {
//        return describe(
//                String.format("@%s(\"%s\")", Adapter.class.getSimpleName(), adapterName),
//                a -> a.getRawType().isEquivalentTo(Adapter.class) && a.as(Adapter.class).value().equals(adapterName)
//        );
//    }
}
