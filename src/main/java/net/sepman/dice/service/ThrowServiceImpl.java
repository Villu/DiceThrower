package net.sepman.dice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import net.sepman.dice.domain.DiceThrow;
import net.sepman.dice.domain.Die;
import net.sepman.dice.repository.ThrowRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class ThrowServiceImpl implements ThrowService {
	static Logger log = LoggerFactory.getLogger(ThrowServiceImpl.class.getName());
	
    public void saveDiceThrow(DiceThrow diceThrow) {
    	
    	List<Die> dice = calculate(diceThrow.getCommand());
    	diceThrow.setDiceThrows(dice);
    	diceThrow.setThrowTime(new Date());
        throwRepository.save(diceThrow);
    }
    
    private List<Die> calculate(String command){
    	if(command == null || "".equals(command)){
    		return null;
    	}
    	List<Die> dice = new ArrayList<Die>();
    	
    	int amount=1;
    	int sides=6;
    	int explodes=0;
    	
    	try {
    		//first is the amount of dice
    		char c = command.charAt(0);
    		if(Character.isDigit(c)){
    			log.debug("Is digit:" + c);
    			amount = Character.getNumericValue(command.charAt(0));
    		}
    		//number of sides indicator followed by number of sides
    		int sideFound = command.indexOf("b");
    		if(sideFound>-1){
    			log.debug("Found sides at:" + sideFound + ", length:" + command.length());
    			if(sideFound+1<command.length()){
    				char sideChar = command.charAt(sideFound+1);
    				log.debug("Found side char:"+sideChar);
    				if(Character.isDigit(sideChar)){
        				log.debug("Side is digit");
        				sides=Character.getNumericValue(sideChar);
    				}
    			}
    		}
    		log.debug("Sides:" + sides);
    		//explodes
    		int explodesFound = command.indexOf("m");
    		if(explodesFound>-1){
    			log.debug("Found explodes at:" + explodesFound + ", length:" + command.length());
    			if(explodesFound+1<command.length()){
    				char explodesChar = command.charAt(explodesFound+1);
    				log.debug("Found explodes char:" + explodesChar);
    				if(Character.isDigit(explodesChar)){
        				log.debug("Explodes is digit.");
        				explodes=Character.getNumericValue(explodesChar);
    				}
    			}
    		}
    		log.debug("Explodes:" + explodes);
    		
		} catch (Exception e) {
			throw new RuntimeException("Code has to start with a digit.");
		}
    	
    	log.debug("Throwing:" + amount);
    	for (int i = 0; i < amount; i++) {
    		Die die = (new Die(sides)).throwDie();
    		dice.add(die);
    		log.debug("Threw:" + die.getThrowResult());
    		if(explodes>0){
    			while(die.getThrowResult()>=explodes){
    				die = (new Die(sides)).throwDie();
    				log.debug("Exploded! New throw:"+die.getThrowResult());
    				dice.add(die);
    			}
    		}
			
		}
    	
    	return dice;
    }
    
    
}
