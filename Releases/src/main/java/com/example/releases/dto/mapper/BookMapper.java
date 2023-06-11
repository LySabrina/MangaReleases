package com.example.releases.dto.mapper;

import com.example.releases.dto.BookDTO;
import com.example.releases.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper //Automatically generate a working implementation of this java file at build-time --> target/generated-soources/annotations
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    public BookDTO mapToBookDTO(Book book);

    public Book mapToBook(BookDTO bookDTO);
}
