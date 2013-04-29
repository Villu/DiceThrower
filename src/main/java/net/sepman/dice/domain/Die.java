package net.sepman.dice.domain;

import java.util.Random;

import javax.validation.constraints.NotNull;

import net.sepman.dice.utils.RandomOrg;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaEntity
public class Die {
	
    public Die(int sides) {
		super();
		this.sides = sides;
		this.throwResult = 0;
	}

	@NotNull
    private int sides = 6;

    private int throwResult;
    
    private int exploded = 0;
    
    private boolean hit = false; 
    
    public Die throwDie(Random random, boolean org){
    	int min = 1;
    	int max = sides;
    	if(org){
    		throwResult = RandomOrg.getRandom(sides);
    	}else{
    		throwResult = random.nextInt(max - min + 1) + min;
    	}
    	return this;
    }
}
