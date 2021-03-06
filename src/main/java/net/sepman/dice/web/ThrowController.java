package net.sepman.dice.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import net.sepman.dice.domain.DiceThrow;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@RequestMapping("/dicethrows")
@Controller
@RooWebScaffold(path = "dicethrows", formBackingObject = DiceThrow.class, delete=false, update=false)
@SessionAttributes("diceThrow")
public class ThrowController {
	
    public void populateEditForm(Model uiModel, DiceThrow diceThrow) {
        if(uiModel.containsAttribute("diceThrow")){
        	//copy from old, to avoid overwriting old throw
        	DiceThrow oldThrow = (DiceThrow)uiModel.asMap().get("diceThrow");
        	diceThrow.setCode(oldThrow.getCode());
        	diceThrow.setCommand(oldThrow.getCommand());
        	diceThrow.setComment(oldThrow.getComment());
        	diceThrow.setOwner(oldThrow.getOwner());
        	diceThrow.setRandomOrg(oldThrow.isRandomOrg());
        }
    	uiModel.addAttribute("diceThrow", diceThrow);
        addDateTimeFormatPatterns(uiModel);
//        uiModel.addAttribute("dies", diceService.findAllDies());
    }
	
    @RequestMapping(value="/list/{code}", produces = "text/html")
    public String listByCode(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @PathVariable("code") String code, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("dicethrows", throwService.findDiceThrowEntriesByCode(firstResult, sizeNo, code));
            float nrOfPages = (float) throwService.countAllDiceThrowsByCode(code) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("dicethrows", throwService.findByCode(code));
        }
        populateEditForm(uiModel,new DiceThrow(code));
        return "dicethrows/list";
    }
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid DiceThrow diceThrow, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, diceThrow);
            return "dicethrows/create";
        }
//        uiModel.asMap().clear();
        uiModel.addAttribute("diceThrow", diceThrow);
        if(!diceThrow.getCode().isEmpty() && !diceThrow.getCommand().isEmpty()){
        	throwService.saveDiceThrow(diceThrow);
        }
        return "redirect:/dicethrows/list/" + encodeUrlPathSegment(diceThrow.getCode().toString(), httpServletRequest);
    }
}
