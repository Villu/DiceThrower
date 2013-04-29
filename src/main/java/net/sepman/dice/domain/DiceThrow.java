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
	}

	@Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yy hh:mm:ss")
    private Date throwTime;

    @NotNull
    private String code;

    private String owner;
    
    private String command;

    private String comment;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Die> diceThrows = new ArrayList<Die>();


}
