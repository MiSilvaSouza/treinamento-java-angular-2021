package com.company.app.services;

import com.company.app.Program;
import com.company.app.model.OrderModel;
import com.company.app.validate.ValidateFiles;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

@Slf4j
public class ServiceProcessFiles {

    private final Path dirEntrada;
    private final List<String> listFiles;

    public ServiceProcessFiles(String caminhoDirEntrada){
        ValidateFiles validate = new ValidateFiles();
        dirEntrada = validate.validateDirEntrada(caminhoDirEntrada);
        listFiles = validate.getListFiles();
    }

    public void executeService() {

        if (listFiles.isEmpty()) {
            log.info("Não há arquivos para processamento!");
            return;
        }

        ServiceReadFiles serviceReadFiles = new ServiceReadFiles();
        listFiles.forEach((arquivo) -> {
            String caminhoArquivo = dirEntrada.toString() + File.separator + arquivo;
            log.info("Processando arquivo: {}", caminhoArquivo);

            List<OrderModel> orders = serviceReadFiles.execute(caminhoArquivo);
            if (orders.isEmpty()) {
                log.info("Não há registros no arquivo {}", caminhoArquivo);
                return;
            }

            log.info("Encontrados [{}] pedidos no arquivo {}", orders.size(), caminhoArquivo);

            //TODO - gravar no banco de dados
        });
    }
}
