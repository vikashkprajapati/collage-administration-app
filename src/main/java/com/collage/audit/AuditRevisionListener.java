package com.collage.audit;


import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditRevisionListener implements RevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {

        String currentUser = Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(com.collage.security.UserPrincipal.class::cast)
                .map(com. collage.security.UserPrincipal::getUser)
                .map(com.collage.model.User::getEmail)
                .orElse("Unknown-User");

        AuditRevisionEntity audit = (AuditRevisionEntity) revisionEntity;
        audit.setUser(currentUser);

    }
}
