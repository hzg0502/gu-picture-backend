package com.guyue.gupicturebackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guyue.gupicturebackend.model.dto.picture.*;
import com.guyue.gupicturebackend.model.entity.Picture;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guyue.gupicturebackend.model.entity.User;
import com.guyue.gupicturebackend.model.vo.PictureVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author qzzq
* @description 针对表【picture(图片)】的数据库操作Service
* @createDate 2026-03-28 16:48:57
*/
public interface PictureService extends IService<Picture> {

    /**
     * 上传图片
     *
     * @param inputSource
     * @param pictureUploadRequest
     * @param loginUser
     * @return
     */
    PictureVO uploadPicture(Object inputSource,
                            PictureUploadRequest pictureUploadRequest,
                            User loginUser);

    QueryWrapper<Picture> getQueryWrapper(PictureQueryRequest pictureQueryRequest);

    PictureVO getPictureVO(Picture picture, HttpServletRequest request);

    Page<PictureVO> getPictureVOPage(Page<Picture> picturePage, HttpServletRequest request);

    void validPicture(Picture picture);

    /**
     * 图片审核
     *
     * @param pictureReviewRequest
     * @param loginUser
     */
    void doPictureReview(PictureReviewRequest pictureReviewRequest, User loginUser);

    void fillReviewParams(Picture picture, User loginUser);

    /**
     * 批量抓取和创建图片
     *
     * @param pictureUploadByBatchRequest
     * @param loginUser
     * @return 成功创建的图片数
     */
    Integer uploadPictureByBatch(
            PictureUploadByBatchRequest pictureUploadByBatchRequest,
            User loginUser
    );

    /**
     * 删除图片文件
     * @param oldPicture
     */
    void clearPictureFile(Picture oldPicture);

    void checkPictureAuth(User loginUser, Picture picture);

    /**
     * 删除图片
     *
     * @param pictureId 图片 id
     * @param loginUser 登录用户
     */
    void deletePicture(long pictureId, User loginUser);

    /**
     * 编辑图片
     *
     * @param pictureEditRequest 图片编辑请求
     * @param loginUser 登录用户
     */
    void editPicture(PictureEditRequest pictureEditRequest, User loginUser);

    /**
     * 根据颜色搜索图片
     *
     * @param spaceId
     * @param picColor
     * @param loginUser
     * @return
     */
    List<PictureVO> searchPictureByColor(Long spaceId, String picColor, User loginUser);

    /**
     * 批量修改图片
     *
     * @param pictureEditByBatchRequest 图片批量修改请求
     * @param loginUser                 登录用户
     */
    void editPictureByBatch(PictureEditByBatchRequest pictureEditByBatchRequest, User loginUser);

}
