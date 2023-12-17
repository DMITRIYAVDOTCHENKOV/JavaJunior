package org.example.seminar_4.hw;


import org.example.seminar_4.models.Student;

import javax.persistence.*;
import java.util.Random;

@Entity
@Table(name = "courses")
public class Course {

    private static final String[] names = new String[]{"Химия", "Математика", "физ-ра", "Алгебра", "Геометрия"};
    private static final Random random = new Random();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private int duration;

    public Course() {

    }

    public Course(int id, String title, int duration) {
        this.id = id;
        this.title = title;
        this.duration = duration;
    }

    public Course(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public static Course create() {
        return new Course(names[random.nextInt(names.length)], random.nextInt(20, 26));
    }


    public void updateDuration() {
        duration = random.nextInt(20, 26);
    }

    public void updateTitle() {
        title = names[random.nextInt(names.length)];
    }


    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                '}';
    }
}



