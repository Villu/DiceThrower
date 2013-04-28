package net.sepman.dice.domain;

import javax.validation.constraints.NotNull;
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
    private int sides;

    private int throwResult;
    
    public Die throwDie(){
    	throwResult = (int) Math.round(Math.random()*sides);
    	return this;
    }
}
