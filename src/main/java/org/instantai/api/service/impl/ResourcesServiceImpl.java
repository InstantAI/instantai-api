package org.instantai.api.service.impl;

import io.fabric8.kubernetes.api.model.Quantity;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.instantai.api.service.ResourcesService;
import org.springframework.stereotype.Service;
import io.fabric8.kubernetes.api.model.metrics.v1beta1.NodeMetrics;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ResourcesServiceImpl implements ResourcesService {
    private static final String CPU = "cpu";
    private static final String MEMORY = "memory";

    @Override
    public List<NodeUsage> getNodeMetrics() {
        try (KubernetesClient client = new KubernetesClientBuilder().build()) {

            if (!client.supports(NodeMetrics.class)) {
                log.warn("Metrics API not supported by the cluster");
                return List.of();
            }
            List<NodeMetrics> nodeMetricsList = client.top().nodes().metrics().getItems();


            return nodeMetricsList.stream().map(metrics -> {
                String nodeName = metrics.getMetadata().getName();
                Map<String, Quantity> usage = metrics.getUsage();
                Map<String, Quantity> capacity = client.nodes()
                        .withName(nodeName)
                        .get()
                        .getStatus()
                        .getCapacity();

                // 计算 CPU 使用率
                int cpuUsageMillicores = parseCpuToMillicores(usage.get(CPU));
                log.info("cpu usage: {}", cpuUsageMillicores);
                int cpuTotalMillicores = parseCpuToMillicores(capacity.get(CPU));
                log.info("cpu total: {}", cpuTotalMillicores);
                int cpuPercent = safePercentage(cpuUsageMillicores, cpuTotalMillicores);

                // 计算内存使用率
                long memoryUsageBytes = parseMemoryToBytes(usage.get(MEMORY));
                long memoryTotalBytes = parseMemoryToBytes(capacity.get(MEMORY));
                int memoryPercent = safePercentage(memoryUsageBytes, memoryTotalBytes);

                return new NodeUsage(nodeName, cpuPercent, memoryPercent);
            }).collect(Collectors.toList());

        } catch (Exception e) {
            log.error("Failed to fetch node metrics", e);
            return List.of();
        }
    }


    private int parseCpuToMillicores(Quantity quantity) {
        if (quantity == null || quantity.getNumericalAmount() == null) return 0;

        BigDecimal number = quantity.getNumericalAmount();
        String amount = quantity.getAmount();

        if (amount.endsWith("m")) {
            return number.intValue(); // already millicores
        } else {
            return number.multiply(BigDecimal.valueOf(1000)).intValue(); // convert cores to millicores
        }
    }

    private long parseMemoryToBytes(Quantity quantity) {
        String value = quantity.getAmount().toUpperCase();
        if (value.endsWith("KI")) {
            return new BigDecimal(value.replace("KI", "")).multiply(BigDecimal.valueOf(1024)).longValue();
        } else if (value.endsWith("MI")) {
            return new BigDecimal(value.replace("MI", "")).multiply(BigDecimal.valueOf(1024 * 1024)).longValue();
        } else if (value.endsWith("GI")) {
            return new BigDecimal(value.replace("GI", "")).multiply(BigDecimal.valueOf(1024 * 1024 * 1024)).longValue();
        } else {
            return new BigDecimal(value).longValue(); // raw bytes or other
        }
    }
    private int safePercentage(long used, long total) {
        if (total == 0) return 0;
        return (int) ((double) used / total * 100);
    }

    @Data
    public static class NodeUsage {
        private final String nodeName;
        private final int cpuPercent;
        private final int memoryPercent;
    }
}
