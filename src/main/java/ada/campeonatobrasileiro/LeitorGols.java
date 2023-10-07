package ada.campeonatobrasileiro;

import ada.campeonatobrasileiro.Repository.JogadorRepository;
import ada.campeonatobrasileiro.domain.Jogador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class LeitorGols {

    public static void lerArquivo () {

       JogadorRepository jogadorRepository = JogadorRepository.getJogadorRepository();

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/campeonato-brasileiro-gols.csv"))) {
            br.readLine();
            String linha;
            while ((linha = br.readLine()) != null) {

                // criar um metodo para fazer essa validacao do tipo de gol
                // nao preciso verificar se ele existe ou nao

                String[] colunas = linha.split(",");
                List<String> dados = Arrays.stream(colunas).map(string -> string.replaceAll("\"", "")).toList();

                // pra que fazer tudo isso se preciso de somente dois indices.

                String nomeJogador = dados.get(3);
                String tipodeGol = dados.get(5);

                if (!jogadorRepository.existeJogador(nomeJogador))  {
                    jogadorRepository.salvar(nomeJogador); // perigo de sobrescrita
                }

                if (tipodeGol.equals("Gol Contra")) {
                    Jogador jogador = jogadorRepository.obterJogador(nomeJogador);
                    jogador.setGolsContra(jogador.getGols() + 1);
                }
                else if (tipodeGol.equals("Penalty")) {
                    Jogador jogador = jogadorRepository.obterJogador(nomeJogador);
                    jogador.setGolsPenalti(jogador.getGols() + 1);

                } else {
                    Jogador jogador = jogadorRepository.obterJogador(nomeJogador);
                    jogador.setGols(jogador.getGols() + 1);
                }

            }

        } catch (IOException e) {
            // atualizar as exceptions.
            System.out.println("Erro");
            System.exit(0);
        }
    }



}
