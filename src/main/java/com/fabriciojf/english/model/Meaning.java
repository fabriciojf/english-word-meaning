package com.fabriciojf.english.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author Fabricio S Costa fabriciojf@gmail.com
 * @class Meaning
 * @version 1.0
 * @since 21/10/2020
 */
@Entity
public class Meaning implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "meaning_option")
    private String meaningOption;

    @ManyToOne
    private Word word;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMeaningOption() {
        return meaningOption;
    }

    public void setMeaningOption(String meaningOption) {
        this.meaningOption = meaningOption;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

}
