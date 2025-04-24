//package mk.ukim.finki.wp.workspaces.web;
//
//import mk.ukim.finki.wp.workspaces.model.ErrorMessage.ErrorMessage;
//import mk.ukim.finki.wp.workspaces.model.exceptions.AccessDeniedException;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity<ErrorMessage> handleAccessDenied(AccessDeniedException ex) {
//        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
//        return ResponseEntity.status(ex.getStatusCode()).body(errorMessage);
//    }
//}