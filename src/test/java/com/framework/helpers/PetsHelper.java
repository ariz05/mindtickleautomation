package com.framework.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.framework.dtos.requests.pet.Category;
import com.framework.dtos.requests.pet.PetDto;
import com.framework.dtos.requests.pet.Tag;

import java.util.HashMap;
import java.util.List;

import static com.framework.utilities.CommonUtils.assertFields;


public class PetsHelper {
    ObjectMapper objectMapper = new ObjectMapper();

    public void validatePetDetailsResponse(HashMap<String, String> responseFields, String responseString) throws JsonProcessingException {
        List<PetDto> petDetailsResponseDto = objectMapper.readValue(responseString, new TypeReference<>() {
        });
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
                    assertFields("contains", "tagId", tag.getId().toString(), responseFields.get("tags"));
                    assertFields("contains", "tagId", tag.getName(), responseFields.get("tags"));
                }

            }

        }
        if (!flag) {
            System.out.println("Pet is not found in the list.");
        }
    }


    public void validatePetDetails(String request, String response) throws JsonProcessingException {
        PetDto petDtoRequest = objectMapper.readValue(request, PetDto.class);
        PetDto petDtoResponse = objectMapper.readValue(response, PetDto.class);

        assertFields("equals", "Id", petDtoRequest.getId().toString(), petDtoResponse.getId().toString());
        assertFields("equals", "CategoryId", petDtoRequest.getCategory().getId().toString(), petDtoResponse.getCategory().getId().toString());
        assertFields("equals", "CategoryName", petDtoRequest.getCategory().getName(), petDtoResponse.getCategory().getName());
        assertFields("equals", "name", petDtoRequest.getName(), petDtoResponse.getName());
        assertFields("equals", "number of photoUrls", String.valueOf(petDtoRequest.getPhotoUrls().size()), String.valueOf(petDtoRequest.getPhotoUrls().size()));
        for (int i = 0; i < petDtoRequest.getPhotoUrls().size(); i++) {
            assertFields("equals", "name", petDtoRequest.getPhotoUrls().get(i), petDtoResponse.getPhotoUrls().get(i));

        }

        for (int i = 0; i < petDtoRequest.getTags().size(); i++) {

            assertFields("contains", "TagId", petDtoResponse.getTags().get(i).getId().toString(), petDtoRequest.getTags().get(i).getId().toString());
            assertFields("contains", "TagName", petDtoResponse.getTags().get(i).getName(), petDtoRequest.getTags().get(i).getName());

        }
        assertFields("equals", "Status", petDtoRequest.getStatus(), petDtoResponse.getStatus());

    }

}

