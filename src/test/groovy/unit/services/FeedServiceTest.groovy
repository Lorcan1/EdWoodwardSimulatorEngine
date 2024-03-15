package groovy.unit.services

import com.example.services.FeedService
import spock.lang.Specification;

class FeedServiceTest extends Specification{

    def "get valid response"(){
        given:
        List testList = new ArrayList();
        FeedService feedService = new FeedService(testList);
        feedService.getRandomResponse("1", "MCFC", "De Bruyne", "Romero", "action1")

        expect:
        testList != null
    }

}
