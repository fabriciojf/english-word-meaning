package com.fabriciojf.english;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Fabricio S Costa fabriciojf@gmail.com
 * @class Index Controller
 * @version 1.0
 * @since 21/10/2020
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

}
