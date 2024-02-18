package com.klimber.springrinha2024q1.models;

import org.springframework.data.annotation.Id;

public record Cliente(@Id Long id,
                      Long limite) {
}
