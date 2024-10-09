
public abstract class Conta implements IConta {
	
	private static final int AGENCIA_PADRAO = 1;
	private static int SEQUENCIAL = 1;

	protected int agencia;
	protected int numero;
	protected double saldo;
	protected Cliente cliente;

	public Conta(Cliente cliente) {
		this.agencia = Conta.AGENCIA_PADRAO;
		this.numero = SEQUENCIAL++;
		this.cliente = cliente;
	}

        @Override
        public boolean validarCodBarras(String codBarras){
            if(codBarras.length() != 44){
                System.out.println("Codigo de barras invalido");
                return false;
            }
            return true;
        }
        
        @Override
        public void pagamento(double valor, String codBarras){
            
            if (!validarCodBarras(codBarras) || this.saldo < valor ) {
                System.out.println("Ocorreu um erro tente novamente");
            } 
            else {
                this.saldo -= valor;
                System.out.println("Pagamento realizado com sucesso.");
                System.out.println("Novo saldo: " + this.saldo); 
        }
        }
                
	@Override
	public void sacar(double valor) {
		saldo -= valor;
	}

	@Override
	public void depositar(double valor) {
		saldo += valor;
	}

	@Override
	public void transferir(double valor, IConta contaDestino) {
		this.sacar(valor);
		contaDestino.depositar(valor);
	}

	public int getAgencia() {
		return agencia;
	}

	public int getNumero() {
		return numero;
	}

	public double getSaldo() {
		return saldo;
	}
        
       
        
	protected void imprimirInfosComuns() {
		System.out.println(String.format("Titular: %s", this.cliente.getNome()));
		System.out.println(String.format("Agencia: %d", this.agencia));
		System.out.println(String.format("Numero: %d", this.numero));
		System.out.println(String.format("Saldo: %.2f", this.saldo));
	}
}
