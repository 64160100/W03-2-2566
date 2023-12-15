import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Student {
    private String name;
    private int score;
    private String grade;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
        setGrade();
    }

    private void setGrade() {
        if (score >= 90) {
            grade = "A";
        } else if (score >= 80) {
            grade = "B";
        } else if (score >= 70) {
            grade = "C";
        } else if (score >= 60) {
            grade = "D";
        } else {
            grade = "F";
        }
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public String getGrade() {
        return grade;
    }
}

public class main {

    public static void main(String[] args) throws IOException {

        String filePath = "PupilScore.csv";
        BufferedReader reader = null;
        FileReader fileReader = null;
        List<Student> students = new ArrayList<>();

        try {
            fileReader = new FileReader(filePath);
            reader = new BufferedReader(fileReader);

            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");

                // ตรวจสอบรูปแบบคะแนน
                try {
                    int score = Integer.parseInt(columns[2]);
                    Student student = new Student(columns[0], score);
                    students.add(student);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid score format for " + columns[0]);
                }
            }

            // เรียงลำดับตามคะแนนจากมากไปน้อย
            Collections.sort(students, Comparator.comparingInt(Student::getScore).reversed());

            // แสดงผลลัพธ์
            for (Student student : students) {
                System.out.println(student.getName() + ": " + student.getGrade());
            }

        } finally {
            if (reader != null) {
                reader.close();
            }
            if (fileReader != null) {
                fileReader.close();
            }
        }
    }
}
