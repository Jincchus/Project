package phoenix.partyquest.api.exception;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import org.springframework.http.MediaType;

import java.io.IOException;

/**
 * Exception Class
 */
public class CustomException extends RuntimeException{

    MAIN_ERROR error;

    @Getter
    public enum MAIN_ERROR{
        NOTFOUND(404, "404에러가 발생했습니다!");

        private int status;
        private String message;

        MAIN_ERROR(int status,String message){
            this.status = status;
            this.message = message;
        }
    }
    public CustomException(MAIN_ERROR error){
        super(error.name());
        this.error = error;
    }
    public void sendResponseError(HttpServletResponse response) throws IOException{
        response.setStatus(error.getStatus());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().print(error.getMessage());
    }

}
