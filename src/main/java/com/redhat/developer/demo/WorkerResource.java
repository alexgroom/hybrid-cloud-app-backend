package com.redhat.developer.demo;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WorkerResource {

  private final Logger LOGGER = LoggerFactory.getLogger(WorkerResource.class);

  @Inject
  @Named("worker-id")
  String workerId;

  @Inject
  @Named("cloud-id")
  String cloudId;

  @Inject
  Utils utils;

  @GET
  @Path("/cloud")
  public String cloud() {
    return cloudId;
  }

  @POST
  @Path("/process")
  public Response process(Message message) {

    var sleepInMillis = message.getRequest().getSleepInMillis();

    if (sleepInMillis != 0) {
      LOGGER.info("Sleeping for {} milliseconds ", sleepInMillis);
      utils.sleepInMilliSeconds(sleepInMillis);
    }
    LOGGER.info("Processing message {} ", message);

    Response response =
        new Response(message.getRequestId(), workerId, cloudId, process(message.getRequest()));
    return response;
  }

  private String process(Request request) {

    String text = request.getText();

    if (request.isUppercase()) {
      text = text.toUpperCase();
    }

    if (request.isReverse()) {
      text = new StringBuilder(text).reverse().toString();
    }
    return "Aloha " + text;
  }
}
