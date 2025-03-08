package org.kubeflow.v1.notebookspec.template;

@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)
@com.fasterxml.jackson.annotation.JsonPropertyOrder({"activeDeadlineSeconds","affinity","automountServiceAccountToken","containers","dnsConfig","dnsPolicy","enableServiceLinks","ephemeralContainers","hostAliases","hostIPC","hostNetwork","hostPID","hostname","imagePullSecrets","initContainers","nodeName","nodeSelector","os","overhead","preemptionPolicy","priority","priorityClassName","readinessGates","restartPolicy","runtimeClassName","schedulerName","securityContext","serviceAccount","serviceAccountName","setHostnameAsFQDN","shareProcessNamespace","subdomain","terminationGracePeriodSeconds","tolerations","topologySpreadConstraints","volumes"})
@com.fasterxml.jackson.databind.annotation.JsonDeserialize(using = com.fasterxml.jackson.databind.JsonDeserializer.None.class)
@javax.annotation.processing.Generated("io.fabric8.java.generator.CRGeneratorRunner")
public class Spec implements io.fabric8.kubernetes.api.model.KubernetesResource {

    @com.fasterxml.jackson.annotation.JsonProperty("activeDeadlineSeconds")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private Long activeDeadlineSeconds;

    public Long getActiveDeadlineSeconds() {
        return activeDeadlineSeconds;
    }

