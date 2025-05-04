import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int LIMIT_USERS = 1;
        boolean WORKING = true;

        Scanner scanner = new Scanner(System.in);
        ArrayList<User> users = new ArrayList<>();

        do {

            System.out.printf("-".repeat(50) + "%n");

            int NumbersOfUsers = users.size();
            System.out.println("Sistema de cadrasto simples - Vagas disponiveis: " + (LIMIT_USERS - NumbersOfUsers));
            if(NumbersOfUsers < LIMIT_USERS ) {
                System.out.println("1 - Adicionar usuario.");
            } else {
                System.out.println("1 - Adicionar usuario. (LOTADO)");
            }
            System.out.println("2 - Ver usuarios.");
            System.out.println("3 - Excluir usuarios.");
            System.out.println("4 - Sair");
            System.out.print("Escolha: ");

            int option = scanner.nextInt();


            switch(option){
                case 1:
                    if (NumbersOfUsers == LIMIT_USERS) {
                        System.out.println("Não é possivel adicionar mais nenhum usuario");
                        continue;
                    }
                    User TempUserInformation = new User();

                    System.out.print("Digite o nome: ");
                    String name = scanner.next();
                    TempUserInformation.setName(name);

                    System.out.print("Digite a idade: ");
                    int age = scanner.nextInt();
                    TempUserInformation.setAge(age);

                    // Ele é do Job xD
                    System.out.print("Digite o trabalho: ");
                    String job = scanner.next();
                    TempUserInformation.setJob(job);

                    // Fiz esse replace, por que dependendo da
                    // localidade o java da erro se o numero é com . ou ,;
                    System.out.print("Digite o salario: ");
                    String salaryInString = scanner.next();
                    double salary = Double.parseDouble(salaryInString.replace(",","."));
                    TempUserInformation.setSalary(salary);

                    users.add(TempUserInformation);

                    System.out.printf("%s foi adicionado ao sistema!%n", name);

                    continue;
                case 2:
                    System.out.printf("-".repeat(50) + "%n");
                    System.out.printf("Total de %d cadastrados!%n", NumbersOfUsers);
                    for (User TempUser : users) {
                        System.out.printf("-".repeat(50) + "%n");
                        System.out.println("Nome: " + TempUser.getName());
                        System.out.println("Idade: " + TempUser.getAge());
                        System.out.println("Trabalho: " + TempUser.getJob());
                        System.out.println("Salario: " + TempUser.getSalary());

                    }
                    continue;
                case 4:
                    System.out.println("Saindo...");
                    WORKING = false;
                default:
                    continue;


            }
        } while (WORKING);

    }
}

class User {
    private String name;
    private int age;
    private String job;
    private double salary;

    // SET functions
    void setName(String name){
        this.name = name;
    }
    void setAge(int age){
        this.age = age;
    }
    void setJob(String job){
        this.job = job;
    }
    void setSalary(double salary){
        this.salary = salary;
    }

    //GET functions
    String getName() {
        return this.name;
    }
    int getAge() {
        return this.age;
    }
    String getJob() {
        return this.job;
    }
    double getSalary() {
        return this.salary;
    }
}