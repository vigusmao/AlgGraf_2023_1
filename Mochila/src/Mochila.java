import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Mochila {

    public static int solve(
            List<ItemMochila> itens,
            int pesoMaximoMochila) {

        Map<Instancia, Integer> memo = new HashMap<>();

        return solveRecursive(  // top down
                itens,
                itens.size(),
                pesoMaximoMochila,
                memo);
    }

//    private static int solveRecursive(
//            List<ItemMochila> itens,
//            int n,
//            int w) {
//
//        if (n == 0 || w == 0) {
//            return 0;
//        }
//
//        ItemMochila ultimo = itens.get(n-1);
//
//        final int pesoUltimo = ultimo.getPeso();
//
//        int valorCom = pesoUltimo > w ? 0 :
//                ultimo.getValor() +
//                        solveRecursive(
//                                itens,
//                                n-1,
//                                w - pesoUltimo);
//
//        int valorSem = solveRecursive(
//                itens,
//                n-1,
//                w);
//
//        return Math.max(valorCom, valorSem);
//    }

    private static int solveRecursive(
            List<ItemMochila> itens,
            int n,
            int w,
            Map<Instancia, Integer> memo) {

        if (n == 0 || w == 0) {
            return 0;
        }

        Instancia instancia = new Instancia(n, w);

        // lê do memo
        Integer resultFromMemo = memo.get(instancia);
        if (resultFromMemo != null) {
            return resultFromMemo;
        }

        ItemMochila ultimo = itens.get(n-1);

        final int pesoUltimo = ultimo.getPeso();

        int valorCom = pesoUltimo > w ? 0 :
                ultimo.getValor() +
                        solveRecursive(
                                itens,
                                n-1,
                                w - pesoUltimo,
                                memo);

        int valorSem = solveRecursive(
                itens,
                n-1,
                w,
                memo);

        int result = Math.max(valorCom, valorSem);

        // escreve no memo
        memo.put(instancia, result);

        return result;
    }

    private static class Instancia {
        final int n;
        final int w;

        public Instancia(int n, int w) {
            this.n = n;
            this.w = w;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Instancia that = (Instancia) o;
            return n == that.n &&
                    w == that.w;
        }

        @Override
        public int hashCode() {
            return Objects.hash(n, w);
        }
    }
}
