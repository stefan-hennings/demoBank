package com.example.demobank.models;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ApprovalDto {
    final private boolean isApproved;

    @JsonCreator
    public ApprovalDto(@JsonProperty("pass") boolean isApproved) {
        this.isApproved = isApproved;
    }
}
