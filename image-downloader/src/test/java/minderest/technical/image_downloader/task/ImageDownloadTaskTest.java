package minderest.technical.image_downloader.task;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ImageDownloadTaskTest {

	private String imageUrl;
	private String invalidUrlNotExist;
	private String invalidUrlSyntaxError;
	private String downloadDir;

	@BeforeEach
	public void setUp() {
		imageUrl = "https://e00-marca.uecdn.es/assets/sports/logos/football/png/36x36/186.png";
		invalidUrlNotExist = "https://invalid.url/image.jpg";
		invalidUrlSyntaxError = "invalid";
		downloadDir = "C:\\Users\\Danie\\Desktop";
	}

	@AfterEach
	public void cleanUp() {
		Path downloadedFile = Paths.get(downloadDir, "186.png");
		try {
			Files.deleteIfExists(downloadedFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDownloadImageSuccessfully() {
		ImageDownloadTask task = new ImageDownloadTask(imageUrl, downloadDir);

		task.run();

		Path downloadedFile = Paths.get(downloadDir, "186.png");
		assertTrue(Files.exists(downloadedFile));

		try {
			Files.deleteIfExists(downloadedFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDownloadImageInvalidUrlNotExist() {
		ImageDownloadTask task = new ImageDownloadTask(invalidUrlNotExist, downloadDir);
		assertThrows(IllegalArgumentException.class, task::run);
	}

	@Test
	public void testDownloadImageInvalidUrlSyntaxError() {
		ImageDownloadTask task = new ImageDownloadTask(invalidUrlSyntaxError, downloadDir);
		assertThrows(IllegalArgumentException.class, task::run);
	}
}
