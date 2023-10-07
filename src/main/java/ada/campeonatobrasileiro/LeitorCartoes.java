package ada.campeonatobrasileiro;

import ada.campeonatobrasileiro.Repository.JogadorRepository;
import ada.campeonatobrasileiro.domain.Jogador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class LeitorCartoes {



    public static void LerArquivo () {

        JogadorRepository jogadorRepository = JogadorRepository.getJogadorRepository();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/campeonato-brasileiro-cartoes.csv"))) {
            br.readLine();

            int linhaLida = 1;
            String linha;
            while ((linha = br.readLine()) != null) {
                //Melhorar essa parte do código (não preciso limpar tudo).

                String[] colunas = linha.split(",");
                List<String> dados = Arrays.stream(colunas).map(string -> string.replaceAll("\"", "")).toList();

                String tipoDoCartao = dados.get(3);
                String nomeJogador = dados.get(4);

                linhaLida++;

                if (nomeJogador.isEmpty()) {
                    System.out.println("JOGADOR VAZIO");
                    System.out.println("LINHA DO ARQUIVO" + linhaLida);
                } else {

                    // Acho que não preciso fazer essa verificação se eu considerar que ler gols ocorre sempre primeiro

                    if (!jogadorRepository.existeJogador(nomeJogador)) {
                        jogadorRepository.salvar(nomeJogador); // perigo de sobrescrita
                    }
                    if (tipoDoCartao.equals("Amarelo")) {
                        jogadorRepository.obterJogador(nomeJogador).setCartoesAmarelos();
                    } else if (tipoDoCartao.equals("Vermelho")) {
                        jogadorRepository.obterJogador(nomeJogador).setCartoesVermelhos();
                    }

                }
            }

        } catch (IOException e) {
            // atualizar as exceptions.
            System.out.println("Erro");
            System.exit(0);
        }





    }


}
