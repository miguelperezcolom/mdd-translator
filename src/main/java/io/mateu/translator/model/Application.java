package io.mateu.translator.model;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.icons.VaadinIcons;
import io.mateu.mdd.core.annotations.Action;
import io.mateu.mdd.core.annotations.UseChips;
import io.mateu.mdd.core.model.common.Resource;
import lombok.MateuMDDEntity;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@MateuMDDEntity
public class Application {

    @NotEmpty
    private String name;

    @NotEmpty
    private String language;

    public DataProvider getLanguageDataProvider() {
        return new ListDataProvider(List.of("en", "es", "de", "fr", "it", "ru", "ar", "cz"));
    }

    @UseChips
    @OneToMany
    private List<Application> dependencies = new ArrayList<>();


    @Action(icon = VaadinIcons.DOWNLOAD)
    public Resource downloadFiles() throws Throwable {
        return new Resource(Zipper.dumpAndZip(id));
    }

}
