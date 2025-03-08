package org.kubeflow.v1.notebookspec.template.spec;

@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)
@com.fasterxml.jackson.annotation.JsonPropertyOrder({"awsElasticBlockStore","azureDisk","azureFile","cephfs","cinder","configMap","csi","downwardAPI","emptyDir","ephemeral","fc","flexVolume","flocker","gcePersistentDisk","gitRepo","glusterfs","hostPath","iscsi","name","nfs","persistentVolumeClaim","photonPersistentDisk","portworxVolume","projected","quobyte","rbd","scaleIO","secret","storageos","vsphereVolume"})
@com.fasterxml.jackson.databind.annotation.JsonDeserialize(using = com.fasterxml.jackson.databind.JsonDeserializer.None.class)
@javax.annotation.processing.Generated("io.fabric8.java.generator.CRGeneratorRunner")
public class Volumes implements io.fabric8.kubernetes.api.model.KubernetesResource {

    @com.fasterxml.jackson.annotation.JsonProperty("awsElasticBlockStore")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private org.kubeflow.v1.notebookspec.template.spec.volumes.AwsElasticBlockStore awsElasticBlockStore;

    public org.kubeflow.v1.notebookspec.template.spec.volumes.AwsElasticBlockStore getAwsElasticBlockStore() {
        return awsElasticBlockStore;
    }

