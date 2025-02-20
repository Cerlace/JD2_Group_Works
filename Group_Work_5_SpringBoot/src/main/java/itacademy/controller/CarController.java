package itacademy.controller;

import itacademy.dto.CarDto;
import itacademy.dto.CarFilterDto;
import itacademy.dto.EngineDto;
import itacademy.service.api.CarService;
import itacademy.service.api.EngineService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;
    private final EngineService engineService;

    @GetMapping
    public String getCarsPaginated(@ModelAttribute("carFilter") CarFilterDto carFilter,
                                   Model model) {
        Page<CarDto> carsPage = carService.getCarsByFilter(carFilter);
        model.addAttribute("carsPageContent", carsPage.getContent());
        model.addAttribute("totalPages", carsPage.getTotalPages());
        return "cars/list";
    }

    @GetMapping("/save")
    public String showSaveForm(Model model) {
        model.addAttribute("car", CarDto.builder().engine(new EngineDto()).build());
        model.addAttribute("enginesList", engineService.findAll());
        return "cars/form";
    }

    @GetMapping("/save/{carId}")
    public String showUpdateForm(Model model,
                                 @PathVariable Long carId) {
        model.addAttribute("car", carService.getCar(carId));
        model.addAttribute("enginesList", engineService.findAll());
        return "cars/form";
    }

    @PostMapping("/save")
    public String saveOrUpdate(@ModelAttribute CarDto carDto) {
        carService.saveOrUpdateCar(carDto);
        return "redirect:/cars";
    }

    @GetMapping("/delete/{id}")
    public String deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return "redirect:/cars";
    }

    @GetMapping("/{id}")
    public String getCarDetails(@PathVariable Long id, Model model) {
        CarDto car = carService.getCar(id);
        model.addAttribute("car", car);
        return "cars/details";
    }
}
