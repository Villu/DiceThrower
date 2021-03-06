// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package net.sepman.dice.service;

import java.util.List;
import net.sepman.dice.domain.Die;
import net.sepman.dice.repository.DiceRepository;
import net.sepman.dice.service.DiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect DiceServiceImpl_Roo_Service {
    
    declare @type: DiceServiceImpl: @Service;
    
    declare @type: DiceServiceImpl: @Transactional;
    
    @Autowired
    DiceRepository DiceServiceImpl.diceRepository;
    
    public long DiceServiceImpl.countAllDies() {
        return diceRepository.count();
    }
    
    public void DiceServiceImpl.deleteDie(Die die) {
        diceRepository.delete(die);
    }
    
    public Die DiceServiceImpl.findDie(Long id) {
        return diceRepository.findOne(id);
    }
    
    public List<Die> DiceServiceImpl.findAllDies() {
        return diceRepository.findAll();
    }
    
    public List<Die> DiceServiceImpl.findDieEntries(int firstResult, int maxResults) {
        return diceRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }
    
    public void DiceServiceImpl.saveDie(Die die) {
        diceRepository.save(die);
    }
    
    public Die DiceServiceImpl.updateDie(Die die) {
        return diceRepository.save(die);
    }
    
}
