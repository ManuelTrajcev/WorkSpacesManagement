package mk.ukim.finki.wp.workspaces.repository;
import mk.ukim.finki.wp.workspaces.model.UserWorkspace;
import mk.ukim.finki.wp.workspaces.model.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserWorkspaceRepository extends JpaRepository<UserWorkspace, Long> {
}
