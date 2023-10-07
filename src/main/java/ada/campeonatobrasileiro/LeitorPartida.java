package ada.campeonatobrasileiro;


import ada.campeonatobrasileiro.Repository.EstadoRepository;
import ada.campeonatobrasileiro.Repository.PartidaRepository;
import ada.campeonatobrasileiro.Repository.TimeRepository;
import ada.campeonatobrasileiro.domain.Partida;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;

public class LeitorPartida {


    public static void lerArquivo() {

        EstadoRepository estadoRepository = EstadoRepository.getEstadoRepository();
        TimeRepository timeRepository = TimeRepository.getTimeRepository();
        PartidaRepository partidaRepository = PartidaRepository.getPartidaRepository();

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/campeonato-brasileiro-full.csv"))) {

            br.readLine();
            String linha;

            while ((linha = br.readLine()) != null) {

                String[] colunas = linha.split(",");
                List<String> dados = Arrays.stream(colunas).map(string -> string.replaceAll("\"", "")).toList();

                String IDPartida = dados.get(0);
                String ano = List.of(dados.get(2).split("/")).get(2);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
                Year anoPartida = Year.parse(ano, formatter);

                String nomeTimeMandante = dados.get(4);
                String nomeTimeVisitante = dados.get(5);
                String nomeTimeVencedor = dados.get(10);

                int placarMandante = Integer.parseInt(dados.get(12));
                int placarVisitante = Integer.parseInt(dados.get(13));

                partidaRepository.salvar(IDPartida, anoPartida, timeRepository.obterTime(nomeTimeVencedor), timeRepository.obterTime(nomeTimeMandante),
                        timeRepository.obterTime(nomeTimeVisitante), placarMandante, placarVisitante);

            }

        } catch (IOException e) {
            // atualizar as exceptions.
            System.out.println("Erro");
            System.exit(0);
        }
    }
}
