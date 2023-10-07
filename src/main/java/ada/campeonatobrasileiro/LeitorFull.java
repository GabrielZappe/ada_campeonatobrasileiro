package ada.campeonatobrasileiro;

import ada.campeonatobrasileiro.Repository.EstadoRepository;
import ada.campeonatobrasileiro.Repository.TimeRepository;
import ada.campeonatobrasileiro.domain.Jogador;
import ada.campeonatobrasileiro.domain.Time;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class LeitorFull {

    public static void lerArquivo() {

        EstadoRepository estadoRepository = EstadoRepository.getEstadoRepository();
        TimeRepository timeRepository = TimeRepository.getTimeRepository();

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/campeonato-brasileiro-full.csv"))) {
            br.readLine();
            String linha;

            while ((linha = br.readLine()) != null) {

                String[] colunas = linha.split(",");
                List<String> dados = Arrays.stream(colunas).map(string -> string.replaceAll("\"", "")).toList();

                String timeMandante = dados.get(4);
                String timeVisitante = dados.get(5);
                String estadoMandante = dados.get(14);
                String estadoVisitante = dados.get(15);

                timeRepository.salvar(timeMandante, estadoRepository.salvar(estadoMandante));
                timeRepository.salvar(timeVisitante, estadoRepository.salvar(estadoVisitante));


            }

        } catch (IOException e) {
            // atualizar as exceptions.
            System.out.println("Erro");
            System.exit(0);
        }
    }
}

