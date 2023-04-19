package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.Map;

public class StudentService {

    private long counterId = 0;

    private Map<Long, Student> students = new HashMap<>();
}
