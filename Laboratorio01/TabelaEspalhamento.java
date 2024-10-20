package Laboratorio01;

/**
 * 
 * Matheus Gomes Lima - 201566 - EC4
 * 
 * 
 * Classe principal de implementacao do laboratorio. 
 * 
 * NAO ALTERE AS DEFINICOES DOS METODOS!
 *
 */
public class TabelaEspalhamento {
	
	
	
	/**
	 * 
	 * Onde os dados devem ficar salvos.
	 * 
	 */
	Elemento[] tabela;
	
	
	
	/**
	 * 
	 * Construtor da classe.
	 * 
	 * Recebe o tamanho inicial dos dados.
	 * 
	 */
	public TabelaEspalhamento(int tamanho) {
		this.tabela = new Elemento[tamanho];
	}
	
	
	
	/**
	 * 
	 * Baseado na chave (o CPF da pessoa), deve gerar um indice inteiro
	 * para indicar em qual posicao essa pessoa deve ser salva na estrutura
	 * principal.
	 * 
	 * Dado um mesmo CPF, deve retornar sempre o mesmo indice.
	 * 
	 */
	int calcularHash(String cpf) {
		String cpfLimpo = cpf.replace(".", "").replace("-", "");
		int hashCPF = cpfLimpo.hashCode();
		int indice = Math.abs(hashCPF) % tabela.length;
		return indice;
	}
	
	
	
	/**
	 * 
	 * Realiza a insercao da pessoa p na tabela de espalhamento. Deve retornar 
	 * true caso a operacao aconteca com sucesso, e false caso contrario.
	 * 
	 */
	boolean inserir(Pessoa p) {
		int indice = calcularHash(p.cpf);
        
        if (tabela[indice] == null) {
            tabela[indice] = new Elemento();
            tabela[indice].pes = p;
            tabela[indice].proximo = null;
            System.out.println("Pessoa: " + p.nome + " inserida na posição " + indice);
            return true;
        }
        
        Elemento atual = tabela[indice];
        while (atual != null) {
            if (atual.pes.cpf.equals(p.cpf)) {
                System.out.println("Pessoa com o CPF " + p.cpf + " já está presente.");
                return false;
            }
            if (atual.proximo == null) {
                break;
            }
            atual = atual.proximo;
		}  

		
        Elemento novoElemento = new Elemento();
        novoElemento.pes = p;
        novoElemento.proximo = null;
        atual.proximo = novoElemento;
        System.out.println("Pessoa " + p.nome + " inserida na posição " + indice + " com colisão.");
        return true;
		
	}
	
	
	
	/**
	 * 
	 * Remove a pessoa da tabela de espalhamento que tenha este CPF. Deve retornar 
	 * true caso a operacao aconteca com sucesso, e false caso contrario.
	 * 
	 */
	boolean remover(String cpf) {
		int indice = calcularHash(cpf);
		
        if (tabela[indice] == null) {
            System.out.println("CPF " + cpf + " não encontrado.");
            return false;
        }

        Elemento atual = tabela[indice];
        Elemento anterior = null;
        
        while (atual != null) {
            if (atual.pes.cpf.equals(cpf)) {
                if (anterior == null) {
                    tabela[indice] = atual.proximo;
                } else {
                    anterior.proximo = atual.proximo;
                }
                System.out.println("Pessoa com CPF " + cpf + " removida.");
                return true;
            }
            anterior = atual;
            atual = atual.proximo;
        }

        System.out.println("CPF " + cpf + " não encontrado.");
        return false;
	}
	
	
	
	/**
	 * 
	 * Verifica se o CPF existe na tabela de espalhamento, e retorna true 
	 * em caso positivo.
	 * 
	 */
	boolean existe(String cpf) {
		int indice = calcularHash(cpf);

        if (tabela[indice] == null) {
            return false;
        }

        Elemento atual = tabela[indice];

        while (atual != null) {
            if (atual.pes.cpf.equals(cpf)) {
                return true;
            }
            atual = atual.proximo;
        }
        return false;
	}
	
	
	
	/**
	 * 
	 * Retorna a pessoa determinada pelo CPF caso exista ou entao retorna 
	 * null, indicando que nao existe uma pessoa com este CPF.
	 * 
	 */
	Pessoa recuperar(String cpf) {
		int indice = calcularHash(cpf);

        if (tabela[indice] == null) {
            return null;
        }

        Elemento atual = tabela[indice];

        while (atual != null) {
            if (atual.pes.cpf.equals(cpf)) {
                return atual.pes;
            }
            atual = atual.proximo;
        }

        return null;
	}

	public void exibirTabela() {
        System.out.println("Tabela de Espalhamento:");
        for (int i = 0; i < tabela.length; i++) {
            System.out.print(i + ": ");
            Elemento atual = tabela[i];
            while (atual != null) {
                System.out.print(atual.pes.nome + " -> ");
                atual = atual.proximo;
            }
            System.out.println("null");
        }
    }


	public static void main(String[] args) {
        
        TabelaEspalhamento tabelaEspalhamento = new TabelaEspalhamento(10);
		tabelaEspalhamento.exibirTabela();
        
        Pessoa p1 = new Pessoa();
        p1.cpf = "123.456.789-09";
        p1.nome = "joana";
        p1.idade = 25;

        Pessoa p2 = new Pessoa();
        p2.cpf = "987.654.321-00";
        p2.nome = "julio";
        p2.idade = 30;

        Pessoa p3 = new Pessoa();
        p3.cpf = "555.555.555-55";
        p3.nome = "carla";
        p3.idade = 22;

        Pessoa p4 = new Pessoa();
        p4.cpf = "123.456.789-09";
        p4.nome = "maria";
        p4.idade = 28;

        Pessoa p5 = new Pessoa();
        p5.cpf = "666.666.666-66";
        p5.nome = "eduardo";
        p5.idade = 35;

        tabelaEspalhamento.inserir(p1);
        tabelaEspalhamento.inserir(p2);
        tabelaEspalhamento.inserir(p3);
        tabelaEspalhamento.inserir(p4);
        tabelaEspalhamento.inserir(p5);

        tabelaEspalhamento.exibirTabela();

        tabelaEspalhamento.remover("987.654.321-00");
        
        tabelaEspalhamento.remover("111.111.111-11");

        tabelaEspalhamento.exibirTabela();

		System.out.println(tabelaEspalhamento.existe("666.666.666-66"));
		System.out.println(tabelaEspalhamento.existe("987.654.321-00"));

		System.out.println(tabelaEspalhamento.recuperar("987.654.321-00"));
		System.out.println(tabelaEspalhamento.recuperar("666.666.666-66"));

    }
}
