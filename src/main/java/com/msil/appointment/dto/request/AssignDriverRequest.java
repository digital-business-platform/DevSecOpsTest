package com.msil.appointment.dto.request;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record AssignDriverRequest(@NotNull UUID driverId) {}
