package kr.co.hanbit.productmanagement.domain.Exception;

import kr.co.hanbit.productmanagement.presentation.ExceptionHandler.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException (String message){
        super(message);
    }


}
