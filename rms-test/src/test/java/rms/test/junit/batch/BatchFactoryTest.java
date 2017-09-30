package rms.test.junit.batch;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rms.SpringBatchApplication;
import rms.batch.app.B001Batch;
import rms.batch.app.BatchFactory;
import rms.common.abstracts.AbstractBatch;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBatchApplication.class)
public class BatchFactoryTest {

    @Autowired
    BatchFactory factory;

    @Test
    public void test_create() throws Exception {
        String batchId = "B001";
        AbstractBatch batch = factory.create(batchId);
        assertTrue(batch instanceof B001Batch);
    }
}
