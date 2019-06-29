package br.com.hbsis.weatherforecast.repository;

import br.com.hbsis.weatherforecast.model.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ColorRepository extends JpaRepository<Color, UUID> {


}
