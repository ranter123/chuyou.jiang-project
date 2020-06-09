package chuyou.jiang.mybatis.controller;

import chuyou.jiang.mybatis.mapper.BannerMapper;
import chuyou.jiang.mybatis.model.Banner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/banner")
@RestController
@Slf4j
public class BannerController {

    @Autowired
    BannerMapper bannerMapper;

    @GetMapping("/getBanner")
    public List<Banner> getBannerInfo() {
        List<Banner> banners = bannerMapper.findBanner();
        log.info(banners.toString());
        return banners;
    }

    @GetMapping("/insertBanner")
    public String insertBanner(@RequestParam("title") String title) {
        Banner banner = new Banner();
        banner.setTitle(title);
        int resultRow = bannerMapper.insertBanner(banner);
        if (resultRow > 0) {
            return "success";
        } else {
            return "error";
        }
    }
}
