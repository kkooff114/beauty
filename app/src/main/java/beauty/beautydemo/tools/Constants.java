package beauty.beautydemo.tools;

/**
 * Created by LJW on 15/3/17.
 */


import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import beauty.beautydemo.R;
import beauty.beautydemo.bean.CityEntity;
import beauty.beautydemo.bean.Comment;
import beauty.beautydemo.bean.LibListItem;
import beauty.beautydemo.bean.NewsEntity;
import beauty.beautydemo.bean.ShopProduct;
import beauty.beautydemo.bean.realm.NoteRealm;
import beauty.beautydemo.custview.imageprocessing.filter.BasicFilter;
import beauty.beautydemo.custview.imageprocessing.filter.colour.AdaptiveThresholdFilter;
import beauty.beautydemo.custview.imageprocessing.filter.colour.AmatorkaFilter;
import beauty.beautydemo.custview.imageprocessing.filter.colour.BrightnessFilter;
import beauty.beautydemo.custview.imageprocessing.filter.colour.ChromaKeyFilter;
import beauty.beautydemo.custview.imageprocessing.filter.colour.ColourInvertFilter;
import beauty.beautydemo.custview.imageprocessing.filter.colour.ColourMatrixFilter;
import beauty.beautydemo.custview.imageprocessing.filter.colour.ContrastFilter;
import beauty.beautydemo.custview.imageprocessing.filter.colour.ExposureFilter;
import beauty.beautydemo.custview.imageprocessing.filter.colour.FalseColourFilter;
import beauty.beautydemo.custview.imageprocessing.filter.colour.GammaFilter;
import beauty.beautydemo.custview.imageprocessing.filter.colour.GreyScaleFilter;
import beauty.beautydemo.custview.imageprocessing.filter.colour.HazeFilter;
import beauty.beautydemo.custview.imageprocessing.filter.colour.HighlightShadowFilter;
import beauty.beautydemo.custview.imageprocessing.filter.colour.HueFilter;
import beauty.beautydemo.custview.imageprocessing.filter.colour.LevelsFilter;
import beauty.beautydemo.custview.imageprocessing.filter.colour.LookupFilter;
import beauty.beautydemo.custview.imageprocessing.filter.colour.LuminanceThresholdFilter;
import beauty.beautydemo.custview.imageprocessing.filter.colour.MissEtikateFilter;
import beauty.beautydemo.custview.imageprocessing.filter.colour.MonochromeFilter;
import beauty.beautydemo.custview.imageprocessing.filter.colour.OpacityFilter;
import beauty.beautydemo.custview.imageprocessing.filter.colour.RGBFilter;
import beauty.beautydemo.custview.imageprocessing.filter.colour.SaturationFilter;
import beauty.beautydemo.custview.imageprocessing.filter.colour.SepiaFilter;
import beauty.beautydemo.custview.imageprocessing.filter.colour.SoftEleganceFilter;
import beauty.beautydemo.custview.imageprocessing.filter.colour.ToneCurveFilter;
import beauty.beautydemo.custview.imageprocessing.filter.effect.BulgeDistortionFilter;
import beauty.beautydemo.custview.imageprocessing.filter.effect.CGAColourSpaceFilter;
import beauty.beautydemo.custview.imageprocessing.filter.effect.CrosshatchFilter;
import beauty.beautydemo.custview.imageprocessing.filter.effect.EmbossFilter;
import beauty.beautydemo.custview.imageprocessing.filter.effect.GlassSphereFilter;
import beauty.beautydemo.custview.imageprocessing.filter.effect.HalftoneFilter;
import beauty.beautydemo.custview.imageprocessing.filter.effect.KuwaharaRadius3Filter;
import beauty.beautydemo.custview.imageprocessing.filter.effect.MosaicFilter;
import beauty.beautydemo.custview.imageprocessing.filter.effect.PinchDistortionFilter;
import beauty.beautydemo.custview.imageprocessing.filter.effect.PixellateFilter;
import beauty.beautydemo.custview.imageprocessing.filter.effect.PolarPixellateFilter;
import beauty.beautydemo.custview.imageprocessing.filter.effect.PolkaDotFilter;
import beauty.beautydemo.custview.imageprocessing.filter.effect.PosterizeFilter;
import beauty.beautydemo.custview.imageprocessing.filter.effect.SketchFilter;
import beauty.beautydemo.custview.imageprocessing.filter.effect.SmoothToonFilter;
import beauty.beautydemo.custview.imageprocessing.filter.effect.SphereRefractionFilter;
import beauty.beautydemo.custview.imageprocessing.filter.effect.StretchDistortionFilter;
import beauty.beautydemo.custview.imageprocessing.filter.effect.SwirlFilter;
import beauty.beautydemo.custview.imageprocessing.filter.effect.ThresholdSketchFilter;
import beauty.beautydemo.custview.imageprocessing.filter.effect.ToonFilter;
import beauty.beautydemo.custview.imageprocessing.filter.effect.VignetteFilter;
import beauty.beautydemo.custview.imageprocessing.filter.processing.BilateralBlurFilter;
import beauty.beautydemo.custview.imageprocessing.filter.processing.BoxBlurFilter;
import beauty.beautydemo.custview.imageprocessing.filter.processing.CannyEdgeDetectionFilter;
import beauty.beautydemo.custview.imageprocessing.filter.processing.ClosingFilter;
import beauty.beautydemo.custview.imageprocessing.filter.processing.ClosingRGBFilter;
import beauty.beautydemo.custview.imageprocessing.filter.processing.ConvolutionFilter;
import beauty.beautydemo.custview.imageprocessing.filter.processing.CropFilter;
import beauty.beautydemo.custview.imageprocessing.filter.processing.DilationFilter;
import beauty.beautydemo.custview.imageprocessing.filter.processing.DilationRGBFilter;
import beauty.beautydemo.custview.imageprocessing.filter.processing.ErosionFilter;
import beauty.beautydemo.custview.imageprocessing.filter.processing.ErosionRGBFilter;
import beauty.beautydemo.custview.imageprocessing.filter.processing.FastBlurFilter;
import beauty.beautydemo.custview.imageprocessing.filter.processing.FlipFilter;
import beauty.beautydemo.custview.imageprocessing.filter.processing.GaussianBlurFilter;
import beauty.beautydemo.custview.imageprocessing.filter.processing.GaussianBlurPositionFilter;
import beauty.beautydemo.custview.imageprocessing.filter.processing.GaussianSelectiveBlurFilter;
import beauty.beautydemo.custview.imageprocessing.filter.processing.MedianFilter;
import beauty.beautydemo.custview.imageprocessing.filter.processing.MotionBlurFilter;
import beauty.beautydemo.custview.imageprocessing.filter.processing.OpeningFilter;
import beauty.beautydemo.custview.imageprocessing.filter.processing.OpeningRGBFilter;
import beauty.beautydemo.custview.imageprocessing.filter.processing.SharpenFilter;
import beauty.beautydemo.custview.imageprocessing.filter.processing.SingleComponentFastBlurFilter;
import beauty.beautydemo.custview.imageprocessing.filter.processing.SingleComponentGaussianBlurFilter;
import beauty.beautydemo.custview.imageprocessing.filter.processing.SobelEdgeDetectionFilter;
import beauty.beautydemo.custview.imageprocessing.filter.processing.ThresholdEdgeDetectionFilter;
import beauty.beautydemo.custview.imageprocessing.filter.processing.TiltShiftFilter;
import beauty.beautydemo.custview.imageprocessing.filter.processing.TransformFilter;
import beauty.beautydemo.custview.imageprocessing.filter.processing.UnsharpMaskFilter;
import beauty.beautydemo.custview.imageprocessing.filter.processing.ZoomBlurFilter;
import beauty.beautydemo.entity.MyIcon;
import beauty.beautydemo.entity.NoteEntity;
import beauty.beautydemo.entity.SubcribeContentListItem;
import beauty.beautydemo.entity.SubscribeListItem;
import beauty.beautydemo.entity.Tag;


