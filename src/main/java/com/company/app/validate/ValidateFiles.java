package com.company.app.validate;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ValidateFiles {

    private List<String> listFiles;

    private void messageValidate(String caminhoDirEntrada, String mensagem) {
        String modeloMensagem = mensagem + " [{0}]";
        System.err.println(MessageFormat.format(modeloMensagem, caminhoDirEntrada));
        System.exit(-2);
    }

    public Path validateDirEntrada(String caminhoDirEntrada) {
        System.out.println("Validação do diretório de entrada...");

        Path dirEntrada = Paths.get(caminhoDirEntrada);
        if (!Files.exists(dirEntrada)) {
            messageValidate(caminhoDirEntrada, "Diretório informado não existe");
            return null;
        }

        if (!Files.isDirectory(dirEntrada)) {
            messageValidate(caminhoDirEntrada, "Caminho informado não é um diretório");
            return null;
        }

        File fDirEntrada = dirEntrada.toFile();
        String[] arquivos = fDirEntrada.list((dir, name) -> name.toUpperCase().matches(
                "(VENDAS)-([0-9]){4}-([0-9]){2}-([0-9]){2}\\.(TXT)"));

        if (arquivos == null || arquivos.length == 0) {
            messageValidate(caminhoDirEntrada, "Não há arquivos no diretório informado");
            return null;
        }

        listFiles = Arrays.asList(arquivos);

        System.out.println("Diretório validado com sucesso " + caminhoDirEntrada);
        return dirEntrada;
    }

    public List<String> getListFiles() {
        if (listFiles == null) {
            listFiles = Collections.emptyList();
        }
        return listFiles;
    }
}
