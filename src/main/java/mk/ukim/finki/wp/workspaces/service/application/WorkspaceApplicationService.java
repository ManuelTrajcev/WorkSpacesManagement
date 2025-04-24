package mk.ukim.finki.wp.workspaces.service.application;

import mk.ukim.finki.wp.workspaces.dto.CreateWorkspaceDto;
import mk.ukim.finki.wp.workspaces.dto.DisplayWorkspaceDto;
import mk.ukim.finki.wp.workspaces.model.domain.User;

import java.util.List;
import java.util.Optional;

public interface WorkspaceApplicationService {
    List<DisplayWorkspaceDto> findAll();
    Optional<DisplayWorkspaceDto> openWorkspace(Long id, Long userId);
}
