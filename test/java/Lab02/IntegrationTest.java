package Lab02;

import domain.Nota;
import domain.Pair;
import domain.Student;
import domain.Tema;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.assertEquals;

public class IntegrationTest {
    private static Service serviceBefore;

    @BeforeClass
    public static void onceExecutedBeforeAll() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        serviceBefore = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @Test
    public void addCorrectStudentTest(){
        assertEquals(1, serviceBefore.saveStudent("student1", "Student1", 200));
        serviceBefore.deleteStudent("student1");
    }

    @Test
    public void addCorrectStudentAndAssignmentTest(){
        assertEquals(1, serviceBefore.saveStudent("student1", "Student1", 200));
        assertEquals(1, serviceBefore.saveTema("tema1", "Description1", 4, 2));
        serviceBefore.deleteStudent("student1");
        serviceBefore.deleteTema("tema1");
    }

    @Test
    public void addCorrectStudentAssignmentAndGradeTest(){
        serviceBefore.saveStudent("student1", "Student1", 200);
        serviceBefore.saveTema("tema1", "Description1", 4, 2);
        int result = serviceBefore.saveNota("student1","tema1", 10, 8, "good");
        Pair idGrade = new Pair("student1","tema1");
        AtomicBoolean exists = new AtomicBoolean(false);
        serviceBefore.findAllNote().forEach(nota-> {if(nota.getID().equals(idGrade)) {
            exists.set(true);
        }});
        if(exists.get())
            assertEquals(0, result);
        else
            assertEquals(1, result);
        serviceBefore.deleteStudent("student1");
        serviceBefore.deleteTema("tema1");
    }
}