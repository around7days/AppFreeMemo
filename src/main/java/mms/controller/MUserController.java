package mms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mms.com.dao.MUserDao;
import mms.com.entity.MUser;


@RestController
@Transactional
public class MUserController {

    @Autowired
    MUserDao dao;

    @RequestMapping("/select")
    public List<MUser> selectAll() {
        return dao.selectAll();
    }

}
