package minderest.technical.image_downloader.utils;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

public class Utils {

	public static void createDirectoryIfNotExists(String directoryPath) {
		if (directoryPath == null || directoryPath.isEmpty()) {
			throw new IllegalArgumentException("The path cannot be empty or null");
		}
		Path path = Paths.get(directoryPath);
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				System.err.println("Error al crear el directorio: " + e.getMessage());
			}
		}
	}

	public static void deleteDirectory(String directoryPath) throws IOException {
		if (directoryPath == null || directoryPath.isEmpty()) {
			throw new IllegalArgumentException("The path cannot be empty or null");
		}
		Path directory = Paths.get(directoryPath);
		if (Files.exists(directory)) {
			try (Stream<Path> paths = Files.walk(directory)) {
				paths.sorted(Comparator.reverseOrder()).forEach(path -> {
					try {
						Files.delete(path);
					} catch (IOException e) {
						throw new UncheckedIOException(e);
					}
				});
			} catch (UncheckedIOException e) {
				throw new IOException("Error deleting directory: " + e.getMessage(), e.getCause());
			}
		}
	}
}
