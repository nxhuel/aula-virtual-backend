package com.ifts.aula_virtual_backend.service;

import com.ifts.aula_virtual_backend.dto.NotesConsultationDto;
import com.ifts.aula_virtual_backend.dto.NotesUploadDto;

import java.util.List;

public interface NotesConsultationService
{
    void uploadNotes(NotesUploadDto notesUploadDto);

    List<NotesConsultationDto> getAllNotesByDni(String dni);
}
