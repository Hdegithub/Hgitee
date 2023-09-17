package com.geekaca.news.service;


import com.geekaca.news.domain.NewsComment;
import com.geekaca.news.utils.PageBean;
import com.geekaca.news.utils.PageQueryUtil;
import com.geekaca.news.utils.PageResult;

public interface CommentService {
    /**
     * 添加评论
     *
     * @param blogComment
     * @return
     */
    Boolean addComment(NewsComment blogComment);

    /**
     * 后台管理系统中评论分页功能
     *
     * @param
     * @return
     */
    PageResult getCommentsPage(PageBean pageBean);

    int getTotalComments();

    /**
     * 批量审核
     *
     * @param ids
     * @return
     */
    Boolean checkDone(Integer[] ids);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    Boolean deleteBatch(Integer[] ids);

    /**
     * 添加回复
     *
     * @param commentId
     * @param replyBody
     * @return
     */
    Boolean reply(Long commentId, String replyBody);

    /**
     * 根据文章id和分页参数获取文章的评论列表
     *
     * @param blogId
     * @param page
     * @return
     */
    PageResult getCommentPageByBlogIdAndPageNum(Long newsId, int page);
}
