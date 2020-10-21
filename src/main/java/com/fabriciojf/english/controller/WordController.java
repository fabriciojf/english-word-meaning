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

@Controller
public class WordController {

    @Autowired
    private WordRepository wrepo;
    
    @Autowired
    private MeaningRepository mrepo;

    @RequestMapping(value = "/addWord", method = RequestMethod.GET)
    public String addForm() {
        return "word/addWords";
    }

    @RequestMapping(value = "/addWord", method = RequestMethod.POST)
    public String addForm(Word word) {
        wrepo.save(word);
        
        return "word/addWords";
    }

    @RequestMapping(value = "/listWords")
    public ModelAndView listWords() {
        ModelAndView mview = new ModelAndView("word/listWords");
        Iterable<Word> words = wrepo.findAll();
        mview.addObject("words", words);
        
        return mview;
    }

    @RequestMapping(value = "/detailWord/{id}", method = RequestMethod.GET)
    public ModelAndView detailWord(@PathVariable("id") int id) {
        ModelAndView mview = new ModelAndView("word/detailWord");
        Word word = wrepo.findById(id);
        mview.addObject("word", word);
        
        return mview;
    }
    
    @RequestMapping(value = "/detailWord/{id}", method = RequestMethod.POST)
    public String detailWord(@PathVariable("id") int id, Meaning meaning) {        
        Word word = wrepo.findById(id);        
        meaning.setWord(word); 
        mrepo.save(meaning);
        
        return "redirect:/detailWord/{id}";        
    }
    
}
