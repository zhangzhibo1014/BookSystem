package com.robin.mapper;

import com.robin.model.Book;

import java.util.List;

/**
 * 数据层接口
 */
public interface BookMapper {
    /**
     * 通过id查找图书
     * @param id
     * @return
     */
    Book findBookById(Integer id);

    /**
     * 查找所有图书
     * @return
     */
    List<Book> findAll();

    /**
     * 添加图书
     * @param book
     * @return
     */
    Integer addBook(Book book);

    /**
     * 更新图书信息
     * @param book
     * @return
     */
    Integer updateBook(Book book);

    /**
     * 通过id删除图书
     * @param id
     * @return
     */
    Integer deleteBook(Integer id);
}
