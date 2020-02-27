package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.*;

public class Main extends Application {

    private static String getAName(Function<Employee, String> getName, Employee employee) {
        return getName.apply(employee);
    }

    private static void printEmployeeByAge(List<Employee> employees, String ageText, Predicate<Employee> ageCondition) {
        employees.forEach(employee -> {
            if (ageCondition.test(employee)) {
                System.out.println(employee.getFirstName() + " " + employee.getLastName() + " is " + ageText);
            } else {
                System.out.println(employee.getFirstName() + " " + employee.getLastName() + " is not " + ageText);
            }
        });
        IntPredicate greaterThan15 = i -> i > 15;
        IntPredicate lessThan100 = i -> i < 100;
        System.out.println(greaterThan15.test(10));

        int a = 20;
        System.out.println(greaterThan15.test(a + 5));
        System.out.println(greaterThan15.and(lessThan100).test(a));

        Random random = new Random();
        Supplier<Integer> randonSupplier = () -> random.nextInt(20);
        for (int i = 0; i < 10; i++) {
            System.out.println(randonSupplier.get());
        }
//        employees.forEach(employee -> {
//            String lastName=employee.getName().substring(employee.getName().indexOf(' ')+1);
//            System.out.println("Last Name is : "+lastName);
//
//        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        Employee clement = new Employee("Clément", "Duprat", 36);
        Employee john = new Employee("john", "marston", 30);
        Employee tim = new Employee("tim", "Buchalka", 22);

        List<Employee> employees = new ArrayList<>();
        employees.add(clement);
        employees.add(john);
        employees.add(tim);

        Function<Employee, String> getLastName = (Employee employee) -> {
            return employee.getName().substring(employee.getName().indexOf(' ') + 1);
        };
        String lastName = getLastName.apply(employees.get(1));
        System.out.println(lastName);
        Function<Employee, String> getFirstName = (Employee employee) -> {
            return employee.getName().substring(0, employee.getName().indexOf(" "));
        };
        Random random1 = new Random();
        for (Employee employee : employees) {
            if (random1.nextBoolean()) {
                System.out.println(getAName(getFirstName, employee));
            } else {
                System.out.println(getAName(getLastName, employee));
            }
        }

        Function<Employee, String> upperCase = employee -> employee.getName().toUpperCase();
        Function<String, String> firstName = name -> name.substring(0, name.indexOf(" "));
        Function chainedFunction = upperCase.andThen(firstName);

        System.out.println(chainedFunction.apply(employees.get(0)));

        BiFunction<String, Employee, String> concatAge = (String name, Employee employee) -> {
            return name.concat(" " + employee.getAge());
        };
        String upperName = upperCase.apply(employees.get(0));
        System.out.println(concatAge.apply(upperName, employees.get(0)));

        IntUnaryOperator incBy5 = i -> i + 5;
        System.out.println(incBy5.applyAsInt(10));
        Consumer<String> c1 = s -> s.toUpperCase();
        Consumer<String> c2 = s -> System.out.println(s);
        c1.andThen(c2).accept("Hello world");


//        employees.forEach(employee -> {
//            System.out.println(employee.getFirstName()+" "+employee.getLastName());
//            System.out.println(employee.getAge());
//        });
//        employees.forEach(employee -> {
//            if(employee.getAge()>30){
//                System.out.println(employee.getFirstName()+" "+employee.getLastName()+" is older than 30");
//            }else { System.out.println(employee.getFirstName()+" "+employee.getLastName()+" is younger than 30");}
//        });
//        printEmployeeByAge(employees,"older than 30",employee ->employee.getAge() >=30);
//        printEmployeeByAge(employees, "younger than 25 ", new Predicate<Employee>() {
//            @Override
//            public boolean test(Employee employee) {
//                return employee.getAge()<25;
//            }
//        });
//
    }
}
