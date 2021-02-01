package gunghorse.com.github.controllers;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import gunghorse.com.github.model.ProductInfo;
import gunghorse.com.github.repositories.ProductInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DBAccess {

    @Autowired
    AmazonDynamoDB amazonDynamoDB;

    @Autowired
    ProductInfoRepository productInfoRepository;

    private final DynamoDBMapper dynamoDBMapper;

    public DBAccess() {
        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
    }

    @RequestMapping(value = "/create")
    public void createIfNotExists(){
        CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(ProductInfo.class);

        tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));

        TableUtils.createTableIfNotExists(amazonDynamoDB, tableRequest);
    }

    @RequestMapping(value = "/random")
    public void random(){
        ProductInfo productInfo = new ProductInfo("ppp", "23.23");
        productInfoRepository.save(productInfo);
    }

    @RequestMapping(value = "/remove")
    public void remove(){
        for (ProductInfo info : productInfoRepository.findByMsrp("ppp")){
            productInfoRepository.delete(info);
        }
    }

}
