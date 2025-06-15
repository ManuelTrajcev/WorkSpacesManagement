package mk.ukim.finki.wp.workspaces.service.domain;

import mk.ukim.finki.wp.workspaces.dto.CreateWorkspaceDto;
import mk.ukim.finki.wp.workspaces.dto.DisplayWorkspaceDto;
import mk.ukim.finki.wp.workspaces.model.domain.User;
import mk.ukim.finki.wp.workspaces.model.domain.UserWorkspace;
import mk.ukim.finki.wp.workspaces.model.domain.Workspace;
import mk.ukim.finki.wp.workspaces.model.enumerations.Role;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface WorkspaceService {
    List<Workspace> findAll();

    Optional<Workspace> openWorkspace(Long workspaceId, Long userId);

    Optional<Workspace> editWorkspace(Long workspaceId, Long userId);

    Optional<Workspace> findById(Long workspaceId);

    List<UserWorkspace> findAllPerUser(Long userId);

}
