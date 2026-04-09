package com.guyue.gupicturebackend.api.imagesearch.baidu;

import com.guyue.gupicturebackend.api.imagesearch.baidu.model.ImageSearchResult;
import com.guyue.gupicturebackend.api.imagesearch.baidu.sub.GetImageFirstUrlApi;
import com.guyue.gupicturebackend.api.imagesearch.baidu.sub.GetImageListApi;
import com.guyue.gupicturebackend.api.imagesearch.baidu.sub.GetImagePageUrlApi;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ImageSearchApiFacade {

    /**
     * 搜索图片
     *
     * @param imageUrl
     * @return
     */
    public static List<ImageSearchResult> searchImage(String imageUrl) {
        String imagePageUrl = GetImagePageUrlApi.getImagePageUrl(imageUrl);
        String imageFirstUrl = GetImageFirstUrlApi.getImageFirstUrl(imagePageUrl);
        List<ImageSearchResult> imageList = GetImageListApi.getImageList(imageFirstUrl);
        return imageList;
    }

    public static void main(String[] args) {
        // 测试以图搜图功能
        String imageUrl = "http://e.hiphotos.baidu.com/image/pic/item/a1ec08fa513d2697e542494057fbb2fb4316d81e.jpg";
        List<ImageSearchResult> resultList = searchImage(imageUrl);
        System.out.println("结果列表" + resultList);
    }
}
