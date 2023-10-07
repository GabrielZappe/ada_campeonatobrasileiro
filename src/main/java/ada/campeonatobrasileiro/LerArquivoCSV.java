package ada.campeonatobrasileiro;
import ada.campeonatobrasileiro.domain.Jogador;
import ada.campeonatobrasileiro.Repository.*;
import ada.campeonatobrasileiro.domain.Partida;

import java.util.Comparator;
import java.util.Optional;


public class LerArquivoCSV {

    public static void main(String[] args) {


        JogadorRepository jogadorRepository = JogadorRepository.getJogadorRepository();
        TimeRepository timeRepository = TimeRepository.getTimeRepository();
        EstadoRepository estadoRepository = EstadoRepository.getEstadoRepository();
        PartidaRepository partidaRepository = PartidaRepository.getPartidaRepository();


      LeitorGols.lerArquivo();
      LeitorCartoes.LerArquivo();
      LeitorFull.lerArquivo();
      LeitorPartida.lerArquivo();


      Optional<Jogador> jogadorMaximoGols = jogadorRepository.listarJogadores().stream().max(Comparator.comparing(Jogador::getGols));
      Optional<Jogador> jogadorMaximoGolsContra = jogadorRepository.listarJogadores().stream().max(Comparator.comparing(Jogador::getGolsContra));
      Optional<Jogador> jogadorMaximoGolsPenalti = jogadorRepository.listarJogadores().stream().max(Comparator.comparing(Jogador::getGolsPenaltis));
//
      Optional<Jogador> jogadorComMaiorNumeroDeCartoesVermelhos = jogadorRepository.listarJogadores().stream().
              max(Comparator.comparing(Jogador::getCartoesVermelhos));
        Optional<Jogador> jogadorComMaiorNumeroDeCartoesAmarelos = jogadorRepository.listarJogadores().stream().
                max(Comparator.comparing(Jogador::getCartoesAmarelos));

        System.out.println(jogadorMaximoGols);
        System.out.println(jogadorMaximoGolsContra);
        System.out.println(jogadorMaximoGolsPenalti);
        System.out.println(jogadorComMaiorNumeroDeCartoesAmarelos);
        System.out.println(jogadorComMaiorNumeroDeCartoesVermelhos);


          Estatistica.partidaComMaiorNumeroDeGols();
          Estatistica.timeMaisVitoriosoNoAnoDe2008();
          Estatistica.EstadoComMenorQuantidadeDeJogosEntre2003e2023();


//
//        System.out.println(jogadorMaximoGols.toString());
//        System.out.println(jogadorMaximoGolsContra.toString());
//        System.out.println(jogadorMaximoGolsPenalti.toString());
//
//        System.out.println();
//
//        System.out.println("Cartões");
//        System.out.println(jogadorComMaiorNumeroDeCartoesAmarelos.toString());
//        System.out.println(jogadorComMaiorNumeroDeCartoesVermelhos.toString());


        // -------------------------------------------

//        estadoRepository.printarTodos();
//        timeRepository.printarTimes();




    }
}

