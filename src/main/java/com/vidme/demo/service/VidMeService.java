package com.vidme.demo.service;


import com.vidme.demo.exception.InformationExistException;
import com.vidme.demo.exception.InformationNotFoundException;
import com.vidme.demo.exception.URLNotFoundException;
import com.vidme.demo.model.Vids;
import com.vidme.demo.repository.VidRepository;
import com.vidme.demo.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



@Service
public class VidMeService {

    public static boolean
    isValidURL(String url)
    {
        // Regex to check valid URL
        String regex = "((http|https)://)(www.)?"
                + "[a-zA-Z0-9@:%._\\+~#?&//=]"
                + "{2,256}\\.[a-z]"
                + "{2,6}\\b([-a-zA-Z0-9@:%"
                + "._\\+~#?&//=]*)";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the string is empty
        // return false
        if (url == null) {
            return false;
        }

        // Find match between given string
        // and regular expression
        // using Pattern.matcher()
        Matcher m = p.matcher(url);

        // Return if the string
        // matched the ReGex
        return m.matches();
    }

    private VidRepository vidRepository;

    @Autowired
    public void setVidMeRepository(VidRepository vidRepository) {
        this.vidRepository = vidRepository;
    }

    public List<Vids> getAllVids() {
        System.out.println("service calling getAllVid's ==>");
        return vidRepository.findAll();
    }


    public Vids getSingleVid(Long vidId) {
        System.out.println("service getSinglePost ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Vids vids = vidRepository.findByIdAndUserId(vidId, userDetails.getUser().getId());
        if (vids == null) {
            throw new InformationNotFoundException("Vid with ID " + vidId + " not found!");
        } else {
            return  vids;
        }
    }

    public Vids createSingleVid(Vids vidsObject) {
        System.out.println("service calling createSingleVids ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Vids vids = vidRepository.findByUserIdAndTitle(userDetails.getUser().getId(), vidsObject.getTitle());
        if (vids != null) {
            throw new InformationExistException("Vid with title " + vids.getTitle() + " already exists");
        } else {

            if(isValidURL(vidsObject.getVidurl())==true) {
                vidsObject.setUsername(userDetails.getUser().getUsername());
                vidsObject.setUser(userDetails.getUser()); //might have to come back to change vidsobject to vids to pass proper data
                vidsObject.setTitle(vidsObject.getTitle());
                vidsObject.setVidurl(vidsObject.getVidurl());
                return vidRepository.save(vidsObject);
            }
            else{
                throw new URLNotFoundException("Vid Url provided is not a proper URL link!"+ " Provided was "+ vidsObject.getVidurl() + " !");
            }
        }
    }
    public ResponseEntity<?> deleteSingleVid(Long vidId) {
        System.out.println("service calling deleteSingleVids ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Vids vids = vidRepository.findByIdAndUserId(vidId, userDetails.getUser().getId());
        if (vids != null) {
            vidRepository.deleteById(vidId);
            HashMap<String, String> responseMessage = new HashMap<>();
            responseMessage.put("status", "Vid with id: " + vidId + " was successfully deleted");
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } else {
            throw new InformationNotFoundException("Vid with ID " + vidId + " not found!");
        }
    }
    public ResponseEntity<?> deleteAllVids() {
        System.out.println("service calling deleteAllVids ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Vids> vids = vidRepository.findByUserId(userDetails.getUser().getId());
        if (!vids.isEmpty()) {
            vidRepository.deleteAll(vids);
            HashMap<String, String> responseMessage = new HashMap<>();
            responseMessage.put("status", "all Vids for user " + userDetails.getUsername() + " successfully deleted");
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } else {
            throw new InformationNotFoundException("Could not find any Vids for user " + userDetails.getUsername());
        }
    }
}
