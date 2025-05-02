package org.instantai.api.service;

import org.instantai.api.service.impl.ResourcesServiceImpl;

import java.util.List;

public interface ResourcesService {
    List<ResourcesServiceImpl.NodeUsage> getNodeMetrics();
}
