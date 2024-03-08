package az.ingress.bankapp.service;

import az.ingress.bankapp.entity.Student;

import java.util.List;

public interface StudentService {

    int saveStudent(Student student);

    List<Student> getAllStudents(String name, String surname);
}
