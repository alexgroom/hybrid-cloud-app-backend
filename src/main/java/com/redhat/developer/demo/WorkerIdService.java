package com.redhat.developer.demo;

import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

@ApplicationScoped
public class WorkerIdService {

    @Named("worker-id")
    @Produces
    public String workerId() {
        return "worker-quarkus-" + UUID.randomUUID().toString().substring(0, 4);
    }
}
