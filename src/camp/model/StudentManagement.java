package camp.model;

import java.util.Scanner;

public class StudentManagement {
    Scanner sc = new Scanner(System.in);
    Student student;

    // 수강생 등록
    public void createStudent() {
        int count = 1;
        int input;

        System.out.println("\n수강생을 등록합니다...");
        System.out.print("수강생 이름 입력: ");

        String studentName = sc.next();
        // 기능 구현 (필수 과목, 선택 과목)
        Subject subject;
        String[] mandatory = new String[3];
        String[] choice = new String[2];
        String comparesubject = "";

        //수강생 상태를 추가 할때 컨디션(상태) 추가!, 기본값 Yellow
        student = new Student(InitData.sequence(InitData.getIndexTypeStudent()), studentName);

        String studentId = student.getStudentId();

        for (int i = 0; i < mandatory.length; i++) {
            System.out.println("==================================");
            System.out.println("필수 과목 3과목을 입력해 주세요..." + count + " 번째");
            System.out.println("1. Java");
            System.out.println("2. 객체지향");
            System.out.println("3. Spring");
            System.out.println("4. JPA");
            System.out.println("5. MySQL");

            input = sc.nextInt();

            if(comparesubject.contains(Integer.toString(input))){
                System.out.println("중복 값이 있습니다. 다시 시도해주세요");
                i--;
                continue;
            }

            switch (input) {
                case 1:
                    mandatory[i] = "Java";
                    subject = new Subject(InitData.sequence(InitData.getIndexTypeSubject()), mandatory[i], InitData.getSubjectTypeMandatory(), studentId);
                    InitData.getSubjectStore().add(subject);
                    comparesubject = comparesubject + input;
                    break;
                case 2:
                    mandatory[i] = "객체지향";
                    subject = new Subject(InitData.sequence(InitData.getIndexTypeSubject()), mandatory[i], InitData.getSubjectTypeMandatory(), studentId);
                    InitData.getSubjectStore().add(subject);
                    comparesubject = comparesubject + input;
                    break;
                case 3:
                    mandatory[i] = "Spring";
                    subject = new Subject(InitData.sequence(InitData.getIndexTypeSubject()), mandatory[i], InitData.getSubjectTypeMandatory(), studentId);
                    InitData.getSubjectStore().add(subject);
                    comparesubject = comparesubject + input;
                    break;
                case 4:
                    mandatory[i] = "JPA";
                    subject = new Subject(InitData.sequence(InitData.getIndexTypeSubject()), mandatory[i], InitData.getSubjectTypeMandatory(), studentId);
                    InitData.getSubjectStore().add(subject);
                    comparesubject = comparesubject + input;
                    break;
                case 5:
                    mandatory[i] = "MySQL";
                    subject = new Subject(InitData.sequence(InitData.getIndexTypeSubject()), mandatory[i], InitData.getSubjectTypeMandatory(), studentId);
                    InitData.getSubjectStore().add(subject);
                    comparesubject = comparesubject + input;
                    break;
                default:
                    System.out.println("잘못 입력하셨습니다");
                    i--;
                    count--;
                    break;
            }
            count++;
        }

        //선택 과목 진행을 위해 초기화
        comparesubject = "";
        count = 1;

        for (int i = 0; i < choice.length; i++) {
            System.out.println("==================================");
            System.out.println("선택 과목 2과목을 입력해 주세요..." + count + " 번째");
            System.out.println("1. 디자인 패턴");
            System.out.println("2. Spring Security");
            System.out.println("3. Redis");
            System.out.println("4. MongoDB");
            input = sc.nextInt();

            if(comparesubject.contains(Integer.toString(input))){
                System.out.println("중복 값이 있습니다. 다시 시도해주세요");
                i--;
                continue;
            }

            switch (input) {
                case 1:
                    choice[i] = "디자인 패턴";
                    subject = new Subject(InitData.sequence(InitData.getIndexTypeSubject()), choice[i], InitData.getSubjectTypeChoice(), studentId);
                    InitData.getSubjectStore().add(subject);
                    comparesubject = comparesubject + input;
                    break;
                case 2:
                    choice[i] = "Spring Security";
                    subject = new Subject(InitData.sequence(InitData.getIndexTypeSubject()), choice[i], InitData.getSubjectTypeChoice(), studentId);
                    InitData.getSubjectStore().add(subject);
                    comparesubject = comparesubject + input;
                    break;
                case 3:
                    choice[i] = "Redis";
                    subject = new Subject(InitData.sequence(InitData.getIndexTypeSubject()), choice[i], InitData.getSubjectTypeChoice(), studentId);
                    InitData.getSubjectStore().add(subject);
                    comparesubject = comparesubject + input;
                    break;
                case 4:
                    choice[i] = "MongoDB";
                    subject = new Subject(InitData.sequence(InitData.getIndexTypeSubject()), choice[i], InitData.getSubjectTypeChoice(), studentId);
                    InitData.getSubjectStore().add(subject);
                    comparesubject = comparesubject + input;
                    break;
                default:
                    System.out.println("잘못 입력하셨습니다");
                    i--;
                    count--;
                    break;
            }
            count++;
        }

        // 수강생 인스턴스 생성 예시 코드
        // 기능 구현

        InitData.getStudentStore().add(student);

        System.out.println(student.getStudentName() + "," + student.getStudentId());

        for (Subject s : InitData.getSubjectStore()) {
            if (s.getStudentId().equals(studentId)) {
                System.out.println(s.getSubjectName() + ',' + s.getSubjectId() + ',' + s.getSubjectType());
            }
        }

        System.out.println("수강생 등록 성공!\n");
    }

