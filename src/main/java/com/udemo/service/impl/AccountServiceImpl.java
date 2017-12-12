package com.udemo.service.impl;

import com.udemo.dao.IAccountDAO;
import com.udemo.entity.Account;
import com.udemo.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Desc: account Service 实现
 * User: hansh
 * Date: 2017/12/12
 * Time: 11:12
 */
@Service
public class AccountServiceImpl implements IAccountService{

    @Autowired
    private IAccountDAO accountDAO;

    @Override
    @Transactional
    public int add(Account account) {
        return accountDAO.add(account);
    }

    @Override
    @Transactional
    public int update(Account account) {
        return accountDAO.add(account);
    }

    @Override
    @Transactional
    public int delete(int id) {
        return accountDAO.delete(id);
    }

    @Override
    public Account findAccountById(int id) {
        return accountDAO.findAccountById(id);
    }

    @Override
    public List<Account> findAccountList() {
        return accountDAO.findAccountList();
    }
}
