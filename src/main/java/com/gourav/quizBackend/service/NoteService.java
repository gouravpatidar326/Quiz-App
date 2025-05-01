package com.gourav.quizBackend.service;

import com.gourav.quizBackend.entity.Note;
import com.gourav.quizBackend.repo.NoteRepository;
import com.gourav.quizBackend.utils.PdfUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public Note saveNote(MultipartFile file) throws IOException {
        Note note = new Note();
        note.setFileName(file.getOriginalFilename());
        note.setFileType(file.getContentType());

        // Compress the PDF file data before saving
        byte[] compressedData = PdfUtils.compressPdf(file.getBytes());

        // Set the compressed file data
        note.setData(compressedData);

        return noteRepository.save(note);
    }

    public Optional<Note> getNoteByFileName(String name) {
        Optional<Note> noteOptional = noteRepository.findByFileName(name);

        // If the note exists, decompress the data before returning
        if (noteOptional.isPresent()) {
            Note note = noteOptional.get();

            // Decompress the PDF data
            byte[] decompressedData = PdfUtils.decompressPdf(note.getData());

            // Update the Note object with the decompressed data (optional)
            note.setData(decompressedData);
        }

        return noteOptional;
    }
}
