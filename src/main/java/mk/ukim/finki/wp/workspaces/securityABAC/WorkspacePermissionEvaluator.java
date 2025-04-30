package mk.ukim.finki.wp.workspaces.securityABAC;

import mk.ukim.finki.wp.workspaces.model.context.WorkspaceContext;
import mk.ukim.finki.wp.workspaces.model.domain.User;
import mk.ukim.finki.wp.workspaces.model.domain.UserWorkspace;
import mk.ukim.finki.wp.workspaces.repository.UserWorkspaceRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class WorkspacePermissionEvaluator {

    private final UserWorkspaceRepository userWorkspaceRepository;

    public WorkspacePermissionEvaluator(UserWorkspaceRepository userWorkspaceRepository) {
        this.userWorkspaceRepository = userWorkspaceRepository;
    }

    public boolean hasAccess(Authentication auth, Long unused, String requiredRole) {
        Long workspaceId = WorkspaceContext.getCurrentWorkspaceId();
        if (auth == null || workspaceId == null) return false;

        Object principal = auth.getPrincipal();
        Long userId;

        if (principal instanceof User) {
            userId = ((User) principal).getId();
        } else {
            try {
                userId = Long.parseLong(auth.getName());
            } catch (NumberFormatException e) {
                return false;
            }
        }

        return userWorkspaceRepository
                .findByUserIdAndWorkspaceId(userId, workspaceId)
                .map(uw -> requiredRole == null || uw.getRoleString().equalsIgnoreCase(requiredRole))
                .orElse(false);
    }

}
