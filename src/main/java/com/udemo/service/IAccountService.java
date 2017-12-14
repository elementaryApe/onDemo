package com.udemo.service;

import com.udemo.model.Account;

import java.util.List;

/**
 * Desc: account Service
 * User: hansh
 * Date: 2017/12/12
 * Time: 11:11
 */
public interface IAccountService {

    int add(Account account);

    int update(Account account);

    int delete(int id);

    Account findAccountById(int id);

    Account findAccountById(String id);

    List<Account> findAccountList();

}
