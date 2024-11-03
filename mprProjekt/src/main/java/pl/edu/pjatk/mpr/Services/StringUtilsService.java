package pl.edu.pjatk.mpr.Services;
import org.springframework.stereotype.Service;
@Service
public class StringUtilsService {
    public String toUpperCase(String input){
        if(input != null){
            return input.toUpperCase();
        }
        else {
            return null;
        }
    }

    public String capitalizeFirstLetter(String input){
        if(input == null || input.isEmpty()){
            return null;
        }
        String lowerCase = input.toLowerCase();
        return Character.toUpperCase(lowerCase.charAt(0)) + lowerCase.substring(1);

    }

}
