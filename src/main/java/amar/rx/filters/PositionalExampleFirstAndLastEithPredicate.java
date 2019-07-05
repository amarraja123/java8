package amar.rx.filters;

import amar.rx.helper.DataGenerator;
import rx.Observable;

/**
 * Created by Amarendra Kumar on 10/18/2016.
 */
public class PositionalExampleFirstAndLastEithPredicate {

    public static void main(final String[] args) {

        final Observable<String> observable = Observable.from(DataGenerator.generateGreekAlphabet());

        observable.first((letter) -> {
            return letter.equals("beta");
        }).subscribe(letter -> {
            System.out.println(letter);
        });
        System.out.println("=========================================");
        observable.last((letter) -> {
            return letter.equals("omega");
        }).subscribe(letter -> {
            System.out.println(letter);
        });

        System.exit(0);

    }
}
