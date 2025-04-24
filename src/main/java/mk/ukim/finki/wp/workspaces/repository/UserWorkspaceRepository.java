package mk.ukim.finki.wp.workspaces.repository;
import mk.ukim.finki.wp.workspaces.model.domain.UserWorkspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserWorkspaceRepository extends JpaRepository<UserWorkspace, Long> {
    UserWorkspace findByUserIdAndWorkspaceId(Long userId, Long workspaceId);
}
