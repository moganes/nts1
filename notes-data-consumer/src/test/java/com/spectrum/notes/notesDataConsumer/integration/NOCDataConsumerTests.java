package com.spectrum.notes.notesDataConsumer.integration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spectrum.notes.notesDataConsumer.model.CMTS;

import java.io.IOException;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class NOCDataConsumerTests {

    //@Test
    public void contextLoads() {
    }

    //@Test
    public void jsonTest() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        String fullMessage = "{\"schema\":{\"namespace\":\"entities\",\"name\":\"main\",\"doc\":\"Schema for CMTS entity\",\"type\":\"struct\",\"fields\":[{\"field\":\"bb_group\",\"optional\":true,\"type\":\"string\"},{\"field\":\"bb_ip\",\"optional\":true,\"type\":\"string\"},{\"field\":\"bb_page\",\"optional\":true,\"type\":\"string\"},{\"field\":\"bb_service\",\"optional\":true,\"type\":\"string\"},{\"field\":\"createdby\",\"optional\":true,\"type\":\"string\"},{\"field\":\"createdtime\",\"optional\":true,\"type\":\"string\"},{\"field\":\"division_id\",\"optional\":true,\"type\":\"string\"},{\"field\":\"division_name\",\"optional\":true,\"type\":\"string\"},{\"field\":\"docsis_affiliate\",\"optional\":true,\"type\":\"string\"},{\"field\":\"docsis_model\",\"optional\":true,\"type\":\"string\"},{\"field\":\"docsis_natural\",\"optional\":true,\"type\":\"string\"},{\"field\":\"docsis_vendor\",\"optional\":true,\"type\":\"string\"},{\"field\":\"enabled\",\"optional\":true,\"type\":\"float\"},{\"field\":\"enterprise_id\",\"optional\":true,\"type\":\"string\"},{\"field\":\"enterprise_name\",\"optional\":true,\"type\":\"string\"},{\"field\":\"entityType\",\"optional\":true,\"type\":\"string\"},{\"field\":\"host_id\",\"optional\":true,\"type\":\"string\"},{\"field\":\"hub_id\",\"optional\":true,\"type\":\"string\"},{\"field\":\"id\",\"optional\":true,\"type\":\"string\"},{\"field\":\"impact\",\"optional\":true,\"type\":\"string\"},{\"field\":\"ip\",\"optional\":true,\"type\":\"string\"},{\"field\":\"key\",\"optional\":true,\"type\":\"string\"},{\"field\":\"keyAttr\",\"optional\":true,\"type\":\"string\"},{\"field\":\"make\",\"optional\":true,\"type\":\"string\"},{\"field\":\"market_id\",\"optional\":true,\"type\":\"string\"},{\"field\":\"market_name\",\"optional\":true,\"type\":\"string\"},{\"field\":\"mgtArea_id\",\"optional\":true,\"type\":\"string\"},{\"field\":\"mgtArea_name\",\"optional\":true,\"type\":\"string\"},{\"field\":\"model\",\"optional\":true,\"type\":\"string\"},{\"field\":\"modifiedby\",\"optional\":true,\"type\":\"string\"},{\"field\":\"modifiedtime\",\"optional\":true,\"type\":\"string\"},{\"field\":\"name\",\"optional\":true,\"type\":\"string\"},{\"field\":\"poller\",\"optional\":true,\"type\":\"string\"},{\"field\":\"pollerId\",\"optional\":true,\"type\":\"string\"},{\"field\":\"prevPollerId\",\"optional\":true,\"type\":\"string\"},{\"field\":\"region_id\",\"optional\":true,\"type\":\"string\"},{\"field\":\"region_name\",\"optional\":true,\"type\":\"string\"},{\"field\":\"scope\",\"optional\":true,\"type\":\"string\"},{\"field\":\"sortOrder\",\"optional\":true,\"type\":\"string\"},{\"field\":\"swVersion\",\"optional\":true,\"type\":\"string\"},{\"field\":\"tftpserver\",\"optional\":true,\"type\":\"string\"},{\"field\":\"trapClass\",\"optional\":true,\"type\":\"string\"},{\"field\":\"type\",\"optional\":true,\"type\":\"string\"}]},\"payload\":{\"poller\":\"austx-riobe-uni-05\",\"mgtArea_id\":\"63d8a37a5d228d7531c18e4f65d9f4f52fd712ba\",\"docsis_model\":\"e6000\",\"docsis_vendor\":\"arris\",\"prevPollerId\":\"59\",\"type\":\"CMTS\",\"trapClass\":\"Network\",\"enabled\":1,\"bb_group\":\"c5\",\"modifiedtime\":\"2018-09-09 22:22:22\",\"docsis_natural\":\"dllxtx5002m\",\"bb_ip\":\"24.164.210.51\",\"createdby\":\"e175850\",\"bb_service\":\"blah data\",\"scope\":\"a81d99414a6ce3c68438699293f02317f5fbeb64\",\"model\":\"e6000\",\"region_name\":\"tex\",\"id\":\"8225e5fff4807b2bf3f5dedbc065f72ed3612932\",\"make\":\"arris\",\"mgtArea_name\":\"ntx\",\"key\":\"dllxtx5002m.tx.rr.com\",\"bb_page\":\"daltxcmt\",\"pollerId\":\"59\",\"entityType\":\"CMTS\",\"impact\":\"99\",\"ip\":\"10.0.9.8\",\"region_id\":\"96f94158c934db511409639d7a6f935945c398da\",\"division_id\":\"c0de028403b639de822c72ba0903ee41588e18e3\",\"market_id\":\"f22ed961d89e860144898ca16a4c20e0308c2496\",\"keyAttr\":\"fqdn\",\"enterprise_id\":\"ced228b4c1b1713ddeb6e16f8394da3ee8a8b288\",\"createdtime\":\"1432279410\",\"market_name\":\"tex\",\"host_id\":\"10858\",\"hub_id\":\"0051e2cc98fdb953158ef2cf9a5a8b61a95545d1\",\"sortOrder\":\"0\",\"name\":\"dllxtx5002m.tx.rr.com\",\"division_name\":\"dal\",\"docsis_affiliate\":\"dallas\",\"modifiedby\":\"blah person\",\"enterprise_name\":\"twc\",\"tftpserver\":\"blah\",\"swVersion\":\"2.1\"}}";

        JsonNode newNode = mapper.readTree(fullMessage);
        JsonNode payloadNode = newNode.get("payload");
        CMTS cmts = mapper.convertValue(payloadNode, CMTS.class);
        System.out.println();

    }
}
