/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics.matcher;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lmantyla
 */
public class QueryBuilder {

    ArrayList<Matcher> matchers;

    public QueryBuilder() {
        matchers = new ArrayList<>();
    }

    public Matcher matchesAll() {
        Matcher[] matcherArray = matchers.toArray(new Matcher[matchers.size()]);
        matchers.clear();
        return new And(matcherArray);
    }

    public Matcher matchesOne() {
        Matcher[] matcherArray = matchers.toArray(new Matcher[matchers.size()]);
        matchers.clear();
        return new Or(matcherArray);
    }

    public Matcher matchesNone() {
        Matcher[] matcherArray = matchers.toArray(new Matcher[matchers.size()]);
        matchers.clear();
        return new Not(matcherArray);
    }

    public QueryBuilder playsIn(String team) {
        matchers.add(new PlaysIn(team));
        return this;
    }

    public QueryBuilder outOfThese(Matcher... matchers) {
        for (Matcher matcher : matchers) {
            this.matchers.add(matcher);
        }
        return this;
    }

    public QueryBuilder hasAtLeast(int value, String category) {
        matchers.add(new HasAtLeast(value, category));
        return this;
    }

    public QueryBuilder hasFewerThan(int value, String category) {
        matchers.add(new HasFewerThan(value, category));
        return this;
    }

}
