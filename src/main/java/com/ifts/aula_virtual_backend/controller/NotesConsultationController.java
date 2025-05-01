package com.ifts.aula_virtual_backend.controller;

import com.ifts.aula_virtual_backend.dto.NotesConsultationDto;
import com.ifts.aula_virtual_backend.dto.NotesUploadDto;
import com.ifts.aula_virtual_backend.service.NotesConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/notes")
@PreAuthorize("denyAll()")
public class NotesConsultationController
{
    @Autowired
    private NotesConsultationService notesConsultationService;

    @PostMapping("/upload")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR')")
    public ResponseEntity<String > uploadNotes(@RequestBody NotesUploadDto notesUploadDto)
    {
        notesConsultationService.uploadNotes(notesUploadDto);
        return new ResponseEntity<>("Notas subidas correctamente", HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<List<NotesConsultationDto>> getNotesByStudent(Authentication authentication)
    {
        String dni = authentication.getName();
        List<NotesConsultationDto> notes = notesConsultationService.getAllNotesByDni(dni);
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }
}
