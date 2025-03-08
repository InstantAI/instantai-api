package org.kubeflow.v1.notebookspec.template.spec.volumes.ephemeral.volumeclaimtemplate;

@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)
@com.fasterxml.jackson.annotation.JsonPropertyOrder({"accessModes","dataSource","dataSourceRef","resources","selector","storageClassName","volumeMode","volumeName"})
@com.fasterxml.jackson.databind.annotation.JsonDeserialize(using = com.fasterxml.jackson.databind.JsonDeserializer.None.class)
@javax.annotation.processing.Generated("io.fabric8.java.generator.CRGeneratorRunner")
public class Spec implements io.fabric8.kubernetes.api.model.KubernetesResource {

    @com.fasterxml.jackson.annotation.JsonProperty("accessModes")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private java.util.List<String> accessModes;

    public java.util.List<String> getAccessModes() {
        return accessModes;
    }

    public void setAccessModes(java.util.List<String> accessModes) {
        this.accessModes = accessModes;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("dataSource")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private org.kubeflow.v1.notebookspec.template.spec.volumes.ephemeral.volumeclaimtemplate.spec.DataSource dataSource;

    public org.kubeflow.v1.notebookspec.template.spec.volumes.ephemeral.volumeclaimtemplate.spec.DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(org.kubeflow.v1.notebookspec.template.spec.volumes.ephemeral.volumeclaimtemplate.spec.DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("dataSourceRef")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private org.kubeflow.v1.notebookspec.template.spec.volumes.ephemeral.volumeclaimtemplate.spec.DataSourceRef dataSourceRef;

    public org.kubeflow.v1.notebookspec.template.spec.volumes.ephemeral.volumeclaimtemplate.spec.DataSourceRef getDataSourceRef() {
        return dataSourceRef;
    }

    public void setDataSourceRef(org.kubeflow.v1.notebookspec.template.spec.volumes.ephemeral.volumeclaimtemplate.spec.DataSourceRef dataSourceRef) {
        this.dataSourceRef = dataSourceRef;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("resources")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private org.kubeflow.v1.notebookspec.template.spec.volumes.ephemeral.volumeclaimtemplate.spec.Resources resources;

    public org.kubeflow.v1.notebookspec.template.spec.volumes.ephemeral.volumeclaimtemplate.spec.Resources getResources() {
        return resources;
    }

    public void setResources(org.kubeflow.v1.notebookspec.template.spec.volumes.ephemeral.volumeclaimtemplate.spec.Resources resources) {
        this.resources = resources;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("selector")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private org.kubeflow.v1.notebookspec.template.spec.volumes.ephemeral.volumeclaimtemplate.spec.Selector selector;

    public org.kubeflow.v1.notebookspec.template.spec.volumes.ephemeral.volumeclaimtemplate.spec.Selector getSelector() {
        return selector;
    }

    public void setSelector(org.kubeflow.v1.notebookspec.template.spec.volumes.ephemeral.volumeclaimtemplate.spec.Selector selector) {
        this.selector = selector;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("storageClassName")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private String storageClassName;

    public String getStorageClassName() {
        return storageClassName;
    }

    public void setStorageClassName(String storageClassName) {
        this.storageClassName = storageClassName;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("volumeMode")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private String volumeMode;

    public String getVolumeMode() {
        return volumeMode;
    }

    public void setVolumeMode(String volumeMode) {
        this.volumeMode = volumeMode;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("volumeName")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private String volumeName;

    public String getVolumeName() {
        return volumeName;
    }

    public void setVolumeName(String volumeName) {
        this.volumeName = volumeName;
    }
}

