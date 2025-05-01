package com.gourav.quizBackend.repo;

import com.gourav.quizBackend.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {
    Optional<Note> findByFileName(String fileName);
}
