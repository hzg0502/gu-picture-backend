package com.guyue.gupicturebackend.controller;

import com.guyue.gupicturebackend.annotation.AuthCheck;
import com.guyue.gupicturebackend.common.BaseResponse;
import com.guyue.gupicturebackend.common.ResultUtils;
import com.guyue.gupicturebackend.exception.BusinessException;
import com.guyue.gupicturebackend.exception.ErrorCode;
import com.guyue.gupicturebackend.exception.ThrowUtils;
import com.guyue.gupicturebackend.model.constant.UserConstant;
import com.guyue.gupicturebackend.model.dto.space.SpaceLevel;
import com.guyue.gupicturebackend.model.dto.space.SpaceUpdateRequest;
import com.guyue.gupicturebackend.model.entity.Space;
import com.guyue.gupicturebackend.model.enums.SpaceLevelEnum;
import com.guyue.gupicturebackend.service.SpaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/space")
public class SpaceController {

    @Resource
    private SpaceService spaceService;

    /**
     * 更新接口
     *
     * @param spaceUpdateRequest 前端请求
     * @return Boolean true 是成功
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateSpace(@RequestBody SpaceUpdateRequest spaceUpdateRequest) {
        if (spaceUpdateRequest == null || spaceUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 将实体类和 DTO 进行转化
        Space space = new Space();
        BeanUtils.copyProperties(spaceUpdateRequest, space);
        // 自动填充数据
        spaceService.fillSpaceBySpaceLevel(space);
        // 数据校验
        spaceService.validSpace(space, true);
        // 判断是否存在
        long id = spaceUpdateRequest.getId();
        Space oldSpace = spaceService.getById(id);
        ThrowUtils.throwIf(oldSpace == null, ErrorCode.NOT_FOUND_ERROR);
        // 操作数据库
        boolean result = spaceService.updateById(space);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    /**
     * 获取所有空间等级
     *
     * @return List<SpaceLevel>
     */
    @GetMapping("/list/level")
    public BaseResponse<List<SpaceLevel>> listSpaceLevel() {
        List<SpaceLevel> spaceLevelList = Arrays.stream(SpaceLevelEnum.values()) // 获取所有枚举
                .map(spaceLevelEnum -> new SpaceLevel(
                        spaceLevelEnum.getValue(),
                        spaceLevelEnum.getText(),
                        spaceLevelEnum.getMaxCount(),
                        spaceLevelEnum.getMaxSize()))
                .collect(Collectors.toList());
        return ResultUtils.success(spaceLevelList);
    }
}

