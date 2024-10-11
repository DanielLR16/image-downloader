package minderest.technical.image_downloader.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UtilsTest {

	private String directoryPathTest;

	@BeforeEach
	public void setUp() {
		directoryPathTest = "C:\\Users\\Danie\\Desktop\\testDir";
		try {
			Files.deleteIfExists(Paths.get(directoryPathTest));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@AfterEach
	public void cleanUp() {
		try {
			Utils.deleteDirectory(directoryPathTest);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCreateDirectoryIfNotExists() {

		Utils.createDirectoryIfNotExists(directoryPathTest);
		assertTrue(Files.exists(Path.of(directoryPathTest)));
	}

	@Test
	public void testCreateDirectoryIfNotExistsDirectoryPathNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			Utils.createDirectoryIfNotExists(null);
		});
	}

	@Test
	public void testCreateDirectoryIfNotExistsDirectoryPathEmpty() {
		assertThrows(IllegalArgumentException.class, () -> {
			Utils.createDirectoryIfNotExists("");
		});
	}

	@Test
	public void testDeleteDirectory() throws Exception {

		Utils.createDirectoryIfNotExists(directoryPathTest);
		Utils.deleteDirectory(directoryPathTest);
		assertFalse(Files.exists(Path.of(directoryPathTest)));
	}

	@Test
	public void testDeleteDirectoryPathNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			Utils.deleteDirectory(null);
		});
	}

	@Test
	public void testDeleteDirectoryPathEmpty() {
		assertThrows(IllegalArgumentException.class, () -> {
			Utils.deleteDirectory("");
		});
	}
}
