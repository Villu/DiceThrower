package net.sepman.dice.repository;

import net.sepman.dice.domain.DiceThrow;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = DiceThrow.class)
public interface ThrowRepository {
}
