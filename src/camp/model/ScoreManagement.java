package camp.model;

import java.util.*;

public class ScoreManagement {
    Scanner sc = new Scanner(System.in);
    //점수 등록
    public void createScore(){
        int score = 0;
        String studentId = getStudentId();
        studentId = validationStudentId(studentId);

        if(studentId != null){
            int count = 0;
            ArrayList<Subject> sub = new ArrayList<>();
            for (Subject subject : InitData.getSubjectStore()) {
                if(subject.getStudentId().equals(studentId)){
                    count++;
                    sub.add(subject);
                    System.out.println(count+"."+subject.getSubjectName() );
                }
            }

            System.out.println("과목을 입력하새요 : ");
            int selectNum = sc.nextInt();
            Subject sub2 = null;
            sub2 = sub.get(selectNum-1);
            int times = getTestTimes(sub2.getStudentId(),sub2.getSubjectId());
            System.out.println(sub2.getSubjectName());
            System.out.println("점수를 입력하세요 : ");
            score = sc.nextInt();
            char rank = calculateGrade(score, Objects.requireNonNull(sub2).getSubjectType());
            InitData.getScoreStore().add(new Score(InitData.sequence(InitData.getIndexTypeScore()),sub2.getSubjectId(),studentId,times,score,rank));

            System.out.println("점수 등록 완료!!");

        }else{
            System.out.println("없는 학생번호 입니다.");
        }

    }

    //학생번호 검증
    public String validationStudentId(String studentId){
        for (Student student : InitData.getStudentStore()) {
            if(studentId.equals(student.getStudentId())){
                return studentId;
            }
        }
        return null;
    }


    public char calculateGrade(int score, String subjectType) {
        if (subjectType.equals(InitData.getSubjectTypeChoice())) {
            return calculateChoiceSubjectGrade(score);
        } else if (subjectType.equals(InitData.getSubjectTypeMandatory())) {
            return calculateMandatorySubjectGrade(score);
        } else {
            return 'X';
        }
    }
    // 필수 과목 등급 계산
    public char calculateMandatorySubjectGrade(int score) {
        if (score >= 95) {
            return 'A';
        } else if (score >= 90) {
            return 'B';
        } else if (score >= 80) {
            return 'C';
        } else if (score >= 70) {
            return 'D';
        } else if (score >= 60) {
            return 'F';
        } else {
            return 'N';
        }
    }

    // 선택 과목 등급 계산
    public char calculateChoiceSubjectGrade(int score) {
        if (score >= 90) {
            return 'A';
        } else if (score >= 80) {
            return 'B';
        } else if (score >= 70) {
            return 'C';
        } else if (score >= 60) {
            return 'D';
        } else if (score >= 50) {
            return 'F';
        } else {
            return 'N';
        }
    }

