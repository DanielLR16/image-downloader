package minderest.technical.image_downloader.service;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import minderest.technical.image_downloader.task.ImageDownloadTask;
import minderest.technical.image_downloader.utils.Utils;

@Service
public class ImageDownloadService {

	private static final int NUM_THREADS = 10;
	private static final int TIMEOUT = 5;
	private final ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

	public void downloadImages(String url, String directoryPath) throws IllegalArgumentException {
		try {
			Utils.createDirectoryIfNotExists(directoryPath);

			Document document = Jsoup.connect(url).get();
			Elements images = document.select("img[src$=.jpg], img[src$=.jpeg], img[src$=.png]");

			List<String> imageUrls = images.stream().map(img -> img.absUrl("src")).toList();
			imageUrls.forEach(imageUrl -> executor.submit(new ImageDownloadTask(imageUrl, directoryPath)));

			executor.shutdown();
			if (!executor.awaitTermination(TIMEOUT, TimeUnit.MINUTES)) {
				executor.shutdownNow();
			}

		} catch (UnknownHostException e) {
			throw new IllegalArgumentException("The URL does not exist " + url);
		} catch (IOException | InterruptedException e) {
			executor.shutdownNow();
			Thread.currentThread().interrupt();
			e.printStackTrace();
		}
	}
}
