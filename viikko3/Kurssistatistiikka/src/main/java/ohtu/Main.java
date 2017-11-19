package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan, ÄLÄ kuitenkaan laita githubiin omaa opiskelijanumeroasi
        String studentNr = "011120775";
        if (args.length > 0) {
            studentNr = args[0];
        }

        String url = "https://studies.cs.helsinki.fi/ohtustats/students/" + studentNr + "/submissions";

        String bodyText = Request.Get(url).execute().returnContent().asString();

        String courseUrl = "https://studies.cs.helsinki.fi/ohtustats/courseinfo";

        String courseText = Request.Get(courseUrl).execute().returnContent().asString();

        //System.out.println("json-muotoinen data:");
        //System.out.println( bodyText );
        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        Course course = mapper.fromJson(courseText, Course.class);

        System.out.println("Oliot:");

        int tunteja = 0;
        int tehtavia = 0;
        for (Submission submission : subs) {
            System.out.println("Viikko " + submission.getWeek() + ": Tehtäviä "
                    + course.getExercises().get(submission.getWeek() - 1) + " kpl");
            System.out.println(submission);
            tunteja += submission.getHours();
            tehtavia += submission.getExercises().size();
        }

        System.out.println("\nTunteja: " + tunteja + " Tehtäviä: " + tehtavia);

        String statsUrl = "https://studies.cs.helsinki.fi/ohtustats/stats";

        String statsResponse = Request.Get(statsUrl).execute().returnContent().asString();

        JsonParser parser = new JsonParser();
        JsonObject parsittuData = parser.parse(statsResponse).getAsJsonObject();

        int palautukset = 0;
        int kaikkitehtavat = 0;
        
        for (int val = 1; val <= parsittuData.size(); val++) {
            palautukset += parsittuData.getAsJsonObject(Integer.toString(val)).get("students").getAsInt();
            kaikkitehtavat += parsittuData.getAsJsonObject(Integer.toString(val)).get("exercise_total").getAsInt();
        }
        
        System.out.println("\nKurssilla yhteensä " + palautukset + " palautusta, "
                + "palautettuja tehtäviä " + kaikkitehtavat);

    }
}
