package minderest.technical.image_downloader.task;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ImageDownloadTask implements Runnable {

	private final String imageUrl;
	private final String directoryPath;

	public ImageDownloadTask(String imageUrl, String directoryPath) {
		this.imageUrl = imageUrl;
		this.directoryPath = directoryPath;
	}

	@Override
	public void run() {
		try {
			URI uri = new URI(imageUrl);
			InputStream in = uri.toURL().openStream();
			String fileName = Paths.get(uri.getPath()).getFileName().toString();
			Files.copy(in, Paths.get(directoryPath, fileName), StandardCopyOption.REPLACE_EXISTING);
			System.out.println("Downloaded: " + imageUrl);
		} catch (URISyntaxException | UnknownHostException e) {
			throw new IllegalArgumentException("The URL does not exist " + imageUrl);
		} catch (IOException e) {
			System.err.println("Failed to download: " + imageUrl);
			e.printStackTrace();
		}
	}

}
