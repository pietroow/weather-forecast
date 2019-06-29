package br.com.hbsis.weatherforecast.example;

import br.com.hbsis.weatherforecast.example.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ColorRepository extends JpaRepository<Color, UUID> {


}
