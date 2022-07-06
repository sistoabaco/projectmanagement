package com.webapp.projectmanagement.controller;

import com.webapp.projectmanagement.model.Partner;
import com.webapp.projectmanagement.model.Project;
import com.webapp.projectmanagement.model.Role;
import com.webapp.projectmanagement.model.User;
import com.webapp.projectmanagement.repository.PartnerRepo;
import com.webapp.projectmanagement.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.List;

@RestController
//@CrossOrigin("http://localhost:3000/")

public class PartnerController {
    @Autowired
    PartnerRepo partnerRepo;
    @Autowired
    UserRepo userRepo;

    @GetMapping("/partnerList")
    public List <Partner> getPartners(){
        return partnerRepo.findAll();
    }

    @GetMapping("/findPartner")
    public Partner findOne(@PathVariable int id){
        return partnerRepo.findById(id).orElse(null);
    }

    @PostMapping("/savePartner")
    public String createPartner(@RequestBody Partner partner, @RequestParam Collection <Role> roles,
                                @RequestBody User user, @RequestParam Collection <Project> projects){

        user.setRole(roles);
        userRepo.save(user);

        partner.setProject(projects);
        partnerRepo.save(partner);

        return "redirect:/partnerList";
    }

    @PutMapping("/updatePartner/{id}")
    public Partner updatePartner(@PathVariable int id, @RequestBody Partner partner){

        Partner oldPartner = partnerRepo.findById(id).orElse(null);
        if (oldPartner == null) return null;

        oldPartner.setAddress(partner.getAddress());
        oldPartner.setFullName(partner.getFullName());
        oldPartner.setNuit(partner.getNuit());
        oldPartner.setProject(partner.getProject());

        return partnerRepo.save(oldPartner);
    }

    @DeleteMapping("/deletePartner/{id}")
    public String deletePartner(@PathVariable int id){
        partnerRepo.deleteById(id);
        return "redirect:/partnerList";
    }
}
