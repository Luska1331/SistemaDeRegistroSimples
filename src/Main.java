import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final int MAX_USERS = 5; // Obrigatório;

        InputHandler input = new InputHandler();
        ArrayList<User> users = new ArrayList<>();

        while (true){
            final String MENU_MESSAGE = " 1 - Adicionar usuário.%n" +
                                        " 2 - Ver usuários.%n" +
                                        " 3 - Excluir usuário.%n" +
                                        " 4 - Sair%n";

            int numbersOfUsers = users.size();
            
            System.out.println("Sistema de cadastro simples - Vagas disponíveis: " + (MAX_USERS - numbersOfUsers));
            System.out.printf(MENU_MESSAGE);

            int option = input.getInput("Escolha: ", Integer.class);

            switch(option){
                case 1:
                    if (numbersOfUsers == MAX_USERS) {
                        System.out.println("Não é possível adicionar mais nenhum usuário");
                        continue;
                    }

                    User TempUserInformation = new User();


                    String name = input.getInput("Digite o nome: ", String.class);
                    TempUserInformation.setName(name);

                    int age = input.getInput("Digite a idade: ", Integer.class);
                    TempUserInformation.setAge(age);

                    String job = input.getInput("Digite o trabalho: ", String.class);
                    TempUserInformation.setJob(job);

                    double salary = input.getInput("Digite o salario: ", Double.class);
                    TempUserInformation.setSalary(salary);

                    users.add(TempUserInformation);

                    System.out.printf("%s foi adicionado ao sistema!%n", name);

                    continue;
                case 2:
                    System.out.printf("Total de %d cadastrados!%n", numbersOfUsers);
                    for (User tempUser : users) {
                        UserManager tempUserManager = new UserManager();
                        System.out.println(tempUserManager.formatMessageOutput(tempUser));
                    }
                    continue;
                case 3:
                    if (numbersOfUsers == 0 ) {
                        System.out.println("Nenhum usuário detectado!");
                        continue;
                    }
                    System.out.println("Lista de usuários:");
                    for (int i = 0; i < numbersOfUsers; i++) {
                        User TempUser = users.get(i);
                        System.out.printf("%d. %s%n",i, TempUser.getName());
                        System.out.println("-".repeat(50));
                    }

                    try {
                        int optionDelPerson = input.getInput("Quem você deseja apagar? ", Integer.class);
                        if (numbersOfUsers < optionDelPerson) {
                            System.out.println("Usuário inexistente.");
                        } else {
                            while (true) {
                                int deleteConfirmation = input.getInput("Deseja deletar? (1 - Sim, 2 - Não) ", Integer.class);
                                if (deleteConfirmation == 1) {
                                    System.out.println(users.get(optionDelPerson).getName() + " foi deletado!");
                                    users.remove(optionDelPerson);
                                } else if (deleteConfirmation == 2) {
                                    System.out.println("Operação cancelada.");
                                } else {
                                    System.out.println("Resposta invalida, tente novamente!");
                                    continue;
                                }
                                break;
                            }
                        }
                        continue;
                    } catch (InputMismatchException e) {
                        System.out.println("Use apenas numeros!");
                    }
                case 4:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção invalida. Por favor insira a opção correta!");
                    break;


            }
            return;
        }

    }
}


// Essa provavelmente foi a parte mais "bonita" do código, pelo gerenciamento.
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

class UserManager {
    /* Função para retornar a mensagem de output das informações do usuário.*/
    String formatMessageOutput(User user) {
    return  String.format("Nome: %s%n", user.getName()) +
            String.format("Idade: %d%n", user.getAge()) +
            String.format("Trabalho: %s%n", user.getJob()) +
            String.format("Salario: %.2f%n", user.getSalary());
    }

}


class InputHandler {
    private final Scanner scanner;

    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }


    public <T> T getInput(String prompt, Class<T> type) {
        System.out.print(prompt);
        while (true) {
            try {
                String input = scanner.nextLine().trim();

                if (type == Integer.class) {
                    return type.cast(Integer.parseInt(input));
                } else if (type == Double.class) {
                    return type.cast(Double.parseDouble(input.replace(",", ".")));
                } else if (type == String.class) {
                    return type.cast(input);
                } else {
                    throw new IllegalArgumentException("Tipo não suportado!");
                }
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Entrada invalida, tente novamente");
            }



        }
    }
}