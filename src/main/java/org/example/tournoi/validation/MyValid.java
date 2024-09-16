package org.example.tournoi.validation;

/* A ajouter dans pom.xml pour validation personnalisée :

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
*/

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


// @ pour indiquer une interface d'annotation
@Constraint(validatedBy = MyValidConstraintValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyValid {

    public String message() default "Ces mots sont inappropriés"; // Message par défaut
    public Class<?>[] groups() default {}; // Regrouper contraintes de validation
    public Class<? extends Payload>[] payload() default {}; // Spécifier le niveau de gravité d'une erreur

    /*
    Mettre ceci par exemple dans entity :

    @MyValid(message = "Gros mots interdits") // Validation personnalisée
    private String message;
    */

}
