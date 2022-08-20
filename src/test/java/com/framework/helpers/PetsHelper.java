package com.framework.helpers;

import com.framework.dtos.requests.pet.Category;
import com.framework.dtos.requests.pet.PetDto;
import com.framework.dtos.requests.pet.Tag;

import java.util.HashMap;
import java.util.List;

import static com.framework.utilities.CommonUtils.assertFields;


public class PetsHelper {
    public void validatePetDetailsResponse(HashMap<String, String> responseFields, List<PetDto> petDetailsResponseDto) {
        boolean flag = false;
        for (PetDto petDto : petDetailsResponseDto) {
            if (petDto.getId().equals(Long.valueOf(responseFields.get("id")))) {
                flag = true;
                assertFields("equals", "id", responseFields.get("id"), petDto.getId().toString());
                assertFields("equals", "status", responseFields.get("status"), petDto.getStatus());
                Category category = petDto.getCategory();
                assertFields("equals", "CategoryId", responseFields.get("categoryId"), category.getId().toString());
                assertFields("equals", "CategoryName", responseFields.get("categoryName"), category.getName());
                assertFields("equals", "name", responseFields.get("name"), petDto.getName());
                List<String> listOfURLs = petDto.getPhotoUrls();
                for (String url : listOfURLs) {
                    assertFields("contains", "ListOfURLs", url.trim(), responseFields.get("photoUrls"));
                }
                List<Tag> listOfTags = petDto.getTags();
                for (Tag tag : listOfTags) {
                    assertFields("contains", "tagId", tag.getId().toString(), responseFields.get("tags").toString());
                    assertFields("contains", "tagId", tag.getName(), responseFields.get("tags").toString());
                }

            }

        }
        if (!flag) {
            System.out.println("Pet does not found in the list.");
        }
    }
}