public class Constants {


    /*
             * 获取新闻列表
             */
    public static ArrayList<NewsEntity> getNewsList() {
        ArrayList<NewsEntity> newsList = new ArrayList<NewsEntity>();
        for (int i = 0; i < 10; i++) {
            NewsEntity news = new NewsEntity();
            news.setId(i);
            news.setNewsId(i);
            news.setCollectStatus(false);
            news.setCommentNum(i + 10);
            news.setInterestedStatus(true);
            news.setLikeStatus(true);
            news.setReadStatus(false);
            news.setNewsCategory("推荐");
            news.setNewsCategoryId(1);
            news.setSource_url("http://news.sina.com.cn/c/2014-05-05/134230063386.shtml");
            news.setTitle("韩新男团Winner组合南泰贤眼妆画法");
            List<String> url_list = new ArrayList<String>();
            if (i % 2 == 1) {
                String url1 = "http://infopic.gtimg.com/qq_news/digi/pics/102/102066/102066094_400_640.jpg";
                String url2 = "http://infopic.gtimg.com/qq_news/digi/pics/102/102066/102066096_400_640.jpg";
                String url3 = "http://infopic.gtimg.com/qq_news/digi/pics/102/102066/102066099_400_640.jpg";
                news.setPicOne(url1);
                news.setPicTwo(url2);
                news.setPicThr(url3);
                news.setSource_url("http://tech.sina.com.cn/zl/post/detail/i/2013-11-06/pid_8436571.htm?from=groupmessage&isappinstalled=0");
                url_list.add(url1);
                url_list.add(url2);
                url_list.add(url3);
            } else {
                news.setTitle("韩国郑秀妍卧蚕眼妆画法");
                String url = "http://r3.sinaimg.cn/2/2014/0417/a7/6/92478595/580x1000x75x0.jpg";
                news.setPicOne(url);
                url_list.add(url);
            }
            news.setPicList(url_list);
            news.setPublishTime(Long.valueOf(i));
            news.setReadStatus(false);
            news.setSource("手机腾讯网");
            news.setSummary("腾讯数码讯（编译：Gin）谷歌眼镜可能是目前最酷的可穿戴数码设备，你可以戴着它去任何地方（只要法律法规允许或是没有引起众怒），作为手机的第二块“增强现实显示屏”来使用。另外，虽然它仍未正式销售，但谷歌近日在美国市场举行了仅限一天的开放购买活动，价格则为1500美元（约合人民币9330元），虽然仍十分昂贵，但至少可以满足一些尝鲜者的需求，也预示着谷歌眼镜的公开大规模销售离我们越来越近了。");
            news.setMark(i);
            if (i == 4) {
                news.setTitle("部落战争强势回归");
                news.setLocal("推广");
                news.setIsLarge(true);
                String url = "http://imgt2.bdstatic.com/it/u=3269155243,2604389213&fm=21&gp=0.jpg";
                news.setSource_url("http://games.sina.com.cn/zl/duanpian/2014-05-21/141297.shtml");
                news.setPicOne(url);
                url_list.clear();
                url_list.add(url);
            } else {
                news.setIsLarge(false);
            }
            if (i == 2) {
                news.setComment("评论部分，说的非常好。");
            }

            if (i <= 2) {
                news.setPublishTime(Long.valueOf(DateTools.getTime()));
            } else if (i > 2 && i <= 5) {
                news.setPublishTime(Long.valueOf(DateTools.getTime()) - 86400);
            } else {
                news.setPublishTime(Long.valueOf(DateTools.getTime()) - 86400 * 2);
            }
            newsList.add(news);
        }
        return newsList;
    }

    /**
     * mark=0 ：推荐
     */
    public final static int mark_recom = 0;
    /**
     * mark=1 ：热门
     */
    public final static int mark_hot = 1;
    /**
     * mark=2 ：首发
     */
    public final static int mark_frist = 2;
    /**
     * mark=3 ：独家
     */
    public final static int mark_exclusive = 3;
    /**
     * mark=4 ：收藏
     */
    public final static int mark_favor = 4;

    /*
     * 获取城市列表
     */
    public static ArrayList<CityEntity> getCityList() {
        ArrayList<CityEntity> cityList = new ArrayList<CityEntity>();
        CityEntity city1 = new CityEntity(1, "安吉", 'A');
        CityEntity city2 = new CityEntity(2, "北京", 'B');
        CityEntity city3 = new CityEntity(3, "长春", 'C');
        CityEntity city4 = new CityEntity(4, "长沙", 'C');
        CityEntity city5 = new CityEntity(5, "大连", 'D');
        CityEntity city6 = new CityEntity(6, "哈尔滨", 'H');
        CityEntity city7 = new CityEntity(7, "杭州", 'H');
        CityEntity city8 = new CityEntity(8, "金沙江", 'J');
        CityEntity city9 = new CityEntity(9, "江门", 'J');
        CityEntity city10 = new CityEntity(10, "山东", 'S');
        CityEntity city11 = new CityEntity(11, "三亚", 'S');
        CityEntity city12 = new CityEntity(12, "义乌", 'Y');
        CityEntity city13 = new CityEntity(13, "舟山", 'Z');
        cityList.add(city1);
        cityList.add(city2);
        cityList.add(city3);
        cityList.add(city4);
        cityList.add(city5);
        cityList.add(city6);
        cityList.add(city7);
        cityList.add(city8);
        cityList.add(city9);
        cityList.add(city10);
        cityList.add(city11);
        cityList.add(city12);
        cityList.add(city13);
        return cityList;
    }

    /* 频道中区域 如杭州 对应的栏目ID */
    public final static int CHANNEL_CITY = 3;


