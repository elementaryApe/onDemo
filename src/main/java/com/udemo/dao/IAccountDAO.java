package com.udemo.dao;

import com.udemo.model.Account;

import java.util.List;

/**
 * Desc: account Dao层
 * User: hansh
 * Date: 2017/12/12
 * Time: 10:55
 */
public interface IAccountDAO {

    int add(Account account);

    int update(Account account);

    int delete(int id);

    Account findAccountById(int id);

    List<Account> findAccountList();
}
