package org.kubeflow.v1.notebookspec.template.spec.affinity.nodeaffinity.requiredduringschedulingignoredduringexecution;

@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)
@com.fasterxml.jackson.annotation.JsonPropertyOrder({"matchExpressions","matchFields"})
@com.fasterxml.jackson.databind.annotation.JsonDeserialize(using = com.fasterxml.jackson.databind.JsonDeserializer.None.class)
@javax.annotation.processing.Generated("io.fabric8.java.generator.CRGeneratorRunner")
public class NodeSelectorTerms implements io.fabric8.kubernetes.api.model.KubernetesResource {

    @com.fasterxml.jackson.annotation.JsonProperty("matchExpressions")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private java.util.List<org.kubeflow.v1.notebookspec.template.spec.affinity.nodeaffinity.requiredduringschedulingignoredduringexecution.nodeselectorterms.MatchExpressions> matchExpressions;

    public java.util.List<org.kubeflow.v1.notebookspec.template.spec.affinity.nodeaffinity.requiredduringschedulingignoredduringexecution.nodeselectorterms.MatchExpressions> getMatchExpressions() {
        return matchExpressions;
    }

    public void setMatchExpressions(java.util.List<org.kubeflow.v1.notebookspec.template.spec.affinity.nodeaffinity.requiredduringschedulingignoredduringexecution.nodeselectorterms.MatchExpressions> matchExpressions) {
        this.matchExpressions = matchExpressions;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("matchFields")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private java.util.List<org.kubeflow.v1.notebookspec.template.spec.affinity.nodeaffinity.requiredduringschedulingignoredduringexecution.nodeselectorterms.MatchFields> matchFields;

    public java.util.List<org.kubeflow.v1.notebookspec.template.spec.affinity.nodeaffinity.requiredduringschedulingignoredduringexecution.nodeselectorterms.MatchFields> getMatchFields() {
        return matchFields;
    }

    public void setMatchFields(java.util.List<org.kubeflow.v1.notebookspec.template.spec.affinity.nodeaffinity.requiredduringschedulingignoredduringexecution.nodeselectorterms.MatchFields> matchFields) {
        this.matchFields = matchFields;
    }
}

