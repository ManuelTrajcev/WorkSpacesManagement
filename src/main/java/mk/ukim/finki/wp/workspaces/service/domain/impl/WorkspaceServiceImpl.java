package mk.ukim.finki.wp.workspaces.service.domain.impl;

import mk.ukim.finki.wp.workspaces.model.domain.UserWorkspace;
import mk.ukim.finki.wp.workspaces.model.domain.Workspace;
import mk.ukim.finki.wp.workspaces.repository.UserWorkspaceRepository;
import mk.ukim.finki.wp.workspaces.repository.WorkspaceRepository;
import mk.ukim.finki.wp.workspaces.service.domain.WorkspaceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkspaceServiceImpl implements WorkspaceService {
    private final WorkspaceRepository workspaceRepository;
    private final UserWorkspaceRepository userWorkspaceRepository;

    public WorkspaceServiceImpl(WorkspaceRepository workspaceRepository, UserWorkspaceRepository userWorkspaceRepository) {
        this.workspaceRepository = workspaceRepository;
        this.userWorkspaceRepository = userWorkspaceRepository;
    }

    @Override
    public List<Workspace> findAll() {
        return workspaceRepository.findAll();
    }

    @Override
    public Optional<Workspace> openWorkspace(Long workspaceId, Long userId) {
        Optional<UserWorkspace> userWorkspace = userWorkspaceRepository.findByWorkspaceIdAndUserId(workspaceId, userId);
        if (userWorkspace.isEmpty()) {
            throw new IllegalArgumentException("Workspace not found");
        }
        Optional<Workspace> workspace = workspaceRepository.findById(userWorkspace.get().getWorkspace().getId());
        return workspace;
    }

    @Override
    public Optional<Workspace> editWorkspace(Long workspaceId, Long userId) {
        Optional<UserWorkspace> userWorkspace = userWorkspaceRepository.findByWorkspaceIdAndUserId(workspaceId, userId);
        if (userWorkspace.isEmpty()) {
            throw new IllegalArgumentException("Workspace not found");
        }
        Optional<Workspace> workspace = workspaceRepository.findById(userWorkspace.get().getWorkspace().getId());
        return workspace;
    }

    @Override
    public Optional<Workspace> findById(Long workspaceId) {
        return workspaceRepository.findById(workspaceId);
    }

    @Override
    public List<UserWorkspace> findAllPerUser(Long userId) {

        return userWorkspaceRepository.findAllByUserId(userId);
    }
}
