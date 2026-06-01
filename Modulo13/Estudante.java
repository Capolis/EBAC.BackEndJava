public class Estudante {
    private String nome;
    private int id;
    private double[] nota;

    // Construtor
    public Estudante(String nome, int id, int numeroDeProvas) {
        this.nome = nome;
        this.id = id;
        this.nota = new double[numeroDeProvas];
    }
    
    // Método para calcular a média
    public double calcularMedia() {
        if (nota == null || nota.length == 0) {
            return 0.0;
        }
        
        double sum = 0;
        for (double grade : nota) {
            sum += grade;
        }
        return sum / nota.length;
    }
    
    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double[] getNota() {
        return nota;
    }

    public void setNota(double[] nota) {
        this.nota = nota;
    }

}