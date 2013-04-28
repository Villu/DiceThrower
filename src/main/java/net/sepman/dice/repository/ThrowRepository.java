package net.sepman.dice.repository;

import java.util.List;

import net.sepman.dice.domain.DiceThrow;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = DiceThrow.class)
public interface ThrowRepository {
	
	List<DiceThrow> findByCode(String code);
	
	List<DiceThrow> findByCode(PageRequest pageRequest, String code);
	
	@Query("select count(p) from DiceThrow p where p.code = ?1")
	long countByCode(String code);
}
