package net.sepman.dice.web;

import net.sepman.dice.domain.Die;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/dies")
@Controller
@RooWebScaffold(path = "dies", formBackingObject = Die.class)
public class DiceController {
}
