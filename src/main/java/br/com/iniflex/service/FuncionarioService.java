package br.com.iniflex.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.iniflex.model.Funcionario;

public class FuncionarioService {

	//3.1 - Inserir todos os funcionários, na mesma ordem e informações da tabela acima.
	public static List<Funcionario> populaFuncionarios() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        List<Funcionario> funcionarios = new ArrayList<Funcionario>();

        funcionarios.add(new Funcionario("Maria", LocalDate.parse("18/10/2000", formatter), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.parse("12/05/1990", formatter), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.parse("02/05/1961", formatter), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.parse("14/10/1988", formatter), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.parse("05/01/1995", formatter), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.parse("19/11/1999", formatter), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.parse("31/03/1993", formatter), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.parse("08/07/1994", formatter), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.parse("24/05/2003", formatter), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.parse("02/09/1996", formatter), new BigDecimal("2799.93"), "Gerente"));

        return funcionarios;
	}
	
	//3.2 - Remover o funcionário “João” da lista.
	public static void removerFuncionarioPorNome(List<Funcionario> funcionarios, String nome) {
	    funcionarios.removeIf(funcionario -> funcionario.getNome().equalsIgnoreCase(nome));
	}

	//3.3 - Imprimir todos os funcionários com todas suas informações.
	public static void imprimirFuncionarios(List<Funcionario> funcionarios) {
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    DecimalFormat df = new DecimalFormat("#,##0.00");

	    for (Funcionario f : funcionarios) {
	        System.out.println("Nome: " + f.getNome());
	        System.out.println("Data de Nascimento: " + f.getDataNascimento().format(dtf));
	        System.out.println("Salário: R$ " + df.format(f.getSalario()));
	        System.out.println("Função: " + f.getFuncao());
	        System.out.println("-------------------------------------");
	    }
	}
	
	//3.4 - Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
	public static void aplicarAumento(List<Funcionario> funcionarios, double percentual) {
		for (Funcionario f : funcionarios) {
	        BigDecimal aumento = f.getSalario()
	            .multiply(BigDecimal.valueOf(percentual / 100));
	        
	        BigDecimal novoSalario = f.getSalario()
	            .add(aumento)
	            .setScale(2, RoundingMode.HALF_UP);
	        
	        f.setSalario(novoSalario);
	    }
	}
	
	//3.5 - Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
	public static Map<String, List<Funcionario>> agruparPorFuncao(List<Funcionario> funcionarios) {
	    return funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));
	}
	
	//3.6 - Imprimir os funcionários, agrupados por função.
	public static void imprimirAgrupadosPorFuncao(Map<String, List<Funcionario>> map) {
	    map.forEach((funcao, lista) -> {
	        System.out.println("Função: " + funcao);
	        lista.forEach(f -> System.out.println(" - " + f.getNome()));
	        System.out.println();
	    });
	}
	
	//3.8 - Imprimir os funcionários que fazem aniversário no mês 10 e 12.
	public static void imprimirAniversariantesMeses(List<Funcionario> funcionarios, int... meses) {
		List<Integer> mesList = Arrays.stream(meses).boxed().toList();

	    for (Funcionario f : funcionarios) {
	        int mes = f.getDataNascimento().getMonthValue();
	        if (mesList.contains(mes)) {
	            String nomeMes = f.getDataNascimento()
	                              .getMonth()
	                              .getDisplayName(TextStyle.FULL, new Locale("pt", "BR"));

	            System.out.println(f.getNome() + " - Aniversário em: " + nomeMes);
	        }
	    }
	}
	
	//3.9 - Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
	public static void imprimirMaisVelho(List<Funcionario> funcionarios) {
	    Funcionario maisVelho = funcionarios.stream()
	        .min(Comparator.comparing(Funcionario::getDataNascimento))
	        .orElse(null);

	    if (maisVelho != null) {
	        int idade = Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears();
	        System.out.println("Funcionário mais velho: " + maisVelho.getNome() + " - Idade: " + idade + " anos");
	    }
	}
	
	//3.10 - Imprimir a lista de funcionários por ordem alfabética.
	public static void imprimirOrdenadoPorNome(List<Funcionario> funcionarios) {
	    funcionarios.stream()
	        .sorted(Comparator.comparing(Funcionario::getNome))
	        .forEach(f -> System.out.println(f.getNome()));
	}
	
	//3.11 - Imprimir o total dos salários dos funcionários.
	public static void imprimirTotalSalarios(List<Funcionario> funcionarios) {
	    BigDecimal total = funcionarios.stream()
	        .map(Funcionario::getSalario)
	        .reduce(BigDecimal.ZERO, BigDecimal::add);

	    DecimalFormat df = new DecimalFormat("#,##0.00");
	    System.out.println("Total dos salários: R$ " + df.format(total));
	}
	
	//3.12 - Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.
	public static void imprimirSalariosMinimos(List<Funcionario> funcionarios) {
	    BigDecimal salarioMinimo = new BigDecimal("1212.00");
	    DecimalFormat df = new DecimalFormat("#,##0.00");
	    for (Funcionario f : funcionarios) {
	        BigDecimal qtd = f.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
	        System.out.println(f.getNome() + " ganha " + df.format(qtd) + " salários mínimos.");
	    }
	}
	
}