    public static ArrayList<LibListItem> getTestLibList() {

        ArrayList<LibListItem> list = new ArrayList<>();

        LibListItem item1 = new LibListItem();
        item1.setProductId("1");
        item1.setProductNameCN("圣罗兰莹亮纯魅口红");
        item1.setProductNameEN("ROUGE VOLUPTE SHINE");
        item1.setProductModel("shine16");
        item1.setOneRate("点评:阿玛尼的唇彩就是一个完美！颜色纯正一次全覆盖！感动的要流泪！");
        item1.setRate(8.3f);
        item1.setProductImage(String.valueOf(R.drawable.product1_a));
        item1.setTryImage(String.valueOf(R.drawable.product1_b1));
        list.add(item1);

        LibListItem item2 = new LibListItem();
        item2.setProductId("2");
        item2.setProductNameCN("迪奥诱惑水感唇釉");
        item2.setProductNameEN("DIOR ADDICT FLUID STICK");
        item2.setProductModel("dior338");
        item2.setRate(6.5f);
        item2.setProductImage(String.valueOf(R.drawable.product2_a));
        item2.setTryImage(String.valueOf(R.drawable.product2_b1));
        item2.setOneRate("点评:阿玛尼的唇彩就是一个完美！颜色纯正一次全覆盖！感动的要流泪！");
        list.add(item2);

        LibListItem item3 = new LibListItem();
        item3.setProductId("3");
        item3.setProductNameCN("圣罗兰莹亮纯魅口红");
        item3.setProductNameEN("ROUGE VOLUPTE PERLE");
        item3.setProductModel("perle110");
        item3.setRate(8.8f);
        item3.setProductImage(String.valueOf(R.drawable.product3_a));
        item3.setTryImage(String.valueOf(R.drawable.product3_b1));
        item3.setOneRate("点评:阿玛尼的唇彩就是一个完美！颜色纯正一次全覆盖！感动的要流泪！");
        list.add(item3);

        LibListItem item4 = new LibListItem();
        item4.setProductId("4");
        item4.setProductNameCN("圣罗兰纯色唇釉");
        item4.setProductNameEN("VERNIS A LEVRES");
        item4.setProductModel("YSL唇釉8");
        item4.setRate(8.8f);
        item4.setProductImage(String.valueOf(R.drawable.product4_a));
        item4.setTryImage(String.valueOf(R.drawable.product4_b1));
        item4.setOneRate("点评:阿玛尼的唇彩就是一个完美！颜色纯正一次全覆盖！感动的要流泪！");
        list.add(item4);

        LibListItem item5 = new LibListItem();
        item5.setProductId("5");
        item5.setProductNameCN("圣罗兰莹亮纯魅口红");
        item5.setProductNameEN("ROUGE VOLUPTE SHINE");
        item5.setProductModel("YSL圆管30");
        item5.setRate(8.3f);
        item5.setProductImage(String.valueOf(R.drawable.product5_a));
        item5.setTryImage(String.valueOf(R.drawable.product5_b1));
        item5.setOneRate("点评:阿玛尼的唇彩就是一个完美！颜色纯正一次全覆盖！感动的要流泪！");
        list.add(item5);

        LibListItem item6 = new LibListItem();
        item6.setProductId("6");
        item6.setProductNameCN("圣罗兰莹亮纯魅口红");
        item6.setProductNameEN("ROUGE VOLUPTE SHINE");
        item6.setProductModel("shine14");
        item6.setRate(8.3f);
        item6.setProductImage(String.valueOf(R.drawable.product6_a));
        item6.setTryImage(String.valueOf(R.drawable.product6_b1));
        item6.setOneRate("点评:阿玛尼的唇彩就是一个完美！颜色纯正一次全覆盖！感动的要流泪！");
        list.add(item6);

        LibListItem item7 = new LibListItem();
        item7.setProductId("7");
        item7.setProductNameCN("圣罗兰莹亮纯魅口红");
        item7.setProductNameEN("ROUGE VOLUPTE SHINE");
        item7.setProductModel("shine14");
        item7.setRate(8.3f);
        item7.setProductImage(String.valueOf(R.drawable.product6_a));
        item7.setTryImage(String.valueOf(R.drawable.product6_b1));
        item7.setOneRate("点评:阿玛尼的唇彩就是一个完美！颜色纯正一次全覆盖！感动的要流泪！");
        list.add(item7);

        LibListItem item8 = new LibListItem();
        item8.setProductId("8");
        item8.setProductNameCN("圣罗兰莹亮纯魅口红");
        item8.setProductNameEN("ROUGE VOLUPTE SHINE");
        item8.setProductModel("shine14");
        item8.setRate(8.3f);
        item8.setProductImage(String.valueOf(R.drawable.product6_a));
        item8.setTryImage(String.valueOf(R.drawable.product6_b1));
        item8.setOneRate("点评:阿玛尼的唇彩就是一个完美！颜色纯正一次全覆盖！感动的要流泪！");
        list.add(item8);

        LibListItem item9 = new LibListItem();
        item9.setProductId("9");
        item9.setProductNameCN("圣罗兰莹亮纯魅口红");
        item9.setProductNameEN("ROUGE VOLUPTE SHINE");
        item9.setProductModel("shine14");
        item9.setRate(8.3f);
        item9.setProductImage(String.valueOf(R.drawable.product6_a));
        item9.setTryImage(String.valueOf(R.drawable.product6_b1));
        item9.setOneRate("点评:阿玛尼的唇彩就是一个完美！颜色纯正一次全覆盖！感动的要流泪！");
        list.add(item9);

        LibListItem item10 = new LibListItem();
        item10.setProductId("10");
        item10.setProductNameCN("圣罗兰莹亮纯魅口红");
        item10.setProductNameEN("ROUGE VOLUPTE SHINE");
        item10.setProductModel("shine14");
        item10.setRate(8.3f);
        item10.setProductImage(String.valueOf(R.drawable.product6_a));
        item10.setTryImage(String.valueOf(R.drawable.product6_b1));
        item10.setOneRate("点评:阿玛尼的唇彩就是一个完美！颜色纯正一次全覆盖！感动的要流泪！");
        list.add(item10);

        LibListItem item11 = new LibListItem();
        item11.setProductId("11");
        item11.setProductNameCN("圣罗兰莹亮纯魅口红");
        item11.setProductNameEN("ROUGE VOLUPTE SHINE");
        item11.setProductModel("shine14");
        item11.setRate(8.3f);
        item11.setProductImage(String.valueOf(R.drawable.product6_a));
        item11.setTryImage(String.valueOf(R.drawable.product6_b1));
        item11.setOneRate("点评:阿玛尼的唇彩就是一个完美！颜色纯正一次全覆盖！感动的要流泪！");
        list.add(item11);

        LibListItem item12 = new LibListItem();
        item12.setProductId("12");
        item12.setProductNameCN("圣罗兰莹亮纯魅口红");
        item12.setProductNameEN("ROUGE VOLUPTE SHINE");
        item12.setProductModel("shine14");
        item12.setRate(8.3f);
        item12.setProductImage(String.valueOf(R.drawable.product6_a));
        item12.setTryImage(String.valueOf(R.drawable.product6_b1));
        item12.setOneRate("点评:阿玛尼的唇彩就是一个完美！颜色纯正一次全覆盖！感动的要流泪！");
        list.add(item12);


        return list;
    }

