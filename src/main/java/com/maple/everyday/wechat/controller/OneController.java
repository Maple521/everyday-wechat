package com.maple.everyday.wechat.controller;

import com.maple.everyday.wechat.common.Constants;
import com.maple.everyday.wechat.common.HttpClient;
import com.maple.everyday.wechat.model.OneArticle;
import com.maple.everyday.wechat.model.OneImage;
import com.maple.everyday.wechat.model.OneQuestion;
import com.maple.everyday.wechat.service.OneArticleService;
import com.maple.everyday.wechat.service.OneImageService;
import com.maple.everyday.wechat.service.OneQuestionService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description 一个 http://wufazhuce.com/
 * @Date 2019/8/13 19:23
 * @Created by 王弘博
 */
@RestController
public class OneController {

    @Resource
    private HttpClient httpClient;


    @Resource
    private OneImageService oneImageService;

    @Resource
    private OneArticleService oneArticleService;

    @Resource
    private OneQuestionService oneQuestionService;

    @GetMapping("/one")
    public String crawlingOne() {
        String result = httpClient.httpGet(Constants.ONE_URL);
        Document doc = Jsoup.parse(result);
        saveOneImage(doc);
        saveOneArticle(doc);
        saveOneQuestion(doc);
        return "保存一个成功";
    }

    /**
     * 保存一个---图片
     *
     * @param doc
     */
    private void saveOneImage(Document doc) {
        Elements items = doc.select(".item").select(".active");
        Element element = items.get(0);
        OneImage bean = new OneImage();
        bean.setType(element.select("div[class=fp-one-imagen-footer]").text());
        bean.setContent(element.select("div[class=fp-one-cita]").text());
        bean.setVolImage(element.select("p[class=titulo]").text().split("\\.")[1]);
        bean.setImageAddress(element.select("a").attr("href"));
        bean.setCrawlTime(new Date());
        oneImageService.insert(bean);
        /*if (CollectionUtils.isNotEmpty(needsaveImage)) {
            oneImageService.insertBatch(needsaveImage);
        }*/
    }

    /**
     * 保存一个---文章
     *
     * @param doc
     */
    private void saveOneArticle(Document doc) {
        Elements items = doc.select(".fp-one-articulo");
        Element element = items.get(0);
        String h4 = element.select("h4").text();

        OneArticle article = new OneArticle();
        article.setVolArticle(element.select("p[class=one-titulo]").text().split("\\.")[1]);
        article.setArticleTitle(element.select(".one-articulo-titulo a").text().split("-")[0].trim());
        article.setArticleAuthor(element.select(".one-articulo-titulo small").text().split("-")[1].trim());
        article.setArticleAddress(element.select("a").attr("href"));
        article.setType(h4);
        article.setCrawlTime(new Date());
        oneArticleService.insert(article);

        /*Elements elements = element.select("li");
        for (Element ele : elements) {
            OneArticle oneArticle = new OneArticle();
            oneArticle.setVolArticle(ele.select("span").text().split("\\.")[1]);
            oneArticle.setArticleTitle(ele.select("a").text().split("-")[0].trim());
            oneArticle.setArticleAddress(ele.select("a").attr("href"));
            oneArticle.setArticleAuthor(ele.select("small").text().split("-")[1].trim());
            oneArticle.setType(h4);
            oneArticle.setCrawlTime(DateUtils.dayAdd(0));
            needsaveArticle.add(oneArticle);
        }
        if (CollectionUtils.isNotEmpty(needsaveArticle)) {
            oneArticleService.insertBatch(needsaveArticle);
        }*/
    }

    /**
     * 保存一个---问题
     *
     * @param doc
     */
    private void saveOneQuestion(Document doc) {
        Elements items = doc.select(".fp-one-cuestion");
        Element element = items.get(0);
        String h4 = element.select("h4").text();
        OneQuestion question = new OneQuestion();
        question.setVolQuestion(element.select("p[class=one-titulo]").text().split("\\.")[1]);
        question.setQuestionTitle(element.select(".one-cuestion-titulo a").text().trim());
        question.setQuestionAddress(element.select("a").attr("href"));
        question.setType(h4);
        question.setCrawlTime(new Date());
        oneQuestionService.insert(question);
    }
}

