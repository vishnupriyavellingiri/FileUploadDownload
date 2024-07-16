package FileToDataUpload.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import FileToDataUpload.Entity.UserDetails;
import FileToDataUpload.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDetails getbyid(long id) {
    	return userRepository.findById(id).get();
    }
    public List<UserDetails> getall() {
    	return userRepository.findAll();
    }
    public void saveUsersFromExcel(MultipartFile file) throws IOException {
        List<UserDetails> userDetailsList = new ArrayList<>();
   
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0); // Assuming the first sheet

        for (Row row : sheet) {
            if (row.getRowNum() == 0) { // Skip header row
                continue;
            }
            UserDetails userDetails = new UserDetails();
            userDetails.setId((long) row.getCell(0).getNumericCellValue());
            userDetails.setUsername(row.getCell(1).getStringCellValue());
            userDetails.setEmail(row.getCell(2).getStringCellValue());
            userDetailsList.add(userDetails);
        }

        // Save data to database
        userRepository.saveAll(userDetailsList);

        workbook.close();
    }
}
