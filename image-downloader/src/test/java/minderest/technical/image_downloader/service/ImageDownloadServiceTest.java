package minderest.technical.image_downloader.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import minderest.technical.image_downloader.utils.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class ImageDownloadServiceTest {

	private ImageDownloadService imageDownloadService = new ImageDownloadService();

	private String validUrl;
	private String invalidUrlNotExist;
	private String invalidUrlSyntaxError;
	private String directoryPath;
	private String directoryPathTest;

	@BeforeEach
	public void setUp() {
		validUrl = "https://www.marca.com";
		directoryPath = "C:\\Users\\Danie\\Desktop\\images";
		invalidUrlNotExist = "https://invalid.url";
		invalidUrlSyntaxError = "invalid";
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
			Utils.deleteDirectory(directoryPath);
			Utils.deleteDirectory(directoryPathTest);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDownloadImagesSuccessfully() throws Exception {
		imageDownloadService.downloadImages(validUrl, directoryPath);
		assertTrue(Files.exists(Path.of(directoryPath)));
		assertTrue(Files.exists(Path.of(directoryPath, "186.png")));
		assertTrue(Files.exists(Path.of(directoryPath, "174.png")));
	}

	@Test
	public void testDownloadImagesInvalidUrlSyntaxError() {
		assertThrows(IllegalArgumentException.class, () -> {
			imageDownloadService.downloadImages(invalidUrlSyntaxError, directoryPath);
		});
	}

	@Test
	public void testDownloadImagesInvalidUrlNotExist() {
		assertThrows(IllegalArgumentException.class, () -> {
			imageDownloadService.downloadImages(invalidUrlNotExist, directoryPath);
		});
	}
}
