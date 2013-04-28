package net.sepman.dice.repository;

import net.sepman.dice.domain.Die;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Die.class)
public interface DiceRepository {
}
