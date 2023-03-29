package com.muamerseljubac.entity.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookEditRequestDTO {

    private UUID id;

    private String title;

    private List<UUID> authorIds;

    private List<UUID> categoryIds;

}
