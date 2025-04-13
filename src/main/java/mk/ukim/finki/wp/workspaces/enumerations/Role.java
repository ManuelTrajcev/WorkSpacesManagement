package mk.ukim.finki.wp.workspaces.enumerations;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN, VISITOR;

    @Override
    public String getAuthority() {
        return name();
    }
}
