package com.oasis.oasisapp.exception;


import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.oasis.oasisapp.validation.ValidationErrorResponse;
import com.oasis.oasisapp.validation.Violation;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorHandlingControllerAdvice {

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse onConstraintValidationException(ConstraintViolationException e) {
        final List<Violation> violations = e.getConstraintViolations().stream()
                .map(violation -> new Violation(violation.getMessage()))
                .collect(Collectors.toList());
        return new ValidationErrorResponse(violations);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        final List<Violation> violations = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new Violation(error.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ValidationErrorResponse(violations);
    }


    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onBadCredentialsException(BadCredentialsException e) {
        final List<Violation> violations = new ArrayList<>();
        violations.add(new Violation("Неверный логин или пароль!"));
        return new ValidationErrorResponse(violations);
    }


    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onUserAlreadyExistException(UserAlreadyExistException e) {
        final List<Violation> violations = new ArrayList<>();
        violations.add(new Violation(e.getMessage()));
        return new ValidationErrorResponse(violations);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onRoleNotFoundException(RoleNotFoundException e) {
        final List<Violation> violations = new ArrayList<>();
        violations.add(new Violation(e.getMessage()));
        return new ValidationErrorResponse(violations);
    }


    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onUsernameNotFoundException(UsernameNotFoundException e) {
        final List<Violation> violations = new ArrayList<>();
        violations.add(new Violation(e.getMessage()));
        return new ValidationErrorResponse(violations);
    }


    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onSQLException(SQLException e) {
        final List<Violation> violations = new ArrayList<>();
        violations.add(new Violation(e.getMessage()));
        return new ValidationErrorResponse(violations);
    }


    @ExceptionHandler(GenreNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onGenreNotFoundException(GenreNotFoundException e) {
        final List<Violation> violations = new ArrayList<>();
        violations.add(new Violation(e.getMessage()));
        return new ValidationErrorResponse(violations);
    }

    @ExceptionHandler(GameAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onGameAlreadyExistException(GameAlreadyExistException e) {
        final List<Violation> violations = new ArrayList<>();
        violations.add(new Violation(e.getMessage()));
        return new ValidationErrorResponse(violations);
    }

    @ExceptionHandler(InvalidFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onInvalidFormatException(InvalidFormatException e) {
        final List<Violation> violations = new ArrayList<>();
        violations.add(new Violation("Неверный формат, поле: " + e.getValue()));
        return new ValidationErrorResponse(violations);
    }

    @ExceptionHandler(ShopDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onShopDataException(ShopDataException e) {
        final List<Violation> violations = new ArrayList<>();
        violations.add(new Violation(e.getMessage()));
        return new ValidationErrorResponse(violations);
    }


    @ExceptionHandler(GameDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onGameDataException(GameDataException e) {
        final List<Violation> violations = new ArrayList<>();
        violations.add(new Violation(e.getMessage()));
        return new ValidationErrorResponse(violations);
    }

    @ExceptionHandler(LibraryDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onLibraryDataException(LibraryDataException e) {
        final List<Violation> violations = new ArrayList<>();
        violations.add(new Violation(e.getMessage()));
        return new ValidationErrorResponse(violations);
    }

    @ExceptionHandler(ItemAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onItemAlreadyExistException(ItemAlreadyExistException e) {
        final List<Violation> violations = new ArrayList<>();
        violations.add(new Violation(e.getMessage()));
        return new ValidationErrorResponse(violations);
    }

    @ExceptionHandler(ItemDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onItemDataException(ItemDataException e) {
        final List<Violation> violations = new ArrayList<>();
        violations.add(new Violation(e.getMessage()));
        return new ValidationErrorResponse(violations);
    }

    @ExceptionHandler(UserActivityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onUserActivityException(UserActivityException e) {
        final List<Violation> violations = new ArrayList<>();
        violations.add(new Violation(e.getMessage()));
        return new ValidationErrorResponse(violations);
    }

    @ExceptionHandler(GuideDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onGuideDataException(GuideDataException e) {
        final List<Violation> violations = new ArrayList<>();
        violations.add(new Violation(e.getMessage()));
        return new ValidationErrorResponse(violations);
    }

    @ExceptionHandler(MarketDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onMarketDataException(MarketDataException e) {
        final List<Violation> violations = new ArrayList<>();
        violations.add(new Violation(e.getMessage()));
        return new ValidationErrorResponse(violations);
    }

    @ExceptionHandler(MismatchedInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onMismatchedInputException(MismatchedInputException e) {
        final List<Violation> violations = new ArrayList<>();
        violations.add(new Violation("Неверный формат, поле: " + e.getTargetType()));
        return new ValidationErrorResponse(violations);
    }


    @ExceptionHandler(NotEnoughBalanceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onNotEnoughBalanceException(NotEnoughBalanceException e) {
        final List<Violation> violations = new ArrayList<>();
        violations.add(new Violation(e.getMessage()));
        return new ValidationErrorResponse(violations);
    }
}
