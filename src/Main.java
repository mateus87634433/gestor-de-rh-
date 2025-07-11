import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Funcionario> funcionarios = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("===== Bem-vindo ao Sistema RHControl =====");
            System.out.println("\n< MENU DO SISTEMA >");
            System.out.println("1. Cadastrar funcionário");
            System.out.println("2. Listar funcionários");
            System.out.println("3. Reajustar salário");
            System.out.println("4. Operações entre dois valores");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (opcao) {
                case 1 -> cadastrarFuncionario();
                case 2 -> listarFuncionarios();
                case 3 -> reajustarSalario();
                case 4 -> operacoesFinanceiras();
                case 0 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    static void cadastrarFuncionario() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Cargo: ");
        String cargo = scanner.nextLine();
        System.out.print("Salário: ");
        double salario = scanner.nextDouble();

        funcionarios.add(new Funcionario(nome, cargo, salario));
        System.out.println("Funcionário cadastrado com sucesso!");
    }

    static void listarFuncionarios() {
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
            return;
        }
        for (Funcionario f : funcionarios) {
            f.exibirDados();
        }
    }

    static void reajustarSalario() {
        listarFuncionarios();
        if (funcionarios.isEmpty()) return;

        System.out.print("Digite o nome do funcionário para reajustar: ");
        String nome = scanner.nextLine();

        Funcionario funcionario = funcionarios.stream()
                .filter(f -> f.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);

        if (funcionario != null) {
            System.out.print("Percentual de reajuste: ");
            double percentual = scanner.nextDouble();
            funcionario.reajustarSalario(percentual);
            System.out.println("Salário reajustado com sucesso.");
            funcionario.exibirDados();
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }

    static void operacoesFinanceiras() {
        System.out.print("Digite o primeiro valor: ");
        double a = scanner.nextDouble();
        System.out.print("Digite o segundo valor: ");
        double b = scanner.nextDouble();

        System.out.println("Soma: " + OperacoesFinanceiras.somar(a, b));
        System.out.println("Subtração: " + OperacoesFinanceiras.subtrair(a, b));
        System.out.println("Multiplicação: " + OperacoesFinanceiras.multiplicar(a, b));
        System.out.println("Divisão: " + OperacoesFinanceiras.dividir(a, b));
        System.out.println("Divisão inteira: " + OperacoesFinanceiras.divisaoInteira((long) a, (long) b));
        System.out.println("Resto: " + OperacoesFinanceiras.resto((long) a, (long) b));
        System.out.println("Exponenciação: " + OperacoesFinanceiras.exponenciar(a, b));
        System.out.println("Maior: " + OperacoesFinanceiras.maior(a, b));
        System.out.println("Menor: " + OperacoesFinanceiras.menor(a, b));
    }
}

// parte funcionario
class Funcionario {
    private String nome;
    private String cargo;
    private double salario;

    public Funcionario(String nome, String cargo, double salario) {
        this.nome = nome;
        this.cargo = cargo;
        this.salario = salario;
    }

    public String getNome() { return nome; }
    public String getCargo() { return cargo; }
    public double getSalario() { return salario; }

    public void reajustarSalario(double percentual) {
        this.salario += salario * percentual / 100;
    }

    public void exibirDados() {
        System.out.printf("Nome: %s | Cargo: %s | Salário: R$ %.2f%n", nome, cargo, salario);
    }
}

// parte financeira 
class OperacoesFinanceiras {
    public static double somar(double a, double b) {
        return a + b;
    }

    public static double subtrair(double a, double b) {
        return a - b;
    }

    public static double multiplicar(double a, double b) {
        return a * b;
    }

    public static double dividir(double a, double b) {
        return b != 0 ? a / b : Double.NaN;
    }

    public static long divisaoInteira(long a, long b) {
        return b != 0 ? a / b : 0;
    }

    public static long resto(long a, long b) {
        return b != 0 ? a % b : 0;
    }

    public static double exponenciar(double base, double expoente) {
        return Math.pow(base, expoente);
    }

    public static double maior(double a, double b) {
        return Math.max(a, b);
    }

    public static double menor(double a, double b) {
        return Math.min(a, b);
    }
}

