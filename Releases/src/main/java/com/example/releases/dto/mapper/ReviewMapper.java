package com.example.releases.dto.mapper;

import com.example.releases.dto.ReviewDTO;
import com.example.releases.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReviewMapper {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    public ReviewDTO mapToReviewDTO(Review review);

    public Review mapToReview(ReviewDTO reviewDTO);
}
