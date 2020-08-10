package com.hcl.dna.transformer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.OnOverflow;

import io.quarkus.scheduler.Scheduled;

@ApplicationScoped
public class TaskScheduler {

	private static final Logger logger = Logger.getLogger(TaskScheduler.class.getName());

	private Integer counter = 0;

	@OnOverflow(value = OnOverflow.Strategy.BUFFER, bufferSize = 300)
	@Inject
	@Channel("geocode-channel")
	private Emitter<String> geoCodeEmitterWithBuffer;

	@Scheduled(every = "P1D", delay = 1)
	public void readAndPublishGeoData() throws Exception {
		BufferedReader postalDataSourceFile = readTxtFile();
		postalDataSourceFile.lines().forEach(this::publishGeoData);
	}

	private Integer incrementCounter() {
		return counter++;
	}

	private void publishGeoData(String line) {
		try {
			geoCodeEmitterWithBuffer.send(line);
			Thread.sleep(2);
			logger.info("Publishing Counter " + incrementCounter());
		} catch (InterruptedException exception) {
			logger.log(Level.WARNING, "Delay Interrupted", exception);
		}
	}

	private BufferedReader readTxtFile() throws Exception {
		File destinationDir = Files.createTempDirectory("geodata").toFile();
		ZipInputStream zipStream = new ZipInputStream(Files.newInputStream(Paths.get("IN.zip")));
		processZipEntries(zipStream,destinationDir);
		zipStream.closeEntry();
		zipStream.close();
		return Files.newBufferedReader(destinationDir.toPath().resolve("IN.txt"));
	}
	
	private void processZipEntries(ZipInputStream zipStream,File destinationDir) throws Exception	{
		ZipEntry zipEntry = zipStream.getNextEntry();
		while (zipEntry != null) {
			extractTxtFile(zipEntry,zipStream,destinationDir);
			zipEntry = zipStream.getNextEntry();
		}		
	}
	
	private void extractTxtFile(ZipEntry zipEntry,ZipInputStream zipStream,File destinationDir) throws Exception	{
		if (zipEntry.getName().equals("IN.txt")) {
			byte[] buffer = new byte[1024];
			File newFile = new File(destinationDir, zipEntry.getName());
			FileOutputStream fos = new FileOutputStream(newFile);
			int len;
			while ((len = zipStream.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			fos.close();
		}
	}
}