    public static ArrayList<String> getLipTryImageList() {
        ArrayList<String> list = new ArrayList<>();

        list.add(String.valueOf(R.drawable.product4_b1));
        list.add(String.valueOf(R.drawable.product4_b2));
        list.add(String.valueOf(R.drawable.product5_b1));
        list.add(String.valueOf(R.drawable.product3_b1));

        return list;
    }

    public static ArrayList<String> getLibColorBoard() {
        ArrayList<String> list = new ArrayList<>();

        list.add(String.valueOf(R.drawable.product4_c));

        return list;
    }

    public static ArrayList<String> getLiboneTwoBoard() {
        ArrayList<String> list = new ArrayList<>();

        list.add(String.valueOf(R.drawable.product4_onetwo10));
        list.add(String.valueOf(R.drawable.product4_onetwo11));
        list.add(String.valueOf(R.drawable.product4_onetwo20));
        list.add(String.valueOf(R.drawable.product4_onetwo21));

        return list;
    }

    public static ArrayList<String> getLibShowColorBoard() {
        ArrayList<String> list = new ArrayList<>();

        list.add(String.valueOf(R.drawable.product4_showcolor));
        return list;
    }

    public static ArrayList<String> getLibDurableBoard() {
        ArrayList<String> list = new ArrayList<>();

        list.add(String.valueOf(R.drawable.product4_durable1));
        list.add(String.valueOf(R.drawable.product4_durable2));
        list.add(String.valueOf(R.drawable.product4_durable3));

        return list;
    }

    public static ArrayList<String> getLibMoistenBoard() {
        ArrayList<String> list = new ArrayList<>();

        list.add(String.valueOf(R.drawable.product4_moisten1));
        return list;
    }

    public static ArrayList<Comment> getLibComment() {
        ArrayList<Comment> list = new ArrayList<>();


        for (int i = 0; i < 10; i++) {
            Comment c = new Comment();
            c.setId(String.valueOf(i));
            c.setIcon(String.valueOf(R.drawable.property_tx));
            c.setUserName("kkooff" + i * 2);
            c.setContent("这是评论 " + i + " ...");
            c.setTime("1分钟前");

            list.add(c);
        }

        return list;

    }

    public static ArrayList<ShopProduct> getShopList() {
        ArrayList<ShopProduct> list = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            ShopProduct shop = new ShopProduct();
            shop.setIcon(String.valueOf(R.drawable.product4_a));
            shop.setName("圣罗兰莹亮纯魅口红 ROUGE VOLUPTE SHINE");
            shop.setPrice(String.valueOf(i * 100 + i * 3));
            shop.setLookNum(String.valueOf(i * 10 + i * 2));

            list.add(shop);
        }

