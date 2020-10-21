package com.fabriciojf.english.repository;

import org.springframework.data.repository.CrudRepository;

import com.fabriciojf.english.model.Word;

/**
 * @author Fabricio S Costa fabriciojf@gmail.com
 * @interface WordRepository
 * @version 1.0
 * @since 21/10/2020
 */
public interface WordRepository extends CrudRepository<Word, String> {

    Word findById(int id);
}
