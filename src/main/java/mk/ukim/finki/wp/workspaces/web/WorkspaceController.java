package mk.ukim.finki.wp.workspaces.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.wp.workspaces.dto.DisplayWorkspaceDto;
import mk.ukim.finki.wp.workspaces.dto.EditWorkspaceDto;
import mk.ukim.finki.wp.workspaces.dto.WorkspaceWithRoleDto;
import mk.ukim.finki.wp.workspaces.model.domain.User;
import mk.ukim.finki.wp.workspaces.model.exceptions.AccessDeniedException;
import mk.ukim.finki.wp.workspaces.service.application.WorkspaceApplicationService;
import mk.ukim.finki.wp.workspaces.service.application.impl.WorkspaceApplicationServiceImpl;
import mk.ukim.finki.wp.workspaces.service.domain.WorkspaceService;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workspace")
@Tag(name = "Workspace API", description = "Endpoints for accessing workspaces")
public class WorkspaceController {
    private final WorkspaceApplicationService workspaceApplicationService;

    public WorkspaceController(WorkspaceApplicationService workspaceApplicationService) {
        this.workspaceApplicationService = workspaceApplicationService;
    }

    @Operation(summary = "Get all workspaces", description = "Retrieves a list of all workspace.")
    @GetMapping
    public List<DisplayWorkspaceDto> findAll() {
        return workspaceApplicationService.findAll();
    }

    @Operation(summary = "Get my workspaces", description = "Retrieves a list of all of my workspaces.")
    @GetMapping("/my-workspaces")
    public List<WorkspaceWithRoleDto> findMyWorkspaces() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User loggedInUser = (User) authentication.getPrincipal();

        return workspaceApplicationService.findAllPerUser(loggedInUser.getId());
    }

    @Operation(summary = "Access a workspace", description = "Access one workspace.")
    @PreAuthorize("@workspacePermissionEvaluator.hasAccess(authentication, #id, null)")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayWorkspaceDto> accessWorkspace(
            @PathVariable Long id,
            @RequestHeader(value = "X-Workspace-Id", required = false) Long workspaceIdHeader) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User loggedInUser = (User) authentication.getPrincipal();

        if (workspaceIdHeader != null) {
            System.out.println("X-Workspace-Id received: " + workspaceIdHeader);
        } else {
            System.out.println("X-Workspace-Id header is missing");
        }

        return workspaceApplicationService.openWorkspace(id, loggedInUser.getId())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Edit a workspace", description = "Edit one workspace.")
    @PreAuthorize("@workspacePermissionEvaluator.hasAccess(authentication, #id, 'ROLE_ADMIN')")
    @GetMapping("/edit/{id}")
    public ResponseEntity<EditWorkspaceDto> editWorkspace(@PathVariable Long id,
                                                          @RequestHeader(value = "X-Workspace-Id", required = false) Long workspaceIdHeader
    ) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User loggedInUser = (User) authentication.getPrincipal();

        if (workspaceIdHeader != null) {
            System.out.println("X-Workspace-Id received: " + workspaceIdHeader);
        } else {
            System.out.println("X-Workspace-Id header is missing");
        }

        return workspaceApplicationService.editWorkspace(id, loggedInUser.getId())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
