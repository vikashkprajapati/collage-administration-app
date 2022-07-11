package com.collage.audit;


import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuditorAwareImpl implements AuditorAware<Long> {


    private com.collage.model.User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && !auth.getName().equalsIgnoreCase("anonymousUser")) {
            return ((com.collage.security.UserPrincipal) auth.getPrincipal()).getUser();
        } else {
            return null;
        }
    }

    @NotNull
    @Override
    public Optional<Long> getCurrentAuditor() {
        com.collage.model.User currentUser = getCurrentUser();
        if (currentUser == null) {
            return Optional.of(1L);
        }
        return Optional.of(currentUser.getId());
    }

}