package br.com.hbsis.weatherforecast.service;

import br.com.hbsis.weatherforecast.exception.ColorNotFoundException;
import br.com.hbsis.weatherforecast.model.Color;
import br.com.hbsis.weatherforecast.model.ColorDTO;
import br.com.hbsis.weatherforecast.repository.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ColorService {

    @Autowired
    private ColorRepository colorRepository;

    public Color create(ColorDTO colorDTO) {
        return colorRepository.save(colorDTO.convertToEntity());
    }

    public Color findById(UUID id) {
        return colorRepository.findById(id)
                .orElseThrow(() -> new ColorNotFoundException(id));
    }

    public List<Color> findAll() {
        return colorRepository.findAll();
    }

    public void deleteById(UUID id) {
        colorRepository.deleteById(id);
    }

    public Color updateColor(ColorDTO colorDTO, UUID id) {
        Color color = findById(id);
        color.update(colorDTO);
        return colorRepository.save(color);
    }
}
