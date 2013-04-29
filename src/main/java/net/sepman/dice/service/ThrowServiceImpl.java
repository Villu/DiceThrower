package net.sepman.dice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
    	ThrowHolder throwHolder = calculate(diceThrow.getCommand());
    	List<Die> dice = throwHolder.dice;
    	diceThrow.setDiceThrows(dice);
    	diceThrow.setHits(throwHolder.hits);
    	diceThrow.setHits(throwHolder.hits);
    	diceThrow.setThrowTime(new Date());
        throwRepository.save(diceThrow);
    }
    
    private ThrowHolder calculate(String command){
    	if(command == null || "".equals(command)){
    		return null;
    	}
    	List<Die> dice = new ArrayList<Die>();
    	
    	int amount=1;
    	int sides=-1;
    	int explodes=0;
    	int hit=0;
    	
    	Random random = new Random();
    	
    	try {
    		
    		Pattern pattern = Pattern.compile("(\\d+)");
    		
    		//first is the amount of dice
    		char c = command.charAt(0);
    		if(Character.isDigit(c)){
    			log.info("Is digit:" + c);
    			Matcher m = pattern.matcher(command);
    			m.find();
    			amount = Integer.parseInt(m.group(1));
    		}
    		
    		sides = findFirstNumberAfterAChar(command, 'b', 6);
    		if(sides!=-1){
    			//found b
    			explodes=6;
    		}else{
    			sides = findFirstNumberAfterAChar(command, 'd', 6);
    		}
    		hit = findFirstNumberAfterAChar(command, 'm', hit);
    		
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Code has to start with a digit.");
		}
    	
    	log.info("Throwing:" + amount);
    	int hits = 0;
    	for (int i = 0; i < amount; i++) {
    		Die die = (new Die(sides)).throwDie(random);
    		if(die.getThrowResult()>=hit && hit >0){
    			die.setHit(true);
    			hits++;
				log.info("Hit!");
    		}
    		dice.add(die);
    		log.info("Threw:" + die.getThrowResult());
    		if(explodes>0){
    			int exploded = 0;
    			while(die.getThrowResult()>=explodes){
    				die = (new Die(sides)).throwDie(random);
    				die.setExploded(++exploded);
    				log.info("Exploded! New throw:"+die.getThrowResult());
    	    		if(die.getThrowResult()>=hit && hit >0){
    	    			die.setHit(true);
    	    			hits++;
        				log.info("Hit!");
    	    		}
    				dice.add(die);
    			}
    		}
			
		}
    	
    	return new ThrowHolder(hits, dice);
    }
    
    private int findFirstNumberAfterAChar(String command, char lookFor, int defaultNumber){
		Pattern pattern = Pattern.compile("(\\d+)");
		int number = -1;
		//explodes
		int numberFound = command.indexOf(lookFor);
		if(numberFound>-1){
			log.info("Found '"+lookFor+"' at:" + numberFound + ", length:" + command.length());
			number = defaultNumber;
			if(numberFound+1<command.length()){
				char c = command.charAt(numberFound+1);
				log.info("Found char:" + c);
				if(Character.isDigit(c)){
    				log.info("Char is digit.");
        			Matcher m = pattern.matcher(command.substring(numberFound+1));
        			m.find();
        			number = Integer.parseInt(m.group(1));
				}
			}
			
		}
		log.info("Number:" + number);
		return number;
    }
    
    class ThrowHolder{
    	public int hits;
    	public ThrowHolder(int hits, List<Die> dice) {
			super();
			this.hits = hits;
			this.dice = dice;
		}
		public List<Die> dice;
    }
    
}
