package com.example.catalogfilm.service.impl;

import com.example.catalogfilm.model.Director;
import com.example.catalogfilm.repository.DirectorRepository;
import com.example.catalogfilm.service.DirectorService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DirectorServiceImpl implements DirectorService {
    private final DirectorRepository directorRepository;
    @SneakyThrows(ChangeSetPersister.NotFoundException.class)
    @Override
    public Director getDirector(UUID directorUuid) {
        Optional<Director> directorOptional = directorRepository.findById(directorUuid);
        return directorOptional.orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }

    @Override
    public Director saveDirector(Director director) {
        return directorRepository.save(director);
    }
}
