package mk.ukim.finki.wp.workspaces.service.application.impl;

import mk.ukim.finki.wp.workspaces.dto.DisplayWorkspaceDto;
import mk.ukim.finki.wp.workspaces.service.application.WorkspaceApplicationService;
import mk.ukim.finki.wp.workspaces.service.domain.WorkspaceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.stream;

@Service
public class WorkspaceApplicationServiceImpl implements WorkspaceApplicationService {
    private final WorkspaceService workspaceService;

    public WorkspaceApplicationServiceImpl(WorkspaceService workspaceService) {
        this.workspaceService = workspaceService;
    }

    @Override
    public List<DisplayWorkspaceDto> findAll() {
        return workspaceService.findAll()
                .stream()
                .map(DisplayWorkspaceDto::from)
                .toList();
    }

    @Override
    public Optional<DisplayWorkspaceDto> openWorkspace(Long id, Long userId) {
       return Optional.ofNullable(workspaceService.openWorkspace(id, userId)
               .map(DisplayWorkspaceDto::from)
               .orElseThrow(() -> new RuntimeException("Workspace not found")));
    }
}
