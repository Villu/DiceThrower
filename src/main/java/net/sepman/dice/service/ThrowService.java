package net.sepman.dice.service;

import java.util.List;

import net.sepman.dice.domain.DiceThrow;
import net.sepman.dice.repository.ThrowRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.layers.service.RooService;



@RooService(domainTypes = { net.sepman.dice.domain.DiceThrow.class })
public interface ThrowService {
	
	List<DiceThrow> findByCode(String code);
	List<DiceThrow> findDiceThrowEntriesByCode(int firstResult, int maxResults, String code);
	long countAllDiceThrowsByCode(String code);
}
