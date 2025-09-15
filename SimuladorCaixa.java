public class SimuladorCaixa {

    private int numCaixas; // numCaixas = Número de Caixas
    private int mediaAtds; // mediaAtds = Média de Atendimentos

    private double mediaTempo; // mediaTempo = Média de Tempo
    private double dpTempo; // dpTempo = Desvio-padrão do Tempo

    private static final double TEMPO_MIN = 0.1; // TEMPO_MIN = Tempo Mínimo

    private final java.util.Random rng = new java.util.Random(42); // rng = Gerador de Números Aleatórios

    public void setNumCaixas(int n) { this.numCaixas = n; }
    public void setMediaAtds(int n) { this.mediaAtds = n; }
    public void setMediaTempo(double mu) { this.mediaTempo = mu; }
    public void setDpTempo(double sigma) { this.dpTempo = sigma; }

    private double tempoNormalTruncado() {
        double z = rng.nextGaussian();
        double s = mediaTempo + dpTempo * z;
        return (s < TEMPO_MIN) ? TEMPO_MIN : s;
    }

    public double simular() {
        double totalMedias = 0.0;
        int clientesInteiros = mediaAtds / numCaixas;
        int clientesRestantes = mediaAtds % numCaixas;
        for (int i = 0; i < numCaixas; i++) {
            if (i == mediaAtds) break;
            if (i < clientesRestantes) {
                totalMedias += simularCaixa(clientesInteiros + 1);
            } else {
                totalMedias += simularCaixa(clientesInteiros);
            }
        }
        return totalMedias / mediaAtds;
    }

    public double simularCaixa(int numClientes) { // numClientes = Número de Clientes
        double soma = 0.0;
        double total = 0.0;
        for (int i = 0; i < numClientes; i++) {
            soma += tempoNormalTruncado();
            total += soma;
        }
        return total;
    }
}