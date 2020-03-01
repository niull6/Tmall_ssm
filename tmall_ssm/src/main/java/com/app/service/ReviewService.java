package com.app.service;

import com.app.bean.Review;

import java.util.List;

public interface ReviewService {
    void add(Review c);

    void delete(int id);
    void update(Review c);
    Review get(int id);
    List list(int pid);

    int getCount(int pid);
}
