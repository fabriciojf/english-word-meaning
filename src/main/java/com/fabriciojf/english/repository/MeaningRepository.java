package com.fabriciojf.english.repository;

import com.fabriciojf.english.model.Meaning;
import com.fabriciojf.english.model.Word;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Fabricio S Costa fabriciojf@gmail.com
 * @interface MeaningRepository
 * @version 1.0
 * @since 21/10/2020
 */
public interface MeaningRepository extends CrudRepository<Meaning, String> {

    List<Meaning> findByWord(Word word);
    
}
