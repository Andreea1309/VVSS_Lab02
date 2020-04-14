package Lab02;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.Assert;
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

import java.security.Provider;
import java.util.ArrayList;
import java.util.Iterator;


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

    public void deleteAllAssignments()
    {
        ArrayList<String> ida = new ArrayList();
        service.findAllTeme().forEach(tema -> ida.add(tema.getID()));
        for (int i = 0; i < ida.size(); i++) {
            service.deleteTema(ida.get(i));
        }
    }
    public void deleteAllStudents() {
        ArrayList<String> ids = new ArrayList();
        service.findAllStudents().forEach(student -> ids.add(student.getID()));
        for (int i = 0; i < ids.size(); i++) {
            service.deleteStudent(ids.get(i));
        }
    }

    //WBT for students
    @org.junit.Test
    public void addAssignment1() {
        deleteAllAssignments();
        service.saveTema("10", "description", 12, 8);
        int i = 0;
        Iterator assignmentsIterator = service.findAllTeme().iterator();
        while(assignmentsIterator.hasNext()) {
            i++;
            assignmentsIterator.next();
        }
        Assert.assertEquals(1, i);
    }
    @org.junit.Test
    public void saveStudent00() {
        deleteAllStudents();
        service.saveStudent("10", "Steve", 923);
        int i = 0;
        Iterator studentsIterator = service.findAllStudents().iterator();
        while(studentsIterator.hasNext()) {
            i++;
            studentsIterator.next();
        }
        Assert.assertEquals(1, i);
    }

    @org.junit.Test
    public void saveStudent01() {
        deleteAllStudents();
        service.saveStudent("", "Mary", 922);
        int i = 0;
        Iterator studentsIterator = service.findAllStudents().iterator();
        while(studentsIterator.hasNext()) {
            i++;
            studentsIterator.next();
        }
        Assert.assertEquals(0, i);
    }

    @org.junit.Test
    public void saveStudent02() {
        deleteAllStudents();
        service.saveStudent(null, "Mary", 922);
        int i = 0;
        Iterator studentsIterator = service.findAllStudents().iterator();
        while(studentsIterator.hasNext()) {
            i++;
            studentsIterator.next();
        }
        Assert.assertEquals(0, i);
    }

    @org.junit.Test
    public void saveStudent03() {
        deleteAllStudents();
        service.saveStudent("2", "", 922);
        int i = 0;
        Iterator studentsIterator = service.findAllStudents().iterator();
        while(studentsIterator.hasNext()) {
            i++;
            studentsIterator.next();
        }
        Assert.assertEquals(0, i);
    }
    @org.junit.Test
    public void saveStudent04() {
        deleteAllStudents();
        service.saveStudent("4", "Mary", 109);
        int i = 0;
        Iterator studentsIterator = service.findAllStudents().iterator();
        while(studentsIterator.hasNext()) {
            i++;
            studentsIterator.next();
        }
        Assert.assertEquals(0, i);
    }
    @org.junit.Test
    public void saveStudent05() {
        deleteAllStudents();
        service.saveStudent("5", "Mary", 111);
        int i = 0;
        Iterator studentsIterator = service.findAllStudents().iterator();
        while(studentsIterator.hasNext()) {
            i++;
            studentsIterator.next();
        }
        Assert.assertEquals(1, i);
    }
    @org.junit.Test
    public void saveStudent06() {
        deleteAllStudents();
        service.saveStudent("9", "Mary", 939);
        int i = 0;
        Iterator studentsIterator = service.findAllStudents().iterator();
        while (studentsIterator.hasNext()) {
            i++;
            studentsIterator.next();
        }
        Assert.assertEquals(0, i);
    }

    //BBT for assignment
    @org.junit.Test
    public void addAssignment00() {
        deleteAllAssignments();
        int result = service.saveTema("0", "description", 4, 8);
        Assert.assertEquals(1, result);
    }
    @org.junit.Test
    public void addAssignment01() {
        deleteAllAssignments();
        int result = service.saveTema("", "description", 8, 4);
        Assert.assertEquals(1, result);
    }
    @org.junit.Test
    public void addAssignment02() {
        deleteAllAssignments();
        int result = service.saveTema(null, "description", 8, 4);
        Assert.assertEquals(1, result);
    }
    @org.junit.Test
    public void addAssignment03() {
        deleteAllAssignments();
        int result = service.saveTema("1", "", 8, 4);
        Assert.assertEquals(1, result);
    }
    @org.junit.Test
    public void addAssignment04() {
        deleteAllAssignments();
        int result = service.saveTema("1", null, 8, 4);
        Assert.assertEquals(1, result);
    }
    @org.junit.Test
    public void addAssignment05() {
        deleteAllAssignments();
        int result = service.saveTema("1", "description", 0, 4);
        Assert.assertEquals(1, result);
    }
    @org.junit.Test
    public void addAssignment06() {
        deleteAllAssignments();
        int result = service.saveTema("1", "description", 15, 4);
        Assert.assertEquals(1, result);
    }
    @org.junit.Test
    public void addAssignment07() {
        deleteAllAssignments();
        int result = service.saveTema("1", "description", 3, 0);
        Assert.assertEquals(1, result);
    }
    @org.junit.Test
    public void addAssignment08() {
        deleteAllAssignments();
        int result = service.saveTema("1", "description", 3, 15);
        Assert.assertEquals(1, result);
    }

    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
}
