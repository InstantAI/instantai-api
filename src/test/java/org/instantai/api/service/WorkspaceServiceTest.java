package org.instantai.api.service;

import org.instantai.api.model.Workspace;
import org.instantai.api.repository.WorkspaceRepository;
import org.instantai.api.service.impl.WorkspaceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class WorkspaceServiceTest {
    @Mock
    private WorkspaceRepository workspaceRepository;

    @InjectMocks
    private WorkspaceServiceImpl workspaceService;

    private final String testName = "test-workspace";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAndDeleteWorkspace() {
        Workspace workspace = Workspace.builder()
                .name(testName)
                .cpuLimit("500m")
                .memoryLimit("512Mi")
                .gpuLimit(0)
                .isNewEntry(true)
                .build();

        // Mock：创建
        when(workspaceRepository.existsById(testName)).thenReturn(Mono.just(false));
        when(workspaceRepository.save(any())).thenReturn(Mono.just(workspace));
        when(workspaceRepository.findById(testName)).thenReturn(Mono.just(workspace));
        when(workspaceRepository.deleteById(testName)).thenReturn(Mono.empty());

        StepVerifier.create(workspaceService.createWorkspace(workspace))
                .expectNextMatches(w -> w.getName().equals(testName))
                .verifyComplete();

        StepVerifier.create(workspaceService.deleteWorkspace(testName))
                .verifyComplete();

        // Verify 方法调用次数
        verify(workspaceRepository).save(any());
        verify(workspaceRepository).deleteById(testName);
    }
}