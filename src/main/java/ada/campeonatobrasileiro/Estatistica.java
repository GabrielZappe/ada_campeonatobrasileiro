package ada.campeonatobrasileiro;
import ada.campeonatobrasileiro.Repository.PartidaRepository;
import ada.campeonatobrasileiro.domain.Estado;
import ada.campeonatobrasileiro.domain.Time;
import ada.campeonatobrasileiro.domain.Partida;

import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

public class Estatistica {

    public static void timeMaisVitoriosoNoAnoDe2008() {

        PartidaRepository partidaRepository = PartidaRepository.getPartidaRepository();

        Year anoAlvo = Year.parse("2008");
        String timeMaisVitorioso = "";
        Optional<Long> maiorNumeroVitorias;

        List<Partida> partidas = partidaRepository.listarPartidas();

        Map<String, Long> contagemVitoriasPorTime = partidas.stream()
                .filter(partida -> partida.getAnoPartida().equals(anoAlvo))
                .filter(partida -> partida.getVencedor() != null)
                .collect(Collectors.groupingBy(
                        partida -> partida.getVencedor().getNome(),
                        Collectors.counting()));


        List<Long> contagens = new ArrayList<>(contagemVitoriasPorTime.values());
        maiorNumeroVitorias = contagens.stream().max(Comparator.naturalOrder());

        if (maiorNumeroVitorias.isPresent()) {
            timeMaisVitorioso = contagemVitoriasPorTime.entrySet().stream()
                    .filter(entry -> entry.getValue().equals(maiorNumeroVitorias.get()))
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .orElse("Nenhum time encontrado");

            System.out.println("Time mais vitorioso em " + anoAlvo + ": " + timeMaisVitorioso);
            System.out.println("Número de vitórias: " + maiorNumeroVitorias.get());
        } else {
            System.out.println("Nenhum time encontrado.");
        }

    }





    public static void partidaComMaiorNumeroDeGols() {

        PartidaRepository partidaRepository = PartidaRepository.getPartidaRepository();
        List<Partida> partidas = partidaRepository.listarPartidas();

        Partida partidaComMaisGols = partidas.stream()
                .max(Comparator.comparingInt(p -> p.getPlacarMandante() + p.getPlacarVisitante()))
                .orElse(null);

        if (partidaComMaisGols != null) {
            System.out.println("Placar da partida com mais gols: " +
                    partidaComMaisGols.getTimeMandante().getNome() + " " +
                    partidaComMaisGols.getPlacarMandante() + " x " +
                    partidaComMaisGols.getPlacarVisitante() + " " +
                    partidaComMaisGols.getTimeVisitante().getNome());
        }

    }


    public static void EstadoComMenorQuantidadeDeJogosEntre2003e2023() {

        PartidaRepository partidaRepository = PartidaRepository.getPartidaRepository();

        Year anoInicial = Year.of(2003);
        Year anoFinal = Year.of(2022);
        String estadoComMenosJogos = null;
        long menorQuantidadeJogos = Long.MAX_VALUE;

        List<Partida> partidas = partidaRepository.listarPartidas();

        Map<String, Long> contagemPorEstado = partidas.stream()
                .filter(partida -> {
                    Year anoPartida = Year.from(partida.getAnoPartida());
                    return !anoPartida.isBefore(anoInicial) && !anoPartida.isAfter(anoFinal);
                })
                .collect(Collectors.groupingBy(
                        partida -> partida.getTimeMandante().getEstado().getNome(),
                        Collectors.counting()
                ));

        for (Map.Entry<String, Long> entry : contagemPorEstado.entrySet()) {
            if (entry.getValue() < menorQuantidadeJogos) {
                estadoComMenosJogos = entry.getKey();
                menorQuantidadeJogos = entry.getValue();
            }
        }

        if (estadoComMenosJogos != null) {
            System.out.println("Estado com menos jogos entre " + anoInicial + " e " + anoFinal + ": " + estadoComMenosJogos);
        } else {
            System.out.println("Nenhum estado encontrado.");
        }





    }
}





