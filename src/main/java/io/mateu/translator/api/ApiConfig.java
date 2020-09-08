package io.mateu.translator.api;

import io.mateu.mdd.util.rest.MateuRestApiResourceConfig;
import io.swagger.annotations.Contact;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("api")
@SwaggerDefinition(info = @Info(
        title = "Example Service",
        description = "A simple example of rest application",
        version = "1.0.0",
        contact = @Contact(
                name = "Miguel Pérez Colom",
                email = "miguelperezcolom@gmail.com",
                url = "http://mateu.io"
        )
)
)
public class ApiConfig extends MateuRestApiResourceConfig {

    public String getPackagesToBeScanned() {
        return "io.mateu.translator.api";
    }

}