package br.com.iniflex;

import java.util.List;
import java.util.Map;

import br.com.iniflex.model.Funcionario;
import br.com.iniflex.service.FuncionarioService;

public class Principal {
	
	public static void main(String[] args) {
		// 3.1 - Inserir todos os funcionários
        List<Funcionario> funcionarios = FuncionarioService.populaFuncionarios();

        // 3.2 - Remover o funcionário “João”
        FuncionarioService.removerFuncionarioPorNome(funcionarios, "João");

        // 3.3 - Imprimir todos os funcionários
        System.out.println("==== Funcionários ====");
        FuncionarioService.imprimirFuncionarios(funcionarios);

        // 3.4 - Aplicar aumento de 10%
        FuncionarioService.aplicarAumento(funcionarios, 10.0);

        // 3.5 - Agrupar por função
        Map<String, List<Funcionario>> agrupado = FuncionarioService.agruparPorFuncao(funcionarios);

        // 3.6 - Imprimir agrupados por função
        System.out.println("\n==== Funcionários agrupados por função ====");
        FuncionarioService.imprimirAgrupadosPorFuncao(agrupado);

        // 3.8 - Aniversariantes dos meses 10 e 12
        System.out.println("\n==== Aniversariantes em Outubro e Dezembro ====");
        FuncionarioService.imprimirAniversariantesMeses(funcionarios, 10, 12);

        // 3.9 - Funcionário mais velho
        System.out.println("\n==== Funcionário mais velho ====");
        FuncionarioService.imprimirMaisVelho(funcionarios);

        // 3.10 - Ordenar por nome
        System.out.println("\n==== Funcionários ordenados por nome ====");
        FuncionarioService.imprimirOrdenadoPorNome(funcionarios);

        // 3.11 - Total dos salários
        System.out.println("\n==== Total dos salários ====");
        FuncionarioService.imprimirTotalSalarios(funcionarios);

        // 3.12 - Quantos salários mínimos cada um ganha
        System.out.println("\n==== Salários em múltiplos do salário mínimo ====");
        FuncionarioService.imprimirSalariosMinimos(funcionarios);
	}
	
	
	
	
}
