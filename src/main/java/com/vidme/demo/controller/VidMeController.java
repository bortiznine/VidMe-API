package com.vidme.demo.controller;
import com.vidme.demo.model.Vids;
import com.vidme.demo.service.VidMeService;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class VidMeController {
    private VidMeService vidMeService;

    @Autowired
    public void setVidMeService(VidMeService vidMeService){
        this.vidMeService = vidMeService;
    }

    //Get all Vid Urls and Titles
    @GetMapping("/vids")
    public List<Vids> getAllVids(){
        System.out.println("getting all Vid's");
        return vidMeService.getAllVids();
    }
//Get a single Vid item
    @GetMapping("/vids/{vidId}")
    public Vids getSingleVid(@PathVariable Long vidId){
        System.out.println("calling getVids");
        return vidMeService.getSingleVid(vidId);
    }
//create a Vid item
    @PostMapping("/vids")
    public Vids createSingleVid(@RequestBody Vids vidsObject){
        System.out.println("calling createVids");
        return vidMeService.createSingleVid(vidsObject);
    }

    // delete a post
    @DeleteMapping("/vids/{vidId}")
    public ResponseEntity<?> deleteSingleVid(@PathVariable Long vidId) {
        System.out.println("calling deleteVids");
        return vidMeService.deleteSingleVid(vidId);
    }
    // delete all post
    @DeleteMapping("/vids")
    public ResponseEntity<?> deleteAllVids() {
        System.out.println("calling deleteAllVids");
        return vidMeService.deleteAllVids();
    }

}
