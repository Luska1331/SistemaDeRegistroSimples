import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final int MAX_USERS = 5;
        boolean WORKING = true;

        Scanner scanner = new Scanner(System.in);
        ArrayList<User> users = new ArrayList<>();

        do {

            System.out.printf("-".repeat(50) + "%n");

            int NumbersOfUsers = users.size();
            System.out.println("Sistema de cadrasto simples - Vagas disponiveis: " + (MAX_USERS - NumbersOfUsers));
            if(NumbersOfUsers < MAX_USERS ) {
                System.out.println("1 - Adicionar usuario.");
            } else {
                System.out.println("1 - Adicionar usuario. (LOTADO)");
            }
            System.out.println("2 - Ver usuarios.");
            System.out.println("3 - Excluir usuarios.");
            System.out.println("4 - Sair");
            System.out.print("Escolha: ");

            int option;
            try {
                option = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Opção invalida, tente novamente");
                scanner.next();
                continue;
            }


            switch(option){
                case 1:
                    if (NumbersOfUsers == MAX_USERS) {
                        System.out.println("Não é possivel adicionar mais nenhum usuario");
                        continue;
                    }
                    String name;
                    int age = 0;
                    String job = "";
                    double salary;

                    User TempUserInformation = new User();

                    System.out.print("Digite o nome: ");
                    try {
                        name = scanner.next();
                    } catch (Exception e){
                        System.out.println("Nome invalido");
                        scanner.next();
                        continue;
                    }
                    TempUserInformation.setName(name);

                    System.out.print("Digite a idade: ");
                    try {
                        age = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Idade invalida");
                        scanner.next();
                        continue;
                    } catch (Exception e ) {
                        System.out.println(e.getMessage());
                    }

                    TempUserInformation.setAge(age);

                    // Ele é do Job xD
                    System.out.print("Digite o trabalho: ");

                    try {
                        job = scanner.next();
                    } catch (InputMismatchException e) {
                        System.out.println("Trabalho invalido");
                        scanner.next();
                        continue;
                    } catch (Exception e ) {
                        System.out.println(e.getMessage());
                    }

                    TempUserInformation.setJob(job);

                    // Fiz esse replace, por que dependendo da
                    // localidade o java da erro se o numero é com . ou ,;
                    System.out.print("Digite o salario: ");
                    try {
                        String salaryInString = scanner.next();
                        salary = Double.parseDouble(salaryInString.replace(",","."));
                    } catch (NumberFormatException e) {
                        System.out.println("Salario invalido!");
                        scanner.next();
                        continue;
                    }

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
                case 3:
                    if (NumbersOfUsers == 0 ) {
                        System.out.println("Nenhum usuario detectado!");
                        continue;
                    }
                    System.out.println("Lista de usuarios:");
                    for (int i = 0; i < NumbersOfUsers; i++) {
                        User TempUser = users.get(i);
                        System.out.printf("%d. %s%n",i, TempUser.getName());
                        System.out.println("-".repeat(50));
                    }

                    try {
                        int optionDelPerson = scanner.nextInt();
                        if (NumbersOfUsers < optionDelPerson) {
                            System.out.println("Usuario inexistente.");
                        } else {
                            System.out.println(users.get(optionDelPerson).getName() + " foi deletado!");
                            users.remove(optionDelPerson);
                        }
                        continue;
                    } catch (InputMismatchException e) {
                        System.out.println("Use apenas numeros!");
                    }
                case 4:
                    System.out.println("Saindo...");
                    WORKING = false;
                    continue;
                default:
                    System.out.println("Opção invalida. Por favor insira a opção correta!");
                    break;


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