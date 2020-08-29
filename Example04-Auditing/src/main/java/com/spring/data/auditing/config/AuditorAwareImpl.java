package com.spring.data.auditing.config;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
            return Optional.of("Admin");
    }

//    @Override
//    public Optional<String> getCurrentAuditor() {
//        if (Objects.nonNull(SecurityContextHolder.getContext().getAuthentication()) &&
//                SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User) {
//            return Optional.of(
//                    ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAccessToken().getPayload().getPreferredUsername());
//        }
//
//        return Optional.empty();
//    }
}
