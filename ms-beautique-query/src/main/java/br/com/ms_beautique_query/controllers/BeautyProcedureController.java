package br.com.ms_beautique_query.controllers;

import br.com.ms_beautique_query.dtos.beautyProcedures.BeautyProcedureDTO;
import br.com.ms_beautique_query.dtos.customers.CustomerDTO;
import br.com.ms_beautique_query.service.BeautyProcedureService;
import br.com.ms_beautique_query.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/beauty-procedures")
public class BeautyProcedureController {
    @Autowired
    private BeautyProcedureService beautyProcedureService;

    @GetMapping()
    ResponseEntity<List<BeautyProcedureDTO>> findAll() {
        return ResponseEntity.ok(beautyProcedureService.listAll());
    }

    @GetMapping("/name/{name}")
    ResponseEntity<List<BeautyProcedureDTO>> findByNameLike(@PathVariable String name) {
        return ResponseEntity.ok(beautyProcedureService.listByNameLikeIgnoreCase(name));
    }

    @GetMapping("/description/{description}")
    ResponseEntity<List<BeautyProcedureDTO>> findByEmailLike(@PathVariable String description) {
        return ResponseEntity.ok(beautyProcedureService.listByDescriptionLikeIgnoreCase(description));
    }
}
