package com.fabriciojf.english;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Fabricio S Costa fabriciojf@gmail.com
 * @class Index Controller
 * @version 1.0
 * @since 21/10/2020
 */
@Controller
public class IndexController implements ErrorController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/error")
    public String getErrorPath() {
        
        ModelAndView mv = new ModelAndView();        
        mv.addObject("flashMessage", (String) "Login Failure");        
        mv.addObject("flashType", (String) "danger");
        
        return "/login.html";
    }

}
