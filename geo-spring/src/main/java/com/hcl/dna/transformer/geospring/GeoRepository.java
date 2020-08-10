package com.hcl.dna.transformer.geospring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeoRepository extends JpaRepository<GeoData, Integer>{

}
