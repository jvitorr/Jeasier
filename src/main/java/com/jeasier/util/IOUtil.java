package com.jeasier.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 
 * @author João Vitor Feitosa
 * @since 0.0.1
 */

public class IOUtil {

	public static final String CONTROLLER_TEMPLATE = "src/main/resources/templates/Controller.txt";

	public static final String PAGE_WRAPPER_TEMPLATE = "src/main/resources/templates/PageWrapper.txt";

	public static final String REPOSITORY_TEMPLATE = "src/main/resources/templates/Repository.txt";

	public static final String FILTER_TEMPLATE = "src/main/resources/templates/Filter.txt";

	public static final String IMPL_TEMPLATE = "src/main/resources/templates/Impl.txt";

	public static final String HELPER_TEMPLATE = "src/main/resources/templates/Helper.txt";

	public static final String FIELD_TEMPLATE = "src/main/resources/templates/Field.txt";

	public static final String FIELD_BOOLEAN_TEMPLATE = "src/main/resources/templates/Field.txt";

	public static final String THYMELEAF_CREATE_TEMPLATE = "src/main/resources/templates/thymeleafCreate.txt";

	public static final String THYMELEAF_LIST_TEMPLATE = "src/main/resources/templates/thymeleafList.txt";

	public static final String THYMELEAF_CREATE_FIELD_TEMPLATE = "src/main/resources/templates/ThymeleafCreateField.txt";

	public static final String THYMELEAF_LIST_FIELD_TEMPLATE = "src/main/resources/templates/ThymeleafFilterField.txt";

	public static final String FIELD_FILTER_TEMPLATE = "src/main/resources/templates/FieldFilterClass.txt";

	public static final String FIELD_FILTER_BOOLEAN_TEMPLATE = "src/main/resources/templates/FieldBoolean.txt";

	public static final String FIELD_FILTER_STRING_TEMPLATE = "src/main/resources/templates/FieldFilterString.txt";

	// pacotes do projeto de destino

	public static String packageRepository = "com.easyteste.repository";

	public static String packageHelper = "com.easyteste.repository.helper";

	public static String packageImpl = "com.easyteste.repository.impl";

	public static String packageFilter = "com.easyteste.filter";

	public static String packageController = "com.easyteste.controller";

	// path do projeto de destino

	public static String targetController = "/Users/joaovitorfeitosa/Desktop/EasyJava/easyteste/src/main/java/com/easyteste/controller/";

	public static String targetRepository = "/Users/joaovitorfeitosa/Desktop/EasyJava/easyteste/src/main/java/com/easyteste/repository/";

	public static String targetHelper = "/Users/joaovitorfeitosa/Desktop/EasyJava/easyteste/src/main/java/com/easyteste/repository/helper/";

	public static String targetImpl = "/Users/joaovitorfeitosa/Desktop/EasyJava/easyteste/src/main/java/com/easyteste/repository/impl/";

	public static String targetFilter = "/Users/joaovitorfeitosa/Desktop/EasyJava/easyteste/src/main/java/com/easyteste/filter/";

	public static String targetModel = "/Users/joaovitorfeitosa/Desktop/EasyJava/easyteste/src/main/java/com/easyteste/model/";

	public static String targetTemple = "/Users/joaovitorfeitosa/Desktop/EasyJava/easyteste/src/main/resources/templates/";

	public static String lerArquivo(String path) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			StringBuilder sb = new StringBuilder();
			while (br.ready()) {
				sb.append(br.readLine());
				sb.append("\n");
			}
			br.close();
			return sb.toString();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return null;
	}

	public static void gravarArquivo(String conteudo, String caminho, String nomeArquivo) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(new FileOutputStream(caminho + nomeArquivo));
		pw.write(conteudo);
		pw.flush();
		pw.close();
	}

	public static void criarPastasCasoNaoExista(String path) {
		File theDir = new File(path);

		// if the directory does not exist, create it
		if (!theDir.exists()) {
			System.out.println("creating directory: " + theDir.getName());
			boolean result = false;

			try {
				theDir.mkdir();
				result = true;
			} catch (SecurityException se) {
				// handle it
			}
			if (result) {
				System.out.println("DIR created");
			}
		}
	}

	// public static void criarPastasGenerator(Classe classe) {
	// criarPastasCasoNaoExista(targetController);
	// criarPastasCasoNaoExista(targetRepository);
	// criarPastasCasoNaoExista(targetFilter);
	// criarPastasCasoNaoExista(targetHelper);
	// criarPastasCasoNaoExista(targetImpl);
	// criarPastasCasoNaoExista(targetModel);
	// criarPastasCasoNaoExista(targetTemple);
	// criarPastasCasoNaoExista(targetTemple + "/" + classe.getNomeAtributo());
	// }

}