        return list;
    }

    ;


    /**
     * 获得品牌筛选第一次group
     *
     * @return
     */
    public static ArrayList<String> getBrandGroup() {
        ArrayList<String> list = new ArrayList<>();

        list.add("迪奥");
        list.add("阿玛尼");
        list.add("香奈儿");
        list.add("魅可");
        list.add("娇兰");
        list.add("圣罗兰");

        return list;
    }

    /**
     * 获得公众号列表
     */
    public static ArrayList<SubscribeListItem> getSubList() {

        ArrayList<SubscribeListItem> list = new ArrayList<>();

        SubscribeListItem item = new SubscribeListItem();
        item.icon = "drawable://" + R.drawable.ic_sub_hz;
        item.name = "韩妆";
        item.message = "福利 | 肌肤胜雪的秘密";
        item.time = "11:32";
        list.add(item);

        SubscribeListItem item2 = new SubscribeListItem();
        item2.icon = "drawable://" + R.drawable.ic_sub_hm;
        item2.name = "画眉";
        item2.message = "欢迎加入半斤塘,开启一段美妆,美型,美梦之旅";
        item2.time = "11:21";
        list.add(item2);

        SubscribeListItem item3 = new SubscribeListItem();
        item3.icon = "drawable://" + R.drawable.ic_sub_sbz;
        item3.name = "上班妆";
        item3.message = "福利 | 肌肤胜雪的秘密";
        item3.time = "11:32";
        list.add(item3);

        SubscribeListItem item4 = new SubscribeListItem();
        item4.icon = "drawable://" + R.drawable.ic_sub_yx;
        item4.name = "眼线";
        item4.message = "欢迎加入半斤塘,开启一段美妆,美型,美梦之旅";
        item4.time = "11:21";
        list.add(item4);

        SubscribeListItem item5 = new SubscribeListItem();
        item5.icon = "drawable://" + R.drawable.ic_sub_bbww;
        item5.name = "芭比娃娃妆";
        item5.message = "欢迎加入半斤塘,开启一段美妆,美型,美梦之旅";
        item5.time = "11:21";
        list.add(item5);

//        SubscribeListItem item6 = new SubscribeListItem();
//        item6.icon = "drawable://" + R.drawable.ic_sub_sg;
//        item6.name = "水果妆";
//        item6.message = "欢迎加入半斤塘,开启一段美妆,美型,美梦之旅";
//        item6.time  = "11:21";
//        list.add(item6);
//
//        SubscribeListItem item7 = new SubscribeListItem();
//        item7.icon = "drawable://" + R.drawable.ic_sub_tmz;
//        item7.name = "透明妆";
//        item7.message = "欢迎加入半斤塘,开启一段美妆,美型,美梦之旅";
//        item7.time  = "11:21";
//        list.add(item7);
//
//        SubscribeListItem item8 = new SubscribeListItem();
//        item8.icon = "drawable://" + R.drawable.ic_sub_wyz;
//        item8.name = "晚宴妆";
//        item8.message = "欢迎加入半斤塘,开启一段美妆,美型,美梦之旅";
//        item8.time  = "11:21";
//        list.add(item8);
//
//        SubscribeListItem item9 = new SubscribeListItem();
//        item9.icon = "drawable://" + R.drawable.ic_sub_wtz;
//        item9.name = "舞台妆";
//        item9.message = "欢迎加入半斤塘,开启一段美妆,美型,美梦之旅";
//        item9.time  = "11:21";
//        list.add(item9);
//
//        SubscribeListItem item10 = new SubscribeListItem();
//        item10.icon = "drawable://" + R.drawable.ic_sub_yxz;
//        item10.name = "烟熏妆";
//        item10.message = "欢迎加入半斤塘,开启一段美妆,美型,美梦之旅";
//        item10.time  = "11:21";
//        list.add(item10);

        return list;
    }

    /**
     * 获得公众号内容列表
     */
    public static ArrayList<SubcribeContentListItem> getSubContentList(String type) {
        ArrayList<SubcribeContentListItem> list = new ArrayList<SubcribeContentListItem>();

        switch (type) {
            case "韩妆":
                SubcribeContentListItem item = new SubcribeContentListItem();
                item.title = "【周末美美出行】粗眉+红唇，韩系裸妆教程！";
                item.img = "drawable://" + R.drawable.sub_hz_01;
                item.content = "在韩剧里大家都见识到韩系裸妆的精致，如何才能画好裸妆成为目前的关键。下面就跟着小都一起来学习如何打造完美韩系裸妆吧！";
                item.publicTime = "2015-07-08";
                item.url = "http://mp.weixin.qq.com/s?__biz=MjM5MjA1MDQwMQ==&mid=203711879&idx=4&sn=650d9289c46195a8ca8ec03c9d7efe03#rd";
                list.add(item);

                SubcribeContentListItem item2 = new SubcribeContentListItem();
                item2.title = "1000秒气场妆容打造法，酷感女王妆容造型";
                item2.img = "drawable://" + R.drawable.sub_hz_02;
                item2.content = "韩国妆容教程，1000秒气场妆容打造法，酷感女王妆容造型, 这次选择是 espoir 的气垫BB";
                item2.publicTime = "2015-07-08";
                item2.url = "http://blog.sina.com.cn/s/blog_1332c03870102vee5.html";
                list.add(item2);

                break;

            case "画眉":
                SubcribeContentListItem item3 = new SubcribeContentListItem();
                item3.title = "画眉技巧很重要，教你粗眉的N种画法！";
                item3.img = "drawable://" + R.drawable.sub_hm_01;
                item3.content = "眉毛是妆容中的点睛之笔，美丽的一天从画眉开始！如何才能画出适合自己的美美的眉毛呢？";
                item3.publicTime = "2015-07-08";
                item3.url = "http://web.toutiao.com/a3908354822/?tt_from=sina&app=news_article&iid=2534712030";
                list.add(item3);

                break;

            case "上班妆":
                SubcribeContentListItem item4 = new SubcribeContentListItem();
                item4.title = "简略上班妆快速化";
                item4.img = "drawable://" + R.drawable.sub_sbz_01;
                item4.content = "女人学化装除了让自个变得漂亮外，还有平时上班所需，化装是种礼节，但却是相对耗时，检测化装技巧的。下面，我们来学习简略上班妆容的画法，疾速化装技巧帮你省时省力。";
                item4.publicTime = "2015-07-08";
                item4.url = "http://weibo.com/p/2304188d6ac3bd0102vcft";
                list.add(item4);

                SubcribeContentListItem item5 = new SubcribeContentListItem();
                item5.title = "淡雅上班妆";
                item5.img = "drawable://" + R.drawable.sub_sbz_02;
                item5.content = "来源：特定网";
                item5.publicTime = "2015-07-08";
                item5.url = "http://sb.tedingwang.cn/t/d/h.html";
                list.add(item5);

                break;

            case "眼线":
                SubcribeContentListItem item6 = new SubcribeContentListItem();
                item6.title = "如何画眼线？";
                item6.img = "drawable://" + R.drawable.sub_yx_01;
                item6.content = "每个女孩都希望自己可以美美的，眼睛大大的，樱桃小嘴，水嫩肌肤。现在这个年代，没有丑女人只有懒女人。来源：@ 毛戈平形象设计艺术学校";
                item6.publicTime = "2015-07-08";
                item6.url = "http://weibo.com/p/2304183df4ba410102vho2";
                list.add(item6);

                SubcribeContentListItem item7 = new SubcribeContentListItem();
                item7.title = "小眼也能迷倒众生！偷师超模画眼线";
                item7.img = "drawable://" + R.drawable.sub_yx_02;
                item7.content = "导读：活跃在时装周的中国美人，无论刘雯、孙菲菲还是新面孔雎晓雯、秦舒她们都有个共同的特点是有一双小小单眼皮眼睛。来源：@新疆美女馆";
                item7.publicTime = "2015-07-08";
                item7.url = "http://weibo.com/p/1001603814096913966605";
                list.add(item7);

                break;

            case "芭比娃娃妆":
                SubcribeContentListItem item8 = new SubcribeContentListItem();
                item8.title = "芭比娃娃妆的画法步骤图解";
                item8.img = "drawable://" + R.drawable.sub_bbww_01;
                item8.content = "选择一款适合自己肤色的饰底乳，像这位MM就选择了一款绿色的妆底乳点上面上，然后将妆底乳均匀涂抹开来，让肤色自然透亮。";
                item8.publicTime = "2015-07-08";
                item8.url = "http://zhuangban.onlylady.com/2013/0523/2931590.shtml";
                list.add(item8);

                break;
        }

        return list;
    }


    /**
     * 获得所有公众号列表
     */
    public static ArrayList<SubscribeListItem> getSubListAll() {

        ArrayList<SubscribeListItem> list = new ArrayList<>();

        SubscribeListItem item = new SubscribeListItem();
        item.icon = "drawable://" + R.drawable.ic_sub_hz;
        item.name = "韩妆";
        item.message = "福利 | 肌肤胜雪的秘密";
        item.time = "11:32";
        item.isSubcribe = true;
        list.add(item);

        SubscribeListItem item2 = new SubscribeListItem();
        item2.icon = "drawable://" + R.drawable.ic_sub_hm;
        item2.name = "画眉";
        item2.message = "欢迎加入半斤塘,开启一段美妆,美型,美梦之旅";
        item2.time = "11:21";
        item2.isSubcribe = true;
        list.add(item2);

        SubscribeListItem item3 = new SubscribeListItem();
        item3.icon = "drawable://" + R.drawable.ic_sub_sbz;
        item3.name = "上班妆";
        item3.message = "福利 | 肌肤胜雪的秘密";
        item3.time = "11:32";
        item3.isSubcribe = true;
        list.add(item3);

        SubscribeListItem item4 = new SubscribeListItem();
        item4.icon = "drawable://" + R.drawable.ic_sub_yx;
        item4.name = "眼线";
        item4.message = "欢迎加入半斤塘,开启一段美妆,美型,美梦之旅";
        item4.time = "11:21";
        item4.isSubcribe = true;
        list.add(item4);

        SubscribeListItem item5 = new SubscribeListItem();
        item5.icon = "drawable://" + R.drawable.ic_sub_bbww;
        item5.name = "芭比娃娃妆";
        item5.message = "欢迎加入半斤塘,开启一段美妆,美型,美梦之旅";
        item5.time = "11:21";
        item5.isSubcribe = true;
        list.add(item5);

        SubscribeListItem item6 = new SubscribeListItem();
        item6.icon = "drawable://" + R.drawable.ic_sub_sg;
        item6.name = "水果妆";
        item6.message = "欢迎加入半斤塘,开启一段美妆,美型,美梦之旅";
        item6.time = "11:21";
        item6.isSubcribe = false;
        list.add(item6);

        SubscribeListItem item7 = new SubscribeListItem();
        item7.icon = "drawable://" + R.drawable.ic_sub_tmz;
        item7.name = "透明妆";
        item7.message = "欢迎加入半斤塘,开启一段美妆,美型,美梦之旅";
        item7.time = "11:21";
        item7.isSubcribe = false;
        list.add(item7);

        SubscribeListItem item8 = new SubscribeListItem();
        item8.icon = "drawable://" + R.drawable.ic_sub_wyz;
        item8.name = "晚宴妆";
        item8.message = "欢迎加入半斤塘,开启一段美妆,美型,美梦之旅";
        item8.time = "11:21";
        item8.isSubcribe = false;
        list.add(item8);

        SubscribeListItem item9 = new SubscribeListItem();
        item9.icon = "drawable://" + R.drawable.ic_sub_wtz;
        item9.name = "舞台妆";
        item9.message = "欢迎加入半斤塘,开启一段美妆,美型,美梦之旅";
        item9.time = "11:21";
        item9.isSubcribe = false;
        list.add(item9);

        SubscribeListItem item10 = new SubscribeListItem();
        item10.icon = "drawable://" + R.drawable.ic_sub_yxz;
        item10.name = "烟熏妆";
        item10.message = "欢迎加入半斤塘,开启一段美妆,美型,美梦之旅";
        item10.time = "11:21";
        item10.isSubcribe = false;
        list.add(item10);

        return list;
    }


    /**
     * 获得tag列表
     *
     * @return
     */
    public static ArrayList<Tag> getTagList() {
        ArrayList<Tag> tagList = new ArrayList<Tag>();

        Tag tag1 = new Tag();
        tag1.icon = "drawable://" + R.drawable.ic_sub_hz;
        tag1.iconInt = R.drawable.ic_sub_hz;
        tag1.name = "通勤";
        tag1.desc = "tag描述";
        tagList.add(tag1);

        Tag tag2 = new Tag();
        tag2.icon = "drawable://" + R.drawable.ic_sub_sbz;
        tag2.iconInt = R.drawable.ic_sub_hz;
        tag2.name = "校园";
        tag2.desc = "tag描述";
        tagList.add(tag2);

        Tag tag3 = new Tag();
        tag3.icon = "drawable://" + R.drawable.ic_sub_hm;
        tag3.iconInt = R.drawable.ic_sub_hm;
        tag3.name = "派对";
        tag3.desc = "tag描述";
        tagList.add(tag3);

        Tag tag4 = new Tag();
        tag4.icon = "drawable://" + R.drawable.ic_sub_bbww;
        tag4.iconInt = R.drawable.ic_sub_bbww;
        tag4.name = "约会";
        tag4.desc = "tag描述";
        tagList.add(tag4);

        Tag tag5 = new Tag();
        tag5.icon = "drawable://" + R.drawable.ic_sub_yx;
        tag5.iconInt = R.drawable.ic_sub_yx;
        tag5.name = "艺术感";
        tag5.desc = "tag描述";
        tagList.add(tag5);

        Tag tag6 = new Tag();
        tag6.icon = "drawable://" + R.drawable.ic_sub_hz;
        tag6.iconInt = R.drawable.ic_sub_hz;
        tag6.name = "前卫";
        tag6.desc = "tag描述";
        tagList.add(tag6);

        Tag tag7 = new Tag();
        tag7.icon = "drawable://" + R.drawable.ic_sub_sg;
        tag7.iconInt = R.drawable.ic_sub_sg;
        tag7.name = "气场";
        tag7.desc = "tag描述";
        tagList.add(tag7);

        Tag tag8 = new Tag();
        tag8.icon = "drawable://" + R.drawable.ic_sub_tmz;
        tag8.iconInt = R.drawable.ic_sub_tmz;
        tag8.name = "日韩式";
        tag8.desc = "tag描述";
        tagList.add(tag8);

        Tag tag9 = new Tag();
        tag9.icon = "drawable://" + R.drawable.ic_sub_wtz;
        tag9.iconInt = R.drawable.ic_sub_wtz;
        tag9.name = "美国甜心";
        tag9.desc = "tag描述";
        tagList.add(tag9);

        Tag tag10 = new Tag();
        tag10.icon = "drawable://" + R.drawable.ic_sub_wyz;
        tag10.iconInt = R.drawable.ic_sub_wyz;
        tag10.name = "法国气质";
        tag10.desc = "tag描述";
        tagList.add(tag10);

        Tag tag11 = new Tag();
        tag11.icon = "drawable://" + R.drawable.ic_sub_yxz;
        tag11.iconInt = R.drawable.ic_sub_yxz;
        tag11.name = "轮廓加强";
        tag11.desc = "tag描述";
        tagList.add(tag11);

        Tag tag12 = new Tag();
        tag12.icon = "drawable://" + R.drawable.ic_sub_hz;
        tag12.iconInt = R.drawable.ic_sub_hz;
        tag12.name = "睫毛";
        tag12.desc = "tag描述";
        tagList.add(tag12);

        Tag tag13 = new Tag();
        tag13.icon = "drawable://" + R.drawable.ic_sub_hm;
        tag13.iconInt = R.drawable.ic_sub_hm;
        tag13.name = "眉毛";
        tag13.desc = "tag描述";
        tagList.add(tag13);

        Tag tag14 = new Tag();
        tag14.icon = "drawable://" + R.drawable.ic_sub_sg;
        tag14.iconInt = R.drawable.ic_sub_sg;
        tag14.name = "完美底妆";
        tag14.desc = "tag描述";
        tagList.add(tag14);

        Tag tag15 = new Tag();
        tag15.icon = "drawable://" + R.drawable.ic_sub_tmz;
        tag15.iconInt = R.drawable.ic_sub_tmz;
        tag15.name = "当季巴黎";
        tag15.desc = "tag描述";
        tagList.add(tag15);

        Tag tag16 = new Tag();
        tag16.icon = "drawable://" + R.drawable.ic_sub_wtz;
        tag16.iconInt = R.drawable.ic_sub_wtz;
        tag16.name = "上电视";
        tag16.desc = "tag描述";
        tagList.add(tag16);

        Tag tag17 = new Tag();
        tag17.icon = "drawable://" + R.drawable.ic_sub_hz;
        tag17.iconInt = R.drawable.ic_sub_hz;
        tag17.name = "女王";
        tag17.desc = "tag描述";
        tagList.add(tag17);

        Tag tag18 = new Tag();
        tag18.icon = "drawable://" + R.drawable.ic_sub_bbww;
        tag18.iconInt = R.drawable.ic_sub_bbww;
        tag18.name = "治愈系";
        tag18.desc = "tag描述";
        tagList.add(tag18);

        return tagList;
    }

    public static ArrayList<BasicFilter> getAllFilter(Context context) {

        filters = new ArrayList<BasicFilter>();

        addFilter(new FlipFilter(FlipFilter.FLIP_HORIZONTAL));
        addFilter(new MosaicFilter(context, R.drawable.webcircles, new PointF(0.125f, 0.125f), new PointF(0.025f, 0.025f), 64, true));
        addFilter(new CGAColourSpaceFilter());
        addFilter(new KuwaharaRadius3Filter());
        //addFilter(new KuwaharaFilter(8)); //Will not work on devices that don't support for loop on shader
        addFilter(new VignetteFilter(new PointF(0.5f, 0.5f), new float[]{0.3f, 0.8f, 0.3f}, 0.3f, 0.75f));
        addFilter(new GlassSphereFilter(new PointF(0.43f, 0.5f), 0.25f, 0.71f, 0.5f));
        addFilter(new SphereRefractionFilter(new PointF(0.43f, 0.5f), 0.25f, 0.71f, 0.5f));
        addFilter(new StretchDistortionFilter(new PointF(0.5f, 0.5f)));
        addFilter(new PinchDistortionFilter(new PointF(0.43f, 0.5f), 0.25f, 0.5f, 0.5f));
        addFilter(new BulgeDistortionFilter(new PointF(0.43f, 0.5f), 0.25f, 0.5f, 0.5f));
        addFilter(new SwirlFilter(new PointF(0.4f, 0.5f), 0.5f, 1f));
        addFilter(new PosterizeFilter(2f));
        addFilter(new EmbossFilter(1.5f));
        addFilter(new SmoothToonFilter(0.25f, 0.5f, 5f));
        addFilter(new ToonFilter(0.4f, 10f));
        addFilter(new ThresholdSketchFilter(0.7f));
        addFilter(new SketchFilter());
        addFilter(new CrosshatchFilter(0.005f, 0.0025f));
        addFilter(new HalftoneFilter(0.01f, 1f));
        addFilter(new PolkaDotFilter(0.9f, 0.03f, 1f));
        addFilter(new PolarPixellateFilter(new PointF(0.4f, 0.5f), new PointF(0.05f, 0.05f)));
        addFilter(new PixellateFilter(0.01f, 1f));
        addFilter(new ZoomBlurFilter(2f, new PointF(0.4f, 0.5f)));
        addFilter(new MotionBlurFilter(2f, 45f));
        addFilter(new OpeningFilter(1));
        addFilter(new OpeningRGBFilter(3));
        addFilter(new ClosingFilter(2));
        addFilter(new ClosingRGBFilter(4));
        addFilter(new ErosionRGBFilter(3));
        addFilter(new ErosionFilter(1));
        addFilter(new DilationRGBFilter(2));
        addFilter(new DilationFilter(4));
        addFilter(new CannyEdgeDetectionFilter(1.0f, 0.1f, 0.4f));
        addFilter(new ThresholdEdgeDetectionFilter(0.6f));
        addFilter(new SobelEdgeDetectionFilter());
        addFilter(new TiltShiftFilter(4f, 0.4f, 0.6f, 0.2f));
        addFilter(new BilateralBlurFilter(1f));
        addFilter(new MedianFilter());
        addFilter(new GaussianBlurPositionFilter(4f, 1.2f, new PointF(0.4f, 0.5f), 0.5f, 0.1f));
        addFilter(new GaussianSelectiveBlurFilter(4f, 1.2f, new PointF(0.4f, 0.5f), 0.5f, 0.1f));
        addFilter(new SingleComponentGaussianBlurFilter(2.3f));
        addFilter(new SingleComponentFastBlurFilter());
        addFilter(new FastBlurFilter());
        addFilter(new UnsharpMaskFilter(2.0f, 0.5f));
        addFilter(new SharpenFilter(1f));
        //addFilter(new LanczosResamplingFilter(256, 128));
        addFilter(new CropFilter(0.25f, 0f, 0.75f, 1f));
        BasicFilter cFilter1 = new CropFilter(0.25f, 0f, 0.75f, 1f);
        cFilter1.rotateClockwise90Degrees(1);
        addFilter(cFilter1);
        BasicFilter cFilter2 = new CropFilter(0.25f, 0f, 0.75f, 1f);
        cFilter2.rotateClockwise90Degrees(2);
        addFilter(cFilter2);
        BasicFilter cFilter3 = new CropFilter(0.25f, 0f, 0.75f, 1f);
        cFilter3.rotateClockwise90Degrees(3);
        addFilter(cFilter3);
        addFilter(new TransformFilter(new float[]{
                1f, 0f, 0f, 0f,
                0f, 1f, 0f, 0f,
                0f, 0f, 1f, 0f,
                -0.5f, 0f, 0f, 1f
        }, false, false));
        addFilter(new ChromaKeyFilter(new float[]{1.0f, 0.3f, 0.0f}, 0.4f, 0.1f));
        addFilter(new AdaptiveThresholdFilter());
        addFilter(new BoxBlurFilter());
        addFilter(new LuminanceThresholdFilter(0.4f));
        addFilter(new OpacityFilter(0.5f));
        addFilter(new SepiaFilter());
        addFilter(new HazeFilter(0.3f, 0.1f));
        addFilter(new FalseColourFilter(new float[]{0.0f, 0.0f, 0.5f}, new float[]{1.0f, 0.0f, 0.0f}));
        addFilter(new MonochromeFilter(new float[]{1.0f, 0.8f, 0.8f}, 1.0f));
        addFilter(new ColourInvertFilter());
        addFilter(new SoftEleganceFilter(context));
        addFilter(new GaussianBlurFilter(2.3f));
        addFilter(new MissEtikateFilter(context));
        addFilter(new AmatorkaFilter(context));
        addFilter(new LookupFilter(context, R.drawable.lookup_soft_elegance_1));
        addFilter(new HighlightShadowFilter(0f, 1f));
        Point[] defaultCurve = new Point[]{new Point(128, 128), new Point(64, 0), new Point(192, 255)};
        addFilter(new ToneCurveFilter(defaultCurve, defaultCurve, defaultCurve, defaultCurve));
        addFilter(new HueFilter(3.14f / 6f));
        addFilter(new BrightnessFilter(0.5f));
        addFilter(new ColourMatrixFilter(new float[]{0.33f, 0f, 0f, 0f,
                0f, 0.67f, 0f, 0f,
                0f, 0f, 1.34f, 0f,
                0.2f, 0.2f, 0.2f, 1.0f}, 0.5f));
        addFilter(new RGBFilter(0.33f, 0.67f, 1.34f));
        addFilter(new GreyScaleFilter());
        addFilter(new ConvolutionFilter(new float[]{
                1 / 25f, 1 / 25f, 1 / 25f, 1 / 25f, 1 / 25f,
                1 / 25f, 1 / 25f, 1 / 25f, 1 / 25f, 1 / 25f,
                1 / 25f, 1 / 25f, 1 / 25f, 1 / 25f, 1 / 25f,
                1 / 25f, 1 / 25f, 1 / 25f, 1 / 25f, 1 / 25f,
                1 / 25f, 1 / 25f, 1 / 25f, 1 / 25f, 1 / 25f}, 5, 5));
        addFilter(new ExposureFilter(0.95f));
        addFilter(new ContrastFilter(1.5f));
        addFilter(new SaturationFilter(0.5f));
        addFilter(new GammaFilter(1.75f));
        addFilter(new LevelsFilter(0.2f, 0.8f, 1f, 0f, 1f));
        return filters;
    }

    private static ArrayList<BasicFilter> filters;

    private static void addFilter(BasicFilter filter) {
        filters.add(filter);
    }

    public static ArrayList<MyIcon> getMyIcon() {

        ArrayList<MyIcon> myIcon = new ArrayList<MyIcon>();

        MyIcon icon = new MyIcon();
        icon.icon = "drawable://" + R.drawable.newlook1;
        icon.desc = "变妆";
        myIcon.add(icon);

        MyIcon icon2 = new MyIcon();
        icon2.icon = "drawable://" + R.drawable.newlook2;
        icon2.desc = "20世纪30年代：憧憬影坛女星的妆容。这种妆容让人感觉到“坚强女性”，乍一看发型像海螺小姐。而漫画《海螺小姐》据说从1946年开始连载的。";
        myIcon.add(icon2);

        MyIcon icon3 = new MyIcon();
        icon3.icon = "drawable://" + R.drawable.newlook3;
        icon3.desc = "20世纪50年代：模仿美式造型和赫本，模仿奥黛丽·赫本等巨星。";
        myIcon.add(icon3);

        MyIcon icon4 = new MyIcon();
        icon4.icon = "drawable://" + R.drawable.newlook4;
        icon4.desc = "20世纪60年代：模仿西洋人脸型的妆容。这种妆容在2000年以后也以60年代复古风的形式出现在广告和杂志上。";
        myIcon.add(icon4);

        MyIcon icon5 = new MyIcon();
        icon5.icon = "drawable://" + R.drawable.newlook5;
        icon5.desc = "20世纪70年代前半期：民族嬉皮士风格。这种风格直到2015年的现在，也在部分人群中有一定的人气。唱作人及个性的艺术家喜欢这种妆容。";
        myIcon.add(icon5);

        MyIcon icon6 = new MyIcon();
        icon6.icon = "drawable://" + R.drawable.newlook6;
        icon6.desc = "20世纪70年代后半期：脱离欧美风重新认识日本美。";
        myIcon.add(icon6);

        MyIcon icon7 = new MyIcon();
        icon7.icon = "drawable://" + R.drawable.newlook7;
        icon7.desc = "20世纪80年代初期到中期：日本职业女性的形象。这种妆容类似女演员宫泽理惠的妆容。";
        myIcon.add(icon7);

        MyIcon icon8 = new MyIcon();
        icon8.icon = "drawable://" + R.drawable.newlook8;
        icon8.desc = "20世纪80年代后期到90年代初期：泡沫经济时期的齐发控。当时著名的女演员浅野温子和浅野优子的妆容就是这个感觉。";
        myIcon.add(icon8);

        MyIcon icon9 = new MyIcon();
        icon9.icon = "drawable://" + R.drawable.newlook9;
        icon9.desc = "20世纪90年代后期到21世纪初：跨世纪的冷艳之美。日本90年代著名歌星安室奈美惠等当时就是这种妆容。";
        myIcon.add(icon9);

        MyIcon icon10 = new MyIcon();
        icon10.icon = "drawable://" + R.drawable.newlook10;
        icon10.desc = "2011年以前：招桃花的盛装。类似人气模特蛯原友里的妆容。";
        myIcon.add(icon10);

        MyIcon icon11 = new MyIcon();
        icon11.icon = "drawable://" + R.drawable.newlook11;
        icon11.desc = "2011年3月11日以后：可爱治愈型美妆。";
        myIcon.add(icon11);

        MyIcon icon12 = new MyIcon();
        icon12.icon = "drawable://" + R.drawable.newlook12;
        icon12.desc = "21世纪10年代中期：景气恢复时期的泡沫经济复兴。最近在大街上经常看到化这种妆的女大学生。";
        myIcon.add(icon12);

        MyIcon icon13 = new MyIcon();
        icon13.icon = "drawable://" + R.drawable.newlook13;
        icon13.desc = "2020年时流行妆容预想一：日式色彩。以日本传统化妆的三种颜色“红”、“白”、“黑”为基调。白色使肌肤充满透明感、内眼角明亮，黑色突出眼眶，红色突出唇部。将日本传统和现代风格调和，表现出日本女性的凛然之美。";
        myIcon.add(icon13);

        MyIcon icon14 = new MyIcon();
        icon14.icon = "drawable://" + R.drawable.newlook14;
        icon14.desc = "2020年时流行妆容预想二：轻松休闲。用蓝色眼线画出双重眼影，整体轻松明快。在富有光泽的肌肤上配以橙色唇彩，轻快运动形象油然而生，表现出飒爽之美。";
        myIcon.add(icon14);

        return myIcon;
    }

    public static ArrayList<MyIcon> getMyLuozhuang() {

        ArrayList<MyIcon> myIcon = new ArrayList<MyIcon>();

        MyIcon icon1 = new MyIcon();
        icon1.icon = "drawable://" + R.drawable.luozhuang1;
        icon1.desc = "我的裸妆1";
        myIcon.add(icon1);

        MyIcon icon2 = new MyIcon();
        icon2.icon = "drawable://" + R.drawable.luozhuang2;
        icon2.desc = "我的裸妆2";
        myIcon.add(icon2);

        MyIcon icon3 = new MyIcon();
        icon3.icon = "drawable://" + R.drawable.luozhuang3;
        icon3.desc = "我的裸妆3";
        myIcon.add(icon3);

        MyIcon icon4 = new MyIcon();
        icon4.icon = "drawable://" + R.drawable.luozhuang4;
        icon4.desc = "我的裸妆4";
        myIcon.add(icon4);

        return myIcon;

    }

    public static List<NoteEntity> getNoteList() {
        long now = new Date().getTime();
        ArrayList<NoteEntity> list = new ArrayList<NoteEntity>();
        NoteEntity note1 = new NoteEntity();
        note1.setLastOprTime(now);
        note1.setLabel("精致柔美妆");
        note1.setContent("在这个换季的时候, 肌肤的问题接踵而来, 是不是应该要正规一下换季护肤的问题呢...");
        note1.setImage("http://ww1.sinaimg.cn/bmiddle/005EBNw4gw1eu6yqrkto4j30dd6ubkjl.jpg");
        list.add(note1);

        NoteEntity note2 = new NoteEntity();
        note2.setLastOprTime(now);
        note2.setLabel("芭比大眼潮妆");
        note2.setContent("芭比最迷人的, 波过于她一双扑闪扑闪的大眼睛, 看芭比的眼睛, 你会...");
        note2.setImage("http://blog.sina.com.cn/s/blog_55cbb71a0102vwyb.html");
        list.add(note2);


        return list;
    }
}

