package net.sepman.dice.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaEntity
public class DiceThrow {

    public DiceThrow(String code) {
		super();
		this.code = code;
		this.randomOrg = true;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yy HH:mm:ss")
    private Date throwTime;

    @NotNull
    private String code;

    private String owner;
    
    private String command;

    private String comment;
    
    private int hits; 
    
    private boolean randomOrg = true;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Die> diceThrows = new ArrayList<Die>();


}
