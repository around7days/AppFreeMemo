package mms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mms.com.doma.dao.MUserDao;
import mms.com.doma.entity.MUser;

@RestController
@Transactional
public class MUserController {

    @Autowired
    MUserDao dao;

    @RequestMapping("/select")
    public MUser selectAll() {
        return dao.selectById("user1");
    }

}
