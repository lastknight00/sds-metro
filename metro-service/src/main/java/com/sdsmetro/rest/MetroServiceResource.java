package com.sdsmetro.rest;

import com.sdsmetro.domain.entity.Metro;
import com.sdsmetro.domain.spec.MetroService;
import com.sdsmetro.domain.spec.sdo.MetroCdo;
import io.naraplatform.share.domain.NameValueList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/s/metros")
public class MetroServiceResource implements MetroService {
    //
    @Autowired
    @Qualifier("metroLogic")
    private MetroService metroService;

    @Override
    @PostMapping
    public String buildMetro(@RequestBody MetroCdo metroCdo) {
        //
        return metroService.buildMetro(metroCdo);
    }

    @Override
    @GetMapping("{metroId}")
    public Metro findMetro(@PathVariable String metroId) {
        //
        return metroService.findMetro(metroId);
    }

    @Override
    @GetMapping(value = "exists", params = "name")
    public boolean existMetroByName(@RequestParam String name) {
        //
        return metroService.existMetroByName(name);
    }

    @Override
    @GetMapping
    public List<Metro> findMetros() {
        //
        return metroService.findMetros();
    }

    @Override
    @PutMapping("{metroId}")
    public void modifyMetro(@PathVariable String metroId, @RequestBody NameValueList nameValues) {
        //
        metroService.modifyMetro(metroId, nameValues);
    }

    @Override
    @DeleteMapping("{metroId}")
    public void removeMetro(@PathVariable String metroId) {
        //
        metroService.removeMetro(metroId);
    }
}