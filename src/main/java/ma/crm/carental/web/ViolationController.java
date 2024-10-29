package ma.crm.carental.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import ma.crm.carental.annotations.ReactiveValidation;
import ma.crm.carental.dtos.violation.ViolationRequestDto;
import ma.crm.carental.dtos.violation.ViolationResponseDto;
import ma.crm.carental.services.ViolationService;


@RestController
@RequestMapping("/violations")
public class ViolationController {
    

    private final ViolationService violationService ;


    ViolationController(
        ViolationService violationService
    ){
        this.violationService = violationService ;
    }

    @PostMapping
    @ReactiveValidation
    List<ViolationResponseDto> save(
        @Valid @RequestBody List<ViolationRequestDto> violationRequestDtos ,
        BindingResult bindingResult
    ){
        return violationService.saveViolations(violationRequestDtos) ;
    }

    @DeleteMapping("/{ids}")
    Map<String , Object> delete(
        @PathVariable List<Long> ids
    ){
        return violationService.deleteViolations(ids) ;
    }

    @PutMapping("/{ids}")
    Map<String , Object> update(
        @PathVariable List<Long> ids ,
        @RequestBody ViolationRequestDto violationRequestDto 
    ){

        List<ViolationRequestDto> violationRequestDtos = new ArrayList<>();
        violationRequestDtos.add(violationRequestDto);
        return violationService.updateViolations(ids, violationRequestDtos) ;
    }


    @GetMapping
    Page<ViolationResponseDto> pagenateClients(
        @RequestParam int page ,
        @RequestParam int pageSize
    ) {

        return violationService.pagenateViolations(PageRequest.of(page, pageSize)) ;
    }


    @GetMapping("/{id}")
    ViolationResponseDto findCient(
        @PathVariable long id 
    ) {
        return violationService.findClient(id) ;
    }
}
