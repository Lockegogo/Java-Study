package com.locke.dao;

import com.locke.pojo.Books;

import java.util.List;

public interface BookMapper {
    // 增加一个 Book
    int addBook(Books book);

    // 根据 id 删除一个 Book
    // int deleteBookById(@Param("bookId") int id);
    int deleteBookById(int id);

    // 更新 Book
    int updateBook(Books books);

    // 根据 id 查询,返回一个 Book
    Books queryBookById(int id);

    // 查询全部 Book, 返回 list 集合
    List<Books> queryAllBook();

    // 根据 id 查询,返回一个Book
    Books queryBookByName(String bookName);
}
