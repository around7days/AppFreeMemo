package rms.common.utils;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import rms.common.entity.MUser;
import rms.common.entity.MUserRole;

public class RmsBeanUtilsTest {

    @Test
    public final void test_createCopyProperties() {
        MUser mUser = new MUser();
        mUser.setUserId("userId");
        MUserRole mUserRole = RmsBeanUtils.createCopyProperties(mUser, MUserRole.class);
        assertThat(mUserRole.getUserId(), is(mUser.getUserId()));
    }

}
