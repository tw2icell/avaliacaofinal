package view;

import controller.UserController;
import model.Contato;
import java.util.List;
import java.util.Scanner;

public class Agendaview {
    private Scanner sc = new Scanner(System.in);
    private UserController controller = new UserController();

    public void start() {
        while (true) {
            System.out.println("\n===== MENU DE CONTATOS =====");
            System.out.println("1 - Adicionar Contato");
            System.out.println("2 - Listar Contatos");
            System.out.println("3 - Atualizar Contato");
            System.out.println("4 - Excluir Contato");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1: adicionarContato(); break;
                case 2: listarContatos(); break;
                case 3: atualizarContato(); break;
                case 4: excluirContato(); break;
                case 0: System.out.println("Encerrando..."); return;
                default: System.out.println("Opção inválida!");
            }
        }
    }

    private void adicionarContato() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        Contato c = new Contato(0, nome, telefone, email);
        controller.adicionar(c);
        System.out.println("Contato adicionado com sucesso!");
    }

    private void listarContatos() {
        List<Contato> lista = controller.listarTodos();
        System.out.println("\n--- LISTA DE CONTATOS ---");
        for (Contato c : lista) {
            System.out.println("ID: " + c.getId() + " | Nome: " + c.getNome() + 
                               " | Telefone: " + c.getTelefone() + " | Email: " + c.getEmail());
        }
        if (lista.isEmpty()) {
            System.out.println("Nenhum contato encontrado.");
        }
    }

    private void atualizarContato() {
        System.out.print("Digite o ID do contato a atualizar: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Novo nome: ");
        String nome = sc.nextLine();
        System.out.print("Novo telefone: ");
        String telefone = sc.nextLine();
        System.out.print("Novo email: ");
        String email = sc.nextLine();
        Contato c = new Contato(id, nome, telefone, email);
        controller.atualizar(c);
        System.out.println("Contato atualizado!");
    }

    private void excluirContato() {
        System.out.print("Digite o ID do contato a excluir: ");
        int id = sc.nextInt();
        sc.nextLine();
        controller.excluir(id);
        System.out.println("Contato excluído com sucesso!");
    }
}
