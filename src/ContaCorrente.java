//ContaCorrente È UMA Conta. Herda tudo de Conta.

    public class ContaCorrente extends Conta {
        private double taxaDeManutencao = 15.90;

        public ContaCorrente(Cliente titular) {
            //Chama o construtor da classe mãe (Conta).
            super(titular);
        }


        //Método que SÒ a ContaCorrente tem.
        public void cobrarTaxa() {
            this.saldo -= this.taxaDeManutencao;
            System.out.println("Taxa de manutenção de R$ " + taxaDeManutencao + " cobrada da conta " + this.numero);

        }
    }

