package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {FacultyService.class})
@ExtendWith(SpringExtension.class)
class FacultyServiceTest {

    @Autowired
    private FacultyService facultyService;

    @MockBean
    private FacultyRepository facultyRepository;

    @Test
    void add_success() {
        //Подготовка входных данных
        String name = "name";
        String color = "color";

        Faculty faculty = new Faculty(name, color);

        //Подготовка ожидаемого результата
        Faculty facultyWithId = new Faculty(name, color);
        facultyWithId.setId(1l);
        when(facultyRepository.save(faculty)).thenReturn(facultyWithId);

        //Начало теста
        Faculty actualFaculty = facultyService.add(name, color);
        assertEquals(facultyWithId, actualFaculty);
        verify(facultyRepository).save(faculty);
        verifyNoMoreInteractions(facultyRepository);
    }

    @Test
    void delete_success() {
        //Подготовка входных данных
        long id = 1;

        Faculty faculty = new Faculty("name", "color");

        //Подготовка ожидаемого результата
        when(facultyRepository.findById(id)).thenReturn(Optional.of(faculty));

        //Начало теста
        Faculty actualFaculty = facultyService.delete(id);
        assertEquals(faculty, actualFaculty);
        verify(facultyRepository).findById(id);
    }

    @Test
    void delete_withException() {
        //Подготовка входных данных
        long id = 1;

        //Подготовка ожидаемого результата
        when(facultyRepository.findById(id)).thenReturn(Optional.empty());
        String expectedMessage = "Факультет c id " + id + " не найден";

        //Начало теста
        Exception exception = assertThrows(
                RuntimeException.class,
                () -> {
                    facultyService.delete(id);
                }
        );
        assertEquals(expectedMessage, exception.getMessage());
        verify(facultyRepository).findById(id);
    }
}
