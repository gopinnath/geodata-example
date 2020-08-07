package com.hcl.dna.transformer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import io.reactivex.Flowable;
import io.smallrye.mutiny.Multi;

@ApplicationScoped
public class DataFeeder {
	
	@Inject 
	@Channel("geocode-channel") 
	private Multi<String> geocode;
	
    @Outgoing("geocode-topic")                         
    public Flowable<String> post() {
    	return Flowable.fromPublisher(geocode);
    }
    
}
