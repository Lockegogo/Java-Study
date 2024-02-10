package com.locke.service;

import com.locke.pojo.Books;

import java.util.List;

// BookService: 底下需要去实现, 调用 dao 层
public interface BookService {
    // 增加一个 Book
    int addBook(Books book);

    // 根据 id 删除一个Book
    int deleteBookById(int id);

    // 更新 Book
    int updateBook(Books books);

    // 根据 id 查询, 返回一个 Book
    Books queryBookById(int id);

    // 查询全部 Book, 返回 list 集合
    List<Books> queryAllBook();

    // 根据 id 查询,返回一个Book
    Books queryBookByName(String bookName);
}