    public void setActiveDeadlineSeconds(Long activeDeadlineSeconds) {
        this.activeDeadlineSeconds = activeDeadlineSeconds;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("affinity")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private org.kubeflow.v1.notebookspec.template.spec.Affinity affinity;

    public org.kubeflow.v1.notebookspec.template.spec.Affinity getAffinity() {
        return affinity;
    }

    public void setAffinity(org.kubeflow.v1.notebookspec.template.spec.Affinity affinity) {
        this.affinity = affinity;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("automountServiceAccountToken")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private Boolean automountServiceAccountToken;

    public Boolean getAutomountServiceAccountToken() {
        return automountServiceAccountToken;
    }

    public void setAutomountServiceAccountToken(Boolean automountServiceAccountToken) {
        this.automountServiceAccountToken = automountServiceAccountToken;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("containers")
    @io.fabric8.generator.annotation.Required()
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private java.util.List<org.kubeflow.v1.notebookspec.template.spec.Containers> containers;

    public java.util.List<org.kubeflow.v1.notebookspec.template.spec.Containers> getContainers() {
        return containers;
    }

    public void setContainers(java.util.List<org.kubeflow.v1.notebookspec.template.spec.Containers> containers) {
        this.containers = containers;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("dnsConfig")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private org.kubeflow.v1.notebookspec.template.spec.DnsConfig dnsConfig;

    public org.kubeflow.v1.notebookspec.template.spec.DnsConfig getDnsConfig() {
        return dnsConfig;
    }

    public void setDnsConfig(org.kubeflow.v1.notebookspec.template.spec.DnsConfig dnsConfig) {
        this.dnsConfig = dnsConfig;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("dnsPolicy")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private String dnsPolicy;

    public String getDnsPolicy() {
        return dnsPolicy;
    }

    public void setDnsPolicy(String dnsPolicy) {
        this.dnsPolicy = dnsPolicy;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("enableServiceLinks")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private Boolean enableServiceLinks;

    public Boolean getEnableServiceLinks() {
        return enableServiceLinks;
    }

    public void setEnableServiceLinks(Boolean enableServiceLinks) {
        this.enableServiceLinks = enableServiceLinks;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("ephemeralContainers")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private java.util.List<org.kubeflow.v1.notebookspec.template.spec.EphemeralContainers> ephemeralContainers;

    public java.util.List<org.kubeflow.v1.notebookspec.template.spec.EphemeralContainers> getEphemeralContainers() {
        return ephemeralContainers;
    }

    public void setEphemeralContainers(java.util.List<org.kubeflow.v1.notebookspec.template.spec.EphemeralContainers> ephemeralContainers) {
        this.ephemeralContainers = ephemeralContainers;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("hostAliases")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private java.util.List<org.kubeflow.v1.notebookspec.template.spec.HostAliases> hostAliases;

    public java.util.List<org.kubeflow.v1.notebookspec.template.spec.HostAliases> getHostAliases() {
        return hostAliases;
    }

    public void setHostAliases(java.util.List<org.kubeflow.v1.notebookspec.template.spec.HostAliases> hostAliases) {
        this.hostAliases = hostAliases;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("hostIPC")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private Boolean hostIPC;

    public Boolean getHostIPC() {
        return hostIPC;
    }

    public void setHostIPC(Boolean hostIPC) {
        this.hostIPC = hostIPC;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("hostNetwork")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private Boolean hostNetwork;

    public Boolean getHostNetwork() {
        return hostNetwork;
    }

    public void setHostNetwork(Boolean hostNetwork) {
        this.hostNetwork = hostNetwork;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("hostPID")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private Boolean hostPID;

    public Boolean getHostPID() {
        return hostPID;
    }

    public void setHostPID(Boolean hostPID) {
        this.hostPID = hostPID;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("hostname")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private String hostname;

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("imagePullSecrets")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private java.util.List<org.kubeflow.v1.notebookspec.template.spec.ImagePullSecrets> imagePullSecrets;

    public java.util.List<org.kubeflow.v1.notebookspec.template.spec.ImagePullSecrets> getImagePullSecrets() {
        return imagePullSecrets;
    }

    public void setImagePullSecrets(java.util.List<org.kubeflow.v1.notebookspec.template.spec.ImagePullSecrets> imagePullSecrets) {
        this.imagePullSecrets = imagePullSecrets;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("initContainers")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private java.util.List<org.kubeflow.v1.notebookspec.template.spec.InitContainers> initContainers;

    public java.util.List<org.kubeflow.v1.notebookspec.template.spec.InitContainers> getInitContainers() {
        return initContainers;
    }

    public void setInitContainers(java.util.List<org.kubeflow.v1.notebookspec.template.spec.InitContainers> initContainers) {
        this.initContainers = initContainers;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("nodeName")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private String nodeName;

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("nodeSelector")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private java.util.Map<java.lang.String, String> nodeSelector;

    public java.util.Map<java.lang.String, String> getNodeSelector() {
        return nodeSelector;
    }

    public void setNodeSelector(java.util.Map<java.lang.String, String> nodeSelector) {
        this.nodeSelector = nodeSelector;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("os")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private org.kubeflow.v1.notebookspec.template.spec.Os os;

    public org.kubeflow.v1.notebookspec.template.spec.Os getOs() {
        return os;
    }

    public void setOs(org.kubeflow.v1.notebookspec.template.spec.Os os) {
        this.os = os;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("overhead")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private java.util.Map<java.lang.String, io.fabric8.kubernetes.api.model.IntOrString> overhead;

    public java.util.Map<java.lang.String, io.fabric8.kubernetes.api.model.IntOrString> getOverhead() {
        return overhead;
    }

    public void setOverhead(java.util.Map<java.lang.String, io.fabric8.kubernetes.api.model.IntOrString> overhead) {
        this.overhead = overhead;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("preemptionPolicy")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private String preemptionPolicy;

    public String getPreemptionPolicy() {
        return preemptionPolicy;
    }

    public void setPreemptionPolicy(String preemptionPolicy) {
        this.preemptionPolicy = preemptionPolicy;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("priority")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private Integer priority;

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("priorityClassName")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private String priorityClassName;

    public String getPriorityClassName() {
        return priorityClassName;
    }

    public void setPriorityClassName(String priorityClassName) {
        this.priorityClassName = priorityClassName;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("readinessGates")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private java.util.List<org.kubeflow.v1.notebookspec.template.spec.ReadinessGates> readinessGates;

    public java.util.List<org.kubeflow.v1.notebookspec.template.spec.ReadinessGates> getReadinessGates() {
        return readinessGates;
    }

    public void setReadinessGates(java.util.List<org.kubeflow.v1.notebookspec.template.spec.ReadinessGates> readinessGates) {
        this.readinessGates = readinessGates;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("restartPolicy")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private String restartPolicy;

    public String getRestartPolicy() {
        return restartPolicy;
    }

    public void setRestartPolicy(String restartPolicy) {
        this.restartPolicy = restartPolicy;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("runtimeClassName")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private String runtimeClassName;

    public String getRuntimeClassName() {
        return runtimeClassName;
    }

    public void setRuntimeClassName(String runtimeClassName) {
        this.runtimeClassName = runtimeClassName;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("schedulerName")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private String schedulerName;

    public String getSchedulerName() {
        return schedulerName;
    }

    public void setSchedulerName(String schedulerName) {
        this.schedulerName = schedulerName;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("securityContext")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private org.kubeflow.v1.notebookspec.template.spec.SecurityContext securityContext;

    public org.kubeflow.v1.notebookspec.template.spec.SecurityContext getSecurityContext() {
        return securityContext;
    }

    public void setSecurityContext(org.kubeflow.v1.notebookspec.template.spec.SecurityContext securityContext) {
        this.securityContext = securityContext;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("serviceAccount")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private String serviceAccount;

    public String getServiceAccount() {
        return serviceAccount;
    }

    public void setServiceAccount(String serviceAccount) {
        this.serviceAccount = serviceAccount;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("serviceAccountName")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private String serviceAccountName;

    public String getServiceAccountName() {
        return serviceAccountName;
    }

    public void setServiceAccountName(String serviceAccountName) {
        this.serviceAccountName = serviceAccountName;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("setHostnameAsFQDN")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private Boolean setHostnameAsFQDN;

    public Boolean getSetHostnameAsFQDN() {
        return setHostnameAsFQDN;
    }

    public void setSetHostnameAsFQDN(Boolean setHostnameAsFQDN) {
        this.setHostnameAsFQDN = setHostnameAsFQDN;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("shareProcessNamespace")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private Boolean shareProcessNamespace;

    public Boolean getShareProcessNamespace() {
        return shareProcessNamespace;
    }

    public void setShareProcessNamespace(Boolean shareProcessNamespace) {
        this.shareProcessNamespace = shareProcessNamespace;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("subdomain")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private String subdomain;

    public String getSubdomain() {
        return subdomain;
    }

    public void setSubdomain(String subdomain) {
        this.subdomain = subdomain;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("terminationGracePeriodSeconds")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private Long terminationGracePeriodSeconds;

    public Long getTerminationGracePeriodSeconds() {
        return terminationGracePeriodSeconds;
    }

    public void setTerminationGracePeriodSeconds(Long terminationGracePeriodSeconds) {
        this.terminationGracePeriodSeconds = terminationGracePeriodSeconds;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("tolerations")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private java.util.List<org.kubeflow.v1.notebookspec.template.spec.Tolerations> tolerations;

    public java.util.List<org.kubeflow.v1.notebookspec.template.spec.Tolerations> getTolerations() {
        return tolerations;
    }

    public void setTolerations(java.util.List<org.kubeflow.v1.notebookspec.template.spec.Tolerations> tolerations) {
        this.tolerations = tolerations;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("topologySpreadConstraints")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private java.util.List<org.kubeflow.v1.notebookspec.template.spec.TopologySpreadConstraints> topologySpreadConstraints;

    public java.util.List<org.kubeflow.v1.notebookspec.template.spec.TopologySpreadConstraints> getTopologySpreadConstraints() {
        return topologySpreadConstraints;
    }

    public void setTopologySpreadConstraints(java.util.List<org.kubeflow.v1.notebookspec.template.spec.TopologySpreadConstraints> topologySpreadConstraints) {
        this.topologySpreadConstraints = topologySpreadConstraints;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("volumes")
    @com.fasterxml.jackson.annotation.JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private java.util.List<org.kubeflow.v1.notebookspec.template.spec.Volumes> volumes;

    public java.util.List<org.kubeflow.v1.notebookspec.template.spec.Volumes> getVolumes() {
        return volumes;
    }

    public void setVolumes(java.util.List<org.kubeflow.v1.notebookspec.template.spec.Volumes> volumes) {
        this.volumes = volumes;
    }
}

