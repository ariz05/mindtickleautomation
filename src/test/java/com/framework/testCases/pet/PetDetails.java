package com.framework.testCases.pet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.framework.dtos.requests.pet.Category;
import com.framework.dtos.requests.pet.PetDto;
import com.framework.dtos.requests.pet.Tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PetDetails {

    public String setPetDetails(HashMap<String, String> requestFields) {
        ObjectMapper objectMapper = new ObjectMapper();
        Category category;
        PetDto petDto;
        String petDetails = "";
        try {
            petDto = new PetDto();
            petDto.setId(Long.valueOf(requestFields.get("id")));
            category = new Category();
            category.setId(Integer.parseInt(requestFields.get("categoryId")));
            category.setName(requestFields.get("categoryName"));
            petDto.setCategory(category);
            petDto.setName(requestFields.get("name"));
            String[] photoURLs = requestFields.get("photoUrls").split(",");
            List<String> photoURLsList = new ArrayList<>();
            for (int i = 0; i < photoURLs.length; i++) {
                photoURLsList.add(photoURLs[i]);
            }
            petDto.setPhotoUrls(photoURLsList);
            List<Tag> tagList = new ArrayList<>();
            String[] tags = requestFields.get("tags").split(",");
            for (int k = 0; k < tags.length; k++) {
                Tag tag = new Tag();
                String[] tagDetails = tags[k].split("_");
                tag.setId(Integer.parseInt(tagDetails[0]));
                tag.setName(tagDetails[1]);
                tagList.add(tag);
            }
            petDto.setTags(tagList);
            petDto.setStatus(requestFields.get("status"));
            petDetails = objectMapper.writeValueAsString(petDto);
            System.out.println(petDetails);

        } catch (Exception ex) {
        }
        return petDetails;
    }
}
