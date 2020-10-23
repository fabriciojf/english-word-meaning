package com.fabriciojf.english.controller;

import com.fabriciojf.english.model.Meaning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fabriciojf.english.model.Word;
import com.fabriciojf.english.repository.MeaningRepository;
import com.fabriciojf.english.repository.WordRepository;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Fabricio S Costa fabriciojf@gmail.com
 * @class WordController
 * @version 1.0
 * @since 21/10/2020
 */
@Controller
public class WordController {

    @Autowired
    private WordRepository wrepo;

    @Autowired
    private MeaningRepository mrepo;
   
    @RequestMapping(value = "/add-word", method = RequestMethod.GET)
    public String addForm() {
        return "word/add-words";
    }

    @RequestMapping(value = "/add-word", method = RequestMethod.POST)
    public String addForm(Word word) {
        wrepo.save(word);

        return "word/add-words";
    }

    @RequestMapping(value = "/list-words")
    public ModelAndView listWords() {
        ModelAndView mview = new ModelAndView("word/list-words");
        Iterable<Word> words = wrepo.findAll();
        mview.addObject("words", words);

        return mview;
    }

    @RequestMapping(value = "/detail-word/{id}", method = RequestMethod.GET)
    public ModelAndView detailWord(@PathVariable("id") int id) {
        ModelAndView mview = new ModelAndView("word/detail-word");
        Word word = wrepo.findById(id);
        mview.addObject("word", word);
        
        Iterable<Meaning> meanings = mrepo.findByWord(word);
        mview.addObject("meanings", meanings);

        return mview;
    }

    @RequestMapping(value = "/detail-word/{id}", method = RequestMethod.POST)
    public String detailWordMeaning(@PathVariable("id") int id, 
            @Valid Meaning meaning, BindingResult result,
            RedirectAttributes attributes) {
        
        if (result.hasErrors()) {
            attributes.addFlashAttribute("flashMessage", "Verify the fields");
            attributes.addFlashAttribute("flashType", "danger");
            return "redirect:/detail-word/{id}";
        }
        
        Word word = wrepo.findById(id);
        meaning.setWord(word);
        mrepo.save(meaning);
        
        attributes.addFlashAttribute("flashMessage", "Meaning added Succefully!");
        attributes.addFlashAttribute("flashType", "success");
        
        return "redirect:/detail-word/{id}";
    }
    
    @RequestMapping("/remove-word/{id}")
    public String removeWord(int id) {
        
        Word word = wrepo.findById(id);        
        wrepo.delete(word);        
        
        return "redirect:/list-words";
    }
    
    @RequestMapping("/remove-meaning/{id}")
    public String removeMeaning(int id) {
        
        Meaning meaning = mrepo.findById(id);        
        int wordId = meaning.getWord().getId();
                
        mrepo.delete(meaning);
        
        return "redirect:/detail-word/" + wordId;
    }

}
