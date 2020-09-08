package io.mateu.translator;

import io.mateu.mdd.core.annotations.Action;
import io.mateu.mdd.core.annotations.MateuMDDApp;
import io.mateu.mdd.core.app.SimpleMDDApplication;
import io.mateu.translator.model.Application;
import io.mateu.translator.model.Translation;

@MateuMDDApp(path = "")
public class MyApp extends SimpleMDDApplication {

    @Action(order = 10)
    public Class applications() {
        return Application.class;
    }

    @Action(order = 20)
    public Class translations() {
        return Translation.class;
    }

}