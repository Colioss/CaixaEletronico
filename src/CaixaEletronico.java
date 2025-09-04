import java.util.InputMismatchException; // Classe para verificação de erros de entrada
import java.util.Scanner;

public class CaixaEletronico {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //--Dados do Banco--
        //Agora criamos criamos instâncias das classes específicas.
        Cliente cliente1 = new Cliente("Fábio Maneiro", "111.222.333-44");
        Conta conta1 = new ContaCorrente(cliente1); // Fabio tem uma conta corrente
        conta1.depositar(1500.0);

        Cliente cliente2 = new Cliente("Juuzou Suzuya", "444.555.666.-77");
        Conta conta2 = new ContaPoupanca(cliente2); // Bruce tem uma conta poupança
        conta2.depositar(100000.0);

        //Colocamos as contas em um array do tipo da superclasse (Conta).
        //Isso é polimorfismo o array pode guardar qualquer objeto que SEJA UMA conta.
        Conta[] contasDoBanco = {conta1, conta2};
        Conta contaAtiva = contasDoBanco[0];

        int opcao = 0;
        while (opcao != 8) {
            System.out.println("\n Caixa Eletrônico ZZ Bank");
            System.out.println("Bem-vindo(a), " + contaAtiva.getNomeTitular() + " | Conta: " + contaAtiva.getClass().getSimpleName());
            System.out.println("1 - Consultar Saldo");
            System.out.println("2 - Depositar");
            System.out.println("3 - Sacar");
            System.out.println("4 - Transferir");
            System.out.println("5 - Trocar de Conta(Login)");

            //Opções específicas de cada conta
            // O operador 'instanceof' verifica o tipo real do objeto
            if (contaAtiva instanceof ContaCorrente) {
                System.out.println("6 - Cobrar taxa de manutenção");
            }
            if (contaAtiva instanceof ContaPoupanca) {
                System.out.println("7 - Fazer Render Juros");
            }
            try { //Verificação de erros com o catch
                opcao = scanner.nextInt();
switch (opcao) {
    case 1: contaAtiva.exibirDados(); break;
    case 2:
        System.out.println("Digite o valor para depositar: ");
        contaAtiva.depositar(scanner.nextDouble());
        break;
    case 3:
        System.out.println("Digite o valor para sacar: ");
        contaAtiva.sacar(scanner.nextDouble());
        break;
    case 4:
        System.out.println("Digite o número da conta destino: ");
        int numContaDestino = scanner.nextInt();
        Conta contaDestino = null;
        for(Conta c : contasDoBanco) if(c.getNumero() == numContaDestino) contaDestino = c;

if (contaDestino != null && contaDestino != contaAtiva) {
    System.out.println("Digite o valor para transferir: ");
    contaAtiva.transferir(contaDestino, scanner.nextDouble());
}else {
    System.out.println("Conta destino inválida ou igual à origem.");
}
break;
    case 5:
        System.out.println("\nCONTAS DISPONÍVEIS");
        for (Conta c : contasDoBanco) {
            System.out.println("N° " + c.getNumero() + " - " + c.getClass().getSimpleName() + " - " + c.getNomeTitular());
        }
        System.out.println("Digite o número da conta desejada: ");
        int numContaLogin = scanner.nextInt();
        boolean encontrada = false;
        for (Conta c : contasDoBanco) {
            if (c.getNumero() == numContaLogin) {
                contaAtiva = c;
                encontrada = true;
                break;
            }
        }
        if(encontrada) System.out.println("LOgin efetuado com sucesso!");
        else System.out.println("Conta não encontrada.");
        break;
    case 6:
        if (contaAtiva instanceof ContaCorrente) {
            //è preciso fazer um "cast" para acessar o método específico.
            //dizemos ao compilador: "trade este objeto como uma ContaCorrente"
            ((ContaCorrente) contaAtiva).cobrarTaxa();
        }else {
            System.out.println("Opção válida apenas para Contas Correntes.");
        }
        break;
    case 7:
        if (contaAtiva instanceof ContaPoupanca) {
            //Fazendo "CAST" para ContaPoupanca
            ((ContaPoupanca) contaAtiva).renderJuros();
        }else {
            System.out.println("Opção válida apenas para Contas Poupança.");
        }
        break;
    case 8:
        System.out.println("\nObrigado por usar o ZZ Bank. Volte sempre!");
        break;
    default:
        System.out.println("Opção inválida.");
        break;
}
            }catch (InputMismatchException e) {
                System.out.println("Erro: Por favor, digite apenas números.");
                scanner.next();
            }
        }
        scanner.close();
    }
}
