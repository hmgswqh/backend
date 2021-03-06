package com.youthchina.controller.community;

import com.youthchina.annotation.RequestBodyDTO;
import com.youthchina.domain.jinhao.Comment;
import com.youthchina.domain.jinhao.Video;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.ListResponse;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.comment.CommentRequestDTO;
import com.youthchina.dto.community.comment.CommentResponseDTO;
import com.youthchina.dto.community.video.VideoResponseDTO;
import com.youthchina.dto.util.PageRequest;
import com.youthchina.exception.zhongyang.exception.BaseException;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.community.AttentionService;
import com.youthchina.service.community.CommentService;
import com.youthchina.service.community.EvaluateService;
import com.youthchina.service.community.VideoServiceImpl;
import com.youthchina.service.user.UserServiceImpl;
import com.youthchina.service.util.StaticFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("${web.url.prefix}/videos/**")
public class VideoController {
    @Autowired
    VideoServiceImpl videoService;

    @Autowired
    private StaticFileService fileService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    CommentService commentService;

    @Autowired
    EvaluateService evaluateService;

     @Autowired
    AttentionService attentionService;

    @GetMapping("/{id}")
    public ResponseEntity getVideo(@PathVariable Integer id,@AuthenticationPrincipal User user) throws NotFoundException {
        Video video = videoService.get(id);
        URL s = fileService.getFileUrl(video.getName(), "China");
        VideoResponseDTO videoResponseDTO = new VideoResponseDTO(video);
        videoResponseDTO.setUrl(s.toString());
        videoResponseDTO.setAttentionCount(attentionService.countAttention(video));
        videoResponseDTO.setEvaluateStatus(evaluateService.evaluateStatus(video,user.getId()));
        videoResponseDTO.setUpvoteCount(evaluateService.countUpvote(video));
        videoResponseDTO.setDownvoteCount(evaluateService.countDownvote(video));
        videoResponseDTO.setAttention((attentionService.isEverAttention(video,user.getId()))==0? false:true);
        return ResponseEntity.ok(new Response(videoResponseDTO, new StatusDTO(200, "success")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteVideo(@PathVariable Integer id) throws NotFoundException {
        videoService.delete(id);
        return ResponseEntity.ok(new Response(new StatusDTO(204, "success")));
    }

    @PostMapping("/**")
    public ResponseEntity addVideo(@RequestPart MultipartFile file, @AuthenticationPrincipal User user) throws BaseException {
        Long id;
        try {
            id = fileService.saveFile(file.getResource(), user.getId());
        } catch (IOException e) {
            throw new BaseException(5000, 500, "Cannot upload file because server end error");
        }

        Video video = new Video();
        video.setName(id.toString());
        video.setUser(user);
        video.setTitle(id.toString());
        video.setUser(user);
        video.setRelaType(1);
        video.setDescription("1111");
        video.setViewCount(12);
        video = videoService.add(video);
        VideoResponseDTO videoResponseDTO = new VideoResponseDTO(video);
        videoResponseDTO.setUrl(fileService.getFileUrl(id.toString()).toString());
        return ResponseEntity.ok(new Response(videoResponseDTO, new StatusDTO(201, "success")));
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<?> addComments(@PathVariable Integer id, @RequestBodyDTO(CommentRequestDTO.class)  Comment comment, @AuthenticationPrincipal User user) throws NotFoundException {
        Video video = new Video();
        video.setId(id);
        comment.setUser(user);
        Comment comment1 = commentService.add(comment,video);
        if (comment1.getId() == null) {
            return ResponseEntity.ok(new Response(new StatusDTO(403, "failed")));
        } else
            return ResponseEntity.ok(new Response(new StatusDTO(201, "success")));
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity getComments(@PathVariable Integer id, PageRequest pageRequest,@AuthenticationPrincipal User user) throws NotFoundException {
        Video video = new Video();
        video.setId(id);
       List<Comment> comments = commentService.getComments(video);

        List<CommentResponseDTO> commentResponseDTOS = new ArrayList<>();
        if(comments!=null) {
            Iterator it = comments.iterator();
            while (it.hasNext()) {
                CommentResponseDTO commentResponseDTO = new CommentResponseDTO((Comment) it.next());
                commentResponseDTO.setUpvoteCount(evaluateService.countUpvote(video));
                commentResponseDTO.setDownvoteCount(evaluateService.countDownvote(video));
                commentResponseDTO.setEvaluateStatus(evaluateService.evaluateStatus(video,user.getId()));
                commentResponseDTOS.add(commentResponseDTO);
            }
        }
        ListResponse listResponse = new ListResponse(pageRequest, commentResponseDTOS.size(), commentResponseDTOS);
        return ResponseEntity.ok(new Response(listResponse, new StatusDTO(200, "success")));
    }

    @PutMapping("/{id}/upvote")
    public ResponseEntity upvoteVideo(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        Video video = new Video();
        video.setId(id);
        evaluateService.upvote(video,user.getId());
        return ResponseEntity.ok(new Response( new StatusDTO(200, "success")));
    }

    @PutMapping("/{id}/downvote")
    public ResponseEntity downvoteVideo(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        Video video = new Video();
        video.setId(id);
        evaluateService.downvote(video,user.getId());
        return ResponseEntity.ok(new Response( new StatusDTO(200, "success")));
    }

    @PutMapping("/{id}/attention")
    public ResponseEntity attentionVideo(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        Video video = new Video();
        video.setId(id);
        attentionService.attention(video,user.getId());
        return ResponseEntity.ok(new Response(new StatusDTO(200, "success")));
    }

}
