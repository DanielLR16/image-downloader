package minderest.technical.image_downloader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import minderest.technical.image_downloader.service.ImageDownloadService;

@SpringBootApplication
public class ImageDownloaderApplication implements CommandLineRunner {

	@Autowired
	private ImageDownloadService imageDownloadService;

	public static void main(String[] args) {
		SpringApplication.run(ImageDownloaderApplication.class, args);
	}

	@Override
	public void run(String... args) {
		if (args.length < 2 || args.length > 2) {
			throw new IllegalArgumentException("Usage: java -jar image-downloader.jar <URL> <Download-Directory>");
		}
		String url = args[0];
		String downloadDir = args[1];

		imageDownloadService.downloadImages(url, downloadDir);
	}

}
