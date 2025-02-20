package itacademy.controller;

import itacademy.dto.EngineDto;
import itacademy.dto.EngineFilterDto;
import itacademy.service.api.EngineService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class EngineController {
    private final EngineService engineService;

    @GetMapping("/engines")
    public String getEngines(@ModelAttribute("engineFilter") EngineFilterDto engineFilter,
                             Model model) {
        Page<EngineDto> enginesPage = engineService.getEnginesByHorsePower(
                engineFilter.getHorsePower(),
                engineFilter.getPageNumber(),
                engineFilter.getPageSize());
        model.addAttribute("engines", enginesPage.getContent());
        model.addAttribute("totalPages", enginesPage.getTotalPages());
        model.addAttribute("newEngine", new EngineDto());
        return "engines/list";
    }

    @PostMapping("/engines/add")
    public String addEngine(@ModelAttribute("newEngine") EngineDto engineDto) {
        engineService.saveOrUpdateEngine(engineDto);
        return "redirect:/engines";
    }

    @PostMapping("/engines/delete/{id}")
    public String deleteEngine(@PathVariable Long id) {
        engineService.deleteEngine(id);
        return "redirect:/engines";
    }

    @GetMapping("/engines/edit/{id}")
    public String editEnginePage(@PathVariable Long id,
                             Model model) {
        model.addAttribute("engine", engineService.getEngine(id));
        return "engines/edit";
    }

    @PostMapping("/engines/edit/{id}")
    public String editEngine(@ModelAttribute("engine") EngineDto engine) {
        engineService.saveOrUpdateEngine(engine);
        return "redirect:/engines";
    }
}
