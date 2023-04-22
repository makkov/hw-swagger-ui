package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty add(String name, String color) {
        Faculty newFaculty = new Faculty(name, color);
        newFaculty = facultyRepository.save(newFaculty);
        return newFaculty;
    }

    public List<Faculty> getAll() {
        return facultyRepository.findAll();
    }

    public Faculty update(long id, String name, String color) {
        Optional<Faculty> facultyForUpdateOpt = facultyRepository.findById(id);

        if (facultyForUpdateOpt.isEmpty()) {
            throw new RuntimeException("Факультет c id " + id + " не найден");
        }

        Faculty facultyForUpdate = facultyForUpdateOpt.get();
        facultyForUpdate.setName(name);
        facultyForUpdate.setColor(color);

        facultyRepository.save(facultyForUpdate);
        return facultyForUpdate;
    }

    public Faculty delete(long id) {
        Optional<Faculty> facultyForDeleteOpt = facultyRepository.findById(id);

        if (facultyForDeleteOpt.isEmpty()) {
            throw new RuntimeException("Факультет c id " + id + " не найден");
        }

        Faculty facultyForDelete = facultyForDeleteOpt.get();

        facultyRepository.delete(facultyForDelete);
        return facultyForDelete;
    }
}
