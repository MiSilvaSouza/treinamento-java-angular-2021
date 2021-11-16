package com.company.app.services;

import com.company.app.Program;
import com.company.app.validate.ValidateFiles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

public class ServiceProcessFiles {

    private static final Logger LOG = LoggerFactory.getLogger(ServiceProcessFiles.class);

    private final Path dirEntrada;
    private final List<String> listFiles;

    public ServiceProcessFiles(String caminhoDirEntrada){
        ValidateFiles validate = new ValidateFiles();
        dirEntrada = validate.validateDirEntrada(caminhoDirEntrada);
        listFiles = validate.getListFiles();
    }

    public void executeService() {

        if (listFiles.isEmpty()) {
            LOG.info("Não há arquivos para processamento!");
            return;
        }

        ServiceReadFiles service = new ServiceReadFiles();
        listFiles.forEach((arquivo) -> {
            String caminhoArquivo = dirEntrada.toString() + File.separator + arquivo;
            LOG.info("Processando arquivo: {}", caminhoArquivo);

            List<String> rows = service.execute(caminhoArquivo);
            if (rows.isEmpty()) {
                LOG.info("Não há registros no arquivo {}", caminhoArquivo);
                return;
            }

            LOG.info("Encontradas [{}] linhas no arquivo {}", rows.size(), caminhoArquivo);
        });
    }
}
