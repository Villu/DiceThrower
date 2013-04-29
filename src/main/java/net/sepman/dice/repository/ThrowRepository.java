package net.sepman.dice.repository;

import java.util.List;

import javax.persistence.OrderBy;

import net.sepman.dice.domain.DiceThrow;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = DiceThrow.class)
public interface ThrowRepository {
	
	@OrderBy("throwTime")
	List<DiceThrow> findByCodeOrderByThrowTimeDesc(String code);
	
	@OrderBy("throwTime")
	List<DiceThrow> findByCodeOrderByThrowTimeDesc(PageRequest pageRequest, String code);
	
	@Query("select count(p) from DiceThrow p where p.code = ?1")
	long countByCode(String code);
}
