package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.HashMap;
import java.util.Map;

@Service
public class FacultyService {

    private long counterId = 0;

    private Map<Long, Faculty> faculties = new HashMap<>();

    public Faculty add(String name, String color) {
        long id = counterId;
        counterId++;
        Faculty newFaculty = new Faculty(id, name, color);
        faculties.put(id, newFaculty);
        return newFaculty;
    }

    public Map<Long, Faculty> getAll() {
        return faculties;
    }

    public Faculty update(long id, String name, String color) {
        Faculty facultyForUpdate = faculties.get(id);
        facultyForUpdate.setName(name);
        facultyForUpdate.setColor(color);
        return facultyForUpdate;
    }

    public Faculty delete(long id) {
        return faculties.remove(id);
    }
}