    public void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");
        // 기능 구현
        InitData.getStudentStore().forEach(student -> System.out.println("학생번호 : " + student.getStudentId() +
                " 이름 : " + student.getStudentName() + " 상태 : " + student.getCondition()));
        System.out.println("\n수강생 목록 조회 성공!");
    }

    public void adjustStudent() {
        int input = 0;
        System.out.println("==================================");
        System.out.println("수강생의 정보를 변경하기 위한 창입니다. 변경을 원하시는 데이터를 입력해주세요.");
        System.out.println("1. 이름 ");
        System.out.println("2. 상태 ");
        System.out.println("3. 메인메뉴로 돌아갑니다. ");
        input = sc.nextInt();

        switch (input) {
            case 1 -> adjustStudentName();
            case 2 -> adjustStudentCondition();
            case 3 -> System.out.println("메인 화면 이동...");
            default -> System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
        }

    }
    //학생 이름 수정 메서드(빈 리스트 및 ST로 미입력시 fail, 잘못 입력했을때의 구현은x)
    public void adjustStudentName() {
        String stid;
        String stname;
        if (InitData.getStudentStore().isEmpty()) {
            System.out.println("수강생 목록이 비어 있습니다. 수강생 등록 먼저 진행해 주세요");
        } else {
            System.out.println("==================================");
            System.out.println("이름 변경하기를 선택 하셨습니다. 변경하실 이름의 수강생 번호(ST%)를 입력 해주세요");
            stid = sc.next();

            if (stid.contains("ST")) {
                System.out.println("변경전 이름 ");
                for (Student s : InitData.getStudentStore()) {
                    if (s.getStudentId().equals(stid)) {
                        System.out.println(s.getStudentName());
                    }
                }

                System.out.println("변경하실 이름을 입력 해주세요");
                stname = sc.next();

                System.out.println("변경후 이름 이름 ");
                for (Student s : InitData.getStudentStore()) {
                    if (s.getStudentId().equals(stid)) {
                        s.setStudentName(stname);
                        System.out.println(s.getStudentName());
                    }
                }
                System.out.println("변경완료 ");
            } else {
                System.out.println("잘못 입력 하셨습니다.");
            }
        }
    }
    //수강생 상태 변경하는 메서드
    public void adjustStudentCondition() {

        String stid;
        int input = 0;
        //boolean check = true;
        if (InitData.getStudentStore().isEmpty()) {
            System.out.println("수강생 목록이 비어 있습니다. 수강생 등록 먼저 진행해 주세요");
        } else {
            System.out.println("==================================");
            System.out.println("상태 변경하기를 선택 하셨습니다. 변경하실 이름의 수강생 번호(ST%)를 입력 해주세요");
            stid = sc.next();
            if (stid.contains("ST")) {

                for (Student s : InitData.getStudentStore()) {
                    if (s.getStudentId().equals(stid)) {
                        System.out.println("변경전 상태");
                        System.out.println(s.getCondition());
                    }
                }


                System.out.println("어떤 상태로 변경 하시겠습니까?");
                System.out.println("1. Green : Good");
                System.out.println("2. Yellow : So So");
                System.out.println("3. Red : Bad");

                input = sc.nextInt();


                for (Student s : InitData.getStudentStore()) {
                    if (s.getStudentId().equals(stid)) {
                        switch (input) {
                            case 1:
                                s.setCondition(InitData.getConditionGreen());
                                System.out.println("변경을 완료 했습니다.");
                                break;
                            case 2:
                                s.setCondition(InitData.getConditionYellow());
                                System.out.println("변경을 완료 했습니다.");
                                break;
                            case 3:
                                s.setCondition(InitData.getConditionRed());
                                System.out.println("변경을 완료 했습니다.");
                                break;
                            default:
                                System.out.println("입력을 잘못 하셨습니다.");
                                break;
                        }
                    }
                }
            } else {
                System.out.println("잘못 입력 하셨습니다.");
            }
        }
    }

    public void conditionList(){
        int input ;
        System.out.println("==================================");
        System.out.println("상태별로 수강생들의 리스트를 확인 할수 있는 화면 입니다.");
        System.out.println("어떤 상태의 리스트를 확인 해보겠습니까?.");
        System.out.println("1. Green : Good");
        System.out.println("2. Yellow : So So");
        System.out.println("3. Red : Bad");
        input =sc.nextInt();


        switch (input) {
            case 1:
                System.out.println("컨디션이 좋은 학생 리스트 입니다.");
                conditionDetail(InitData.getConditionGreen());
                break;
            case 2:
                System.out.println("컨디션이 보통인 학생 리스트 입니다.");
                conditionDetail(InitData.getConditionYellow());
                break;
            case 3:
                System.out.println("컨디션이 안좋은 학생 리스트 입니다.");
                conditionDetail(InitData.getConditionRed());
                break;
            default:
                System.out.println("입력을 잘못 하셨습니다.");
                break;
        }
    }

    public void conditionDetail(String condition){
        for (Student s : InitData.getStudentStore()) {
            if (s.getCondition().equals(condition)) {
                System.out.println("이름 : " + s.getStudentName() + " / " + "상태 : " + s.getCondition() + " / " + "수강생 번호 : " + s.getStudentId());
            }
        }
    }
}

