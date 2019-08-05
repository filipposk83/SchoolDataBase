package schooldatabase;

import Dao.AssignmentDao;
import Dao.ClassroomDao;
import Dao.CourseDao;
import Dao.StudentDao;
import Dao.StudentWorkDao;
import Dao.TrainerDao;
import entities.Assignment;
import entities.Classroom;
import entities.Course;
import entities.Student;
import entities.StudentWork;
import entities.Trainer;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Menu {

    Scanner inp = new Scanner(System.in);
    Validate v = new Validate();
    StudentDao sdao = new StudentDao();
    CourseDao cdao = new CourseDao();
    ClassroomDao cldao = new ClassroomDao();
    AssignmentDao adao = new AssignmentDao();
    StudentWorkDao wdao = new StudentWorkDao();
    TrainerDao tdao = new TrainerDao();

    public int basicMenu(Scanner inp) {
        int ch;
        boolean isFirstTime = true;
        do {
            if (isFirstTime) {
                System.out.println("You have the below printing options.");
                System.out.println("");
                System.out.println("1. A list of all students.");
                System.out.println("2. A list of all trainers.");
                System.out.println("3. A list of all assignments.");
                System.out.println("4. A list of all courses.");
                System.out.println("5. A list of all students per course.");
                System.out.println("6. A list of all trainers per course.");
                System.out.println("7. A list of all assignments per course.");
                System.out.println("8. A list of all assignments per course per student.");
                System.out.println("9. A list of students that belong to more than 3 courses.");
                System.out.println("10. Finish the printing process.");
                System.out.println("Please type the number you want between 1-10.");
                isFirstTime = false;
            } else {
                System.out.println("Please type the correct number between 1-10.");
                System.out.println("1. A list of all students.");
                System.out.println("2. A list of all trainers.");
                System.out.println("3. A list of all assignments.");
                System.out.println("4. A list of all courses.");
                System.out.println("5. A list of all students per course.");
                System.out.println("6. A list of all trainers per course.");
                System.out.println("7. A list of all assignments per course.");
                System.out.println("8. A list of all assignments per course per student.");
                System.out.println("9. A list of students that belong to more than 3 courses.");
                System.out.println("10. Finish the printing process.");
                System.out.println("Please type the number you want between 1-10.");
            }
            while (!inp.hasNextInt()) {
                System.out.println("Option must be a number. Enter number between 1-10");
                inp.next();
            }
            ch = inp.nextInt();
        } while (ch != 1 && ch != 2 && ch != 3 && ch != 4 && ch != 5 && ch != 6 && ch != 7 && ch != 8 && ch != 9 && ch != 10);

        return ch;
    }

    public void printingMenu(Menu m) {
        int choice2;
        do {
            choice2 = m.basicMenu(inp);
            if (choice2 == 1) {
                sdao.printStudents(sdao.getStudents());
            } else if (choice2 == 2) {
                tdao.printTrainers(tdao.getTrainers());
            } else if (choice2 == 3) {
                adao.printAssignments(adao.getAssignments());
            } else if (choice2 == 4) {
                cdao.printCourses(cdao.getCourses());
            } else if (choice2 == 5) {
                cldao.printClassroom(cldao.getClassroom(), cdao.getCourses());
            } else if (choice2 == 6) {
                tdao.printTrainersPerCourse(cdao.getCourses(), tdao.getTrainers());
            } else if (choice2 == 7) {
                adao.printAssignmentsPerCourse(adao.getAssignments(), cdao.getCourses());
            } else if (choice2 == 8) {
                wdao.printStudentWork(wdao.getStudentWork(), adao.getAssignments());
            } else if (choice2 == 9) {
                sdao.printStudentsByCondition(sdao.getStudents(), cldao.getClassroom());
            }
        } while (choice2 >= 1 && choice2 < 10);
    }

    public int subMenu(Scanner inp) {
        int ch;
        boolean isFirstTime = true;
        System.out.println("You finished with the printing process. Now you have to input data to the DataBase");
        do {
            if (isFirstTime) {
                System.out.println("You have the below insert options.");
                System.out.println("");
                System.out.println("1. Input students.");
                System.out.println("2. Input trainers.");
                System.out.println("3. Input assignments.");
                System.out.println("4. Input courses.");
                System.out.println("5. Input students per course.");
                System.out.println("6. Input trainers per course.");
                System.out.println("7. Input assignments per course per student.");
                System.out.println("8. Finish the insert process.");
                System.out.println("Please type the number you want between 1-8.");
                isFirstTime = false;
            } else {
                System.out.println("Please type the correct number between 1-8.");
                System.out.println("1. Input students.");
                System.out.println("2. Input trainers.");
                System.out.println("3. Input assignments.");
                System.out.println("4. Input courses.");
                System.out.println("5. Input students per course.");
                System.out.println("6. Input trainers per course.");
                System.out.println("7. Input assignments per course per student.");
                System.out.println("8. Finish the insert process.");
                System.out.println("Please type the number you want between 1-8.");
            }
            while (!inp.hasNextInt()) {
                System.out.println("Option must be a number. Enter number between 1-8");
                inp.next();
            }
            ch = inp.nextInt();
        } while (ch != 1 && ch != 2 && ch != 3 && ch != 4 && ch != 5 && ch != 6 && ch != 7 && ch != 8);

        return ch;
    }

    public void insertMenu(Menu m) {
        int choice2;
        do {
            choice2 = m.subMenu(inp);
            if (choice2 == 1) {
                insertMenuStudent();
            } else if (choice2 == 2) {
                insertMenuTrainer();
            } else if (choice2 == 3) {
                insertMenuAssignment();
            } else if (choice2 == 4) {
                insertMenuCourse();
            } else if (choice2 == 5) {
                insertMenuCourseStudent();
            } else if (choice2 == 6) {
                insertMenuCourseTrainer();
            } else if (choice2 == 7) {
                insertMenuStudentWork();
            }
        } while (choice2 >= 1 && choice2 < 8);
    }

    public void insertMenuStudent() {
        String choice;
        System.out.println("OK. You choose to input a student to the database of school.");
        do {
            Student st = new Student();
            System.out.println("Give the fisrt name of the student");
            st.setSfname(v.returnValidString(inp));
            System.out.println("Give the last name of the student");
            st.setSlname(v.returnValidString(inp));
            System.out.println("Give the date of birth like yyyy-MM-dd. ");
            LocalDate sdate = LocalDate.parse(v.returnValidDate(inp));
            st.setSdate(sdate);
            System.out.println("Give the student's fees");
            st.setSfees(v.returnValidDouble(inp));
            if (!isStudentExist(st, sdao)) {
//                System.out.println(st);
                sdao.insertStudent(st);
            } else {
                System.out.println("The student " + st.getSfname() + " " + st.getSlname() + " is already exists. Please try again.");
            }
            System.out.println("Do you want to input data for another student? Y or N");
            choice = inp.next();
            while (!choice.equalsIgnoreCase("n") && !choice.equalsIgnoreCase("y")) {
                System.out.println("You must input Y or N. Re-enter please.");
                choice = inp.next();
            }
        } while (!choice.equalsIgnoreCase("n"));
    }

    public static boolean isStudentExist(Student newS, StudentDao sdao) {
        boolean isexist = false;
        for (Student s : sdao.getStudents()) {
            if (s.getSfname().equalsIgnoreCase(newS.getSfname()) && s.getSlname().equalsIgnoreCase(newS.getSlname())) {
                isexist = true;
            }
        }
        return isexist;
    }

    public void insertMenuTrainer() {
        String choice;
        System.out.println("OK. You choose to input a trainer to the database of school.");
        int cid;
        do {
            Trainer tr = new Trainer();
            System.out.println("Give the fisrt name of the trainer");
            tr.setTfname(v.returnValidString(inp));
            System.out.println("Give the last name of the trainer");
            tr.setTlname(v.returnValidString(inp));
            System.out.println("Give the subject of the trainer");
            tr.setTsubject(v.returnValidString(inp));
            System.out.println("Choose from the below list, which course will teach " + tr.getTfname() + " " + tr.getTlname() + ".");
            cid = chooseCourse(cdao, inp);
            tr.setCourse(cdao.getCourseById(cid));
            if (!isTrainerExist(tr, tdao)) {
//                System.out.println(tr);
                tdao.insertTrainer(tr);
            } else {
                System.out.println("The trainer " + tr.getTfname() + " " + tr.getTlname() + " is already exists. Please try again.");
            }
            System.out.println("Do you want to input data for another trainer? Y or N");
            choice = inp.next();
            while (!choice.equalsIgnoreCase("n") && !choice.equalsIgnoreCase("y")) {
                System.out.println("You must input Y or N. Re-enter please.");
                choice = inp.next();
            }
        } while (!choice.equalsIgnoreCase("n"));
    }

    public static boolean isTrainerExist(Trainer newT, TrainerDao tdao) {
        boolean isexist = false;
        for (Trainer t : tdao.getTrainers()) {
            if (t.getTfname().equalsIgnoreCase(newT.getTfname()) && t.getTlname().equalsIgnoreCase(newT.getTlname())) {
                isexist = true;
            }
        }
        return isexist;
    }

    public void insertMenuAssignment() {
        String choice;
        System.out.println("OK. You choose to input an assignment to the database of school.");
        int cid;
        do {
            Assignment a = new Assignment();
            System.out.println("Give the title of the assignment");
            a.setAtitle(v.returnValidString(inp));
            System.out.println("Give the description of the assignment");
            a.setAdescr(v.returnValidString(inp));
            System.out.println("Choose from the below list, in which course " + a.getAtitle() + " belongs.");
            cid = chooseCourse(cdao, inp);
            a.setCourse(cdao.getCourseById(cid));
            System.out.println("Give the date of the " + a.getAtitle() + ". It must be in the period that " + cdao.getCourseById(cid).getCtitle() + " lasts.");
            LocalDate adate = LocalDate.parse(v.returnValidDate(inp));
            while (adate.isAfter(cdao.getCourseById(cid).getCend()) || adate.isBefore(cdao.getCourseById(cid).getCstart())) {
                System.out.println("The date of the " + a.getAtitle() + " must be in the period that " + cdao.getCourseById(cid).getCtitle() + " lasts. Re-enter please the date.");
                adate = LocalDate.parse(v.returnValidDate(inp));
            }
            a.setAdate(adate);
            if (!isAssignmentExist(a, adao)) {
//                System.out.println(a);
                adao.insertAssignment(a);
            } else {
                System.out.println("The assignment " + a.getAtitle() + "(" + a.getCourse().getCtitle() + ") is already exists. Please try again.");
            }
            System.out.println("Do you want to input data for another assignment? Y or N");
            choice = inp.next();
            while (!choice.equalsIgnoreCase("n") && !choice.equalsIgnoreCase("y")) {
                System.out.println("You must input Y or N. Re-enter please.");
                choice = inp.next();
            }
        } while (!choice.equalsIgnoreCase("n"));
    }

    public static boolean isAssignmentExist(Assignment newA, AssignmentDao adao) {
        boolean isexist = false;
        for (Assignment a : adao.getAssignments()) {
            if (a.getAtitle().equalsIgnoreCase(newA.getAtitle()) && a.getCourse().getCtitle().equalsIgnoreCase(newA.getCourse().getCtitle())) {
                isexist = true;
            }
        }
        return isexist;
    }

    public void insertMenuCourse() {
        String choice;
        System.out.println("OK. You choose to input a course to the database of school.");
        do {
            Course c = new Course();
            System.out.println("Give the title of the course");
            c.setCtitle(v.returnValidString(inp));
            System.out.println("Give the stream of the course " + c.getCtitle());
            c.setCstream(v.returnValidString(inp));
            System.out.println("Give the type of the course " + c.getCtitle());
            c.setCtype(v.returnValidString(inp));
            System.out.println("Give the start date of the course " + c.getCtitle());
            LocalDate startdate = LocalDate.parse(v.returnValidDate(inp));
            c.setCstart(startdate);
            System.out.println("Give the end date of the course " + c.getCtitle());
            LocalDate enddate = LocalDate.parse(v.returnValidDate(inp));
            while (enddate.isBefore(startdate)) {
                System.out.println("The end-date of the course must be after the start-date. Re-enter please the end-date.");
                enddate = LocalDate.parse(v.returnValidDate(inp));
            }
            c.setCend(enddate);
            if (!isCourseExist(c, cdao)) {
//                System.out.println(c);
                cdao.insertCourse(c);
            } else {
                System.out.println("The course " + c.getCtitle() + "(" + c.getCstream() + ") is already exists. Please try again.");
            }
            System.out.println("Do you want to input data for another course? Y or N");
            choice = inp.next();
            while (!choice.equalsIgnoreCase("n") && !choice.equalsIgnoreCase("y")) {
                System.out.println("You must input Y or N. Re-enter please.");
                choice = inp.next();
            }
        } while (!choice.equalsIgnoreCase("n"));
    }

    public static boolean isCourseExist(Course newC, CourseDao cdao) {
        boolean isexist = false;
        for (Course c : cdao.getCourses()) {
            if (c.getCtitle().equals(newC.getCtitle()) && c.getCstream().equals(newC.getCstream())) {
                isexist = true;
            }
        }
        return isexist;
    }

    public void insertMenuCourseStudent() {
        String choice;
        System.out.println("OK. You choose to input a classroom(student/course) to the database of school.");
        int cid, sid;
        do {
            System.out.println("First, you have to choose the course that student will take from the below list. ");
            cid = chooseCourse(cdao, inp);
            System.out.println("Then, choose the student from the below list that will take the course " + cdao.getCourseById(cid).getCtitle());
            sid = chooseStudent(sdao, inp);
            Classroom cl = new Classroom(cdao.getCourseById(cid), sdao.getStudentById(sid));
            if (!isClassroomExist(cl, cldao)) {
//                System.out.println(cl);
                cldao.insertClassroom(cl);
            } else {
                System.out.println("The classroom (" + cl.getCourse().getCtitle() + "--" + cl.getStudent().getSfname() + " " + cl.getStudent().getSlname() + ") is already exists. Please try again.");
            }
            System.out.println("Do you want to input data for another classroom? Y or N");
            choice = inp.next();
            while (!choice.equalsIgnoreCase("n") && !choice.equalsIgnoreCase("y")) {
                System.out.println("You must input Y or N. Re-enter please.");
                choice = inp.next();
            }
        } while (!choice.equalsIgnoreCase("n"));
    }

    public static boolean isClassroomExist(Classroom newCl, ClassroomDao cldao) {
        boolean isexist = false;
        for (Classroom cl : cldao.getClassroom()) {
            if (cl.getCourse().getCid() == newCl.getCourse().getCid() && cl.getStudent().getSid() == newCl.getStudent().getSid()) {
                isexist = true;
            }
        }
        return isexist;
    }

    public void insertMenuCourseTrainer() {
        String choice;
        System.out.println("OK. You choose to input trainer(s) for a course to the database of school.");
        int cid;
        do {
            System.out.println("First, you have to choose the course from the below list. ");
            cid = chooseCourse(cdao, inp);
            System.out.println("The trainers who teach this course until now are the below: ");
            tdao.printTrainers(tdao.getTrainerByCourse(cdao.getCourseById(cid)));
            System.out.println("Now, you must input more trainers for the course --> " + cdao.getCourseById(cid).getCtitle());
            do {
                Trainer tr = new Trainer();
                System.out.println("Give the fisrt name of the trainer");
                tr.setTfname(v.returnValidString(inp));
                System.out.println("Give the last name of the trainer");
                tr.setTlname(v.returnValidString(inp));
                System.out.println("Give the subject of the trainer");
                tr.setTsubject(v.returnValidString(inp));
                if (!isTrainerExist(tr, tdao)) {
                    tr.setCourse(cdao.getCourseById(cid));
//                    System.out.println(tr);
                    tdao.insertTrainer(tr);
                } else {
                    System.out.println("The trainer " + tr.getTfname() + " " + tr.getTlname() + " is already exists. Please try again.");
                }
                System.out.println("Do you want to input another trainer for " + cdao.getCourseById(cid).getCtitle() + "? Y or N");
                choice = inp.next();
                while (!choice.equalsIgnoreCase("n") && !choice.equalsIgnoreCase("y")) {
                    System.out.println("You must input Y or N. Re-enter please.");
                    choice = inp.next();
                }
            } while (!choice.equalsIgnoreCase("n"));
            System.out.println("Do you want to input trainers for another course? Y or N");
            choice = inp.next();
            while (!choice.equalsIgnoreCase("n") && !choice.equalsIgnoreCase("y")) {
                System.out.println("You must input Y or N. Re-enter please.");
                choice = inp.next();
            }
        } while (!choice.equalsIgnoreCase("n"));
    }

    public void insertMenuStudentWork() {
        String choice;
        System.out.println("OK. You choose to input a classroom(student/course), with the assignments that has, to the database of school.");
        int cid, sid;
        do {
            System.out.println("First, you have to choose the course that student will take from the below list. ");
            cid = chooseCourse(cdao, inp);
            System.out.println("Then, choose the student from the below list that will take the course " + cdao.getCourseById(cid).getCtitle());
            sid = chooseStudent(sdao, inp);
            Classroom cl = new Classroom(cdao.getCourseById(cid), sdao.getStudentById(sid));
            if (!isClassroomExist(cl, cldao)) {
//                System.out.println(cl);
                cldao.insertClassroom(cl);
                List<Assignment> list = adao.getAssignmentByCourse(cdao.getCourseById(cid));
                for (Assignment a : list) {
                    StudentWork w = new StudentWork();
                    w.setClassroom(cl);
                    w.setAssignment(a);
                    System.out.println("Give the oral mark of the " + a.getAtitle() + "(" + cl.getStudent().getSfname() + " " + cl.getStudent().getSlname() + "/" + cl.getCourse().getCtitle() + ")");
                    w.setOral(v.returnValidInteger(inp));
                    System.out.println("Give the total mark of the " + a.getAtitle() + "(" + cl.getStudent().getSfname() + " " + cl.getStudent().getSlname() + "/" + cl.getCourse().getCtitle() + ")");
                    w.setTotal(v.returnValidInteger(inp));
//                    System.out.println(w);
                    wdao.insertWork(w);
                }
            } else {
                if (isStudentWorkNotExist(cl.getClid(), wdao)) {
                    List<Assignment> list = adao.getAssignmentByCourse(cdao.getCourseById(cid));
                    for (Assignment a : list) {
                        StudentWork w = new StudentWork();
                        w.setClassroom(cl);
                        w.setAssignment(a);
                        System.out.println("Give the oral mark of the " + a.getAtitle() + "(" + cl.getStudent().getSfname() + " " + cl.getStudent().getSlname() + "/" + cl.getCourse().getCtitle() + ")");
                        w.setOral(v.returnValidInteger(inp));
                        System.out.println("Give the total mark of the " + a.getAtitle() + "(" + cl.getStudent().getSfname() + " " + cl.getStudent().getSlname() + "/" + cl.getCourse().getCtitle() + ")");
                        w.setTotal(v.returnValidInteger(inp));
//                        System.out.println(w);
                        wdao.insertWork(w);
                    }
                } else {
                    System.out.println("The assignments for student " + cl.getStudent().getSfname() + " " + cl.getStudent().getSlname() + " are already exist.");
                }
            }
            System.out.println("Do you want to input data for another student/work? Y or N");
            choice = inp.next();
            while (!choice.equalsIgnoreCase("n") && !choice.equalsIgnoreCase("y")) {
                System.out.println("You must input Y or N. Re-enter please.");
                choice = inp.next();
            }
        } while (!choice.equalsIgnoreCase("n"));
    }

    public static boolean isStudentWorkNotExist(int clid, StudentWorkDao wdao) {
        boolean isexist = false;
        for (StudentWork w : wdao.getStudentWork()) {
            if (w.getClassroom().getClid() == clid) {
                isexist = true;
            }
        }
        return isexist;
    }

    public static int chooseCourse(CourseDao cdao, Scanner inp) {
        int i;
        boolean isFirstTime = true;
        do {
            if (isFirstTime) {
                cdao.printCourses(cdao.getCourses());
                System.out.println("Please input a number that matches with the row of list.");
                isFirstTime = false;
            } else {
                System.out.println("Wrong number.Please enter a number from the below list.");
                cdao.printCourses(cdao.getCourses());
            }
            while (!inp.hasNextInt()) {
                System.out.println("Your input must be a number. Enter number");
                inp.next();
            }
            i = inp.nextInt();
        } while (i < 1 || i > cdao.getCourses().size());
        return i;
    }

    public static int chooseStudent(StudentDao sdao, Scanner inp) {
        int i;
        boolean isFirstTime = true;
        do {
            if (isFirstTime) {
                sdao.printStudents(sdao.getStudents());
                System.out.println("Please input a number that matches with the row of list.");
                isFirstTime = false;
            } else {
                System.out.println("Wrong number.Please enter a number from the below list.");
                sdao.printStudents(sdao.getStudents());
            }
            while (!inp.hasNextInt()) {
                System.out.println("Your input must be a number. Enter number");
                inp.next();
            }
            i = inp.nextInt();
        } while (i < 1 || i > sdao.getStudents().size());
        return i;
    }

    public static int chooseTrainer(TrainerDao tdao, Scanner inp) {
        int i;
        boolean isFirstTime = true;
        do {
            if (isFirstTime) {
                tdao.printTrainers(tdao.getTrainers());
                System.out.println("Please input a number that matches with the row of list.");
                isFirstTime = false;
            } else {
                System.out.println("Wrong number.Please enter a number from the below list.");
                tdao.printTrainers(tdao.getTrainers());
            }
            while (!inp.hasNextInt()) {
                System.out.println("Your input must be a number. Enter number");
                inp.next();
            }
            i = inp.nextInt();
        } while (i < 1 || i > tdao.getTrainers().size());
        return i;
    }

    public static int chooseAssignment(AssignmentDao adao, Scanner inp) {
        int i;
        boolean isFirstTime = true;
        do {
            if (isFirstTime) {
                adao.printAssignments(adao.getAssignments());
                System.out.println("Please input a number that matches with the row of list.");
                isFirstTime = false;
            } else {
                System.out.println("Wrong number.Please enter a number from the below list.");
                adao.printAssignments(adao.getAssignments());
            }
            while (!inp.hasNextInt()) {
                System.out.println("Your input must be a number. Enter number");
                inp.next();
            }
            i = inp.nextInt();
        } while (i < 1 || i > adao.getAssignments().size());
        return i;
    }

    public void noAccess() {
        System.out.println("Sorry, you tried 3 times with no success.");
        System.out.println("You must log in if you want to access the DataBase.");
        System.out.println("Try another time.");
    }

    public boolean returnToStart() {
        boolean returnStart = false;
        System.out.println("Do you want to return to the basic menu or log out from the application?\nPlease enter 'Y'(basic menu) or 'N'(log out)");
        String choice = inp.next();
        while (!choice.equalsIgnoreCase("n") && !choice.equalsIgnoreCase("y")) {
            System.out.println("You must input Y or N. Re-enter please.");
            choice = inp.next();
        }
        if (choice.equalsIgnoreCase("y")) {
            returnStart = true;
        } else {
            System.out.println("Thanks for using the school DataBase.\nLog out process......................");
        }
        return returnStart;
    }
}
