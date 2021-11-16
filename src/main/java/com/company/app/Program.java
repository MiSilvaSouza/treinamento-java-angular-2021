package com.company.app;

import com.company.app.services.ServiceProcessFiles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Program {

    private static final Logger LOG = LoggerFactory.getLogger(Program.class);

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Parâmetro caminho obrigatório");
            System.out.println("Uso: ");
            System.out.println("java com.avanade.aplicacao.Programa [CAMINHO]");
            System.exit(-1);
            return;
        }
        LOG.info("Iniciando aplicação...");
        Program program = new Program();
        program.iniciar(args[0]);
    }



    public void iniciar(String caminhoDirEntrada) {
        ServiceProcessFiles service = new ServiceProcessFiles(caminhoDirEntrada);
        service.executeService();
    }
}
