package br.com.hbsis.weatherforecast.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ColorController {

    private final ColorService colorService;

    @Autowired
    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    @PostMapping("/color")
    public ResponseEntity<Color> create(@RequestBody ColorDTO colorDTO) {
        Color color = colorService.create(colorDTO);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(color.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/color/{id}")
    public Color findById(@PathVariable("id") UUID id) {
        return colorService.findById(id);
    }

    @GetMapping("/color")
    public List<Color> findAll() {
        return colorService.findAll();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/color/{id}")
    public void deleteColor(@PathVariable("id") UUID id) {
        colorService.deleteById(id);
    }

    @PutMapping("/color/{id}")
    public Color updateColor(@RequestBody ColorDTO colorDTO,
                             @PathVariable("id") UUID id) {
        return colorService.updateColor(colorDTO, id);
    }
}
