package az.ingress.bankapp.service.impl;

import az.ingress.bankapp.entity.Student;
import az.ingress.bankapp.repository.StudentRepository;
import az.ingress.bankapp.service.StudentService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public int saveStudent(Student student) {
        return 1;
    }

    @Override
    public List<Student> getAllStudents(String name, String surname) {
        Specification<Student> studentSpecification = null;

        studentSpecification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (name != null) {
                predicateList.add(criteriaBuilder.equal(root.get("name"), name));
            }
            if (surname != null) {
                predicateList.add(criteriaBuilder.equal(root.get("surname"), surname));
            }
            log.info("predicateList: {}", predicateList);
            log.info("predicateList Array: {}", predicateList.toArray(new Predicate[0]));
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        };

        return studentRepository.findAll(studentSpecification);
    }


}
