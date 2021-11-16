package com.company.app.services;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;

public class ServiceReadFiles {

    private static final Logger LOG = LoggerFactory.getLogger(ServiceReadFiles.class);

    public List<String> execute(String caminhoArquivo) {
        try {
            File arquivo = new File(caminhoArquivo);
            return FileUtils.readLines(arquivo, Charset.defaultCharset());
        } catch (IOException e) {
            LOG.error("Falha ao ler arquivo [{}]", caminhoArquivo, e);
            return Collections.emptyList();
        }
    }
}
