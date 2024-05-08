package camp.model;

import java.util.Iterator;
import java.util.Scanner;

public class StudentManagement {
    Scanner sc = new Scanner(System.in);
    // 수강생 등록
    public void createStudent() {
        int count = 1;
        int input;
        System.out.println("\n수강생을 등록합니다...");
        System.out.print("수강생 이름 입력: ");

        String studentName = sc.next();
        // 기능 구현 (필수 과목, 선택 과목)
        Subject subject;
        String[] mandatory =new String[3];
        String[] choice =new String[2];
        Student student = new Student(InitData.sequence(InitData.getIndexTypeStudent()), studentName);
        String studentId = student.getStudentId();

        for (int i = 0 ; i < mandatory.length ; i++) {
            System.out.println("==================================");
            System.out.println("필수 과목 3과목을 입력해 주세요..."+count +" 번째");
            System.out.println("1. Java");
            System.out.println("2. 객체지향");
            System.out.println("3. Spring");
            System.out.println("4. JPA");
            System.out.println("5. MySQL");

            input = sc.nextInt();
            switch (input) {
                case 1 :
                    mandatory[i] = "Java";
                    subject = new Subject(InitData.sequence(InitData.getIndexTypeSubject()), mandatory[i],InitData.getSubjectTypeMandatory(),studentId);
                    InitData.getSubjectStore().add(subject);
                    break;
                case 2 :
                    mandatory[i] = "객체지향";
                    subject = new Subject(InitData.sequence(InitData.getIndexTypeSubject()), mandatory[i],InitData.getSubjectTypeMandatory(),studentId);
                    InitData.getSubjectStore().add(subject);
                    break;
                case 3 :
                    mandatory[i] = "Spring";
                    subject = new Subject(InitData.sequence(InitData.getIndexTypeSubject()), mandatory[i],InitData.getSubjectTypeMandatory(),studentId);
                    InitData.getSubjectStore().add(subject);
                    break;
                case 4 :
                    mandatory[i] = "JPA";
                    subject = new Subject(InitData.sequence(InitData.getIndexTypeSubject()), mandatory[i],InitData.getSubjectTypeMandatory(),studentId);
                    InitData.getSubjectStore().add(subject);
                    break;
                case 5 :
                    mandatory[i] = "MySQL";
                    subject = new Subject(InitData.sequence(InitData.getIndexTypeSubject()), mandatory[i],InitData.getSubjectTypeMandatory(),studentId);
                    InitData.getSubjectStore().add(subject);
                    break;
                default:
                    System.out.println("잘못 입력하셨습니다");
                    break;
            }
            count++;
        }
        count = 1;
        for (int i = 0 ; i < choice.length ; i++) {
            System.out.println("==================================");
            System.out.println("선택 과목 2과목을 입력해 주세요..." + count + " 번째");
            System.out.println("1. 디자인 패턴");
            System.out.println("2. Spring Security");
            System.out.println("3. Redis");
            System.out.println("4. MongoDB");
            input = sc.nextInt();

            switch (input) {
                case 1:
                    choice[i] = "디자인 패턴";
                    subject = new Subject(InitData.sequence(InitData.getIndexTypeSubject()), choice[i],InitData.getSubjectTypeChoice(),studentId);
                    InitData.getSubjectStore().add(subject);
                    break;
                case 2:
                    choice[i] = "Spring Security";
                    subject = new Subject(InitData.sequence(InitData.getIndexTypeSubject()), choice[i],InitData.getSubjectTypeChoice(),studentId);
                    InitData.getSubjectStore().add(subject);
                    break;
                case 3:
                    choice[i] = "Redis";
                    subject = new Subject(InitData.sequence(InitData.getIndexTypeSubject()), choice[i],InitData.getSubjectTypeChoice(),studentId);
                    InitData.getSubjectStore().add(subject);
                    break;
                case 4:
                    choice[i] = "MongoDB";
                    subject = new Subject(InitData.sequence(InitData.getIndexTypeSubject()), choice[i],InitData.getSubjectTypeChoice(),studentId);
                    InitData.getSubjectStore().add(subject);
                    break;
                default:
                    System.out.println("잘못 입력하셨습니다");
                    break;
            }
            count++;
        }

        // 수강생 인스턴스 생성 예시 코드
        // 기능 구현

        InitData.getStudentStore().add(student);

        System.out.println(student.getStudentName()+","+student.getStudentId());

        for(Subject s : InitData.getSubjectStore()){
            System.out.println(s.getSubjectName()+','+s.getSubjectId()+','+s.getSubjectType());
        }
        System.out.println("수강생 등록 성공!\n");
    }
    public void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");
        // 기능 구현
        InitData.getStudentStore().forEach(student -> System.out.println("학생번호 : "+student.getStudentId() +
                " 이름 : "+ student.getStudentName()));
        System.out.println("\n수강생 목록 조회 성공!");
    }

    // 수강생 삭제
    public void deleteStudent() {
        System.out.println("\n수강생을 삭제합니다...");
        System.out.print("삭제할 수강생의 학생번호를 입력하세요: ");
        String studentId = sc.next();

        Iterator<Student> studentIterator = InitData.getStudentStore().iterator();
        while (studentIterator.hasNext()) {
            Student student = studentIterator.next();
            if (student.getStudentId().equals(studentId)) {
                // 해당 수강생의 모든 점수 기록 삭제
                Iterator<Subject> subjectIterator = InitData.getSubjectStore().iterator();
                while (subjectIterator.hasNext()) {
                    Subject subject = subjectIterator.next();
                    if (subject.getStudentId().equals(studentId)) {
                        subjectIterator.remove();
                    }
                }
                studentIterator.remove();
                System.out.println("수강생 삭제 완료!");
                return;
            }
        }
        System.out.println("해당하는 학생을 찾을 수 없습니다.");
    }

}
