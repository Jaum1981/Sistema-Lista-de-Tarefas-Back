package com.jaum1981.todolist.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Object id) {
        super("Resource Not Found. Id: " + id);
    }
}
