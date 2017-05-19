package com.bsk.controllers;

import com.bsk.domain.SalePosition;
import com.bsk.dto.SalePositionDTO;
import com.bsk.mapper.SalePositionMapper;
import com.bsk.services.SalePositionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "salepositions")
public class SalePositionController {

    private SalePositionService salePositionService;
    private SalePositionMapper salePositionMapper;

    public SalePositionController(SalePositionService salePositionService, SalePositionMapper salePositionMapper) {
        this.salePositionService = salePositionService;
        this.salePositionMapper = salePositionMapper;
    }

    private String showHome(Model model) {
        model.addAttribute("salepositions", salePositionService.read());
        return "redirect:/?tabName=pozycjesprzedazy";
    }

    @PostMapping(value = "/create")
    public String create(@Valid @ModelAttribute("salepositionDTO") SalePositionDTO salePositionDTO, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            SalePosition salePosition = salePositionMapper.map(salePositionDTO);
            salePositionService.save(salePosition);
            return showHome(model);
        }
        return "redirect:/?tabName=pozycjesprzedazy";
    }

    @PutMapping(value = "/update/{id}")
    public String update(@PathVariable int id, @Valid SalePosition salePosition, Model model) {
        salePositionService.save(salePosition);
        return showHome(model);
    }

    @DeleteMapping(value = "/delete/{id}")
    public String delete(@PathVariable int id, Model model) {
        salePositionService.delete(id);
        return showHome(model);
    }
}
