package io.openshift.booster.messaging;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WorkerResource {

  @Inject
  @Named("worker-id")
  String workerId;

  @Inject
  @Named("cloud-id")
  String cloudId;

  @GET
  @Path("/cloud")
  public String cloud() {
    return cloudId;
  }

  @POST
  @Path("/process")
  public Response process(Message message) {
    System.out.println(message);
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
