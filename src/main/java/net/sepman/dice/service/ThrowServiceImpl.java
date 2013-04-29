package net.sepman.dice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sepman.dice.domain.DiceThrow;
import net.sepman.dice.domain.Die;
import net.sepman.dice.repository.ThrowRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class ThrowServiceImpl implements ThrowService {
	static Logger log = LoggerFactory.getLogger(ThrowServiceImpl.class.getName());
	
	public List<DiceThrow> findByCode(String code){
		return throwRepository.findByCode(code);
	}
	
    public List<DiceThrow> findDiceThrowEntriesByCode(int firstResult, int maxResults, String code) {
        return throwRepository.findByCode(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults), code);
    }
    
    public long countAllDiceThrowsByCode(String code) {
        return throwRepository.countByCode(code);
    }
	
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
    	
    	Random random = new Random();
    	
    	try {
    		
    		Pattern pattern = Pattern.compile("(\\d+)");
    		
    		//first is the amount of dice
    		char c = command.charAt(0);
    		if(Character.isDigit(c)){
    			log.debug("Is digit:" + c);
    			Matcher m = pattern.matcher(command);
    			m.find();
    			amount = Integer.parseInt(m.group(1));
    		}
    		
    		sides = findFirstNumberAfterAChar(command, 'b', sides);
    		sides = findFirstNumberAfterAChar(command, 'd', sides);
    		explodes = findFirstNumberAfterAChar(command, 'm', explodes);
    		
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Code has to start with a digit.");
		}
    	
    	log.debug("Throwing:" + amount);
    	for (int i = 0; i < amount; i++) {
    		Die die = (new Die(sides)).throwDie(random);
    		dice.add(die);
    		log.debug("Threw:" + die.getThrowResult());
    		if(explodes>0){
    			int exploded = 0;
    			while(die.getThrowResult()>=explodes){
    				die = (new Die(sides)).throwDie(random);
    				die.setExploded(++exploded);
    				log.debug("Exploded! New throw:"+die.getThrowResult());
    				dice.add(die);
    			}
    		}
			
		}
    	
    	return dice;
    }
    
    private int findFirstNumberAfterAChar(String command, char lookFor, int defaultNumber){
		Pattern pattern = Pattern.compile("(\\d+)");
		int number = defaultNumber;
		//explodes
		int numberFound = command.indexOf(lookFor);
		if(numberFound>-1){
			log.debug("Found number at:" + numberFound + ", length:" + command.length());
			if(numberFound+1<command.length()){
				char c = command.charAt(numberFound+1);
				log.debug("Found char:" + c);
				if(Character.isDigit(c)){
    				log.debug("Char is digit.");
        			Matcher m = pattern.matcher(command.substring(numberFound+1));
        			m.find();
        			number = Integer.parseInt(m.group(1));
				}
			}
		}
		log.debug("Number:" + number);
		return number;
    }
    
}
