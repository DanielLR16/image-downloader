package minderest.technical.image_downloader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import minderest.technical.image_downloader.utils.Utils;

public class ImageDownloaderApplicationTests {

	private String validUrl;
	private String downloadDir;

	@BeforeEach
	void setUp() {
		validUrl = "https://www.marca.com";
		downloadDir = "C:\\Users\\Danie\\Desktop\\images";
	}

	@AfterEach
	public void cleanUp() {
		try {
			Utils.deleteDirectory(downloadDir);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testRunWithValidArgs() {
		String[] args = { validUrl, downloadDir };
		ImageDownloaderApplication.main(args);
		assertTrue(Files.exists(Path.of(downloadDir)));
		assertTrue(Files.exists(Path.of(downloadDir, "186.png")));
		assertTrue(Files.exists(Path.of(downloadDir, "174.png")));
	}

	@Test
	void testRunWithInsufficientArgs() {
		String[] args = { validUrl };

		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
			ImageDownloaderApplication.main(args);
		});

		assertEquals("Usage: java -jar image-downloader.jar <URL> <Download-Directory>", thrown.getMessage());
	}

	@Test
	void testRunWithNoArgs() {
		String[] args = {};

		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
			ImageDownloaderApplication.main(args);
		});

		assertEquals("Usage: java -jar image-downloader.jar <URL> <Download-Directory>", thrown.getMessage());
	}

	@Test
	void testRunWithMoreTwoArgs() {
		String[] args = { validUrl, validUrl, validUrl };

		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
			ImageDownloaderApplication.main(args);
		});

		assertEquals("Usage: java -jar image-downloader.jar <URL> <Download-Directory>", thrown.getMessage());
	}
}
