package com.fabriciojf.english.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * @author Fabricio S Costa fabriciojf@gmail.com
 * @class Word
 * @version 1.0
 * @since 20 de out de 2020
 */
@Entity
@Table(name = "english_word")
public class Word implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "word_reference")
    private String wordReference;

    @Column(name = "main_meaning")
    private String mainMeaning;
    private int active;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "word")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Meaning> meaning;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWordReference() {
        return wordReference;
    }

    public void setWordReference(String wordReference) {
        this.wordReference = wordReference.trim();
    }

    public String getMainMeaning() {
        return mainMeaning;
    }

    public void setMainMeaning(String mainMeaning) {
        this.mainMeaning = mainMeaning;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public List<Meaning> getMeaning() {
        return meaning;
    }

    public void setMeaning(List<Meaning> meaning) {
        this.meaning = meaning;
    }

}
