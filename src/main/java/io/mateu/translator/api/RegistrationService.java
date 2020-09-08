package io.mateu.translator.api;


import io.swagger.annotations.Api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("recurso")
@Api
public class RegistrationService {

    @GET
    public void put(@QueryParam("applicationId") String applicationId, @QueryParam("key") String key, @QueryParam("text") String text) {

    }

}
