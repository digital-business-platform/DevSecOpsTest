package com.msil.appointment.exception;
import java.time.Instant;
import java.util.List;
public record ApiError(String code, String message, List<String> details, String path, Instant timestamp) {}
