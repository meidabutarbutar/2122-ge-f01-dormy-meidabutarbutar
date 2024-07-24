package pbo.f01;

/**
 * 12S20038 - Arni Sitorus
 * 12S20049 - Meida Butarbutar
 */

import java.util.List;
import java.util.Set;
import java.util.Scanner;
import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import pbo.f01.model.Student;
import pbo.f01.model.Dorm;

public class App {

    private static EntityManagerFactory factory;
    private static EntityManager entityManager;
    static final String DATA_SEPARATOR = "#";
    private static final Dorm[] dorms = null;

    public static void main(String[] _args) {

        factory = Persistence.createEntityManagerFactory("dormy_pu");
        entityManager = factory.createEntityManager();

        Scanner input = new Scanner(System.in);
        String line = null;

        while (true) {
            line = input.nextLine();
            if (line.equals("---")) {
                break;
            }

            String[] data = line.split(DATA_SEPARATOR);
            String command = data[0];
            data = Arrays.copyOfRange(data, 1, data.length);

            if (command.equals("display-all")) {
                displayAllStudents();
                displayAllDorms();
                cleanTables();

            } else if (command.equals("student-add")) {

                seedTables();

            } else if (command.equals("dorm-add")) {
                seedTables();
            } else if (command.equals("assign")) {
                selectAll();
            }

        }
        input.close();
    }

    private static void displayAllStudents() {
        String jpql = "SELECT s FROM Student s ORDER BY s.id ";
        List<Student> students = entityManager.createQuery(jpql, Student.class)
                .getResultList();
        System.out.println("displayall--");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    private static void displayAllDorms() {
        String jpql = "SELECT d FROM Dorm d ORDER BY d.name";
        List<Dorm> dorms = entityManager.createQuery(jpql, Dorm.class)
                .getResultList();

        System.out.println("displayAll--");
        for (Dorm d : dorms) {
            System.out.println(d);

            Set<Student> students = d.getStudents();
            for (Student s : students) {
                System.out.println(s);
            }

        }

    }

    private static void seedTables() {
        System.out.println("seedTables--");

        entityManager.getTransaction().begin();

        Student slaki = new Student("12S21010", "Bobby Siagian", "2021", "male");
        Student slakilagi = new Student("12S21001", "Dhino Turnip", "2021", "male");
        Student scewe = new Student("12S21006", "Wenny Sitinjak", "2021", "female");

        Dorm dcewe = new Dorm("Pniel", "5", "female");
        Dorm dcowo = new Dorm("Kapernaum", "5", "male");

        entityManager.persist(slaki);
        entityManager.persist(slakilagi);
        entityManager.persist(scewe);

        entityManager.persist(dcewe);
        entityManager.persist(dcowo);

        entityManager.flush();
        entityManager.getTransaction().commit();

    }

    private static void cleanTables() {
        String deleteStudentJpql = "DELETE FROM Student s";
        String deleteDormJpql = "DELETE FROM Dorm d";

        entityManager.getTransaction().begin();

        int deletedStudents = entityManager.createQuery(deleteStudentJpql).executeUpdate();
        int deletedDorms = entityManager.createQuery(deleteDormJpql).executeUpdate();

        entityManager.flush();
        entityManager.getTransaction().commit();

        System.out.println("cleanTables: " + deletedStudents + " students");
        System.out.println("cleanTables: " + deletedDorms + " groups");

    }

    private static void selectAll() {
        for (Dorm dorm : dorms) {
            if (dorm.getCapacity() == 5)
                ;

        }
        String jpql = "SELECT d FROM Dorm ORDER BY d.name";
        TypedQuery<Dorm> query = entityManager.createQuery(jpql, Dorm.class);
        List<Dorm> dorms = query.getResultList();

        System.out.println("--");
        for (Dorm d : dorms) {
            System.out.println(d);
        }
    }
}
