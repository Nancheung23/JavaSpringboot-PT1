package com.foodrecipes.webapp.controller;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.foodrecipes.webapp.dto.CommentDTO;
import com.foodrecipes.webapp.model.Comment;
import com.foodrecipes.webapp.repository.CommentRepository;
// import com.foodrecipes.webapp.repository.RecipeRepository;
// import com.foodrecipes.webapp.repository.UserRepository;
import com.foodrecipes.webapp.service.CommentConversionService;
import com.foodrecipes.webapp.service.ImageStorageService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    // private UserRepository userRepository;
    // private RecipeRepository recipeRepository;
    private final CommentRepository commentRepository;
    private final CommentConversionService commentConversionService;
    private final ImageStorageService imageStorageService;

    @Autowired
    public CommentController(CommentRepository commentRepository, CommentConversionService commentConversionService, ImageStorageService imageStorageService) {
        this.commentRepository = commentRepository;
        this.commentConversionService = commentConversionService;
        this.imageStorageService = imageStorageService;
    }

    @GetMapping("/")
    public Iterable<Comment> getComments() {
        return commentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Comment> getCommentById(@PathVariable Long id) {
        return commentRepository.findById(id);
    }

    @PostMapping("/")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDto) {
        Comment comment = commentConversionService.convertToEntity(commentDto);
        commentRepository.save(comment);
        return ResponseEntity.ok(commentConversionService.convertToDto(comment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDTO> modifyComment(@PathVariable Long id, CommentDTO commentDto) {
        Comment comment = commentConversionService.convertToEntity(commentDto);
        if (!commentRepository.existsById(id)) {
            return createComment(commentDto);
        } else {
            comment.setId(id);
            commentRepository.save(comment);
            return ResponseEntity.ok(commentConversionService.convertToDto(comment));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommentDTO> deleteComment(@PathVariable Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(NoSuchElementException::new);
        if (comment != null) {
            commentRepository.delete(comment);
        }
        return ResponseEntity.ok(commentConversionService.convertToDto(comment));
    }

    @PostMapping("/{id}/image")
    public ResponseEntity<CommentDTO> postImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new NoSuchElementException("comment not found"));
        String fileUrl = imageStorageService.fileStore(file);
        comment.setPicture(fileUrl);
        comment.setId(id);
        commentRepository.save(comment);
        return ResponseEntity.ok(commentConversionService.convertToDto(comment));
    }
}