    public void setAwsElasticBlockStore(org.kubeflow.v1.notebookspec.template.spec.volumes.AwsElasticBlockStore awsElasticBlockStore) {
        this.awsElasticBlockStore = awsElasticBlockStore;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("azureDisk")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private org.kubeflow.v1.notebookspec.template.spec.volumes.AzureDisk azureDisk;

    public org.kubeflow.v1.notebookspec.template.spec.volumes.AzureDisk getAzureDisk() {
        return azureDisk;
    }

    public void setAzureDisk(org.kubeflow.v1.notebookspec.template.spec.volumes.AzureDisk azureDisk) {
        this.azureDisk = azureDisk;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("azureFile")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private org.kubeflow.v1.notebookspec.template.spec.volumes.AzureFile azureFile;

    public org.kubeflow.v1.notebookspec.template.spec.volumes.AzureFile getAzureFile() {
        return azureFile;
    }

    public void setAzureFile(org.kubeflow.v1.notebookspec.template.spec.volumes.AzureFile azureFile) {
        this.azureFile = azureFile;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("cephfs")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private org.kubeflow.v1.notebookspec.template.spec.volumes.Cephfs cephfs;

    public org.kubeflow.v1.notebookspec.template.spec.volumes.Cephfs getCephfs() {
        return cephfs;
    }

    public void setCephfs(org.kubeflow.v1.notebookspec.template.spec.volumes.Cephfs cephfs) {
        this.cephfs = cephfs;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("cinder")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private org.kubeflow.v1.notebookspec.template.spec.volumes.Cinder cinder;

    public org.kubeflow.v1.notebookspec.template.spec.volumes.Cinder getCinder() {
        return cinder;
    }

    public void setCinder(org.kubeflow.v1.notebookspec.template.spec.volumes.Cinder cinder) {
        this.cinder = cinder;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("configMap")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private org.kubeflow.v1.notebookspec.template.spec.volumes.ConfigMap configMap;

    public org.kubeflow.v1.notebookspec.template.spec.volumes.ConfigMap getConfigMap() {
        return configMap;
    }

    public void setConfigMap(org.kubeflow.v1.notebookspec.template.spec.volumes.ConfigMap configMap) {
        this.configMap = configMap;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("csi")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private org.kubeflow.v1.notebookspec.template.spec.volumes.Csi csi;

    public org.kubeflow.v1.notebookspec.template.spec.volumes.Csi getCsi() {
        return csi;
    }

    public void setCsi(org.kubeflow.v1.notebookspec.template.spec.volumes.Csi csi) {
        this.csi = csi;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("downwardAPI")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private org.kubeflow.v1.notebookspec.template.spec.volumes.DownwardAPI downwardAPI;

    public org.kubeflow.v1.notebookspec.template.spec.volumes.DownwardAPI getDownwardAPI() {
        return downwardAPI;
    }

    public void setDownwardAPI(org.kubeflow.v1.notebookspec.template.spec.volumes.DownwardAPI downwardAPI) {
        this.downwardAPI = downwardAPI;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("emptyDir")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private org.kubeflow.v1.notebookspec.template.spec.volumes.EmptyDir emptyDir;

    public org.kubeflow.v1.notebookspec.template.spec.volumes.EmptyDir getEmptyDir() {
        return emptyDir;
    }

    public void setEmptyDir(org.kubeflow.v1.notebookspec.template.spec.volumes.EmptyDir emptyDir) {
        this.emptyDir = emptyDir;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("ephemeral")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private org.kubeflow.v1.notebookspec.template.spec.volumes.Ephemeral ephemeral;

    public org.kubeflow.v1.notebookspec.template.spec.volumes.Ephemeral getEphemeral() {
        return ephemeral;
    }

    public void setEphemeral(org.kubeflow.v1.notebookspec.template.spec.volumes.Ephemeral ephemeral) {
        this.ephemeral = ephemeral;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("fc")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private org.kubeflow.v1.notebookspec.template.spec.volumes.Fc fc;

    public org.kubeflow.v1.notebookspec.template.spec.volumes.Fc getFc() {
        return fc;
    }

    public void setFc(org.kubeflow.v1.notebookspec.template.spec.volumes.Fc fc) {
        this.fc = fc;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("flexVolume")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private org.kubeflow.v1.notebookspec.template.spec.volumes.FlexVolume flexVolume;

    public org.kubeflow.v1.notebookspec.template.spec.volumes.FlexVolume getFlexVolume() {
        return flexVolume;
    }

    public void setFlexVolume(org.kubeflow.v1.notebookspec.template.spec.volumes.FlexVolume flexVolume) {
        this.flexVolume = flexVolume;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("flocker")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private org.kubeflow.v1.notebookspec.template.spec.volumes.Flocker flocker;

    public org.kubeflow.v1.notebookspec.template.spec.volumes.Flocker getFlocker() {
        return flocker;
    }

    public void setFlocker(org.kubeflow.v1.notebookspec.template.spec.volumes.Flocker flocker) {
        this.flocker = flocker;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("gcePersistentDisk")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private org.kubeflow.v1.notebookspec.template.spec.volumes.GcePersistentDisk gcePersistentDisk;

    public org.kubeflow.v1.notebookspec.template.spec.volumes.GcePersistentDisk getGcePersistentDisk() {
        return gcePersistentDisk;
    }

    public void setGcePersistentDisk(org.kubeflow.v1.notebookspec.template.spec.volumes.GcePersistentDisk gcePersistentDisk) {
        this.gcePersistentDisk = gcePersistentDisk;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("gitRepo")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private org.kubeflow.v1.notebookspec.template.spec.volumes.GitRepo gitRepo;

    public org.kubeflow.v1.notebookspec.template.spec.volumes.GitRepo getGitRepo() {
        return gitRepo;
    }

    public void setGitRepo(org.kubeflow.v1.notebookspec.template.spec.volumes.GitRepo gitRepo) {
        this.gitRepo = gitRepo;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("glusterfs")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private org.kubeflow.v1.notebookspec.template.spec.volumes.Glusterfs glusterfs;

    public org.kubeflow.v1.notebookspec.template.spec.volumes.Glusterfs getGlusterfs() {
        return glusterfs;
    }

    public void setGlusterfs(org.kubeflow.v1.notebookspec.template.spec.volumes.Glusterfs glusterfs) {
        this.glusterfs = glusterfs;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("hostPath")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private org.kubeflow.v1.notebookspec.template.spec.volumes.HostPath hostPath;

    public org.kubeflow.v1.notebookspec.template.spec.volumes.HostPath getHostPath() {
        return hostPath;
    }

    public void setHostPath(org.kubeflow.v1.notebookspec.template.spec.volumes.HostPath hostPath) {
        this.hostPath = hostPath;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("iscsi")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private org.kubeflow.v1.notebookspec.template.spec.volumes.Iscsi iscsi;

    public org.kubeflow.v1.notebookspec.template.spec.volumes.Iscsi getIscsi() {
        return iscsi;
    }

    public void setIscsi(org.kubeflow.v1.notebookspec.template.spec.volumes.Iscsi iscsi) {
        this.iscsi = iscsi;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("name")
    @io.fabric8.generator.annotation.Required()
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("nfs")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private org.kubeflow.v1.notebookspec.template.spec.volumes.Nfs nfs;

    public org.kubeflow.v1.notebookspec.template.spec.volumes.Nfs getNfs() {
        return nfs;
    }

    public void setNfs(org.kubeflow.v1.notebookspec.template.spec.volumes.Nfs nfs) {
        this.nfs = nfs;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("persistentVolumeClaim")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private org.kubeflow.v1.notebookspec.template.spec.volumes.PersistentVolumeClaim persistentVolumeClaim;

    public org.kubeflow.v1.notebookspec.template.spec.volumes.PersistentVolumeClaim getPersistentVolumeClaim() {
        return persistentVolumeClaim;
    }

    public void setPersistentVolumeClaim(org.kubeflow.v1.notebookspec.template.spec.volumes.PersistentVolumeClaim persistentVolumeClaim) {
        this.persistentVolumeClaim = persistentVolumeClaim;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("photonPersistentDisk")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private org.kubeflow.v1.notebookspec.template.spec.volumes.PhotonPersistentDisk photonPersistentDisk;

    public org.kubeflow.v1.notebookspec.template.spec.volumes.PhotonPersistentDisk getPhotonPersistentDisk() {
        return photonPersistentDisk;
    }

    public void setPhotonPersistentDisk(org.kubeflow.v1.notebookspec.template.spec.volumes.PhotonPersistentDisk photonPersistentDisk) {
        this.photonPersistentDisk = photonPersistentDisk;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("portworxVolume")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private org.kubeflow.v1.notebookspec.template.spec.volumes.PortworxVolume portworxVolume;

    public org.kubeflow.v1.notebookspec.template.spec.volumes.PortworxVolume getPortworxVolume() {
        return portworxVolume;
    }

    public void setPortworxVolume(org.kubeflow.v1.notebookspec.template.spec.volumes.PortworxVolume portworxVolume) {
        this.portworxVolume = portworxVolume;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("projected")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private org.kubeflow.v1.notebookspec.template.spec.volumes.Projected projected;

    public org.kubeflow.v1.notebookspec.template.spec.volumes.Projected getProjected() {
        return projected;
    }

    public void setProjected(org.kubeflow.v1.notebookspec.template.spec.volumes.Projected projected) {
        this.projected = projected;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("quobyte")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private org.kubeflow.v1.notebookspec.template.spec.volumes.Quobyte quobyte;

    public org.kubeflow.v1.notebookspec.template.spec.volumes.Quobyte getQuobyte() {
        return quobyte;
    }

    public void setQuobyte(org.kubeflow.v1.notebookspec.template.spec.volumes.Quobyte quobyte) {
        this.quobyte = quobyte;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("rbd")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private org.kubeflow.v1.notebookspec.template.spec.volumes.Rbd rbd;

    public org.kubeflow.v1.notebookspec.template.spec.volumes.Rbd getRbd() {
        return rbd;
    }

    public void setRbd(org.kubeflow.v1.notebookspec.template.spec.volumes.Rbd rbd) {
        this.rbd = rbd;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("scaleIO")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private org.kubeflow.v1.notebookspec.template.spec.volumes.ScaleIO scaleIO;

    public org.kubeflow.v1.notebookspec.template.spec.volumes.ScaleIO getScaleIO() {
        return scaleIO;
    }

    public void setScaleIO(org.kubeflow.v1.notebookspec.template.spec.volumes.ScaleIO scaleIO) {
        this.scaleIO = scaleIO;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("secret")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private org.kubeflow.v1.notebookspec.template.spec.volumes.Secret secret;

    public org.kubeflow.v1.notebookspec.template.spec.volumes.Secret getSecret() {
        return secret;
    }

    public void setSecret(org.kubeflow.v1.notebookspec.template.spec.volumes.Secret secret) {
        this.secret = secret;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("storageos")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private org.kubeflow.v1.notebookspec.template.spec.volumes.Storageos storageos;

    public org.kubeflow.v1.notebookspec.template.spec.volumes.Storageos getStorageos() {
        return storageos;
    }

    public void setStorageos(org.kubeflow.v1.notebookspec.template.spec.volumes.Storageos storageos) {
        this.storageos = storageos;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("vsphereVolume")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private org.kubeflow.v1.notebookspec.template.spec.volumes.VsphereVolume vsphereVolume;

    public org.kubeflow.v1.notebookspec.template.spec.volumes.VsphereVolume getVsphereVolume() {
        return vsphereVolume;
    }

    public void setVsphereVolume(org.kubeflow.v1.notebookspec.template.spec.volumes.VsphereVolume vsphereVolume) {
        this.vsphereVolume = vsphereVolume;
    }
}

