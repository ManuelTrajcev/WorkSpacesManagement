package mk.ukim.finki.wp.workspaces.repository;

import mk.ukim.finki.wp.workspaces.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

