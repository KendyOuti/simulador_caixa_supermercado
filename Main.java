import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String... args) {

        final int NUM_SIMS = 1000; // NUM_SIMS = Número de Simulações
        final List<Double> medias = new ArrayList<>();

        SimuladorCaixa sim = new SimuladorCaixa(); // sim = Simulador

        sim.setNumCaixas(1); // NumCaixas = Número de Caixas
        sim.setMediaAtds(100); // MediaAtds = Média de Atendimentos
        sim.setMediaTempo(5.00); // MediaTempo = Média de Tempo
        sim.setDpTempo(2.00); // DpTempo = Desvio-padrão do Tempo
        for (int i = 0; i < NUM_SIMS; i++) {
            double mediaAtd = sim.simular(); // mediaAtd = Média de Atendimento
            medias.add(mediaAtd);
        }

        double media = calcularMedia(medias);
        double dp = calcularDp(medias, media); // dp = Desvio-padrão

        System.out.printf("Média dos tempos de atendimento (%.0f simulações): %.3fmin%n", (double) NUM_SIMS, media);
        System.out.printf("Desvio-padrão das médias: %.3fmin%n", dp);
    }

    private static double calcularMedia(List<Double> valores) {
        double soma = 0.0;
        for (double valor : valores) soma += valor;
        return soma / valores.size();
    }

    private static double calcularDp(List<Double> valores, double media) {
        double somaQuadrados = 0.0;
        for (double valor : valores) {
            double dif = valor - media; // dif = Diferença
            somaQuadrados += dif * dif;
        }
        return Math.sqrt(somaQuadrados / (valores.size() - 1));
    }
}