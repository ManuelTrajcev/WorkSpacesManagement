package mk.ukim.finki.wp.workspaces.repository;
import aj.org.objectweb.asm.commons.Remapper;
import mk.ukim.finki.wp.workspaces.model.domain.UserWorkspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserWorkspaceRepository extends JpaRepository<UserWorkspace, Long> {
    Optional<UserWorkspace> findByWorkspaceIdAndUserId(Long workspaceId, Long userId);

    Optional<UserWorkspace> findByUserIdAndWorkspaceId(Long userId, Long workspaceId);

    List<UserWorkspace> findAllByUserId(Long userId);
}
