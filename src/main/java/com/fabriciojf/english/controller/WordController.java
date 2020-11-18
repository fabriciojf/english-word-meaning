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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public ModelAndView addForm(RedirectAttributes attributes) {
        
        Pageable limit = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "id"));
        Iterable<Word> words = wrepo.findAll(limit);
        ModelAndView mview = new ModelAndView("word/add-words");
        mview.addObject("words", words);
        
        attributes.addFlashAttribute("flashMessage", "Meaning added Succefully!");
        attributes.addFlashAttribute("flashType", "success");
        
        return mview;
    }

    @RequestMapping(value = "/add-word", method = RequestMethod.POST)
    public String addForm(Word word) {
        
        if (wrepo.findByWordReference(word.getWordReference().trim()) == null) {
            wrepo.save(word);            
        }        
        return "redirect:/add-word";
    }
    
    @RequestMapping(value = "/update-word", method = RequestMethod.POST)
    public String updateForm(Word word, RedirectAttributes attributes) {        
        wrepo.save(word);        
        
        attributes.addFlashAttribute("flashMessage", "Meaning Updated Successfully!");
        attributes.addFlashAttribute("flashType", "success");
        
        return "redirect:/detail-word/" + word.getId();
    }

    @RequestMapping(value = "/list-words")
    public ModelAndView listWords() {
        
        // Paging items, with sort need JpaRepository instead of CrudRepository
        Pageable limit = PageRequest.of(0, 200, Sort.by(Sort.Direction.ASC, "wordReference"));
        
        ModelAndView mview = new ModelAndView("word/list-words");
        Iterable<Word> words = wrepo.findAll(limit);
        
        mview.addObject("words", words);

        return mview;
    }
        
    @RequestMapping(value = "/print-words")
    public ModelAndView printWords() {
        
        // Paging items, with sort need JpaRepository instead of CrudRepository
        Pageable limit = PageRequest.of(0, 200, Sort.by(Sort.Direction.ASC, "wordReference"));
        
        ModelAndView mview = new ModelAndView("word/print-words");
        Iterable<Word> words = wrepo.findAll(limit);
        
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
