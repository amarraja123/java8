package amar.rx.intro;

import rx.Observable;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kumarao on 16-08-2016.
 */
public class SimpleDemo {

    public static void main(final String[] args) {
        /*List<String> shapeList = Arrays.asList("rectangle", "square", "triangle");
        Observable<String> observableShape = Observable.from(shapeList);
        Subscription subscribe = observableShape.subscribe(new SimpleObserver());*/
        final List<String> symbols = Arrays.asList("AAPL", "GOOG", "ORCL");

        final Observable<StockInfo> feed = StockServer.getFeed(symbols);
        feed.onErrorResumeNext(throwable -> {
            //System.out.println(throwable);
            return StockInfo.getDefaultPrice();
        })
                .subscribe(System.out::println, System.out::println);
    }
}
