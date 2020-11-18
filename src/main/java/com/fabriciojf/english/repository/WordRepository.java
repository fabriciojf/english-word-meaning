package com.fabriciojf.english.repository;

import com.fabriciojf.english.model.Word;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Fabricio S Costa fabriciojf@gmail.com
 * @interface WordRepository
 * @version 1.0
 * @since 21/10/2020
 */
public interface WordRepository extends JpaRepository<Word, String> {

    Word findById(int id);
    List<Word> findByOrderByWordReferenceAsc(Pageable page);
    Word findByWordReference(String wordReference);
}
