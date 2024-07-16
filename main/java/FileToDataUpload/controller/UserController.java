package FileToDataUpload.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import FileToDataUpload.Entity.UserDetails;
import FileToDataUpload.service.UserService;

@RestController
public class UserController {
	@Autowired
    private UserService userService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            userService.saveUsersFromExcel(file);
            return ResponseEntity.ok().body("File uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }
    }
    
    @GetMapping("/get/{id}")
    public UserDetails getbyid(@PathVariable long id) {
    	return userService.getbyid(id);
    }
    
    @GetMapping("/getall")
    public List<UserDetails> getall() {
    	return userService.getall();
    }
    
}
