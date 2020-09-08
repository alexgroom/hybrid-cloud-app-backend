package com.redhat.developer.demo;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.NamespaceList;
import io.fabric8.kubernetes.client.KubernetesClient;

@ApplicationScoped
public class CloudIdentifierService {

  @Inject
  KubernetesClient kubernetesClient;

  @Produces
  @Named("cloud-id")
  @SuppressWarnings("unchecked")
  public String whichCloud() {

    try {
      NamespaceList namespaces = kubernetesClient.namespaces().list();

      Optional<Namespace> optNS = namespaces.getItems().stream()
          .filter(n -> n.getMetadata().getName().equals("openshift-kube-apiserver")).findFirst();

      if (optNS.isPresent()) {
        String configYAML = kubernetesClient.configMaps().inNamespace("openshift-kube-apiserver")
            .withName("config").get().getData().get("config.yaml");
        Yaml yaml = new Yaml(new Constructor(Map.class));
        Map<String, Object> map = yaml.load(new StringReader(configYAML));
        Map<String, Object> apiServerArgs = (Map<String, Object>) map.get("apiServerArguments");
        ArrayList<String> cloudProviders = (ArrayList<String>) apiServerArgs.get("cloud-provider");
        System.out.println(cloudProviders.get(0));
        return String.valueOf(cloudProviders.get(0));
      }
    } catch (Exception e) {
      // if any error happens getting the cloud provider return unknown
    }

    return "unknown";

  }
}
