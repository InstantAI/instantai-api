package org.kubeflow.v1.notebookstatus;

@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)
@com.fasterxml.jackson.annotation.JsonPropertyOrder({"running","terminated","waiting"})
@com.fasterxml.jackson.databind.annotation.JsonDeserialize(using = com.fasterxml.jackson.databind.JsonDeserializer.None.class)
@javax.annotation.processing.Generated("io.fabric8.java.generator.CRGeneratorRunner")
public class ContainerState implements io.fabric8.kubernetes.api.model.KubernetesResource {

    @com.fasterxml.jackson.annotation.JsonProperty("running")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private org.kubeflow.v1.notebookstatus.containerstate.Running running;

    public org.kubeflow.v1.notebookstatus.containerstate.Running getRunning() {
        return running;
    }

    public void setRunning(org.kubeflow.v1.notebookstatus.containerstate.Running running) {
        this.running = running;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("terminated")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private org.kubeflow.v1.notebookstatus.containerstate.Terminated terminated;

    public org.kubeflow.v1.notebookstatus.containerstate.Terminated getTerminated() {
        return terminated;
    }

    public void setTerminated(org.kubeflow.v1.notebookstatus.containerstate.Terminated terminated) {
        this.terminated = terminated;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("waiting")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private org.kubeflow.v1.notebookstatus.containerstate.Waiting waiting;

    public org.kubeflow.v1.notebookstatus.containerstate.Waiting getWaiting() {
        return waiting;
    }

    public void setWaiting(org.kubeflow.v1.notebookstatus.containerstate.Waiting waiting) {
        this.waiting = waiting;
    }
}

