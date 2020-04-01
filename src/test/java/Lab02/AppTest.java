package Lab02;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


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


/**
 * Unit test for simple App.
 */
public class AppTest 
{
    Service service;
    StudentXMLRepository fileRepository1;
    TemaXMLRepository fileRepository2;
    NotaXMLRepository fileRepository3;

    @Before
    public void initData(){
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        Validator<Student> studentValidator = new StudentValidator();

        fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @Test
    public void addStudentRepo(){

        assertTrue(service.saveStudent("23", "Samantha", 937) == 0);
        assertEquals(fileRepository1.findOne("23").getNume(), "Samantha");

    }

    @Test
    public void addTemaRepo(){

        assertTrue(service.saveTema("5", "test", 7, 5) == 0);
        assertEquals(fileRepository2.findOne("5").getDescriere(), "test");

    }

    @Test
    public void testAddTemaNullId() {

        assertFalse(service.saveTema("", "Tema1", 2, 1) == 0);

    }



    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
}
