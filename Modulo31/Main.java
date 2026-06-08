import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
        public static void main(String[] args) {
                List<Funcionario> todosFuncionarios = new ArrayList<>();

                //  PREENCHENDO OS DADOS CONFORME A REGRA 

                // 1 a 3: Nascidos entre 1975 e 1985 COM salário MENOR ou IGUAL a R$ 3000,00
                todosFuncionarios.add(new Funcionario("João", LocalDate.of(1980, 5, 10), new BigDecimal("2500.00"),
                                TipoContrato.CLT));
                todosFuncionarios.add(new Funcionario("Maria", LocalDate.of(1978, 8, 15), new BigDecimal("3000.00"),
                                TipoContrato.PJ));
                todosFuncionarios.add(new Funcionario("José", LocalDate.of(1982, 12, 1), new BigDecimal("1500.00"),
                                TipoContrato.CLT));

                // 4 e 5: Nascidos entre 1975 e 1985 COM salário MAIOR que R$ 3000,00 (Para
                // testar o filtro de salário)
                todosFuncionarios.add(new Funcionario("Ana", LocalDate.of(1976, 3, 20), new BigDecimal("5000.00"),
                                TipoContrato.CLT));
                todosFuncionarios.add(new Funcionario("Pedro", LocalDate.of(1984, 7, 30), new BigDecimal("8000.00"),
                                TipoContrato.CLT));

                // 6 a 45: Faltam 40 funcionários para totalizar os 45 exigidos.
                // Vamos usar um laço para criar o restante.
                // Eles serão menores de 25 anos (nascidos em 2005) e CLT para alimentar o 3º
                // exercício.
                for (int i = 6; i <= 45; i++) {
                        BigDecimal salarioProgressivo = new BigDecimal("4000.00").add(new BigDecimal(i * 100));
                        todosFuncionarios.add(new Funcionario(
                                        "Funcionario Jovem " + i,
                                        LocalDate.of(2005, 1, 1),
                                        salarioProgressivo,
                                        TipoContrato.CLT));
                }

                // Criando a empresa e a lista de empresas
                Empresa minhaEmpresa = new Empresa(todosFuncionarios);
                List<Empresa> listaEmpresas = Arrays.asList(minhaEmpresa);

                // ================= SAÍDAS =================

                // Exercicio 1: Filtrar funcionários nascidos entre 1975 e 1985 com salário <= R$ 3000,00
                System.out.println("Filtro: Nascidos 1975-1985 com Salário menor ou igual a 3000 \n");
                List<Funcionario> filtrados = filtrarFuncionariosPorNascimentoESalario(listaEmpresas);
                filtrados.forEach(f -> System.out.println("Encontrado: " + f.getNome() + " | Salário: R$ "
                                + f.getSalario() + " | Nascimento: " + f.getDataNascimento()));
                System.out.println("Total encontrados: " + filtrados.size());

                // Exercicio 2: Somar números pares
                System.out.println("\nSomar Números Pares");
                List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
                int somaPares = somarNumerosPares(numeros);
                System.out.println("Soma dos pares: " + somaPares);

                // Exercicio 3: Obter os top 30 maiores salários de funcionários CLT menores de 25 anos
                System.out.println("\nTop 30 Maiores Salários (CLT e menor de 25 anos) ");
                List<BigDecimal> top30 = obterTop30SalariosCLT(listaEmpresas);
                System.out.println("Total de salários retornados: " + top30.size());
                System.out.println("Lista dos maiores salários em ordem decrescente:");
                for (int i = 0; i < top30.size(); i++) {
                        System.out.println((i + 1) + "º: R$ " + top30.get(i));
                }
        }

        // Retorna a lista de funcionários nascidos entre 1975 e 1985 com salário menor ou igual a R$ 3000,00
        private static List<Funcionario> filtrarFuncionariosPorNascimentoESalario(List<Empresa> empresas) {
                LocalDate dataInicio = LocalDate.of(1975, 1, 1);
                LocalDate dataFim = LocalDate.of(1985, 12, 31);
                BigDecimal limiteSalario = new BigDecimal("3000.00");

                return empresas.stream()
                                .flatMap(empresa -> empresa.getFuncionarios().stream())
                                .filter(func -> !func.getDataNascimento().isBefore(dataInicio)
                                                && !func.getDataNascimento().isAfter(dataFim))
                                .filter(func -> func.getSalario().compareTo(limiteSalario) <= 0)
                                .collect(Collectors.toList());
        }

        // Retorna a soma dos números pares da lista
        private static int somarNumerosPares(List<Integer> numeros) {
                return numeros.stream()
                                .filter(n -> n % 2 == 0)
                                .mapToInt(Integer::intValue)
                                .sum();
        }

        // Retorna os top 30 maiores salários de funcionários CLT menores de 25 anos, ordenados do maior para o menor
        private static List<BigDecimal> obterTop30SalariosCLT(List<Empresa> empresas) {
                return empresas.stream()
                                .flatMap(empresa -> empresa.getFuncionarios().stream())
                                .filter(func -> func.getTipoContrato() == TipoContrato.CLT)
                                .filter(func -> func.getIdade() < 25)
                                .map(Funcionario::getSalario)
                                .sorted(Comparator.reverseOrder())
                                .limit(30)
                                .collect(Collectors.toList());
        }
}