package com.gto.bang.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gto.bang.common.constant.Constant;
import com.gto.bang.controller.ArticleController;
import com.gto.bang.dao.ArticleMapper;
import com.gto.bang.dao.CommentMapper;
import com.gto.bang.model.Article;
import com.gto.bang.model.ArticleWithBLOBs;
import com.gto.bang.model.BrowseRecord;
import com.gto.bang.model.User;
import com.gto.bang.service.ArticleService;
import com.gto.bang.service.BrowseRecordService;
import com.gto.bang.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by shenjialong on 16/6/23.
 * <p>
 * 20191221 完成改造
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    public static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserService userService;

    @Autowired
    BrowseRecordService browseRecordService;

    public static final String TAG = "articleService|";

    @Override
    public void updateViewTimes(Article articleVO) {
        articleMapper.updateViewTimes(articleVO.getId());
    }

    @Override
    public void createNewArticle(ArticleWithBLOBs articleVO) {
        articleVO.setCreateTime(new Date());
        articleMapper.insertSelective(articleVO);
    }

    @Override
    public void updateArticle(ArticleWithBLOBs articleVO) {
        articleMapper.updateByPrimaryKeySelective(articleVO);
    }

    @Override
    public ArticleWithBLOBs getArticle(Integer id) {

        ArticleWithBLOBs articleWithBLOBs = articleMapper.selectByPrimaryKey(id);
        if (articleWithBLOBs != null) {
            User condition = new User();
            condition.setId(articleWithBLOBs.getAuthorId());
            User userInfo = userService.queryUser(condition);
            articleWithBLOBs.setUsername(userInfo.getUserName());
        }
        return articleWithBLOBs;
    }

    @Override
    public List<Article> getArticleListByUserid(Integer authorId, Integer startId, Integer type) {
        ArticleWithBLOBs condition = new ArticleWithBLOBs();
        condition.setType(type.byteValue());
        condition.setAuthorId(authorId);
        List<Article> result = articleMapper.selectByCondition(condition);
        return result;
    }

    /**
     * 20200606 增加 articleType 字段
     * 用于区分是否是热门文章
     *
     * @param type
     * @param page
     * @param articleType
     * @return
     */
    @Override
    public PageInfo<Article> getArticleList(Integer type, PageInfo<Article> page, Integer articleType, Integer userId) {

        LOGGER.info(TAG + "getArticleList params userId={}, articleType={},type={}",
                userId, articleType, type);
        ArticleWithBLOBs condition = new ArticleWithBLOBs();
        condition.setType(type.byteValue());
        // 是否筛选热门文章
        condition.setArticleType(articleType);
        PageHelper.startPage(page.getPageNum(), page.getPageSize());

        List<Article> list;
        if (type != null && type == Constant.ART_ARTICLE && userId != null) {
            //过滤查询
            list = articleMapper.selectByUserId(userId, articleType);
            // 记录看过哪些文章 add by 20200618
            setRecord(list, userId);
        } else {
            list = articleMapper.selectByCondition(condition);
        }
        setUpCommentNum(list);
        return new PageInfo<Article>(list);
    }

    /**
     * 获取浏览记录
     * 20200619
     *
     * @param userId
     * @return
     */
    public BrowseRecord getBrowseRecord(Integer userId) {
        BrowseRecord record = browseRecordService.queryByUserId(userId);
        LOGGER.info(TAG + "getBrowseRecord, param userId={}, result record={}", userId, JSON.toJSONString(record));
        return record;
    }

    /**
     * @param list
     * @param userId
     * @decribe 记录看过哪些文章
     * @date 20200618
     * @decribe 每次存储一条记录, 去重的时候需要关注所有相关记录
     */
    public void setRecord(List<Article> list, Integer userId) {

        LOGGER.info(TAG + "setRecord|step 1, userId={} ", userId);
        if (CollectionUtils.isEmpty(list) || userId == null) {
            LOGGER.info(TAG + "setRecord| list is empty or userId is null, return derictly");
            return;
        }

        List<Integer> artIds = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            artIds.add(list.get(i).getId());
        }

        String recordIds = StringUtils.join(artIds, Constant.SEPRATOR);
        StringBuffer sb = new StringBuffer(recordIds);
        LOGGER.info(TAG + "ArticleServiceImpl|setRecord|step 1.5, userId={}, recordIds={}", userId, JSON.toJSONString(recordIds));

        BrowseRecord oldRecord = getBrowseRecord(userId);

        // 有记录则更新
        if (oldRecord != null) {
            sb.append(Constant.SEPRATOR).append(oldRecord.getRecordIds());
            oldRecord.setRecordIds(sb.toString());
            browseRecordService.updateBrowseRecord(oldRecord);
            LOGGER.info(TAG + "setRecord|step 3, userId={}, oldRecord={}", userId, JSON.toJSONString(oldRecord));
            return;
        } else {
            // 没记录则新增
            BrowseRecord browseRecord = new BrowseRecord();
            browseRecord.setUserId(userId);
            browseRecord.setRecordIds(sb.toString());
            LOGGER.info(TAG + "setRecord|step 3, userId={}, params={}", userId, JSON.toJSONString(browseRecord));
            browseRecordService.addBrowseRecord(browseRecord);
        }
        LOGGER.info(TAG + "setRecord|step over.....");

    }


    public void setUpCommentNum(List<Article> list) {
        LOGGER.info(TAG + "setUpCommentNum|begin ....");
        for (int i = 0; i < list.size(); i++) {
            Article article = list.get(i);
            Integer number = commentMapper.commentNumOfArticle(article.getId());
//            commentDao.numOfComment(article.getId());
            article.setCommentNum(number);
        }
        LOGGER.info(TAG + "setUpCommentNum|end ....");
    }


    /**
     * 获取用户的文章id数量
     *
     * @param authorId
     * @param type     逗号隔开
     * @return
     */
    @Override
    public List<Integer> getArticleIdList(Integer authorId, String type) {
        ArticleWithBLOBs condition = new ArticleWithBLOBs();
        condition.setAuthorId(authorId);

        String[] typeArr = StringUtils.split(type, ",");
        List<String> typeList = Arrays.asList(typeArr);
        condition.setTypeList(typeList);

        List<Article> list = articleMapper.selectByCondition(condition);
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Article article = list.get(i);
            ids.add(article.getId());
        }
        return ids;
    }
}
