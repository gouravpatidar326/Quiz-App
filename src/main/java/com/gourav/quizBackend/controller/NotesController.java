package com.gourav.quizBackend.controller;

import com.gourav.quizBackend.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/notes")
public class NotesController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/upload")
    public String uploadNote(@RequestParam("file") MultipartFile file) {
        try {
            noteService.saveNote(file);
            return "Uploaded successfully: " + file.getOriginalFilename();
        } catch (Exception e) {
            e.printStackTrace();
            return "Upload failed!";
        }
    }

    @GetMapping("/displayNotes/{name}")
    public ResponseEntity<ByteArrayResource> displayNote(@PathVariable String name) {
        return noteService.getNoteByFileName(name)
                .map(note -> {
                    return ResponseEntity.ok()
                            .contentType(MediaType.parseMediaType(note.getFileType()))
                            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + note.getFileName() + "\"")
                            .body(new ByteArrayResource(note.getData()));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
