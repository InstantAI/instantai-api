package org.kubeflow.v1;

@io.fabric8.kubernetes.model.annotation.Version(value = "v1" , storage = true , served = true)
@io.fabric8.kubernetes.model.annotation.Group("kubeflow.org")
@io.fabric8.kubernetes.model.annotation.Singular("notebook")
@io.fabric8.kubernetes.model.annotation.Plural("notebooks")
@javax.annotation.processing.Generated("io.fabric8.java.generator.CRGeneratorRunner")
public class Notebook extends io.fabric8.kubernetes.client.CustomResource<org.kubeflow.v1.NotebookSpec, org.kubeflow.v1.NotebookStatus> implements io.fabric8.kubernetes.api.model.Namespaced {
}

