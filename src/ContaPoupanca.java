//ContaPoupanca É UMA Conta.
public class ContaPoupanca extends Conta {
    private double taxaDeJuros = 0.005; // 0.5% ao Mês

    public ContaPoupanca(Cliente titular) {
        //chama o construtor da classe mãe (Conta).
        super(titular);
    }

    //Método que só a contapoupanca tem.
    public void renderJuros() {
        double juros = this.saldo * this.taxaDeJuros;
        this.saldo += juros;
        System.out.println("Juros de R$ " + String.format("%.2f", juros) + " renderam na conta " + this.numero);
    }
}

