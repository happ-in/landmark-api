package com.landmark.app.controller;

import com.landmark.app.model.dto.TourInfoDTO;
import com.landmark.app.model.dto.TourReviewDTO;
import com.landmark.app.model.dto.commnet.InfoCommentDTO;
import com.landmark.app.model.dto.user.UserDTO;
import com.landmark.app.service.TourInfoService;
import com.landmark.app.utils.LoggerUtils;
import com.landmark.app.utils.helper.AccountHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.landmark.app.utils.constants.Constants.*;

@RestController
@RequestMapping(value = TOUR_INFO_API)
public class TourInfoController extends LoggerUtils {
    private TourInfoService tourInfoService;
    private AccountHelper accountHelper;

    @Autowired
    public TourInfoController(TourInfoService tourInfoService, AccountHelper accountHelper) {
        this.tourInfoService = tourInfoService;
        this.accountHelper = accountHelper;
    }

    @PostMapping
    public ResponseEntity<?> registerTour(@RequestBody TourInfoDTO tourInfoDTO, HttpServletRequest request) {
        try {
            int userId = accountHelper.getAccountId(request);
            tourInfoDTO.setUserId(userId);
            return new ResponseEntity<>(tourInfoService.registerTourist(tourInfoDTO), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("registerTourInfo : " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateTour(@RequestBody TourInfoDTO tourInfoDTO, HttpServletRequest request) {
        try {
            int userId = accountHelper.getAccountId(request);
            return new ResponseEntity<>(tourInfoService.updateTours(tourInfoDTO, userId), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("updateTourInfo : " + e.getMessage());
            return new ResponseEntity<>(0, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{tourId}")
    public ResponseEntity<?> deleteTour(@PathVariable("tourId") int tourId, HttpServletRequest request) {
        try {
            UserDTO user = accountHelper.getAccountInfo(request);
            String role = user.getRole().getRolename();
            int userId = accountHelper.getAccountId(request);

            return new ResponseEntity<>(tourInfoService.deleteTours(tourId, userId, role), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("updateTourInfo : " + e.getMessage());
            return new ResponseEntity<>(0, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 댓글
     */

    @PostMapping(value = "/comment/{infoId}")
    public ResponseEntity<?> registerInfoComment(@PathVariable("infoId") int infoId, @RequestBody InfoCommentDTO commentDTO, HttpServletRequest request) {
        try {
            int userId = accountHelper.getAccountId(request);
            commentDTO.setUserId(userId);
            commentDTO.setInfoId(infoId);
            return new ResponseEntity<>(tourInfoService.registerInfoComment(commentDTO), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("TourInfoController(registerInfoComment) : " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/comment/{infoId}")
    public ResponseEntity<?> findAllInfoComments(@PathVariable("infoId") int infoId, HttpServletRequest request) {
        try {
            return new ResponseEntity<>(tourInfoService.findAllInfoComments(infoId), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("TourInfoController(findAllInfoComments) : " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/comment")
    public ResponseEntity<?> updateInfoComment(@RequestBody InfoCommentDTO commentDTO, HttpServletRequest request) {
        try {
            int userId = accountHelper.getAccountId(request);
            return new ResponseEntity<>(tourInfoService.updateInfoComment(commentDTO, userId), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("TourInfoController(updateInfoComment) : " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/comment/{id}")
    public ResponseEntity<?> deleteInfoComment(@PathVariable("id") int id, HttpServletRequest request) {
        try {
            int userId = accountHelper.getAccountId(request);
            String role = accountHelper.getRole(request);
            return new ResponseEntity<>(tourInfoService.deleteInfoCommet(id, userId, role), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("TourInfoController(deleteInfoComment) : " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
