package demo;

public class demo01 {
    public static void main(String[] args) {
        Person personA = new Person();
        personA.name = "A";
        Person personB = personA;
        System.out.println(personB.getName());
    }
}

class Person {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
