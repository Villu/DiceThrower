package net.sepman.dice.web;

import net.sepman.dice.domain.DiceThrow;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/dicethrows")
@Controller
@RooWebScaffold(path = "dicethrows", formBackingObject = DiceThrow.class, delete=false, update=false)
public class ThrowController {
}
