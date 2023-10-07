package ada.campeonatobrasileiro.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public abstract class AbstractRepository <T, U> {
    private final Map<T,U> dados = new HashMap<>();

   public abstract boolean salvar(T identificador);

   public List<U> listarTodos () {
       return new ArrayList<>(dados.values());
   }


   public U obterEntidade (T identificador) {
       return dados.get(identificador);
   }


    public abstract boolean salvar(int IDPartida);
}
