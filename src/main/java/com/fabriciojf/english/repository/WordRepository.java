package com.fabriciojf.english.repository;

import org.springframework.data.repository.CrudRepository;

import com.fabriciojf.english.model.Word;

public interface WordRepository extends CrudRepository<Word, String> {	
	Word findById(int id);
}