    // 수강생의 과목별 회차 점수 수정
    public void updateRoundScoreBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        studentId = validationStudentId(studentId);
        // 해당 수강생이 존재하는지 확인
        Student student = findStudentById(studentId);
        if (student != null) {
            System.out.println("\n수정할 과목을 선택하세요:");
            List<Subject> sub = displaySubjects(studentId); // 모든 과목 목록을 보여줌

            System.out.print("과목 번호를 입력하세요: ");
            int subjectIndex = sc.nextInt();

            // 입력한 번호에 해당하는 과목을 찾아옴
            Subject selectedSubject = sub.get(subjectIndex - 1);
            System.out.println(selectedSubject.getSubjectName());
            if (selectedSubject != null) {

                System.out.print("수정할 회차를 입력하세요: ");
                int round = sc.nextInt();
                // 해당 수강생의 해당 과목 점수 조회


                System.out.print("수정할 점수를 입력하세요: ");
                int newScore = sc.nextInt();
                boolean roundOk = false;
                for (Score score : InitData.getScoreStore()) {
                    // 기존 점수를 업데이트
                    if (score.getStudentId().equals(studentId) && score.getSubjectId().equals(selectedSubject.getSubjectId()) && score.getTimes() == round) {
                        score.setScore(newScore);
                        score.setRank(calculateGrade(newScore,selectedSubject.getSubjectType()));
                        roundOk = true;

                        break;
                    }
                }
                if (roundOk){
                    System.out.println("시험 점수가 수정되었습니다.");
                }else{
                    System.out.println("해당 회차가 없습니다.");
                }

            } else {
                System.out.println("\n해당 수강생의 " + selectedSubject.getSubjectName() + " 과목 시험 점수가 없습니다.");
            }
        } else {
            System.out.println("\n해당하는 학생을 찾을 수 없습니다.");
        }
    }

    // 모든 과목을 화면에 출력하는 메서드
    public ArrayList<Subject> displaySubjects(String studentId) {
        System.out.println("==================================");
        System.out.println("과목 목록:");
        ArrayList<Subject> sub = new ArrayList<>(); //조회용 과목들을 담을 리스트
        int count = 0;
        for (Subject subject : InitData.getSubjectStore()) {
            if(subject.getStudentId().equals(studentId)){
                count++;
                sub.add(subject);
                System.out.println(count+"."+subject.getSubjectName());
            }
        }
        return sub;
    }

    // 수강생 ID로 수강생 찾기
    public Student findStudentById(String studentId) {
        for (Student student : InitData.getStudentStore()) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    //회차별 특정과목 등급 조회
    public void inquireRoundGradeBySubject (){
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        // 기능 구현 (조회할 특정 과목)
        studentId = validationStudentId(studentId);
        if (studentId != null) {
            System.out.println("조회할 과목을 입력하세요 :");
            int count = 0;
            ArrayList<Subject> sub = new ArrayList<>(); //조회용 과목들을 담을 리스트
            for (Subject subject : InitData.getSubjectStore()) {
                if(subject.getStudentId().equals(studentId)){
                    count++;
                    sub.add(subject);
                    System.out.println(count+"."+subject.getSubjectName());
                }
            }

            int selectNum = 0;
            selectNum = sc.nextInt();

            Subject sub2;

            if (selectNum < 1 || selectNum > sub.size()) {
                System.out.println("유효하지 않은 선택입니다. 다시 시도하세요.");
                return;  // 인덱스 범위 벗어남을 방지하기 위해 종료
            }
            sub2 = sub.get(selectNum-1);

            System.out.println(sub2.getSubjectName());
            boolean subjectOk = false;
            //과목 유효성 검사
            for (Subject subject1 : InitData.getSubjectStore()) {
                if (sub2.getSubjectId().equals(subject1.getSubjectId())) {
                    System.out.println("과목 유효성 검사...");
                    subjectOk = true;
                    break;
                }
            }

            if (subjectOk) {
                System.out.println("회차별 등급을 조회합니다...");

                for (Score score1 : InitData.getScoreStore()) {
                    if (score1.getStudentId().equals(studentId) && score1.getSubjectId().equals(sub2.getSubjectId())) {
                        System.out.println(score1.getTimes() + "회차 " + "등급: " + score1.getRank());
                    }
                }
                System.out.println("\n등급 조회 성공!");
            }else{
                System.out.println("해당 과목이 존재하지 않습니다.");
            }
        } else {
            System.out.println("입력한 학생번호가 존재하지 않습니다.");
        }
    }

    //과목 별 평균 등급 조회
    public void inquireAverageRank(){
        String studentId = getStudentId();
        studentId = validationStudentId(studentId);
        if(studentId != null){
            System.out.println("\n평균을 조회할 과목을 선택하세요:");
            List<Subject> sub = displaySubjects(studentId); // 과목 목록을 보여줌

            System.out.print("과목 번호를 입력하세요: ");
            int subjectIndex = sc.nextInt();

            // 입력한 번호에 해당하는 과목을 찾아옴
            Subject selectedSubject = sub.get(subjectIndex - 1);
            System.out.println(selectedSubject.getSubjectName());
            if (selectedSubject != null) {
                ArrayList<Integer> studentScore = new ArrayList<>();
                for (Score score : InitData.getScoreStore()) {
                    if(studentId.equals(score.getStudentId()) && selectedSubject.getSubjectId().equals(score.getSubjectId())){
                        studentScore.add(score.getScore());
                    }
                }
                int average = (int)studentScore.stream().mapToInt(num -> num).summaryStatistics().getAverage();
                char averageRank = calculateGrade(average, selectedSubject.getSubjectType());
                System.out.println(selectedSubject.getSubjectName()+" 과목   평균점수 : " + average + " 평균등급 : " + averageRank);
            }else{
                System.out.println("해당 과목이 존재하지 않습니다.");
            }


        }else{
            System.out.println("입력한 학생번호가 존재하지 않습니다.");
        }

    }

    //시험 회차를 반환하는 메서드
    public int getTestTimes(String studentIdInput, String subjectIdInput){
        List<Score> scores = new ArrayList<>();
        for (Score score : InitData.getScoreStore()) {
            if(score.getStudentId().equals(studentIdInput) && score.getSubjectId().equals(subjectIdInput)){
                scores.add(score);
            }
        }
        return scores.size()+1;
    }

    public String getStudentId() {
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        return sc.next();
    }



}