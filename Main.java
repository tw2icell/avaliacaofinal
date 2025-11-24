import java.sql.*;
import java.util.Scanner;

public class Main {

    // SEU BANCO NEON (não mexa aqui)
    private static final String URL = "jdbc:postgresql://ep-broad-paper-ahkkx4e7-pooler.c-3.us-east-1.aws.neon.tech/neondb?sslmode=require";
    private static final String USER = "neondb_owner";
    private static final String PASS = "npg_mvL8FnOXyNZ0";

    public static void main(String[] args) {

        // Cria a tabela automaticamente (só na primeira vez)
        criarTabela();

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== CADASTRO DE CONTATOS ===");
            System.out.println("1 - Cadastrar novo contato");
            System.out.println("2 - Listar todos os contatos");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            int op = sc.nextInt();
            sc.nextLine(); // limpa o enter

            if (op == 1) {
                cadastrar(sc);
            } else if (op == 2) {
                listar();
            } else if (op == 0) {
                System.out.println("Programa encerrado. Até mais!");
                break;
            } else {
                System.out.println("Opção inválida!");
            }
        }
    }

    // Cadastra um novo contato
    private static void cadastrar(Scanner sc) {
        System.out.print("Digite o nome: ");
        String nome = sc.nextLine();

        System.out.print("Digite o telefone: ");
        String telefone = sc.nextLine();

        System.out.print("Digite o e-mail: ");
        String email = sc.nextLine();

        String sql = "INSERT INTO contato (nome, telefone, email) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nome);
            ps.setString(2, telefone);
            ps.setString(3, email);
            ps.executeUpdate();

            System.out.println("Contato cadastrado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
        }
    }

    // Lista todos os contatos já cadastrados
    private static void listar() {
        String sql = "SELECT * FROM contato ORDER BY id";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("\n=== SEUS CONTATOS ===");
            System.out.println("ID\tNOME\t\t\tTELEFONE\t\tE-MAIL");
            System.out.println("-".repeat(70));

            boolean tem = false;
            while (rs.next()) {
                tem = true;
                System.out.printf("%d\t%-20s\t%-15s\t%s%n",
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("telefone"),
                    rs.getString("email"));
            }

            if (!tem) {
                System.out.println("Nenhum contato cadastrado ainda.");
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }
    }

     
    private static void criarTabela() {
        String sql = """
            CREATE TABLE IF NOT EXISTS contato (
                id SERIAL PRIMARY KEY,
                nome VARCHAR(100),
                telefone VARCHAR(20),
                email VARCHAR(150)
            )
            """;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement st = conn.createStatement()) {
            st.execute(sql);
        } catch (Exception e) {
            System.out.println("Erro na tabela: " + e.getMessage());
        }
    }
}
