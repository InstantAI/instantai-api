package org.kubeflow.v1.notebookspec.template.spec;

@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)
@com.fasterxml.jackson.annotation.JsonPropertyOrder({"nameservers","options","searches"})
@com.fasterxml.jackson.databind.annotation.JsonDeserialize(using = com.fasterxml.jackson.databind.JsonDeserializer.None.class)
@javax.annotation.processing.Generated("io.fabric8.java.generator.CRGeneratorRunner")
public class DnsConfig implements io.fabric8.kubernetes.api.model.KubernetesResource {

    @com.fasterxml.jackson.annotation.JsonProperty("nameservers")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private java.util.List<String> nameservers;

    public java.util.List<String> getNameservers() {
        return nameservers;
    }

    public void setNameservers(java.util.List<String> nameservers) {
        this.nameservers = nameservers;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("options")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private java.util.List<org.kubeflow.v1.notebookspec.template.spec.dnsconfig.Options> options;

    public java.util.List<org.kubeflow.v1.notebookspec.template.spec.dnsconfig.Options> getOptions() {
        return options;
    }

    public void setOptions(java.util.List<org.kubeflow.v1.notebookspec.template.spec.dnsconfig.Options> options) {
        this.options = options;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("searches")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private java.util.List<String> searches;

    public java.util.List<String> getSearches() {
        return searches;
    }

    public void setSearches(java.util.List<String> searches) {
        this.searches = searches;
    }
}

