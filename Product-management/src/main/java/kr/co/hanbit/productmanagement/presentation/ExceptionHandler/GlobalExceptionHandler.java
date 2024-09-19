package kr.co.hanbit.productmanagement.presentation.ExceptionHandler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import kr.co.hanbit.productmanagement.domain.Exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex){
        // 예외에 대한 처리
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        List<String> errors = constraintViolations.stream()
                .map(
                        constraintViolation -> //constraintViolation.getPropertyPath()+", "+constraintViolation.getMessage()
                                extractField(constraintViolation.getPropertyPath())+", "+constraintViolation.getMessage()
                ).toList();
        ErrorMessage errMsg = new ErrorMessage(errors);
        return new ResponseEntity(errMsg, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<String> errors = fieldErrors.stream()
                .map(fieldError -> fieldError.getField()+", "+fieldError.getDefaultMessage())
                .toList();
        ErrorMessage errorMessage = new ErrorMessage(errors);
        return new ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleEntityNotFoundException(EntityNotFoundException ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());

        ErrorMessage errorMessage = new ErrorMessage(errors);
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    private String extractField(Path path){

        /* ConstraintViolationException 처리 핸들러에서는
        ValidationService에서 사용한 메서드와 매개 변수 이름이 함꼐 노출되기에 통일성 있게 수정해 주는 작업 */

        String[] splittedArray = path.toString().split("[.]");  // '.'을 기준으로 나눈 후 가장 마지막 요소만 사용
        int lastIndex = splittedArray.length-1;
        return splittedArray[lastIndex];
    }
}
