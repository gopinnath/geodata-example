package com.hcl.dna.transformer;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

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
	
	@Scheduled(every = "P1D",delay = 2)
    public void publishGeoData() throws Exception,InterruptedException {
		BufferedReader postalDataSourceFile = Files.newBufferedReader(Paths.get("IN.txt"));
		postalDataSourceFile.lines().forEach(line -> {
			try {
				Thread.sleep(10);
			} catch (InterruptedException exception) {
				logger.log(Level.WARNING,"Delay Interupted",exception);
			}
			geoCodeEmitterWithBuffer.send(line);
			logger.info("Publishing Counter " + incrementCounter());
		});
    }
	
	private Integer incrementCounter()	{
		return counter++;
	}
}
