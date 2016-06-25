package mms.uniq.mst.user.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import mms.com.doma.dao.MUserDao;
import mms.com.doma.entity.MUser;

@Controller
@Transactional
public class UserSearchController {

    @Autowired
    MUserDao dao;

    @RequestMapping("/mst/user")
    public MUser selectAll() {
        return dao.selectById("user01");
    }

}
