// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package net.sepman.dice.service;

import java.util.List;
import net.sepman.dice.domain.Die;
import net.sepman.dice.service.DiceService;

privileged aspect DiceService_Roo_Service {
    
    public abstract long DiceService.countAllDies();    
    public abstract void DiceService.deleteDie(Die die);    
    public abstract Die DiceService.findDie(Long id);    
    public abstract List<Die> DiceService.findAllDies();    
    public abstract List<Die> DiceService.findDieEntries(int firstResult, int maxResults);    
    public abstract void DiceService.saveDie(Die die);    
    public abstract Die DiceService.updateDie(Die die);    
}
