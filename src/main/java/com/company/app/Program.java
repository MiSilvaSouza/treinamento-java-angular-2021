package com.company.app;

import com.company.app.services.ServiceProcessFiles;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Program {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Parâmetro caminho obrigatório");
            System.out.println("Uso: ");
            System.out.println("java com.avanade.aplicacao.Programa [CAMINHO]");
            System.exit(-1);
            return;
        }
        log.info("Iniciando aplicação...");
        Program program = new Program();
        program.iniciar(args[0]);
    }

    public void iniciar(String caminhoDirEntrada) {
        ServiceProcessFiles service = new ServiceProcessFiles(caminhoDirEntrada);
        service.executeService();
    }
}
