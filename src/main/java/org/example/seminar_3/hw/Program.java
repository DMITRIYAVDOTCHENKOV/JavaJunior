package org.example.seminar_3.hw;
//
//  Разработайте класс Student с полями String name, int age, transient double GPA (средний балл).
//  Обеспечьте поддержку сериализации для этого класса. Создайте объект класса Student и инициализируйте
//  его данными. Сериализуйте этот объект в файл. Десериализуйте объект обратно в программу из файла.
//  Выведите все поля объекта, включая GPA, и обсудите, почему значение GPA не было сохранено/восстановлено.




import java.io.*;

public class Program {
    public static void main(String[] args) throws IOException, ClassNotFoundException {


        Student student1 = new Student("Станислав", 37, 6.3);

        try(FileOutputStream fileOutputStream = new FileOutputStream("student.bin");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
            objectOutputStream.writeObject(student1);
            System.out.println("Объект UserData сериализован.");
        }

        try(FileInputStream fileInputStream = new FileInputStream("student.bin");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
            student1 = (Student) objectInputStream.readObject();
            System.out.println("Объект Student десериализован.");
        }

        System.out.println("Объект Student десериализован.");
        System.out.println("Имя: " + student1.getName());
        System.out.println("Возраст: " + student1.getAge());
        System.out.println("GPA (должен быть null, так как transient): " + student1.getGPA());

    }

}
