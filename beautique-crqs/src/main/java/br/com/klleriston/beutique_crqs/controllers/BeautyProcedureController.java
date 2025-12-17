package br.com.klleriston.beutique_crqs.controllers;

import br.com.klleriston.beutique_crqs.dtos.BeautyProcedureDTO;
import br.com.klleriston.beutique_crqs.services.BeautyProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/beautyProcedure")
public class BeautyProcedureController {
    @Autowired
    private BeautyProcedureService beautyProcedureService;

    @PostMapping
    public ResponseEntity<BeautyProcedureDTO> create(@RequestBody BeautyProcedureDTO beautyProcedureDTO) {
        return ResponseEntity.ok(beautyProcedureService.create(beautyProcedureDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        beautyProcedureService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping
    public ResponseEntity<BeautyProcedureDTO> update(@RequestBody BeautyProcedureDTO beautyProcedureDTO) {
        return ResponseEntity.ok(beautyProcedureService.update(beautyProcedureDTO));
    }
}
