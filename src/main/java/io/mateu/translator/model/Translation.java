package io.mateu.translator.model;

import io.mateu.mdd.core.model.multilanguage.Literal;
import lombok.MateuMDDEntity;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@MateuMDDEntity
public class Translation {

    @NotNull
    @ManyToOne
    private final Application application;

    @NotEmpty
    private String key;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private Literal literal = new Literal();

}
