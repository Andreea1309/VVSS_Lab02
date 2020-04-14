package Lab02;


import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.Before;
import org.junit.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

import static org.junit.Assert.assertEquals;

public class BigBangTests {

    protected Validator<Student> studentValidator;
    protected Validator<Tema> temaValidator;
    protected Validator<Nota> notaValidator;

    protected StudentXMLRepository fileRepository1;
    protected TemaXMLRepository fileRepository2;
    protected NotaXMLRepository fileRepository3;

    protected Service service;

    @Before
    public void setUp(){
        studentValidator = new StudentValidator();
        temaValidator = new TemaValidator();
        notaValidator = new NotaValidator();
        fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @Test
    public void testaddGradeBigBang(){
        int initNo = service.getNumberOfStudents();
        String newId = String.valueOf(initNo + 1);
        service.saveStudent(newId, "Mary", 937);

        int initNoTeme = service.getNumberOfTeme();
        String newIdTema = String.valueOf(initNoTeme + 1);
        service.saveTema(newIdTema, "descriere", 5, 2);
        assertEquals(service.saveNota(newId, newIdTema, 7, 4, "good"), 1);
    }

    @Test
    public void testaddStudentBigBang(){
        int initNo = service.getNumberOfStudents();
        String newId = String.valueOf(initNo + 1);

        int actualResult = service.saveStudent(newId, "Mary", 937);
        int expectedResult = 1;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testaddAssignmentBigBang(){
        int initNo = service.getNumberOfTeme();
        String newId = String.valueOf(initNo + 1);

        int actualResult = service.saveTema(newId, "descriere", 5, 2);
        int expectedResult = 1;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testintegrateBigBang(){
        testaddGradeBigBang();
        testaddAssignmentBigBang();
        testaddStudentBigBang();
    }
}