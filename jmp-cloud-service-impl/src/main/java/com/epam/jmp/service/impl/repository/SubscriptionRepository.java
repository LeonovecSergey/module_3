package com.epam.jmp.service.impl.repository;

import com.epam.jmp.service.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(exported = false)
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}
