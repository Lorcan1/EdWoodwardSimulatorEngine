import com.example.services.FeedService
import spock.lang.Specification;

class FeedServiceTest extends Specification{

    def "get valid response"(){
        given:
        FeedService feedService = new FeedService();
        List list = feedService.getRandomResponse("1", "MCFC", "De Bruyne", "Romero", "action1")

        expect:
        list != null
    }

}
