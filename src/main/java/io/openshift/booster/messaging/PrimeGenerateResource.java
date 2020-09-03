package io.openshift.booster.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static java.util.concurrent.TimeUnit.SECONDS;

@Path("/api")
public class PrimeGenerateResource {

  private final Logger LOGGER = LoggerFactory.getLogger(PrimeGenerateResource.class);

  @Inject
  PrimeService primeService;

  @Inject
  Utils utils;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/prime")
  public Response getPrimes(PrimeNumberRequest primeNumberRequest) {
    var upto = primeNumberRequest.maximumNumber;
    var sleepInSeconds = primeNumberRequest.sleepInSeconds;
    var memLoad = primeNumberRequest.memoryLoadInMB;

    LOGGER.info("Query Parameters Upto {} Sleep in seconds {} Memory Load {} ", upto,
        sleepInSeconds, memLoad);

    if (sleepInSeconds != 0) {
      utils.sleepInSeconds(sleepInSeconds);
    }

    if (memLoad != 0) {
      utils.loadMemory(memLoad);
    }

    if (upto <= 1) {
      return Response.serverError()
          .entity(String.format("Value should be greater than 1 but recevied %d", upto)).build();
    }

    int bigPrime = primeService.biggestPrime(upto);
    JsonObject result = new JsonObject().put("biggestPrime", bigPrime);

    return Response.ok(Json.encodePrettily(result)).build();
  }



}
