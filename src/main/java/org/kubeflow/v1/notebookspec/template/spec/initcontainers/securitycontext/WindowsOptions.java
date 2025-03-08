package org.kubeflow.v1.notebookspec.template.spec.initcontainers.securitycontext;

@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)
@com.fasterxml.jackson.annotation.JsonPropertyOrder({"gmsaCredentialSpec","gmsaCredentialSpecName","hostProcess","runAsUserName"})
@com.fasterxml.jackson.databind.annotation.JsonDeserialize(using = com.fasterxml.jackson.databind.JsonDeserializer.None.class)
@javax.annotation.processing.Generated("io.fabric8.java.generator.CRGeneratorRunner")
public class WindowsOptions implements io.fabric8.kubernetes.api.model.KubernetesResource {

    @com.fasterxml.jackson.annotation.JsonProperty("gmsaCredentialSpec")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private String gmsaCredentialSpec;

    public String getGmsaCredentialSpec() {
        return gmsaCredentialSpec;
    }

    public void setGmsaCredentialSpec(String gmsaCredentialSpec) {
        this.gmsaCredentialSpec = gmsaCredentialSpec;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("gmsaCredentialSpecName")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private String gmsaCredentialSpecName;

    public String getGmsaCredentialSpecName() {
        return gmsaCredentialSpecName;
    }

    public void setGmsaCredentialSpecName(String gmsaCredentialSpecName) {
        this.gmsaCredentialSpecName = gmsaCredentialSpecName;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("hostProcess")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private Boolean hostProcess;

    public Boolean getHostProcess() {
        return hostProcess;
    }

    public void setHostProcess(Boolean hostProcess) {
        this.hostProcess = hostProcess;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("runAsUserName")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private String runAsUserName;

    public String getRunAsUserName() {
        return runAsUserName;
    }

    public void setRunAsUserName(String runAsUserName) {
        this.runAsUserName = runAsUserName;
    }
}

