package br.com.alura;

import java.math.BigDecimal;

public class AppBanco {

    public static void main(String[] args) {
        var cliente = new Cliente("Paula");
        var conta = new Conta(cliente, new BigDecimal("150"));

        var operacao = new OperacaoSaque(conta, new BigDecimal("150"));

        Thread saque1 = new Thread(operacao);
        Thread saque2 = new Thread(operacao);

        saque2.start();
        saque1.start();
        try {
            saque1.join(); //para a thread main esperar as outras threads antes de continuar
            saque2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(Thread.currentThread().getName());
        System.out.printf("""
                Saldo final: %s
                """, conta.getSaldo());
    }


}
