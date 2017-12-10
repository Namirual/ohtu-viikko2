package statistics;

import statistics.matcher.*;

public class Main {

    public static void main(String[] args) {
        Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstats-2013-14.herokuapp.com/players.txt"));

        System.out.println("At least 10 goals:");

        Matcher m = new And(new HasAtLeast(10, "goals"),
                new HasAtLeast(10, "assists"),
                new PlaysIn("PHI")
        );

        for (Player player : stats.matches(m)) {
            System.out.println(player);
        }

        System.out.println("\nNot 1 goal:");

        m = new Not(new HasAtLeast(1, "goals"));

        for (Player player : stats.matches(m)) {
            System.out.println(player);
        }

        System.out.println("\n40 goals or 60 assits or 85 points: ");

        m = new Or(new HasAtLeast(40, "goals"),
                new HasAtLeast(60, "assists"),
                new HasAtLeast(85, "points")
        );

        for (Player player : stats.matches(m)) {
            System.out.println(player);
        }

        System.out.println("\nFewer than 1 goals: ");
        m = new HasFewerThan(1, "goals");

        for (Player player : stats.matches(m)) {
            System.out.println(player);
        }

        System.out.println("NYR -pelaajat, vähintään 10 maalia, ei yli 25 syöttöä");
        QueryBuilder query = new QueryBuilder();

        m = query.playsIn("NYR")
                .hasAtLeast(10, "goals")
                .hasFewerThan(25, "assists").matchesAll();

        for (Player player : stats.matches(m)) {
            System.out.println(player);
        }

        System.out.println("Joko yli 10 maalia ja alle 20 syöttöä PHI:ssä "
                + "tai yli 60 pistettä EDM:issä");

        Matcher m1 = query.playsIn("PHI")
                .hasAtLeast(10, "goals")
                .hasFewerThan(20, "assists").matchesAll();

        Matcher m2 = query.playsIn("EDM")
                .hasAtLeast(60, "points").matchesAll();

        m = query.outOfThese(m1, m2).matchesOne();

        for (Player player : stats.matches(m)) {
            System.out.println(player);
        }
    }
}
