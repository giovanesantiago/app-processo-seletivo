package candidatura;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ProcessoSeletivo {
    public static void main(String[] args) {

        imprimirSelecionados();
    }

    static void ligandoSelecionados(ArrayList<String> candidatos) {

        for (String canditado: candidatos) {
            ligar(canditado);
        }

    }

    static void ligar(String candidato) {
        int tentativaRealizada = 1;
        boolean continuarTentanto = true;
        boolean atendeu = false;

        do {
            atendeu = atender();
            continuarTentanto = !atendeu;

            if(continuarTentanto) {
                tentativaRealizada++;
            }else {
                System.out.println("Contato com o canditado : " + candidato + " foi realizada com sucesso");
            }

        } while (continuarTentanto && tentativaRealizada <= 3);

        if(atendeu) {
            System.out.println("Numero de tentativas : " + tentativaRealizada);
        }else {
            System.out.println("Não conseguimos contato com o candidato : " + candidato);
        }

    }

    static void imprimirSelecionados() {
        ArrayList<String> candidatos = selecaoCadidados();
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Candidatos Selecionados : ");


        for (String candidato: candidatos ) {
            System.out.println(candidato);
        }
        System.out.println("--------------------------------------------------------------------");
        ligandoSelecionados(candidatos);

    }

    static ArrayList<String> selecaoCadidados() {
        String [] candidatos = {"FELIPE","MARCIA","JULIA","PAULO","AUGUSTO","MONICA","FABRICIO","MIRELA","DANIELA",
                "JORGE"};
        ArrayList<String> candidatosSelecionados = new ArrayList<>();
        int qntCandidatosSelecionados = 0;
        int candidatoAtual = 0;
        while (qntCandidatosSelecionados < 5 && candidatoAtual < candidatos.length ) {
            String candidato = candidatos[candidatoAtual];
            double salarioPretendido = valorPretendido();

            System.out.println("O candidato " + candidato + " tem pretensão de salario : R$" + salarioPretendido);

            if (analisarCandidato(salarioPretendido)) {
                System.out.println("Candidato : " + candidato + " foi selecionado para vaga.");
                candidatosSelecionados.add(candidato);
                qntCandidatosSelecionados++;
            }

            candidatoAtual++;
        }
        System.out.println("Canditados selecionados : " + qntCandidatosSelecionados);

        return candidatosSelecionados;
    }



    static boolean analisarCandidato(double salarioPretendido) {
        final double SALARIO_BASE = 2000.0;

        if(SALARIO_BASE > salarioPretendido) {
            /*System.out.println("Ligar para canditado");*/
            return true;
        } else if (SALARIO_BASE == salarioPretendido) {
            /*System.out.println("Ligar para candidato com contra proposta !");*/
            return true;
        }else {
            /*System.out.println("AGUARDANDO O RESULTADO DOS DEMAIS CANDIDATOS");*/
            return false;
        }
    }


    //métodos auxiliares
    static boolean atender() {
        return new Random().nextInt(3)==1;
    }

    static double valorPretendido() {
        return ThreadLocalRandom.current().nextDouble(1800, 2200);
    }
}
